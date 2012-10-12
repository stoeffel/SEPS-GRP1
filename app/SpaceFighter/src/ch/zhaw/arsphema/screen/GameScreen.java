package ch.zhaw.arsphema.screen;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.controller.HeroController;
import ch.zhaw.arsphema.model.Background;
import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.model.NavigationOverlay;
import ch.zhaw.arsphema.model.enemies.AbstractEnemy;
import ch.zhaw.arsphema.model.enemies.EnemyFactory;
import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.services.SoundManager;
import ch.zhaw.arsphema.util.Paths;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen extends AbstractScreen {
	
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis
	private Hero hero;
	private HeroController controller;
	private NavigationOverlay overlay;
	private List<AbstractEnemy> enemies;
	private List<Shot> heroShots, enemyShots, shotsToRemove;
	private EnemyFactory enemyFactory;
	private float elapsed = 0;
	private Background bg1,bg2;
	
	public GameScreen(MyGdxGame game) {
		super(game);
	}

	@Override
	public void show() {
		loadTextures();
		controller = new HeroController(hero);
		enemies = new ArrayList<AbstractEnemy>();
		heroShots = new ArrayList<Shot>();
		enemyShots = new ArrayList<Shot>();
		shotsToRemove = new ArrayList<Shot>();
		enemyFactory = EnemyFactory.getInstance();
		Gdx.input.setInputProcessor(controller);
		Services.setSoundManager(new SoundManager());
	}

	private void loadTextures() {
		hero = new Hero(5, Sizes.DEFAULT_WORLD_HEIGHT / 2 + Sizes.SHIP_HEIGHT / 2, new Texture(Gdx.files.internal(Paths.HERO)));
		overlay = new NavigationOverlay(new Texture(Gdx.files.internal(Paths.OVERLAY_SPRITE)));
		bg1 = new Background(new TextureRegion(new Texture(Gdx.files.internal(Paths.BACKGROUND_STARS))),0,0,Sizes.DEFAULT_WORLD_WIDTH,Sizes.DEFAULT_WORLD_HEIGHT);
		bg2 = new Background(new TextureRegion(new Texture(Gdx.files.internal(Paths.BACKGROUND_STARS))),bg1.getWidth(),0,Sizes.DEFAULT_WORLD_WIDTH,Sizes.DEFAULT_WORLD_HEIGHT);
		//TODO create one wide file for background and move with textureregion?
		ShotFactory.loadTextures();
	}

	@Override
	public void render(float delta) {
		elapsed += delta;
		drawGame(delta);
		
		//hero stuff
		hero.move(delta);
		heroShots.addAll(hero.shoot(delta));
		heroSuffering();
		killAllTheEnemies();
		enemies.addAll(enemyFactory.dropEnemy(delta));
		
		controller.update(delta);
	}

	private void drawGame(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		bg1.draw(batch,delta, elapsed,ppuX,ppuY); // draw Background
		bg2.draw(batch,delta, elapsed,ppuX,ppuY); // draw Background
		hero.draw(batch,delta, elapsed,ppuX,ppuY);

		for(AbstractEnemy enemy : enemies)
		{
			enemy.move(delta);//TODO remove out of view enemies
		}
		for(AbstractEnemy enemy : enemies)
		{
			//TODO draw in enemyObject?
			batch.draw(enemy.getTextureRegion(), enemy.x * ppuX, enemy.y * ppuY, enemy.width * ppuX, enemy.height * ppuY);
		}
		
		// start overlay is displayed 5 sec
		if (elapsed >= 5) { 
			batch.draw(overlay.getTexture(NavigationOverlay.GAME), ppuX * overlay.x, ppuY * overlay.y, ppuX * overlay.width, ppuY * overlay.height);
		} else {
			batch.draw(overlay.getTexture(NavigationOverlay.START), ppuX * overlay.x, ppuY * overlay.y, ppuX * overlay.width, ppuY * overlay.height);
		}
		drawShots(delta);
		batch.end();
	}
	
	private void drawShots(float delta)
 {
		for (Shot shot : heroShots) {
			shot.draw(batch, delta, elapsed, ppuX, ppuY);
			if (shot.shouldBeRemoved()) {
				shotsToRemove.add(shot);
			}

		}
		for (Shot shot : enemyShots) {
			shot.draw(batch, delta, elapsed, ppuX, ppuY);
			if (shot.shouldBeRemoved()) {
				shotsToRemove.add(shot);
			}

		}
		
		heroShots.removeAll(shotsToRemove);
		enemyShots.removeAll(shotsToRemove);
		shotsToRemove.clear();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		controller.resize(width, height);
		
		ppuX = (float) width / Sizes.DEFAULT_WORLD_WIDTH;
		ppuY = (float) height / Sizes.DEFAULT_WORLD_HEIGHT;
	}

	private void heroSuffering() {
		for(Shot shot : enemyShots)
		{
			if(shot.overlaps(hero))
			{
				if(hero.lowerHealth(shot.getDamage())){
					//TODO gameover screen... hero suffered too much :'(
				}
			}
		}
	}
	
	private void killAllTheEnemies() {
		for(Shot shot : heroShots)
		{
			for(AbstractEnemy enemy : enemies)
			if(shot.overlaps(enemy))
			{
				if(enemy.lowerHealth(shot.getDamage())){
					//TODO enemy is dead... loot him!!!
				}
			}
		}
	}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

}