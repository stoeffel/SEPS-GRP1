package ch.zhaw.arsphema.util;

import com.badlogic.gdx.assets.AssetManager;

public class SpaceAssetManager extends AssetManager {
	static SpaceAssetManager instance;
    
    
    private SpaceAssetManager()
    {
    }
    
    public static void createInstance()
    {
        instance = new SpaceAssetManager();
    }
    
    public static SpaceAssetManager getInstance()
    {
    	if (instance == null){
    		createInstance();
    	}
        return instance;
    }

}
