package ch.zhaw.arsphema.model.shot;

import com.badlogic.gdx.graphics.Texture;

public class ShotFactory
{
    private static ShotFactory instance;
    
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
    public static Shot createShot(float x, float y, float width, float height, Texture texture)
    {
        return new Shot(x, y, width, height, texture);
    }

}