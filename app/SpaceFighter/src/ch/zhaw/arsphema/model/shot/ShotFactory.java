package ch.zhaw.arsphema.model.shot;

import java.util.ArrayList;


import ch.zhaw.arsphema.services.SoundManager;
import ch.zhaw.arsphema.util.Paths;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShotFactory
{
    public final int STANDARD = 0;
	private static ShotFactory instance;
    
    
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
    public Shot createShot(float x, float y, int type)
    {
    	Shot shot = new Shot(x, y);
        return shot;
    }
    
    
	public static void loadTextures(){
		
	}

}