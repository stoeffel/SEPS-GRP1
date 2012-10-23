package ch.zhaw.arsphema.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	public static final Sound SHOT;
	public static final Sound DANGER;
	public static final Sound EXPLOSION;
	public static final Sound HURT;
	public static final Sound BEEP;
	
	static {
		SHOT = Gdx.audio.newSound(Gdx.files.internal(Paths.SFX_SHOT));
		DANGER = Gdx.audio.newSound(Gdx.files.internal(Paths.SFX_DANGER));
		EXPLOSION = Gdx.audio.newSound(Gdx.files.internal(Paths.SFX_EXPLOSION));
		HURT = Gdx.audio.newSound(Gdx.files.internal(Paths.SFX_HURT));
		BEEP = Gdx.audio.newSound(Gdx.files.internal(Paths.SFX_BEEP));
	}
	
}
