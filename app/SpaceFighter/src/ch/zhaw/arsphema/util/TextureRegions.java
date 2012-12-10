package ch.zhaw.arsphema.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * statische klasse für texturen
 * @author schtoeffel
 *
 */
public class TextureRegions {
	public static final TextureRegion HERO;
	
	public static final TextureRegion OVERLAY_SPRITE;
		
	// SHOTS
	public static final TextureRegion OVERHEATBAR;
	public static final TextureRegion CROSSHAIR;
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
		SpaceAssetManager spam = SpaceAssetManager.getInstance();
		HERO = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("heroSprite");
		OVERLAY_SPRITE = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("overlaySprite");
		OVERHEATBAR = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("overheatbar");
		CROSSHAIR = new TextureRegion(spam.get(Paths.CROSSHAIR, Texture.class));
		DANGER = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("Danger");
		SHOT = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("standard");
		SHOT_GREEN = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("green");
		SHOT_BLUE = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("blue");
		SHOT_ULTIMATE = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("ultimate");
		BACKGROUND_STARS = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("stars");
		PLANETS = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("planets");
		ONE_UP = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("1up");
		POWERUP_SHOT_GREEN = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("shotGreen");
		POWERUP_ULTIMATE = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("killAll");
		CTRLS = spam.get(Paths.ATLAS, TextureAtlas.class).findRegion("controls");
	}
}
