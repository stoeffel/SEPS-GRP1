package ch.zhaw.arsphema.model.shot;

import ch.zhaw.arsphema.util.Textures;

public class ShotFactory
{
    public static final int STANDARD = 0;
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
    		loadTextures();
    		createInstance();
    	}
        return instance;
    }

    
    //SHOT LASER
    public static Shot createShot(float x, float y, int type, boolean isEnemyShot)
    {
    	//@stoeffel: type?
    	Shot shot = new Shot(x, y, isEnemyShot);
    	shot.setTexture(Textures.SHOT);
        return shot;
    }

    
	public static void loadTextures(){
		
	}

}