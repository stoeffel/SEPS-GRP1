package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.controller.EnemyManager;
import ch.zhaw.arsphema.controller.HeroController;
import ch.zhaw.arsphema.controller.PlanetManager;
import ch.zhaw.arsphema.controller.PointManager;
import ch.zhaw.arsphema.controller.ShotManager;
import ch.zhaw.arsphema.model.Background;
import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.services.MusicManager;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.services.SoundManager;
import ch.zhaw.arsphema.util.Musics;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen extends AbstractScreen {
	
	//TODO load all static data provider classes in constructor
	
	private Hero hero;
	private HeroController controller;
	private ShotManager shotManager;
	private EnemyManager enemyManager;
	private PlanetManager planetManager;
	private PointManager pointManager;
	private Renderer renderer;
	private Background bg;
	private float elapsed = 0;
	private boolean pause = false;
	
	public GameScreen(MyGdxGame game) {
		super(game);
		hero = new Hero(Sizes.DEFAULT_WORLD_WIDTH/10, Sizes.DEFAULT_WORLD_HEIGHT / 2 + Sizes.SHIP_HEIGHT / 2, TextureRegions.HERO);
	}

	@Override
	public void show() {
		controller = new HeroController(hero);
		shotManager = new ShotManager();
		enemyManager = new EnemyManager();
		planetManager = new PlanetManager();
		pointManager = new PointManager();
		bg = new Background(new TextureRegion(TextureRegions.BACKGROUND_STARS),0,0,Sizes.DEFAULT_WORLD_WIDTH,Sizes.DEFAULT_WORLD_HEIGHT);
		
		renderer = new Renderer(bg);
		Gdx.input.setInputProcessor(controller);
		Services.setSoundManager(new SoundManager());
		Services.setMusicManager(new MusicManager());
		Services.getMusicManager().setVolume(0.25f); // TODO set volume in preference screen
		Services.getMusicManager().play(Musics.AMBIENTE);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		controller.resize(width, height);
		renderer.setPpuX((float) width / Sizes.DEFAULT_WORLD_WIDTH);
		renderer.setPpuY((float) height / Sizes.DEFAULT_WORLD_HEIGHT);
	}

	@Override
	public void render(float delta) {
		if(pause)
			return;
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
		
		//hero stuff
		hero.move(delta);
		shotManager.heroShoots(hero.shoot(delta));
		shotManager.heroSuffering(hero);
		if(hero.isDead()){
			game.gameOver();
			Services.turnOffSound();
		}
		pointManager.increaseTimePoints(elapsed);
		
		//enemy stuff
		pointManager.increasePointsOfEnemies(elapsed, enemyManager.killEnemies(shotManager));// first kill, then move and create new
		enemyManager.computeEnemyMovements(delta);
		enemyManager.enemyShooting(shotManager, delta);
		enemyManager.dropEnemies(delta, elapsed);
		
		//shot stuff
		shotManager.moveShots(delta);
		shotManager.cleanUpShots();
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
		pause = true;
	}

	@Override
	public void resume() {
		pause = false;
	}

}