package ch.zhaw.arsphema.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures {
	public static final Texture HERO;
	
	public static final Texture OVERLAY_SPRITE;
		
	// SHOTS
	public static final TextureRegion OVERHEATBAR;
	public static final Texture SHOT;

	// BACKGROUNDS
	public static final Texture BACKGROUND_STARS;
	
	static {
		HERO = new Texture(Gdx.files.internal(Paths.HERO));
		OVERLAY_SPRITE = new Texture(Gdx.files.internal(Paths.OVERLAY_SPRITE));
		OVERHEATBAR = new TextureRegion(new Texture(Gdx.files.internal(Paths.OVERHEATBAR)));
		SHOT = new Texture(Gdx.files.internal(Paths.SHOT));
		BACKGROUND_STARS = new Texture(Gdx.files.internal(Paths.BACKGROUND_STARS));
	}
}
