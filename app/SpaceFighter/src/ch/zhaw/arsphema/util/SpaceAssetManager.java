package ch.zhaw.arsphema.util;

import com.badlogic.gdx.assets.AssetManager;

/**
 * singleton assetmanager
 * lädt alle assets im loading screen
 * @author schtoeffel
 *
 */
public class SpaceAssetManager extends AssetManager {
	static SpaceAssetManager instance;
    
    
    private SpaceAssetManager()
    {
    }
    
    /** 
     * singleton
     */
    public static void createInstance()
    {
        instance = new SpaceAssetManager();
    }
    
    /**
     * getter für instance
     * @return
     */
    public static SpaceAssetManager getInstance()
    {
    	if (instance == null){
    		createInstance();
    	}
        return instance;
    }

}
