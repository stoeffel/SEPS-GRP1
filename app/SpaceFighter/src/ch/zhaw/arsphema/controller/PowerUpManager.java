package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.model.powerup.AbstractPowerUp;
import ch.zhaw.arsphema.model.powerup.KillEmAllPowerUp;
import ch.zhaw.arsphema.model.powerup.OneUp;
import ch.zhaw.arsphema.model.powerup.ShotGreen;
import ch.zhaw.arsphema.model.powerup.SlowDownPowerUp;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class PowerUpManager {

	private Hero hero;
	private Array<AbstractPowerUp> powerUps;
	private Array<AbstractPowerUp> usedPowerUps;
	
	private float lastTime, maxInterval;
	
	private float propabilityNoPu;
	private static final float PROP_ONEUP = 0.15f, PROP_KILLALL = 0.25f; //, PROP_SHOTENH = 0.6f not used but it is 0.6f;
	

	public PowerUpManager(Hero hero) {
		this.hero = hero;
		powerUps = new Array<AbstractPowerUp>();
		usedPowerUps = new Array<AbstractPowerUp>();
		
		// the longer you played the more pu you get
		lastTime = 0;
		maxInterval = 8f;
		propabilityNoPu = maxInterval / 10;
	}

	public void oneUp() {
		hero.oneUp();
	}
	
	/**
	 * 
	 */
	public void createPowerUp(float x, float y, float elapsed) {
		switch (calcWhichPowerUp(elapsed)) {
		case 1:
			powerUps.add(createOneUp(x,y));
			break;
		case 2:
			powerUps.add(createShotGreen(x, y));
			break;
		case 3:
			powerUps.add(createUltimate(x,y));
			break;

		default:
			break;
		}
	}
	
	

	/**
	 * TODO make a more fancy algo
	 * @return
	 */
	private int calcWhichPowerUp(float elapsed) {
		double rand = Math.random();
		float delta = elapsed - lastTime; 
		if (rand < propabilityNoPu - (delta/10)) {
			return 0; // no Power up
		}
		propabilityNoPu = maxInterval / 10;
		lastTime = elapsed;
		rand = Math.random();
		if (rand < PROP_ONEUP) {
			return 1; 
		} else if (rand < PROP_ONEUP + PROP_KILLALL) {
			return 3;
		}
		
		return 2;
	}

	public OneUp createOneUp(float x, float y) {
		return new OneUp(x, y, Sizes.POWER_UP_WITDH, Sizes.POWER_UP_HEIGHT, TextureRegions.ONE_UP);
	}
	
	public ShotGreen createShotGreen(float x, float y) {
		return new ShotGreen(x, y, Sizes.POWER_UP_WITDH, Sizes.POWER_UP_HEIGHT, TextureRegions.POWERUP_SHOT_GREEN);
	}
	
	public SlowDownPowerUp createSlowDown(float x, float y) {
		return new SlowDownPowerUp(x, y, Sizes.POWER_UP_WITDH, Sizes.POWER_UP_HEIGHT, TextureRegions.POWERUP_SHOT_GREEN);
	}
	
	public KillEmAllPowerUp createKillEmAll(float x, float y) {
		return new KillEmAllPowerUp(x, y, Sizes.POWER_UP_WITDH, Sizes.POWER_UP_HEIGHT, TextureRegions.POWERUP_SHOT_GREEN);
	}

	public Array<AbstractPowerUp> getPowerUps() {
		return powerUps;
	}

	public void setPowerUps(Array<AbstractPowerUp> powerUps) {
		this.powerUps = powerUps;
	}
	
	public void colideWithHero(final Hero hero){
		for(final AbstractPowerUp pu : powerUps){
			if(pu.overlaps(hero)){
				usedPowerUps.add(pu);
				hero.addPowerUps(pu);
			}
		}
		removeUsedPowerUps();
	}
	
	private void removeUsedPowerUps() {
		for(final AbstractPowerUp pu : usedPowerUps)
		{
			powerUps.removeValue(pu, false);
		}
		usedPowerUps.clear();
	}

	public void movePowerUps(float delta) {
		for(final AbstractPowerUp pu : powerUps){
			pu.move(delta);
		}
	}
	
	private AbstractPowerUp createUltimate(float x, float y) {
		return new KillEmAllPowerUp(x, y, Sizes.POWER_UP_WITDH, Sizes.POWER_UP_HEIGHT, TextureRegions.POWERUP_ULTIMATE);
	}

	

}
