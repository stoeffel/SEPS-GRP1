package ch.zhaw.arsphema.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Textures {
	public static final Texture HERO = new Texture(Gdx.files.internal(Paths.HERO));
	
	public static final Texture OVERLAY_SPRITE = new Texture(Gdx.files.internal(Paths.OVERLAY_SPRITE));
		
	// SHOTS
	public static final Texture SHOT = new Texture(Gdx.files.internal(Paths.SHOT));


	// BACKGROUNDS
	public static final Texture BACKGROUND_STARS = new Texture(Gdx.files.internal(Paths.BACKGROUND_STARS));
}
