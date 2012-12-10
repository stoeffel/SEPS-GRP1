package ch.zhaw.arsphema.model.shot;

import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.utils.Array;

/**
 * fabrik fuer die schusse
 */
public class ShotFactory
{
	private static float heroY;
	private static Array<Shot> reuseArray = new Array<Shot>();
    
	/**
	 * schuss typen
	 */
    public enum Type {
    	STANDARD,
    	GREEN,
    	ULTIMATE, BLUE
    }
	static ShotFactory instance;
    
    
    private ShotFactory()
    {
    }
    /**
     * erstellt eine instanz der schussfabrik
     */
    public static void createInstance()
    {
        instance = new ShotFactory();
    }
    /**
     * gibt die instanz zurueck
     * @return instance die instanz der schussfabrik
     */
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
	/**
	 * erstellt einen geleiteten schuss 
	 * @param speed die geshwindigkeit de schusses
	 * @param type der typ des schusses
	 * @param isEnemyShot gibt an ob der schuss von einem gegner kommt
	 * @return shot der schuss
	 */
    public static Shot createHeroDirectedShot(final float x, final float y, final float speed, final Type type, final boolean isEnemyShot)
    {
    	Shot shot = createShot(x, y, speed, type, isEnemyShot);
    	//calculate direction
        shot.updateSpeed(calculateSpeed(speed, x, y - heroY) , true);
    	return shot;
    }

    /**
     * berechnet die geschwindigkeit
     * @param speed die geschwindigkeit
     * @return die neue geschwindigkeit
     */
    private static float calculateSpeed(final float speed, final float distanceX, final float distanceY){
    	return speed / (distanceX + distanceY) * distanceX;
    }
    /**
     * erstellt einen diagonalen schuss
     * @param speed die geschwindigkeit de schusses
     * @param type den typ des schusses
     * @param isEnemyShot gibt an ob schuss von gegner kommt
     * @return shot der schuss
     */
    public static Shot createDiagonalShot(final float x, final float y, final float speed, final Type type, final boolean isEnemyShot, final boolean up)
    {
    	final Shot shot = createShot(x, y, speed, type, isEnemyShot);
    	//calculate direction
    	float xSpeed = calculateSpeed(speed, Sizes.DEFAULT_WORLD_WIDTH, Sizes.DEFAULT_WORLD_HEIGHT / 2);
    	shot.updateSpeed(xSpeed, up);
    	return shot;
    }
    /**
     * erstellt einen schuss
     * @param speed die geschwindigkeit de schusses
     * @param type den typ des schusses
     * @param isEnemyShot gibt an ob schuss von gegner kommt
     * @return shot der schuss
     */
    public static Shot createShot(final float x, final float y, final float speed, final Type type, final boolean isEnemyShot)
    {
    	Shot shot = new Shot(x, y, isEnemyShot, speed);
    	shot.setHeight(Sizes.SHOT_HEIGHT);
    	shot.setWidth(Sizes.SHOT_WIDTH);
    	shot.setDamage(1);
    	if (type.equals(Type.STANDARD)) {
    		shot.setTextureRegion(TextureRegions.SHOT);
		} else if (type.equals(Type.GREEN)) {
			shot.setTextureRegion(TextureRegions.SHOT_GREEN);
		} else if (type.equals(Type.BLUE)) {
			shot.setTextureRegion(TextureRegions.SHOT_BLUE);
			shot.setHeight(Sizes.SHOT_HEIGHT*2);
			shot.setWidth(Sizes.SHOT_WIDTH*2);
		} else if (type.equals(Type.ULTIMATE)) {
			shot.setTextureRegion(TextureRegions.SHOT_ULTIMATE);
			shot.setHeight(Sizes.DEFAULT_WORLD_HEIGHT);
			shot.dontDestroyOnHit();
			shot.setDamage(10000);
		}
        return shot;
    }
    
    /**
     * erstellt ein schuss in einem array
     * @param speed die geschwindigkeit de schusses
     * @param type den typ des schusses
     * @param isEnemyShot gibt an ob schuss von gegner kommt
     * @return shot der schuss
     */
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