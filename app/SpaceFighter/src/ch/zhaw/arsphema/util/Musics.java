package ch.zhaw.arsphema.util;


import com.badlogic.gdx.audio.Music;

/**
 * statische klasse für hintergrund musik
 * @author schtoeffel
 *
 */
public class Musics {
	public static final Music TRACK_01;
	public static final Music TRACK_02;
	public static final Music TRACK_03;
	public static final Music TRACK_04;
	public static final Music GAME_OVER;
	
	
	static {
		TRACK_01 = SpaceAssetManager.getInstance().get(Paths.TRACK_01, Music.class);
		TRACK_02 = SpaceAssetManager.getInstance().get(Paths.TRACK_02, Music.class);
		TRACK_03 = SpaceAssetManager.getInstance().get(Paths.TRACK_03, Music.class);
		TRACK_04 = SpaceAssetManager.getInstance().get(Paths.TRACK_04, Music.class);
		GAME_OVER = SpaceAssetManager.getInstance().get(Paths.GAME_OVER, Music.class);
		
		
	}
	
}

