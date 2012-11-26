package ch.zhaw.arsphema.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegions {
	public static final TextureRegion HERO;
	
	public static final TextureRegion OVERLAY_SPRITE;
		
	// SHOTS
	public static final TextureRegion OVERHEATBAR;
	public static final TextureRegion DANGER;
	public static final TextureRegion SHOT;
	public static final TextureRegion SHOT_GREEN;
	public static final TextureRegion SHOT_ULTIMATE;
	public static final TextureRegion SHOT_BLUE;

	// BACKGROUNDS
	public static final TextureRegion BACKGROUND_STARS;

	public static final TextureRegion PLANETS;

	// POWER_UPS
	public static final TextureRegion ONE_UP;
	public static final TextureRegion POWERUP_SHOT_GREEN;
	public static final TextureRegion POWERUP_ULTIMATE;

	public static final TextureRegion CTRLS;



	
	
	static {
		HERO = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("heroSprite");
		OVERLAY_SPRITE = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("overlaySprite");
		OVERHEATBAR = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("overheatbar");
		DANGER = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("Danger");
		SHOT = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("standard");
		SHOT_GREEN = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("green");
		SHOT_BLUE = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("blue");
		SHOT_ULTIMATE = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("ultimate");
		BACKGROUND_STARS = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("stars");
		PLANETS = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("planets");
		ONE_UP = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("1up");
		POWERUP_SHOT_GREEN = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("shotGreen");
		POWERUP_ULTIMATE = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("killAll");
		CTRLS = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("controls");
	}
}
