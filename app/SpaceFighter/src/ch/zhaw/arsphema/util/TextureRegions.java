package ch.zhaw.arsphema.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegions {
	public static final TextureRegion HERO;
	
	public static final TextureRegion OVERLAY_SPRITE;
		
	// SHOTS
	public static final TextureRegion OVERHEATBAR;
	public static final TextureRegion SHOT;

	// BACKGROUNDS
	public static final TextureRegion BACKGROUND_STARS;

	public static final TextureRegion PLANETS;

	// POWER_UPS
	public static final TextureRegion ONE_UP;
	
	static {
		HERO = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.HERO, Texture.class));
		OVERLAY_SPRITE = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.OVERLAY_SPRITE, Texture.class));
		OVERHEATBAR = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.OVERHEATBAR, Texture.class));
		SHOT = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.SHOT, Texture.class));
		BACKGROUND_STARS = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.BACKGROUND_STARS, Texture.class));
		PLANETS = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.PLANETS, Texture.class));
		ONE_UP = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.ONE_UP, Texture.class));
	}
}
