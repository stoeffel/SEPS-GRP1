package ch.zhaw.arsphema.model.shot;

import ch.zhaw.arsphema.util.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ShotFactory
{
    public static final int STANDARD = 0;
	private static ShotFactory instance;
    
    private static class Textures {

		public static Texture STANDARD = null;
    	
    }
    
    private ShotFactory()
    {
    }
    
    public static void createInstance()
    {
        new ShotFactory();
    }
    
    public static ShotFactory getInstance()
    {
        return instance;
    }

    
    //SHOT LASER
    public static Shot createShot(float x, float y, int type, boolean isEnemyShot)
    {
    	//@stoeffel: type?
    	Shot shot = new Shot(x, y, isEnemyShot);
    	shot.setTexture(Textures.STANDARD);
        return shot;
    }

    
	public static void loadTextures(){
		Textures.STANDARD = new Texture(Gdx.files.internal(Paths.SHOT));
	}

}