package ch.zhaw.arsphema.util;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	public static final Sound SHOT;
	public static final Sound DANGER;
	public static final Sound EXPLOSION;
	public static final Sound HURT;
	public static final Sound BEEP;
	public static final Music AMBIENTE;
	
	static {
		SHOT = SpaceAssetManager.getInstance().get(Paths.SFX_SHOT, Sound.class);
		DANGER = SpaceAssetManager.getInstance().get(Paths.SFX_DANGER, Sound.class);
		EXPLOSION = SpaceAssetManager.getInstance().get(Paths.SFX_EXPLOSION, Sound.class);
		HURT = SpaceAssetManager.getInstance().get(Paths.SFX_HURT, Sound.class);
		BEEP = SpaceAssetManager.getInstance().get(Paths.SFX_BEEP, Sound.class);
		AMBIENTE = SpaceAssetManager.getInstance().get(Paths.MUSIC_AMBIENTE, Music.class);
	}
}
