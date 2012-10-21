package ch.zhaw.arsphema.services;

public class Services {
	private static SoundManager soundManager;
	private static MusicManager musicManager;

	public static SoundManager getSoundManager() {
		return soundManager;
	}

	public static void setSoundManager(SoundManager soundManager) {
		Services.soundManager = soundManager;
	}

	public static MusicManager getMusicManager() {
		return musicManager;
	}

	public static void setMusicManager(MusicManager musicManager) {
		Services.musicManager = musicManager;
	}

	public static void turnOffSound() {
		soundManager.dispose();
		musicManager.dispose();
	}
	
}
