package ch.zhaw.arsphema.controller;

import java.util.Random;

import ch.zhaw.arsphema.model.Explosion;
import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.model.enemies.AbstractEnemy;
import ch.zhaw.arsphema.model.enemies.EnemyFactory;
import ch.zhaw.arsphema.model.enemies.EnemyGroup;
import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.utils.Array;

/**
 * EnemyManager:
 * Verwaltet die Gegner welche zerstört wurden oder auch noch nicht.
 * 
 * @author schtoeffel
 *
 */
public class EnemyManager {

	private Array<EnemyGroup> groups, killedGroups;
	private Array<AbstractEnemy> killedEnemies;
	private Array<Explosion> explosions;
	private static EnemyFactory enemyFactory;
	private static boolean dropEnemies = false;
	private float nextEnemyToDrop = 0;
	//set to hard so the calculation creates easy groups at the beginning
	private int groupDiffcultyOne = 2; // latest deployment
	private int groupDiffcultyTwo = 2; // second latest deployment
	private int groupDiffcultyThree = 2; // third latest deployment
	private Random random = new Random();
	private PowerUpManager pum;
	
	/**
	 * Konstruktur
	 * @param pum
	 */
	public EnemyManager(PowerUpManager pum)
	{
		killedEnemies = new Array<AbstractEnemy>();
		enemyFactory = EnemyFactory.getInstance();
		groups = new Array<EnemyGroup>();
		killedGroups = new Array<EnemyGroup>();
		explosions = new Array<Explosion>();
		this.pum = pum;
	}
	
	/**
	 * bewegt gruppen von gegnern und entfernt sie, falls sie aus dem Sichtfeld kommen
	 * @param delta
	 */
	public void computeEnemyMovements(float delta) {
		for (EnemyGroup group : groups) {
			if (group.move(delta)){
				killedEnemies.addAll(group.getMembers());
			}
			removeEnemies(group);
		}
		removeEnemyGroups();
		removeExplosions();
	}

	/**
	 * test ob der hero mit einem enemy kollidiert
	 * @param hero
	 */
	public void colideWithHero(final Hero hero) {
		for (final EnemyGroup group : groups) {
			if(group.overlaps(hero)){
				for (final AbstractEnemy enemy : group.getMembers()) {
					if (enemy.overlaps(hero)) {
						killedEnemies.add(enemy);
						hero.lowerHealth(enemy.getCollisionDamage());
					}
				}
				removeEnemies(group);
			}
		}
	}

	/**
	 * test ob ein gegner von einem schuss getroffen wurde
	 * @param shotManager
	 * @return points earned in this round
	 */
	public int killEnemies(final ShotManager shotManager, float elapsed) {
		int totalPoints = 0;
		for(Shot shot : shotManager.getHeroShots())
		{
			//shot overlaps with group
			for (final EnemyGroup group : groups) {
				if(shot.overlaps(group)){
					for(AbstractEnemy enemy : group.getMembers()){
						if(shot.overlaps(enemy))
						{
							if(enemy.lowerHealth(shot.getDamage())){
								totalPoints += enemy.getBasePoints();
								killedEnemies.add(enemy);
								explosions.add(new Explosion(enemy.x + enemy.width / 2, 
										enemy.y + enemy.height / 2));
								pum.createPowerUp(enemy.x, enemy.y,elapsed); // create a power up
								//remove enemy from group
							}
							if (shot.destroyOnHit)
								shotManager.getShotsToRemove().add(shot);
						}
					}
					removeEnemies(group);
					// no use for "continue" since groups can overlap
				}
			}
			if (!shot.destroyOnHit)
				for (Shot enemyShot : shotManager.getEnemyShots()) {
					if (enemyShot.overlaps(shot)) {
						shotManager.getShotsToRemove().add(enemyShot);
					}
				}
		}
		return totalPoints;
	}

	/**
	 * entfernt tote gegner
	 * @param group
	 */
	private void removeEnemies(final EnemyGroup group) {
		for(final AbstractEnemy enemy : killedEnemies)
		{
			group.getMembers().removeValue(enemy, false);
		}
		killedEnemies.clear();
	}
	
	/**
	 * entfernt eine gruppe
	 */
	private void removeEnemyGroups() {
		for(final EnemyGroup group : groups){
			if(group.getMembers().size == 0){
				killedGroups.add(group);
			}
		}
		for(final EnemyGroup enemyGroup : killedGroups){
			groups.removeValue(enemyGroup, false);
		}
		killedEnemies.clear();
	}

	/**
	 * entferne explosion
	 */
	private void removeExplosions() {
		for(final Explosion explosion : explosions)
		{
			if (explosion.isFinished())
				explosions.removeValue(explosion, false);
		}
	}
	
	/**
	 * lässt gegner schiessen
	 * @param shotManager
	 * @param delta
	 */
	public void enemyShooting(final ShotManager shotManager, final float delta) {
		for(final EnemyGroup group : groups){
			for(final AbstractEnemy enemy : group.getMembers())
			{
				final Array<Shot> tempShot = enemy.shoot(delta);
				if(tempShot != null)
					shotManager.getEnemyShots().addAll(tempShot);
			}
		}
	}

	/**
	 * berechnet ob bereits wieder eine gegnergruppe erstellt werden muss
	 * @param delta
	 * @param elapsed
	 */
	public void dropEnemies(float delta, float elapsed) {
		if (dropEnemies) {
			if(nextEnemyToDrop <= 0) {
				addEnemies(delta, elapsed);
				// check elapsed time for next enemy set to drop 
				// now after 200 sec every 0.5 sec new enemy should appear (approach is linear)
				nextEnemyToDrop = 4 - (elapsed * 5 / 200) * groupDiffcultyOne;
				if(nextEnemyToDrop < 0.5f)
					nextEnemyToDrop = 0.5f;
			} else {
				nextEnemyToDrop -= delta;
			}
		}
	}

	/**
	 * neue gegner hinzufügen
	 * @param delta
	 * @param elapsed
	 */
	private void addEnemies(final float delta, final float elapsed) {
		//assumption: beginning of only hard enemies @200secs
		final int lastGroupsDifficulty = groupDiffcultyOne + groupDiffcultyTwo + groupDiffcultyThree;
		final int nextGroupDiffLevel = calculateNextGroupDifficultyLevel(elapsed, lastGroupsDifficulty);
		groupDiffcultyThree = groupDiffcultyTwo;
		groupDiffcultyTwo = groupDiffcultyOne;
		groupDiffcultyOne = nextGroupDiffLevel;
		final EnemyGroup group = enemyFactory.createGroupByDifficultyLevel(nextGroupDiffLevel, elapsed);
		group.move(delta);
		final Array<AbstractEnemy> newEnemies = group.getMembers(); 
		if (newEnemies != null) {
			groups.add(group);
		}
	}
	
	/**
	 * berechnet den schwierigkeitsgrad der nächsten gegner
	 * @param elapsed
	 * @param sumDifLevel
	 * @return
	 */
	private int calculateNextGroupDifficultyLevel(final float elapsed, final float sumDifLevel)
	{
		final float div;
		if(sumDifLevel == 0)
			div = 1;
		else
			div = 1 / sumDifLevel;
		int base = 20 + random.nextInt(20);
		float result = elapsed / (base / div); 
		if(result > 2)
			return 2;
		return Math.round(result);
	}
	
	/**
	 * returns getötete gegner
	 * @return
	 */
	public Array<AbstractEnemy> getKilledEnemies() {
		return killedEnemies;
	}

	/**
	 * fügt tote gegner dem array hinzu
	 * @param killedEnemies
	 */
	public void setKilledEnemies(Array<AbstractEnemy> killedEnemies) {
		this.killedEnemies = killedEnemies;
	}

	/**
	 * aktiviert die Factory
	 */
	public static void activateEnemyFactory(){
		dropEnemies = true;
	}
	
	/**
	 * deaktiviert die Factory
	 */
	public static void deactivateEnemyFactory(){
		dropEnemies = false;
	}

	/**
	 * returns explosionen
	 * @return
	 */
	public Array<Explosion> getExplosions() {
		return explosions;
	}

	/**
	 * lass es knallen
	 * @param explosions
	 */
	public void setExplosions(Array<Explosion> explosions) {
		this.explosions = explosions;
	}

	/**
	 * returns gruppen
	 * @return
	 */
	public Array<EnemyGroup> getGroups() {
		return groups;
	}

}
