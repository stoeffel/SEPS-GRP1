package ch.zhaw.arsphema.util;


import com.badlogic.gdx.audio.Music;

public class Musics {
	public static final Music AMBIENTE;
	
	static {
		AMBIENTE = SpaceAssetManager.getInstance().get(Paths.MUSIC_AMBIENTE, Music.class);
		
	}
	
}

