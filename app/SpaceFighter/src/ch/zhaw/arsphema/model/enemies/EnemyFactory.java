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
	
	public EnemyGroup createUfoGroup()
	{
		Array<AbstractEnemy> ufos = new Array<AbstractEnemy>();
		ufos.add(createUfo(0,0));
		ufos.add(createUfo(Sizes.UFO_WIDTH+2,Sizes.UFO_HEIGHT));
		ufos.add(createUfo(Sizes.UFO_WIDTH+2,-Sizes.UFO_HEIGHT));
		return new EnemyGroup(Sizes.DEFAULT_WORLD_WIDTH+Sizes.UFO_WIDTH,Sizes.DEFAULT_WORLD_HEIGHT/2,ufos);
	}
    
    public AbstractEnemy createUfo(float offset_x, float offset_y)
    {
    	//TODO y
    	Ufo ufo = new Ufo(Sizes.DEFAULT_WORLD_WIDTH+Sizes.UFO_WIDTH, Sizes.DEFAULT_WORLD_HEIGHT / 2, offset_x, offset_y, 
    			Sizes.UFO_WIDTH, Sizes.UFO_HEIGHT, EnemyTextures.UFO);
    	return ufo;
    }

}