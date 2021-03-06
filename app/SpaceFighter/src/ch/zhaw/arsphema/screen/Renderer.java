package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.controller.*;
import ch.zhaw.arsphema.model.*;
import ch.zhaw.arsphema.model.enemies.AbstractEnemy;
import ch.zhaw.arsphema.model.enemies.EnemyGroup;
import ch.zhaw.arsphema.model.powerup.AbstractPowerUp;
import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.TextureRegions;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * Renderingklasse welche die Spielelemente zeichnent.
 */
public class Renderer {

    protected final SpriteBatch batch;
    private final int SHOW_OVERLAY_TIME = 5;
    private float ppuX; // pixels per unit on the X axis
    private float ppuY; // pixels per unit on the Y axis
    public static float WORLD_WIDTH;
    public static float WORLD_HEIGHT;
    private boolean showOverlay = true;
    private NavigationOverlay overlay;
    private Background bg;
    private ShapeRenderer shapeRenderer;

    /**
     * Konstuktor
     *
     * @param bg Hintergrund
     */
    public Renderer(Background bg) {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        this.bg = bg;

        loadTextures();
    }

    private void loadTextures() {
        overlay = new NavigationOverlay(TextureRegions.OVERLAY_SPRITE);
    }

    /**
     * Setzt den Screen zurück
     */
    public void cleanScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Zeichnet die Gegner
     *
     * @param enemies EnemyManager
     */
    public void drawEnemies(final EnemyManager enemies) {
        batch.begin();
        for (final EnemyGroup group : enemies.getGroups()) {
            for (final AbstractEnemy enemy : group.getMembers()) {
                enemy.draw(batch, ppuX, ppuY);
            }
        }

        for (final Explosion explosion : enemies.getExplosions()) {
            explosion.draw(batch, ppuX, ppuY);
        }

        batch.end();
        shapeRenderer.begin(ShapeType.FilledRectangle);
        for (final EnemyGroup group : enemies.getGroups()) {
            for (final AbstractEnemy enemy : group.getMembers()) {
                enemy.drawHealthBar(shapeRenderer, ppuX, ppuY);

            }
        }
        shapeRenderer.end();
        //debug
//		shapeRenderer.begin(ShapeType.Rectangle);
//		for(final EnemyGroup group : enemies.getGroups())
//		{
//			shapeRenderer.rect(group.x * ppuX, group.y * ppuY, group.width * ppuX, group.height * ppuY);
//		}
//		shapeRenderer.end();

    }

    /**
     * Zeichnet die Schüsse
     *
     * @param shots ShotManager
     */
    public void drawShots(final ShotManager shots) {
        batch.begin();
        for (final Shot shot : shots.getHeroShots()) {
            shot.draw(batch, ppuX, ppuY);
            if (shot.shouldBeRemoved()) {
                shots.getShotsToRemove().add(shot);
            }
        }
        for (final Shot shot : shots.getEnemyShots()) {
            shot.draw(batch, ppuX, ppuY);
            if (shot.shouldBeRemoved()) {
                shots.getShotsToRemove().add(shot);
            }
        }
        batch.end();
    }

    /**
     * Zeichnet das Raumschiff, welches der Spieler Steuert.
     *
     * @param hero Hero
     */
    public void drawHero(final Hero hero) {
        batch.begin();
        hero.draw(batch, ppuX, ppuY);
        batch.end();
    }

    /**
     * Zeichnet die übrigen Spielelemente wie die Punkte, den Hintergrund, die Planeten oder Steuerungssymbole
     *
     * @param elapsed       Vergangene Zeit
     * @param planetManager PlanetManager
     * @param pointManager  PointManager
     * @param controls      Controls
     */
    public void drawMisc(final float elapsed, final PlanetManager planetManager, final PointManager pointManager, Controls controls) {
        batch.begin();
        bg.draw(batch, ppuX, ppuY); // draw Background

        // draw planets
        for (final Planet planet : planetManager.getPlanets()) {
            planet.draw(batch, ppuX, ppuY);
            if (planet.shouldBeRemoved()) {
                planetManager.getPlanetsToRemove().add(planet);
            }
        }
        planetManager.createPlanet(elapsed);


        if (showOverlay) {
            // start overlay is displayed 5 sec
            batch.draw(overlay.getTexture(NavigationOverlay.START), ppuX * overlay.x, ppuY * overlay.y, ppuX * Sizes.DEFAULT_WORLD_WIDTH, ppuY * Sizes.DEFAULT_WORLD_HEIGHT);
            if (elapsed >= SHOW_OVERLAY_TIME) {
                EnemyManager.activateEnemyFactory();
                showOverlay = false;
            }
        } else {
            batch.draw(overlay.getTexture(NavigationOverlay.GAME), ppuX * overlay.x, ppuY * overlay.y, ppuX * Sizes.DEFAULT_WORLD_WIDTH, ppuY * Sizes.DEFAULT_WORLD_HEIGHT);
        }
        pointManager.draw(batch, ppuX, ppuY);
        controls.draw(batch, ppuX, ppuY);
        batch.end();
    }

    public void drawPowerUps(PowerUpManager powerUpManager) {
        batch.begin();
        for (final AbstractPowerUp powerup : powerUpManager.getPowerUps()) {
            powerup.draw(batch, ppuX, ppuY);
        }
        batch.end();
    }

    /**
     * Setzt die Points Per Unit auf der X-Achse
     *
     * @param ppuX Points per Unit
     */
    public void setPpuX(float ppuX) {
        this.ppuX = ppuX;
    }

    /**
     * Setzt die Points Per Unit auf der Y-Achse
     *
     * @param ppuY Points per Unit
     */
    public void setPpuY(float ppuY) {
        this.ppuY = ppuY;
    }
}
