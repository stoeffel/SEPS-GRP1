package ch.zhaw.arsphema.util;

import com.badlogic.gdx.Gdx;
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
	
	static {
		HERO = new TextureRegion(new Texture(Gdx.files.internal(Paths.HERO)));
		OVERLAY_SPRITE = new TextureRegion(new Texture(Gdx.files.internal(Paths.OVERLAY_SPRITE)));
		OVERHEATBAR = new TextureRegion(new Texture(Gdx.files.internal(Paths.OVERHEATBAR)));
		SHOT = new TextureRegion(new Texture(Gdx.files.internal(Paths.SHOT)));
		BACKGROUND_STARS = new TextureRegion(new Texture(Gdx.files.internal(Paths.BACKGROUND_STARS)));
		PLANETS = new TextureRegion(new Texture(Gdx.files.internal(Paths.PLANETS)));
	}
}
