package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.screen.Renderer;
import ch.zhaw.arsphema.util.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class PointManager {

    private int enemyPoints = 0;
    private int timeBonus = 0;
    private int totalPoints = 0;
    private BitmapFont counterfont;
    private Label showMeYourPoints;

    public PointManager() {
        counterfont = new BitmapFont(Gdx.files.internal(Paths.COUNTER_FONT), false);
        LabelStyle labelStyle = new Label.LabelStyle(counterfont, Color.WHITE);
        showMeYourPoints = new Label("0", labelStyle);
    }

    public void increasePointsOfEnemies(final float timeElapsed, final int addPoints) {
        enemyPoints += addPoints * (timeElapsed * 1.5f);
    }


    public void increaseTimePoints(final float timeElapsed) {
        timeBonus = (int) (timeElapsed * 13f);
    }

    public void draw(SpriteBatch batch) {
        totalPoints = enemyPoints + timeBonus;
        showMeYourPoints.setText("" + totalPoints);
        BitmapFont.TextBounds bounds = counterfont.getBounds(String.valueOf(totalPoints));
        showMeYourPoints.x = Renderer.WORLD_WIDTH / 2 - bounds.width / 2;
        showMeYourPoints.y = (Renderer.WORLD_HEIGHT - 5) - bounds.height;
        showMeYourPoints.draw(batch, 1);
    }

}
