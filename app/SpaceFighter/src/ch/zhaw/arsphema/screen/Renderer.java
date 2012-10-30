package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.controller.EnemyManager;
import ch.zhaw.arsphema.controller.PlanetManager;
import ch.zhaw.arsphema.controller.PointManager;
import ch.zhaw.arsphema.controller.PowerUpManager;
import ch.zhaw.arsphema.controller.ShotManager;
import ch.zhaw.arsphema.model.Background;
import ch.zhaw.arsphema.model.Explosion;
import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.model.NavigationOverlay;
import ch.zhaw.arsphema.model.Planet;
import ch.zhaw.arsphema.model.enemies.AbstractEnemy;
import ch.zhaw.arsphema.model.enemies.EnemyGroup;
import ch.zhaw.arsphema.model.enemies.EnemyPaths;
import ch.zhaw.arsphema.model.powerup.AbstractPowerUp;
import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer {

    protected final SpriteBatch batch;
    private final int SHOW_OVERLAY_TIME = 5;
	private static float ppuX = Sizes.DEFAULT_WORLD_WIDTH; // pixels per unit on the X axis
	private static float ppuY = Sizes.DEFAULT_WORLD_HEIGHT; // pixels per unit on the Y axis
	public static float WORLD_WIDTH;
	public static float WORLD_HEIGHT;
	private boolean showOverlay = true;
	private NavigationOverlay overlay;
	private Background bg;
	private Pause pause;
    
    public Renderer(Background bg)
    {
        batch = new SpriteBatch();
        this.bg = bg;
        
		loadTextures();
    }
    
	private void loadTextures() {
		overlay = new NavigationOverlay(TextureRegions.OVERLAY_SPRITE);
		pause = new Pause();
	}
	
	public void cleanScreen() {
		Gdx.gl.glClearColor(0f, 0f, 0f, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		
	}

    public void drawEnemies(final EnemyManager enemies)
    {
    	batch.begin();
		for(final EnemyGroup group : enemies.getGroups())
		{
			for(final AbstractEnemy enemy : group.getMembers())
			{
				enemy.draw(batch);
			}
		}
		
		for(final Explosion explosion : enemies.getExplosions())
		{
			explosion.draw(batch);
		}
		
		batch.end();
    }
    
	public void drawShots(final ShotManager shots) 
	{
    	batch.begin();
		for (final Shot shot : shots.getHeroShots()) 
		{
			shot.draw(batch);
			if (shot.shouldBeRemoved()) {
				shots.getShotsToRemove().add(shot);
			}
		}
		for (final Shot shot : shots.getEnemyShots()) {
			shot.draw(batch);
			if (shot.shouldBeRemoved()) {
				shots.getShotsToRemove().add(shot);
			}
		}
		batch.end();
	}

    public void drawHero(final Hero hero)
    {
		batch.begin();
		hero.draw(batch);
		batch.end();
    }
    
    public void drawMisc(final float elapsed, final PlanetManager planetManager, final PointManager pointManager)
    {
		batch.begin();
		bg.draw(batch); // draw Background
		
		// draw planets
		for (final Planet planet : planetManager.getPlanets()) 
		{
			planet.draw(batch);
			if (planet.shouldBeRemoved()) {
				planetManager.getPlanetsToRemove().add(planet);
			}
		}
		planetManager.createPlanet(elapsed);
		

		if(showOverlay)
		{
			// start overlay is displayed 5 sec
			batch.draw(overlay.getTexture(NavigationOverlay.START), overlay.x, overlay.y, overlay.width, overlay.height);
			if (elapsed >= SHOW_OVERLAY_TIME)
			{
				EnemyManager.activateEnemyFactory();
				showOverlay = false;
			}
		} else {
			batch.draw(overlay.getTexture(NavigationOverlay.GAME), overlay.x, overlay.y, overlay.width, overlay.height);
		}
		pointManager.draw(batch);
		batch.end();
    }
    
    public void drawPause() {
    	batch.begin();
		pause.draw(batch);
		batch.end();
	}

	public static float getPpuX() {
		return ppuX;
	}

	public static float getPpuY() {
		return ppuY;
	}

	public void drawPowerUps(PowerUpManager powerUpManager) {
		batch.begin();
		for (final AbstractPowerUp powerup : powerUpManager.getPowerUps()) 
		{
			powerup.draw(batch);
		}
		batch.end();
	}

	public void resizeComponents(final float newPpuX, final float newPpuY, final EnemyManager enemyManager,
			final PlanetManager planetManager, final PowerUpManager powerUpManager,
			final ShotManager shotManager, final Hero hero, final boolean init) {
		if(init)
		{
			EnemyPaths.setSize(newPpuX, newPpuY);
			Sizes.setSize(newPpuX, newPpuY);
		}
		else{
			EnemyPaths.resize(ppuX, ppuY, newPpuX, newPpuY);
			Sizes.resize(ppuX, ppuY, newPpuX, newPpuY);
		}
		hero.resize(ppuX, ppuY, newPpuX, newPpuY);
		enemyManager.resize(ppuX, ppuY, newPpuX, newPpuY);
		planetManager.resize(ppuX, ppuY, newPpuX, newPpuY);
		powerUpManager.resize(ppuX, ppuY, newPpuX, newPpuY);
		shotManager.resize(ppuX, ppuY, newPpuX, newPpuY);
		WORLD_HEIGHT = Sizes.DEFAULT_WORLD_HEIGHT * newPpuY;
		WORLD_WIDTH = Sizes.DEFAULT_WORLD_WIDTH * newPpuX;
		overlay.width = WORLD_WIDTH;
		overlay.height = WORLD_HEIGHT;
		bg.width = WORLD_WIDTH;
		bg.height = WORLD_HEIGHT;
		ppuX = newPpuX;
		ppuY = newPpuY;
	}


    
}
