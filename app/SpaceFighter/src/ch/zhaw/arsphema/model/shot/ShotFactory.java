package ch.zhaw.arsphema.model.shot;

import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.utils.Array;

public class ShotFactory
{
	private static float heroY;
	private static Array<Shot> reuseArray = new Array<Shot>();
    
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
	public static Array<Shot> createDirectedShotInArray(float x, float y, final float speed, Type type, boolean isEnemyShot) {
		reuseArray.clear();
    	reuseArray.add(createHeroDirectedShot(x, y, speed, type, isEnemyShot));
    	return reuseArray;
    }
	
    public static Shot createHeroDirectedShot(final float x, final float y, final float speed, final Type type, final boolean isEnemyShot)
    {
    	Shot shot = createShot(x, y, speed, type, isEnemyShot);
    	//calculate direction
        shot.updateYSpeed((y - ShotFactory.heroY) / (Sizes.SHIP_X_POSITION - x));
    	return shot;
    }
    
    public static Shot createShot(final float x, final float y, final float speed, final Type type, final boolean isEnemyShot)
    {
    	Shot shot = new Shot(x, y, isEnemyShot, speed);
    	if (type.equals(Type.STANDARD)) {
    		shot.setTextureRegion(TextureRegions.SHOT);
		} else if (type.equals(Type.GREEN)) {
			shot.setTextureRegion(TextureRegions.SHOT_GREEN);
		}
        return shot;
    }
    
    
    public static Array<Shot> createShotInArray(float x, float y, final float speed, Type type, boolean isEnemyShot)
    {
    	reuseArray.clear();
    	reuseArray.add(createShot(x, y, speed, type, isEnemyShot));
    	return reuseArray;
    }

	public static void setHeroY(float heroY) {
		ShotFactory.heroY = heroY;
	}

}