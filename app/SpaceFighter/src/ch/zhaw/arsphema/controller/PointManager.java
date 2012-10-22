package ch.zhaw.arsphema.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PointManager {
	
	private int enemyPoints = 0;
	private int timeBonus = 0;
	private int totalPoints = 0;
	
	public void increasePointsOfEnemies(final float timeElapsed, final int addPoints){
		enemyPoints += addPoints * (timeElapsed * 1.5f);
	}
	
	
	public void increaseTimePoints(final float timeElapsed){
		timeBonus = (int) (timeElapsed * 13f);
	}
	
	public void draw(SpriteBatch batch) {
		//TODO draw points!
		totalPoints = enemyPoints + timeBonus;
//		System.out.println(totalPoints);
	}

}
