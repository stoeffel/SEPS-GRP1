package ch.zhaw.arsphema.model.enemies;

import ch.zhaw.arsphema.util.EnemyTextures;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.utils.Array;


public class EnemyFactory
{
	private static EnemyFactory instance;
    private EnemyFactory(){/*Singleton*/}
    
    public static EnemyFactory getInstance()
    {
    	if(instance == null)
    		instance = new EnemyFactory();
    	return instance;
    }
	
	public Array<AbstractEnemy> createUfoGroup()
	{
		Array<AbstractEnemy> ufos = new Array<AbstractEnemy>();
		ufos.add(createUfo(0));
		ufos.add(createUfo(Sizes.UFO_WIDTH + 1));
		ufos.add(createUfo(Sizes.UFO_WIDTH*2 + 1));
		ufos.add(createUfo(Sizes.UFO_WIDTH*3 + 1));
		return ufos;
	}
    
    public AbstractEnemy createUfo(float x)
    {
    	//TODO y
    	Ufo ufo = new Ufo(Sizes.DEFAULT_WORLD_WIDTH + x, Sizes.DEFAULT_WORLD_HEIGHT / 2, 
    			Sizes.UFO_WIDTH, Sizes.UFO_HEIGHT, EnemyTextures.UFO);
    	return ufo;
    }

}