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
    public static final int STANDARD = 0;
	private static ShotFactory instance;
    private static ArrayList<Shot> shots =new ArrayList<Shot>();
    
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
    public static Shot createShot(float x, float y, int type)
    {
    	Shot shot = new Shot(x, y);
    	shot.setTexture(Textures.STANDARD);
		shots.add(shot);
        return shot;
    }
    

	public static void draw(SpriteBatch batch, float delta, float elapsed,
			float ppuX, float ppuY) {
		ArrayList<Shot> shotsToRemove = new ArrayList<Shot>();
		for (Shot shot : shots) {
			shot.draw(batch,delta,elapsed,ppuX,ppuY);
			if (shot.shouldBeRemoved()){
				shotsToRemove.add(shot);
			}
			
		}
		for (Shot shot : shotsToRemove) {
			shots.remove(shot);
		}
	}
    
	public static void loadTextures(){
		Textures.STANDARD = new Texture(Gdx.files.internal(Paths.SHOT));
	}

}