package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.model.powerup.AbstractPowerUp;
import ch.zhaw.arsphema.model.powerup.OneUp;
import ch.zhaw.arsphema.model.powerup.ShotGreen;
import ch.zhaw.arsphema.model.shot.ShotFactory;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.utils.Array;

public class PowerUpManager {

	private Hero hero;
	private Array<AbstractPowerUp> powerUps;
	private Array<AbstractPowerUp> usedPowerUps;
	

	public PowerUpManager(Hero hero) {
		this.hero = hero;
		powerUps = new Array<AbstractPowerUp>();
		usedPowerUps = new Array<AbstractPowerUp>();
	}

	public void oneUp() {
		hero.oneUp();
	}
	
	/**
	 * 
	 */
	public void createPowerUp(float x, float y) {
		switch (calcWhichPowerUp()) {
		case 1:
			powerUps.add(createOneUp(x,y));
			break;
		case 2:
			powerUps.add(createShotGreen(x, y));
			break;

		default:
			break;
		}
	}
	
	/**
	 * TODO make a more fancy algo
	 * @return
	 */
	private int calcWhichPowerUp() {
		double rand = Math.random();
		if (rand < 0.5) {
			return 0; // no Power up
		}
		if (rand < 0.505) {
			return 1; // Probability for a one up is 1/200
		}
		if (rand < 0.510) {
			return 2; // Probability for a one up is 1/200
		}
		return 0;
	}

	public OneUp createOneUp(float x, float y) {
		return new OneUp(x, y, Sizes.POWER_UP_WITDH, Sizes.POWER_UP_HEIGHT, TextureRegions.ONE_UP);
	}
	public ShotGreen createShotGreen(float x, float y) {
		return new ShotGreen(x, y, Sizes.POWER_UP_WITDH, Sizes.POWER_UP_HEIGHT, TextureRegions.POWERUP_SHOT_GREEN);
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
				pu.doSomething(this);
				usedPowerUps.add(pu);
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

	public void setShotGreen() {
		hero.setShotType(ShotFactory.Type.GREEN);
		hero.setShootingFrequency(0.05f);
	}

	public void setShotStd() {
		hero.setShotType(ShotFactory.Type.STANDARD);
		hero.setShootingFrequency(0.1f);
	}

}
