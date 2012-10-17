package ch.zhaw.arsphema.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	public static final Sound SHOT;
	public static final Sound DANGER;
	
	static {
		SHOT = Gdx.audio.newSound(Gdx.files.internal(Paths.SFX_SHOT));
		DANGER = Gdx.audio.newSound(Gdx.files.internal(Paths.SFX_DANGER));
	}
	
}
