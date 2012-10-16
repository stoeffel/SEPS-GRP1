package ch.zhaw.arsphema.services;

public class Services {
	private static SoundManager soundManager;

	public static SoundManager getSoundManager() {
		return soundManager;
	}

	public static void setSoundManager(SoundManager soundManager) {
		Services.soundManager = soundManager;
	}
	
	
}
