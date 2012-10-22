package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.utils.Array;

public class ShotManager {

	private Array<Shot> heroShots, enemyShots, shotsToRemove;
	
	public ShotManager()
	{
		heroShots = new Array<Shot>();
		enemyShots = new Array<Shot>();
		shotsToRemove = new Array<Shot>();
	}

	public void cleanUpShots() {
		removeShots(heroShots);
		removeShots(enemyShots);
		shotsToRemove.clear();		
	}
	
	private void removeShots(final Array<Shot> shotArray) {
		for(final Shot shot : shotsToRemove)
		{
			shotArray.removeValue(shot, false);
		}
	}

	public boolean heroSuffering(final Hero hero) {
		for(final Shot shot : enemyShots)
		{
			if(shot.overlaps(hero))
			{
				hero.lowerHealth(shot.getDamage());
				shotsToRemove.add(shot);
			}
		}
		return false;
	}

	public void heroShoots(final Array<Shot> shots) {
		if(shots  != null )
			heroShots.addAll(shots);			
	}
	
	public void moveShots(final float delta) {
		for(final Shot shot : enemyShots)
			shot.move(delta);
		for(final Shot shot : heroShots)
			shot.move(delta);
	}
	
	
	public Array<Shot> getHeroShots() {
		return heroShots;
	}

	public void setHeroShots(Array<Shot> heroShots) {
		this.heroShots = heroShots;
	}

	public Array<Shot> getEnemyShots() {
		return enemyShots;
	}

	public void setEnemyShots(Array<Shot> enemyShots) {
		this.enemyShots = enemyShots;
	}

	public Array<Shot> getShotsToRemove() {
		return shotsToRemove;
	}

	public void setShotsToRemove(Array<Shot> shotsToRemove) {
		this.shotsToRemove = shotsToRemove;
	}
	
}
