package ch.zhaw.arsphema.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Musics {
	public static final Music AMBIENTE;
	
	static {
		AMBIENTE = Gdx.audio.newMusic(Gdx.files.internal(Paths.MUSIC_AMBIENTE));
		
	}
	
}

