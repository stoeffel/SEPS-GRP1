package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.model.Explosion;
import ch.zhaw.arsphema.model.enemies.AbstractEnemy;
import ch.zhaw.arsphema.model.enemies.EnemyFactory;
import ch.zhaw.arsphema.model.enemies.EnemyGroup;
import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.utils.Array;

public class EnemyManager {

	private Array<EnemyGroup> groups;
	private Array<AbstractEnemy> enemies, killedEnemies;
	private Array<Explosion> explosions;
	private static EnemyFactory enemyFactory;
	private static boolean dropEnemies = false;
	private float nextEnemyToDrop = 0;
	//set to hard so the calculation creates easy groups at the beginning
	private EnemyGroupDifficulty groupDiffcultyOne = EnemyGroupDifficulty.HARD; // latest deployment
	private EnemyGroupDifficulty groupDiffcultyTwo = EnemyGroupDifficulty.HARD; // secont latest deployment
	private EnemyGroupDifficulty groupDiffcultyThree = EnemyGroupDifficulty.HARD; // third latest deployment
	
	
	public enum EnemyGroupDifficulty {
		EASY (1),
		MEDIUM (2),
		HARD (3);
	    private final int difficultyLevel;
	    EnemyGroupDifficulty(int i) {
	        this.difficultyLevel = i;
	    }
	}
	
	public EnemyManager()
	{
		enemies = new Array<AbstractEnemy>();
		killedEnemies = new Array<AbstractEnemy>();
		enemyFactory = EnemyFactory.getInstance();
		groups = new Array<EnemyGroup>();
		explosions = new Array<Explosion>();
	}
	
	public void computeEnemyMovements(float delta) {
		for (EnemyGroup group : groups) {
			if (group.move(delta)){
				killedEnemies.addAll(group.getMembers());
			}
		}
		removeEnemies();
		removeExplosions();
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
					explosions.add(new Explosion(enemy.x+enemy.width/2,enemy.y+enemy.height/2));
				}
				shotManager.getShotsToRemove().add(shot);
			}
		}
		removeEnemies();
//		if(enemies.size == 0){ //TODO maybe add
//			nextEnemyToDrop /= 2; //if all enemies killed, next drop of enemy is sooner
//		}
		return totalPoints;
	}

	private void removeEnemies() {
		for(final AbstractEnemy enemy : killedEnemies)
		{
			enemies.removeValue(enemy, false);
		}
		killedEnemies.clear();
	}

	private void removeExplosions() {
		for(final Explosion explosion : explosions)
		{
			if (explosion.isFinished())
				explosions.removeValue(explosion, false);
		}
	}
	
	public void enemyShooting(final ShotManager shotManager, final float delta) {
		for(final AbstractEnemy enemy : enemies)
		{
			final Array<Shot> tempShot = enemy.shoot(delta);
			if(tempShot != null)
				shotManager.getEnemyShots().addAll(tempShot);
		}
	}

	public void dropEnemies(float delta, float elapsed) {
		if (dropEnemies) {
			if(nextEnemyToDrop <= 0) {
				addEnemies(elapsed);
				// check elapsed time for next enemy set to drop 
				// now after 100 sec every 0.5 sec new enemy should appear (approach is linea)
				nextEnemyToDrop = 4 - (elapsed * 5 / 100);
				if(nextEnemyToDrop < 0.5f)
					nextEnemyToDrop = 0.5f;
			} else {
				nextEnemyToDrop -= delta;
			}
		}
	}

	private void addEnemies(final float elapsed) {
		//assumption: beginning of only hard enemies @200secs
		final int lastGroupsDifficulty = groupDiffcultyOne.difficultyLevel + groupDiffcultyTwo.difficultyLevel + groupDiffcultyThree.difficultyLevel;
		final int nextGroupDiffLevel = calculateNextGroupDifficultyLevel(elapsed, lastGroupsDifficulty);
		
		final EnemyGroup group = enemyFactory.createGroupByDifficultyLevel(nextGroupDiffLevel, elapsed);
		final Array<AbstractEnemy> newEnemies = group.getMembers(); 
		if (newEnemies != null) {
			enemies.addAll(newEnemies);
			groups.add(group);
		}
	}
	
	private int calculateNextGroupDifficultyLevel(final float elapsed, final int sumDifLevel)
	{
//		float s = 0.015 * elapsed + 2;
		// TODO some fancy algorithm to define next 	
		return 1;
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

	public Array<Explosion> getExplosions() {
		return explosions;
	}

	public void setExplosions(Array<Explosion> explosions) {
		this.explosions = explosions;
	}
	
}
