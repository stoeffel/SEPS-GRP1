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
		case 5:
			currentTrack = Musics.TRACK_05;
			break;
		case 6:
			currentTrack = Musics.TRACK_06;
			break;
		case 7:
			currentTrack = Musics.TRACK_07;
			break;
		case 8:
			currentTrack = Musics.TRACK_08;
			break;
		case 9:
			currentTrack = Musics.TRACK_09;
			break;
		case 10:
			currentTrack = Musics.TRACK_10;
			break;
		case 11:
			currentTrack = Musics.TRACK_11;
			break;
		case 12:
			currentTrack = Musics.TRACK_12;
			break;
		case 13:
			currentTrack = Musics.TRACK_13;
			break;
		case 14:
			currentTrack = Musics.TRACK_14;
			break;
		case 15:
			currentTrack = Musics.TRACK_15;
			break;
		case 16:
			currentTrack = Musics.TRACK_16;
			break;
		case 17:
			currentTrack = Musics.TRACK_17;
			break;
		case 18:
			currentTrack = Musics.TRACK_18;
			break;
		case 19:
			currentTrack = Musics.TRACK_19;
			break;
		case 20:
			currentTrack = Musics.TRACK_20;
			break;
		case 21:
			currentTrack = Musics.TRACK_21;
			break;
		case 22:
			currentTrack = Musics.TRACK_22;
			break;
		case 23:
			currentTrack = Musics.TRACK_23;
			break;
		case 24:
			currentTrack = Musics.TRACK_24;
			break;
		default:
			currentTrack = Musics.TRACK_01;
			break;
		}
		play(currentTrack);
	}
}
