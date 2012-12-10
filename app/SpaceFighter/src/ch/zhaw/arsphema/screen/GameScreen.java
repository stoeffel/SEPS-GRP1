package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.controller.*;
import ch.zhaw.arsphema.model.Background;
import ch.zhaw.arsphema.model.Controls;
import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.model.shot.ShotFactory;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.Musics;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.TextureRegions;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


/**
 * Screenklasse in welcher das eigentliche Spiel angezeigt wird.
 */
public class GameScreen extends AbstractScreen {

    private Hero hero;
    private JoyStickController controller;
    private ShotManager shotManager;
    private EnemyManager enemyManager;
    private PlanetManager planetManager;
    private PointManager pointManager;
    private PowerUpManager powerUpManager;
    private Renderer renderer;
    private Background bg;
    private float elapsed;

    public GameState gameState;
    private Controls controls;

    public enum GameState {
        PAUSED, RESUMED, PLAY
    }

    /**
     * Konstruktor
     *
     * @param game Instanz der Game Klasse
     */
    public GameScreen(MyGdxGame game) {
        super(game);
        elapsed = 0;
        EnemyManager.deactivateEnemyFactory();

        hero = new Hero(Sizes.SHIP_X_POSITION, Sizes.DEFAULT_WORLD_HEIGHT / 2 + Sizes.SHIP_HEIGHT / 2, TextureRegions.HERO);
        controls = new Controls(0, Sizes.DEFAULT_WORLD_HEIGHT / 2 + Sizes.CTRL_HEIGHT / 2, Sizes.CTRL_WIDTH, Sizes.CTRL_HEIGHT, TextureRegions.CTRLS);
        gameState = GameState.PLAY;
    }

    /**
     * Initialisiert ein neues Spiel
     */
    public void initGame() {
        controller = new JoyStickController(hero, controls);
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
        Services.getMusicManager().playRandom();
    }

    @Override
    /**
     * Wird bei jeder Anpassung der Screengrösse aufgerufen.
     */
    public void resize(int width, int height) {
        super.resize(width, height);
        controller.resize(width, height);
        renderer.setPpuX((float) width / Sizes.DEFAULT_WORLD_WIDTH);
        renderer.setPpuY((float) height / Sizes.DEFAULT_WORLD_HEIGHT);
    }

    @Override
    /**
     * Wird bei jedem Renderingvorgang aufgerufen und stellt somit den Hauptablauf des Spieles dar.
     */
    public void render(final float delta) {
        if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            pause();
        }
        if (gameState.equals(GameState.PAUSED)) {
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
        renderer.drawMisc(elapsed, planetManager, pointManager, controls);
        renderer.drawEnemies(enemyManager);
        renderer.drawHero(hero);
        renderer.drawShots(shotManager);
        renderer.drawPowerUps(powerUpManager);

        //hero stuff
        hero.move(delta);
        shotManager.heroShoots(hero.shoot(delta));
        shotManager.heroSuffering(hero);
        enemyManager.colideWithHero(hero);
        if (hero.isDead()) {
            game.gameOver(pointManager.getTotalPoints());
            Services.turnOffSound();
            Services.getMusicManager().play(Musics.GAME_OVER);
        }
        ShotFactory.setHeroY(hero.y);

        //points
        pointManager.increaseTimePoints(elapsed);

        //enemy stuff
        pointManager.increasePointsOfEnemies(elapsed, enemyManager.killEnemies(shotManager, elapsed));// first kill, then move and create new
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
    /**
     * Pausiert das Spiel und zeigt den Pause-Screen an
     */
    public void pause() {
        gameState = GameState.PAUSED;
        game.setScreen(game.getPauseScreen());
    }

    @Override
    /**
     * Setzt d
     */
    public void resume() {
        gameState = GameState.RESUMED;
    }

}