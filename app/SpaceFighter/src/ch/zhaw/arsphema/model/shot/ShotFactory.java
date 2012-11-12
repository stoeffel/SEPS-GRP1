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
        shot.updateSpeed(calculateSpeed(speed, x, y - heroY) , true);
    	return shot;
    }

    private static float calculateSpeed(final float speed, final float distanceX, final float distanceY){
    	return speed / (distanceX + distanceY) * distanceX;
    }
    
    public static Shot createDiagonalShot(final float x, final float y, final float speed, final Type type, final boolean isEnemyShot, final boolean up)
    {
    	final Shot shot = createShot(x, y, speed, type, isEnemyShot);
    	//calculate direction
    	float xSpeed = calculateSpeed(speed, Sizes.DEFAULT_WORLD_WIDTH, Sizes.DEFAULT_WORLD_HEIGHT / 2);
    	shot.updateSpeed(xSpeed, up);
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