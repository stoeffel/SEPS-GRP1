package ch.zhaw.arsphema.controller;

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
	private Label showMeYourPoints;
	
	public PointManager()
	{
        LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.SPACE_FONT), false), Color.WHITE);
        showMeYourPoints = new Label("Arsphema", labelStyle);
	}
	
	public void increasePointsOfEnemies(final float timeElapsed, final int addPoints){
		enemyPoints += addPoints * (timeElapsed * 1.5f);
	}
	
	
	public void increaseTimePoints(final float timeElapsed){
		timeBonus = (int) (timeElapsed * 13f);
	}
	
	public void draw(SpriteBatch batch) {
		//TODO do the needfull spoerri
		totalPoints = enemyPoints + timeBonus;
		showMeYourPoints.setText("points: " + totalPoints);
//		batch.draw(showMeYourPoints, Sizes.DEFAULT_WORLD_WIDTH / 2, Sizes.DEFAULT_WORLD_HEIGHT);
		// System.out.println(totalPoints);
	}
	
}
