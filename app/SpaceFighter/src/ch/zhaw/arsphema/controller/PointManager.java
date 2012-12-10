package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.UiStyles;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Punkte Kontroller:
 * zeigt die Punkte des Spielers an
 * @author schtoeffel
 *
 */
public class PointManager {

    private int enemyPoints = 0;
    private int timeBonus = 0;
    private int totalPoints = 0;
    private BitmapFont counterfont;
    private Label showMeYourPoints;

    /**
     * Konstruktor
     */
    public PointManager() {
        showMeYourPoints = new Label("0", UiStyles.getPointLabelStyle(0));
    }

    /**
     * erhöht den Punkte zähler um die Punkte die der Gegner wert war.
     * @param timeElapsed
     * @param addPoints
     */
    public void increasePointsOfEnemies(final float timeElapsed, final int addPoints) {
        enemyPoints += addPoints * (timeElapsed * 1.5f);
    }

    /**
     * erhöht die punkte für langes überleben
     * @param timeElapsed
     */
    public void increaseTimePoints(final float timeElapsed) {
        timeBonus = (int) (timeElapsed * 13f);
    }

    /**
     * zeigt die punkte an
     * @param batch
     * @param ppuX
     * @param ppuY
     */
    public void draw(SpriteBatch batch, float ppuX, float ppuY) {
        totalPoints = enemyPoints + timeBonus;
        showMeYourPoints.setStyle(UiStyles.getPointLabelStyle(ppuY));
        showMeYourPoints.setText("" + totalPoints);
        BitmapFont.TextBounds bounds = showMeYourPoints.getStyle().font.getBounds(String.valueOf(totalPoints));
        showMeYourPoints.x = Sizes.DEFAULT_WORLD_WIDTH / 2 * ppuX - bounds.width / 2;
        showMeYourPoints.y = (Sizes.DEFAULT_WORLD_HEIGHT - 5) * ppuY - bounds.height;
        showMeYourPoints.draw(batch, 1);
    }

    /**
     * getter totalPoints
     * @return
     */
    public int getTotalPoints() {
        return totalPoints;
    }
}
