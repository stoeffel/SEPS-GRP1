package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.model.enemies.AbstractEnemy;
import ch.zhaw.arsphema.model.enemies.EnemyFactory;
import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.utils.Array;

public class EnemyManager {

	private Array<AbstractEnemy> enemies, killedEnemies;
	private static EnemyFactory enemyFactory;
	
	public EnemyManager()
	{
		enemies = new Array<AbstractEnemy>();
		killedEnemies = new Array<AbstractEnemy>();
		enemyFactory = EnemyFactory.getInstance();
	}
	
	public void computeEnemyMovements(float delta) {
		for(AbstractEnemy enemy : enemies)
		{
			if(enemy.move(delta))
				killedEnemies.add(enemy);
		}
		removeEnemies();
	}
	
	public void killEnemies(final ShotManager shotManager) {
		for(Shot shot : shotManager.getHeroShots())
		{
			for(AbstractEnemy enemy : enemies)
			if(shot.overlaps(enemy))
			{
				if(enemy.lowerHealth(shot.getDamage())){
					//TODO enemy is dead... loot him!!! (point berechnung)
//					enemy.getBasePoints();
					killedEnemies.add(enemy);
				}
				shotManager.getShotsToRemove().add(shot);
			}
		}
		removeEnemies();
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

	public void dropEnemies(float delta, float elapsed) {
		final Array<AbstractEnemy> newEnemies = enemyFactory.dropEnemy(delta, elapsed);
		if(newEnemies != null)
			enemies.addAll(newEnemies);		
	}

	public static void activateEnemyFactory(){
		enemyFactory.setDropEnemies(true);
	}
	public static void deactivateEnemyFactory(){
		enemyFactory.setDropEnemies(false);
	}

	
}
