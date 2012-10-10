package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.controller.InGameController;
import ch.zhaw.arsphema.model.Background;
import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.model.NavigationOverlay;
import ch.zhaw.arsphema.model.shot.ShotFactory;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.services.SoundManager;
import ch.zhaw.arsphema.util.Paths;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends AbstractScreen {

	
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis
	private Hero hero;
	private InGameController controller;
	private NavigationOverlay overlay;
	private float elapsed = 0;
	private Background bg1,bg2;
	
	

	public GameScreen(MyGdxGame game) {
		super(game);
		
	}


	@Override
	public void show() {
		loadTextures();
		controller = new InGameController(hero);
		Gdx.input.setInputProcessor(controller);
		Services.setSoundManager(new SoundManager());
	}

	private void loadTextures() {
		hero = new Hero(5, Sizes.DEFAULT_WORLD_HEIGHT / 2 + Sizes.SHIP_HEIGHT / 2, new Texture(Gdx.files.internal(Paths.HERO)));
		overlay = new NavigationOverlay(new Texture(Gdx.files.internal(Paths.OVERLAY_SPRITE)));
		bg1 = new Background(new Texture(Gdx.files.internal(Paths.BACKGROUND_STARS)),0,0,Sizes.DEFAULT_WORLD_WIDTH,Sizes.DEFAULT_WORLD_HEIGHT);
		bg2 = new Background(new Texture(Gdx.files.internal(Paths.BACKGROUND_STARS)),bg1.getWidth(),0,Sizes.DEFAULT_WORLD_WIDTH,Sizes.DEFAULT_WORLD_HEIGHT);
		
		ShotFactory.loadTextures();
	}

	@Override
	public void render(float delta) {
		elapsed += delta;
		Gdx.gl.glClearColor(0f, 0f, 0f, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		hero.move(delta);
		controller.update(delta);
		batch.begin();
//		batch.disableBlending();

		bg1.draw(batch,delta, elapsed,ppuX,ppuY); // draw Background
		bg2.draw(batch,delta, elapsed,ppuX,ppuY); // draw Background

		
		ShotFactory.draw(batch,delta, elapsed,ppuX,ppuY);
		hero.draw(batch,delta, elapsed,ppuX,ppuY);
		
		// start overlay wird 2 sec angezeigt
		if (elapsed >= 5){ 
			batch.draw(overlay.getTexture(overlay.GAME), ppuX * overlay.x, ppuY * overlay.y, ppuX * overlay.width, ppuY * overlay.height);
		} else {
			batch.draw(overlay.getTexture(overlay.START), ppuX * overlay.x, ppuY * overlay.y, ppuX * overlay.width, ppuY * overlay.height);
		}
		
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		controller.resize(width, height);
		
		ppuX = (float) width / Sizes.DEFAULT_WORLD_WIDTH;
		ppuY = (float) height / Sizes.DEFAULT_WORLD_HEIGHT;
		
		
	}

	@Override
	public void hide() {
		
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}