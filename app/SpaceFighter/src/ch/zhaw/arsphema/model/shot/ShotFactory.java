package ch.zhaw.arsphema.model.shot;

import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.utils.Array;

public class ShotFactory
{
    public enum Type {
    	STANDARD,
    	GREEN
    }
	static ShotFactory instance;
    
    
    private ShotFactory()
    {
    }
    
    public static void createInstance()
    {
        instance = new ShotFactory();
    }
    
    public static ShotFactory getInstance()
    {
    	if (instance == null){
    		createInstance();
    	}
        return instance;
    }

    //SHOT LASER
    private static Shot createShot(final float x, final float y, final float speed, final Type type, final boolean isEnemyShot)
    {
    	Shot shot = new Shot(x, y, isEnemyShot, speed);
    	if (type.equals(Type.STANDARD)) {
    		shot.setTextureRegion(TextureRegions.SHOT);
		} else if (type.equals(Type.GREEN)) {
			shot.setTextureRegion(TextureRegions.SHOT_GREEN);
		}
    	
    	
        return shot;
    }
    
    
    private static Array<Shot> reuseArray = new Array<Shot>();
    public static Array<Shot> createShotInArray(float x, float y, final float speed, Type type, boolean isEnemyShot)
    {
    	reuseArray.clear();
    	reuseArray.add(createShot(x, y, speed, type, isEnemyShot));
    	return reuseArray;
    }
    

}