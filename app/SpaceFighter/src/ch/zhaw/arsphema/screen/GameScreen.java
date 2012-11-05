package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.controller.EnemyManager;
import ch.zhaw.arsphema.controller.GameController;
import ch.zhaw.arsphema.controller.HeroController;
import ch.zhaw.arsphema.controller.PlanetManager;
import ch.zhaw.arsphema.controller.PointManager;
import ch.zhaw.arsphema.controller.PowerUpManager;
import ch.zhaw.arsphema.controller.ShotManager;
import ch.zhaw.arsphema.model.Background;
import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.Musics;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.Sounds;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class GameScreen extends AbstractScreen {
	
	
	private Hero hero;
	private HeroController controller;
	private ShotManager shotManager;
	private EnemyManager enemyManager;
	private PlanetManager planetManager;
	private PointManager pointManager;
	private PowerUpManager powerUpManager;
	private Renderer renderer;
	private Background bg;
	private float elapsed;

	public GameState gameState;
	private GameController gameController;
	public enum GameState {
		PAUSED, RESUMED, PLAY
	};
	
	public GameScreen(MyGdxGame game) {
		super(game);
		elapsed = 0;
		EnemyManager.deactivateEnemyFactory();
		
		hero = new Hero(Sizes.DEFAULT_WORLD_WIDTH/10, Sizes.DEFAULT_WORLD_HEIGHT / 2 + Sizes.SHIP_HEIGHT / 2, TextureRegions.HERO);
		gameState = GameState.PLAY;
	}

	@Override
	public void show() {
		controller = new HeroController(hero);
		gameController = new GameController(this);
		shotManager = new ShotManager();
		powerUpManager = new PowerUpManager(hero);
		enemyManager = new EnemyManager(powerUpManager);
		planetManager = new PlanetManager();
		pointManager = new PointManager();
		bg = new Background(TextureRegions.BACKGROUND_STARS, 0, 0,
				Sizes.DEFAULT_WORLD_WIDTH, Sizes.DEFAULT_WORLD_HEIGHT);
		
		renderer = new Renderer(bg);
		Gdx.input.setInputProcessor(controller);
		Gdx.input.setCatchBackKey(true);
		
		Services.turnOffSound(); 
		Services.getMusicManager().setVolume(.75f); // TODO set volume in preference screen
		Services.getMusicManager().playRandom();
	}

	boolean init = true;
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		controller.resize(width, height);
		renderer.setPpuX((float) width / Sizes.DEFAULT_WORLD_WIDTH);
		renderer.setPpuY((float) height / Sizes.DEFAULT_WORLD_HEIGHT);
	}

	@Override
	public void render(final float delta) {
		//TODO move this to input processor
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			gameState = GameState.PAUSED;
		}
		if (gameState.equals(GameState.PAUSED)) {
			renderer.cleanScreen();
			renderer.drawPause();
			Gdx.input.setInputProcessor(gameController);
			return;
		} else if (gameState.equals(GameState.RESUMED)) {
			gameState = GameState.PLAY;
			super.render(0);
			Gdx.input.setInputProcessor(controller);
			return;
		}
		
		
		elapsed += delta;
		//check memory usage
//		long memTotal = Runtime.getRuntime().totalMemory();
//		long memFree = Runtime.getRuntime().freeMemory();
//		long memUsage = memTotal - memFree;
//		System.out.println("Memory: t: " + memTotal + " f: " + memFree + " u: " + memUsage);
		bg.move(delta);
		
		//draw stuff and so
		renderer.cleanScreen();
		renderer.drawMisc(elapsed, planetManager, pointManager);
		renderer.drawEnemies(enemyManager);
		renderer.drawHero(hero);
		renderer.drawShots(shotManager);
		renderer.drawPowerUps(powerUpManager);
		
		//hero stuff
		hero.move(delta);
		shotManager.heroShoots(hero.shoot(delta));
		shotManager.heroSuffering(hero);
		enemyManager.colideWithHero(hero);
		if(hero.isDead()){
			game.gameOver(pointManager.getTotalPoints());
			Services.turnOffSound();
			Services.getMusicManager().play(Musics.GAME_OVER);
		}
		pointManager.increaseTimePoints(elapsed);
		
		//enemy stuff
		pointManager.increasePointsOfEnemies(elapsed, enemyManager.killEnemies(shotManager));// first kill, then move and create new
		enemyManager.computeEnemyMovements(delta);//
		enemyManager.enemyShooting(shotManager, delta);
		enemyManager.dropEnemies(delta, elapsed);
		
		//shot stuff
		shotManager.moveShots(delta);
		shotManager.cleanUpShots();
		
		//powerup stuff
		powerUpManager.movePowerUps(delta);
		powerUpManager.colideWithHero(hero);
		
		//user input
		controller.update(delta);
		
		//planet stuff
		planetManager.movePlanets(delta);
		planetManager.cleanUpPlanets();
	}

	@Override
	public void hide() {
		pause();
	}

	@Override
	public void pause() {
		gameState = GameState.PAUSED;
	}

	@Override
	public void resume() {
		
	}

}