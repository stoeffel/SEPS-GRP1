package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.model.enemies.AbstractEnemy;
import ch.zhaw.arsphema.model.enemies.EnemyFactory;
import ch.zhaw.arsphema.model.enemies.EnemyGroup;
import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.utils.Array;

public class EnemyManager {

	private Array<EnemyGroup> groups;
	private Array<AbstractEnemy> enemies, killedEnemies;
	private static EnemyFactory enemyFactory;
	private static boolean dropEnemies = false;
	
	public EnemyManager()
	{
		enemies = new Array<AbstractEnemy>();
		killedEnemies = new Array<AbstractEnemy>();
		enemyFactory = EnemyFactory.getInstance();
		groups = new Array<EnemyGroup>();
	}
	
	public void computeEnemyMovements(float delta) {
		for (EnemyGroup group : groups) {
			if (group.move(delta)){
				killedEnemies.addAll(group.getMembers());
			}
		}
		removeEnemies();
	}
	
	/**
	 * @param shotManager
	 * @return points earned in this round
	 */
	public int killEnemies(final ShotManager shotManager) {
		int totalPoints = 0;
		for(Shot shot : shotManager.getHeroShots())
		{
			for(AbstractEnemy enemy : enemies)
			if(shot.overlaps(enemy))
			{
				if(enemy.lowerHealth(shot.getDamage())){
					totalPoints += enemy.getBasePoints();
					killedEnemies.add(enemy);
				}
				shotManager.getShotsToRemove().add(shot);
			}
		}
		removeEnemies();
		return totalPoints;
	}

	private void removeEnemies() {
		for(final AbstractEnemy enemy : killedEnemies)
		{
			enemies.removeValue(enemy, false);
		}
		killedEnemies.clear();
	}

	public void enemyShooting(final ShotManager shotManager, final float delta) {
		for(final AbstractEnemy enemy : enemies)
		{
			final Array<Shot> tempShot = enemy.shoot(delta);
			if(tempShot != null)
				shotManager.getEnemyShots().addAll(tempShot);
		}
	}

	private float nextEnemyToDrop = 0;
	public void dropEnemies(float delta, float elapsed) {
		if (dropEnemies) {
			if(nextEnemyToDrop <= 0) {
				addEnemies(delta);
				// check elapsed time for next enemy set to drop 
				// now after 100 sec every 0.5 sec new enemy should appear (approach is linea)
				nextEnemyToDrop = 5 - (elapsed * 5 / 1000 % 100);
				if(nextEnemyToDrop < 0.5f)
					nextEnemyToDrop = 0.5f;
			} else {
				nextEnemyToDrop -= delta;
			}
		}
	}

	private void addEnemies(final float delta) {
		EnemyGroup group = enemyFactory.createUfoGroup();
		final Array<AbstractEnemy> newEnemies = group.getMembers();
		if (newEnemies != null) {
			enemies.addAll(newEnemies);
			groups.add(group);
		}
	}
	
	public Array<AbstractEnemy> getEnemies() 
	{
		return enemies;
	}

	public void setEnemies(Array<AbstractEnemy> enemies) {
		this.enemies = enemies;
	}

	public Array<AbstractEnemy> getKilledEnemies() {
		return killedEnemies;
	}

	public void setKilledEnemies(Array<AbstractEnemy> killedEnemies) {
		this.killedEnemies = killedEnemies;
	}

	public static void activateEnemyFactory(){
		dropEnemies = true;
	}
	public static void deactivateEnemyFactory(){
		dropEnemies = false;
	}
	
}
