package ch.zhaw.arsphema.model.enemies;

import java.util.Collections;
import java.util.List;

import ch.zhaw.arsphema.util.EnemyTextures;
import ch.zhaw.arsphema.util.Sizes;


public class EnemyFactory
{
	private static EnemyFactory instance;
    private EnemyFactory(){
    	/*Singleton*/
    }
    
    public static EnemyFactory getInstance()
    {
    	if(instance == null)
    		instance = new EnemyFactory();
    	return instance;
    }
    
    public static AbstractEnemy createUfo()
    {
    	//TODO y
    	Ufo ufo = new Ufo(Sizes.DEFAULT_WORLD_WIDTH, Sizes.DEFAULT_WORLD_HEIGHT / 2, 
    			Sizes.UFO_WIDTH, Sizes.UFO_HEIGHT, EnemyTextures.UFO);
    	return ufo;
    }
    
    private float dropEnemy = 5;
	public List<AbstractEnemy> dropEnemy(float delta) {
		dropEnemy -= delta;
		if(dropEnemy <= 0)
		{
			//TODO randomize (list?)
			dropEnemy = 5f;
			return Collections.singletonList(createUfo());
		}
		return Collections.emptyList();
	}

}