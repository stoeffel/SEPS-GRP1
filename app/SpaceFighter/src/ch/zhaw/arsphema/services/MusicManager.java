package ch.zhaw.arsphema.services;


import java.util.Random;

import ch.zhaw.arsphema.util.Musics;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

/**
 * A service that manages the background music.
 * <p>
 * Only one music may be playing at a given time.
 */
public class MusicManager
    implements
        Disposable
{
    
    /**
     * Holds the music currently being played, if any.
     */
    private Music musicBeingPlayed;

    /**
     * The volume to be set on the music.
     */
    private float volume = 1f;

    /**
     * Whether the music is enabled.
     */
    private boolean enabled = true;

    /**
     * Creates the music manager.
     */
    public MusicManager()
    {
    }

    /**
     * Plays the given music (starts the streaming).
     * <p>
     * If there is already a music being played it is stopped automatically.
     */
    public void play(
    		Music music )
    {
        // check if the music is enabled
        if( ! enabled ) return;

        // stop any music being played
        
//        stop(); // removing this solves the restart problem (if menu-music is added, maybe it's needed again)

        // start streaming the new music
        musicBeingPlayed = music;
        musicBeingPlayed.setVolume( volume );
        musicBeingPlayed.setLooping( true );
        musicBeingPlayed.play();
    }

    /**
     * Stops and disposes the current music being played, if any.
     */
    public void stop()
    {
        if( musicBeingPlayed != null ) {
            
            musicBeingPlayed.stop();
            musicBeingPlayed.dispose();
        }
    }

    /**
     * Sets the music volume which must be inside the range [0,1].
     */
    public void setVolume(
        float volume )
    {
        

        // check and set the new volume
        if( volume < 0 || volume > 1f ) {
            throw new IllegalArgumentException( "The volume must be inside the range: [0,1]" );
        }
        this.volume = volume;

        // if there is a music being played, change its volume
        if( musicBeingPlayed != null ) {
            musicBeingPlayed.setVolume( volume );
        }
    }

    /**
     * Enables or disabled the music.
     */
    public void setEnabled(
        boolean enabled )
    {
        this.enabled = enabled;

        // if the music is being deactivated, stop any music being played
        if( ! enabled ) {
            stop();
        }
    }

    /**
     * Disposes the music manager.
     */
    public void dispose()
    {
        stop();
    }

	public void stopMusic() {
		if( musicBeingPlayed != null ) {
			musicBeingPlayed.stop();
		}
	}

	public void playRandom() {
		int rand = new Random().nextInt(19);
		Music currentTrack;
		System.out.println(rand);
		switch (rand) {
		case 1:
			currentTrack = Musics.TRACK_01;
			break;
		case 2:
			currentTrack = Musics.TRACK_02;
			break;
		case 3:
			currentTrack = Musics.TRACK_03;
			break;
		case 4:
			currentTrack = Musics.TRACK_04;
			break;
		
		default:
			currentTrack = Musics.TRACK_01;
			break;
		}
		play(currentTrack);
	}
}
