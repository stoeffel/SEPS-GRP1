package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.utils.Array;

/**
 * Schuss Kontroller:
 * kontrolliert alle schüsse
 * @author schtoeffel
 *
 */
public class ShotManager {

	private Array<Shot> heroShots, enemyShots, shotsToRemove;
	
	/**
	 * Konstruktor
	 */
	public ShotManager()
	{
		heroShots = new Array<Shot>();
		enemyShots = new Array<Shot>();
		shotsToRemove = new Array<Shot>();
	}

	/**
	 * räumt schüsse auf
	 */
	public void cleanUpShots() {
		removeShots(heroShots);
		removeShots(enemyShots);
		shotsToRemove.clear();		
	}
	
	/**
	 * entfernt schüsse
	 * @param shotArray
	 */
	private void removeShots(final Array<Shot> shotArray) {
		for(final Shot shot : shotsToRemove)
		{
			shotArray.removeValue(shot, false);
		}
	}

	/**
	 * test ob der held getroffen wurde
	 * @param hero
	 * @return
	 */
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

	/**
	 * fügt hero schüsse hinzu
	 * @param shots
	 */
	public void heroShoots(final Array<Shot> shots) {
		if(shots  != null )
			heroShots.addAll(shots);			
	}
	
	/**
	 * bewegt schüsse
	 * @param delta
	 */
	public void moveShots(final float delta) {
		for(final Shot shot : enemyShots)
			shot.move(delta);
		for(final Shot shot : heroShots)
			shot.move(delta);
	}
	
	/**
	 * getter für heroschüsse
	 * @return
	 */
	public Array<Shot> getHeroShots() {
		return heroShots;
	}

	/**
	 * setter für heroShots
	 * @param heroShots
	 */
	public void setHeroShots(Array<Shot> heroShots) {
		this.heroShots = heroShots;
	}

	/**
	 * getter für enemyshots
	 * @return
	 */
	public Array<Shot> getEnemyShots() {
		return enemyShots;
	}

	/**
	 * setter für enemyshots
	 */
	public void setEnemyShots(Array<Shot> enemyShots) {
		this.enemyShots = enemyShots;
	}

	/**
	 * getter für zulöschende schüsse
	 * @return
	 */
	public Array<Shot> getShotsToRemove() {
		return shotsToRemove;
	}

	/**
	 * setter für zulöschende schüsse
	 * @param shotsToRemove
	 */
	public void setShotsToRemove(Array<Shot> shotsToRemove) {
		this.shotsToRemove = shotsToRemove;
	}
	
}
