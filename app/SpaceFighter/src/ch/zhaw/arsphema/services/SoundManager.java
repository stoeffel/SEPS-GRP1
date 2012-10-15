package ch.zhaw.arsphema.services;
import ch.zhaw.arsphema.services.SoundManager.TyrianSound;
import ch.zhaw.arsphema.util.LRUCache;
import ch.zhaw.arsphema.util.LRUCache.CacheEntryRemovedListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

/**
 * A service that manages the sound effects.
 */
public class SoundManager
    implements
        CacheEntryRemovedListener<TyrianSound,Sound>,
        Disposable
{
    /**
     * The available sound files.
     */
    public enum TyrianSound
    {
        SHOT( "sounds/shot.wav" ),
        DANGER( "sounds/danger.wav" ),
        SPACE_AMBIENTE( "sounds/SpaceAmbiente.mp3" );

        private final String fileName;

        private TyrianSound(
            String fileName)
        {
            this.fileName = fileName;
        }

        public String getFileName()
        {
            return fileName;
        }
    }

    /**
     * The volume to be set on the sound.
     */
    private float volume = 1f;

    /**
     * Whether the sound is enabled.
     */
    private boolean enabled = true;

    /**
     * The sound cache.
     */
    private final LRUCache<TyrianSound,Sound> soundCache;

    /**
     * Creates the sound manager.
     */
    public SoundManager()
    {
        soundCache = new LRUCache<SoundManager.TyrianSound,Sound>( 10 );
        soundCache.setEntryRemovedListener( this );
    }

    /**
     * Plays the specified sound.
     * @param b 
     */
    public void play(
        TyrianSound sound, boolean loop )
    {
        // check if the sound is enabled
        if( ! enabled ) return;

        // try and get the sound from the cache
        Sound soundToPlay = soundCache.get( sound );
        if( soundToPlay == null ) {
            FileHandle soundFile = Gdx.files.internal( sound.getFileName() );
            soundToPlay = Gdx.audio.newSound( soundFile );
            soundCache.add( sound, soundToPlay );
        }

        // play the sound
        if (loop){
        	soundToPlay.loop( volume );
        } else {
        	soundToPlay.play( volume );
        }
    }
    
    public void stop(
            TyrianSound sound )
        {
            // check if the sound is enabled
            if( ! enabled ) return;

            // try and get the sound from the cache
            Sound soundToPlay = soundCache.get( sound );
            if( soundToPlay == null ) {
                FileHandle soundFile = Gdx.files.internal( sound.getFileName() );
                soundToPlay = Gdx.audio.newSound( soundFile );
                soundCache.add( sound, soundToPlay );
            }

            
            	soundToPlay.stop();
            
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

    // EntryRemovedListener implementation

    @Override
    public void notifyEntryRemoved(
        TyrianSound key,
        Sound value )
    {
        
        value.dispose();
    }

    /**
     * Disposes the sound manager.
     */
    public void dispose()
    {
        
        for( Sound sound : soundCache.retrieveAll() ) {
            sound.stop();
            sound.dispose();
		}
	}
}