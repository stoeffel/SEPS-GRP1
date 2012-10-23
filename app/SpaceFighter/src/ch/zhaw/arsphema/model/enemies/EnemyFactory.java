package ch.zhaw.arsphema.model.enemies;

import java.util.Random;

import ch.zhaw.arsphema.util.EnemyTextures;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.utils.Array;


public class EnemyFactory
{
	private static int POINTS_UFO = 1;
	private static int POINTS_SAUCER = 2;
	private static EnemyFactory instance;
	private Random random = new Random();
	private final int AMOUNT_OF_EASY_GROUPS = 2;
	private final int AMOUNT_OF_MEDIUM_GROUPS = 1;
	private final int AMOUNT_OF_HARD_GROUPS = 1;
    private EnemyFactory(){/*Singleton*/}
    
    public static EnemyFactory getInstance()
    {
    	if(instance == null)
    		instance = new EnemyFactory();
    	return instance;
    }

	public EnemyGroup createUfoGroup(final float shootFrequency, final float y)
	{
		//TODO use shootfrequency
		Array<AbstractEnemy> ufos = new Array<AbstractEnemy>();
		ufos.add(createUfo(0,0));
		ufos.add(createUfo(Sizes.UFO_WIDTH+2,Sizes.UFO_HEIGHT));
		ufos.add(createUfo(Sizes.UFO_WIDTH+2,-Sizes.UFO_HEIGHT));
		return new EnemyGroup(Sizes.DEFAULT_WORLD_WIDTH + Sizes.UFO_WIDTH, y, ufos, 
				EnemyPaths.ZICK_ZACK, false, EnemyPaths.ZICK_ZACK_SPEED);
	}
    
    public AbstractEnemy createUfo(float offset_x, float offset_y)
    {
    	Ufo ufo = new Ufo(Sizes.DEFAULT_WORLD_WIDTH+Sizes.UFO_WIDTH, Sizes.DEFAULT_WORLD_HEIGHT / 2, offset_x, offset_y, 
    			Sizes.UFO_WIDTH, Sizes.UFO_HEIGHT, EnemyTextures.UFO, POINTS_UFO);
    	return ufo;
    }

	public EnemyGroup createSaucerGroup(final float shootFrequency, final float y, final int amountOfEnemies)
	{
		//TODO use shootfrequency
		Array<AbstractEnemy> saucers = new Array<AbstractEnemy>();
		final float groupHeight = amountOfEnemies / 2 * Sizes.SAUCER_HEIGHT;
		for(int i = 0; i < amountOfEnemies; i++){
			saucers.add(createSaucer(i * Sizes.SAUCER_WIDTH * 0.5f, random.nextInt((int)groupHeight)));
		}
		return new EnemyGroup(Sizes.DEFAULT_WORLD_WIDTH + Sizes.UFO_WIDTH, y, saucers, 
				EnemyPaths.STRAIGHT, true, EnemyPaths.STRAIGHT_SAUCER_SPEED);
	}
	
    public AbstractEnemy createSaucer(final float offsetX, final float offsetY)
    {
    	final Saucer saucer = new Saucer(Sizes.DEFAULT_WORLD_WIDTH + Sizes.UFO_WIDTH, 
    			Sizes.DEFAULT_WORLD_HEIGHT / 2, 
    			offsetX, offsetY, 
    			Sizes.SAUCER_WIDTH, Sizes.SAUCER_HEIGHT, EnemyTextures.SAUCER, POINTS_SAUCER);
    	return saucer;
    }

	public EnemyGroup createGroupByDifficultyLevel(final int nextGroupDiffLevel, final float elapsed) {
		// a group should not be wider than a screen width
		switch (nextGroupDiffLevel)
		{
		case 0:
			return createEasyGroup(elapsed);
		case 1:
			return createMediumGroup(elapsed);
		case 2:
			return createHardGroup(elapsed);
		}
		throw new IllegalStateException();
	}

	private EnemyGroup createEasyGroup(final float elapsed) {
		final int nextGroupId = random.nextInt(AMOUNT_OF_EASY_GROUPS);
		switch (nextGroupId){
		case 0:
			return createUfoGroup(elapsed, random.nextFloat() * Sizes.DEFAULT_WORLD_HEIGHT);
		case 1:
			return createSaucerGroup(elapsed, random.nextFloat() * Sizes.DEFAULT_WORLD_HEIGHT, 20);
		}
		throw new IllegalStateException();
	}
	
	private EnemyGroup createMediumGroup(final float elapsed) {
		final int nextGroupId = random.nextInt(AMOUNT_OF_MEDIUM_GROUPS);
		switch (nextGroupId){
		case 0:
			return createUfoGroup(elapsed, random.nextFloat() * Sizes.DEFAULT_WORLD_HEIGHT);
		}
		throw new IllegalStateException();
	}
	
	private EnemyGroup createHardGroup(final float elapsed) {
		final int nextGroupId = random.nextInt(AMOUNT_OF_HARD_GROUPS);
		switch (nextGroupId){
		case 0:
			return createUfoGroup(elapsed, random.nextFloat() * Sizes.DEFAULT_WORLD_HEIGHT);
		}
		throw new IllegalStateException();
	}

}