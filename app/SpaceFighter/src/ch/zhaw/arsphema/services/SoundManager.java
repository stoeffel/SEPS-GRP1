package ch.zhaw.arsphema.services;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * A service that manages the sound effects.
 */
public class SoundManager
    implements
        Disposable
{
    
    /**
     * The volume to be set on the sound.
     */
    private float volume = 1f;

    /**
     * Whether the sound is enabled.
     */
    private boolean enabled = true;

    private Array<Sound> soundsPlaying;

    /**
     * Creates the sound manager.
     */
    public SoundManager()
    {
    	soundsPlaying = new Array<Sound>();
    }

    /**
     * Plays the specified sound.
     * @param b 
     */
    public void play(
    		Sound sound, boolean loop )
    {
        // check if the sound is enabled
        if( ! enabled ) return;

        soundsPlaying.add(sound);
        
        // play the sound
        if (loop){
        	sound.loop();
        } else {
        	sound.play( volume );
        }
    }
    
    public void stop(
            Sound sound )
        {
            // check if the sound is enabled
            if( ! enabled ) return;
            sound.stop();
            soundsPlaying.removeValue(sound, false);
        }

    /**
     * Sets the sound volume which must be inside the range [0,1].
     */
    public void setVolume(
        float volume )
    {
        

        // check and set the new volume
        if( volume < 0 || volume > 1f ) {
            throw new IllegalArgumentException( "The volume must be inside the range: [0,1]" );
        }
        this.volume = volume;
    }

    /**
     * Enables or disabled the sound.
     */
    public void setEnabled(
        boolean enabled )
    {
        this.enabled = enabled;
    }

    
    /**
     * Disposes the sound manager.
     */
    public void dispose()
    {
        
        for( Sound sound : soundsPlaying ) {
            sound.stop();
            sound.dispose();
            soundsPlaying.removeValue(sound, false);
		}
	}

	public boolean isPlaying(Sound sound) {
		return soundsPlaying.indexOf(sound, false) >= 0;
	}

	public void stopSounds() {
        for( Sound sound : soundsPlaying ) {
            sound.stop();
            soundsPlaying.removeValue(sound, false);
		}
	}
}