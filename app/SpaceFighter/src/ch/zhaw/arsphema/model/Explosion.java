package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.Sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * klasse fuer die explosions animation
 */
public class Explosion {
	
	private Array<ParticleEmitter> emitters;
	
	private float x,y;

	private ParticleEmitter emitter;

	/**
	 * konstruktor
	 */
	public Explosion(float x, float y) {
		ParticleEffect effect = new ParticleEffect();
		// effect should be here and not in the Effects class
		effect.load(Gdx.files.internal("data/explosion2/def"),Gdx.files.internal("data/explosion2"));
		
		emitters = new Array<ParticleEmitter>(effect.getEmitters());

		effect.getEmitters().add(emitters.get(0));
		emitter = emitters.get(0);
		
		emitter.start();
		
		Services.getSoundManager().play(Sounds.EXPLOSION, false);
		
		this.x = x;
		this.y = y;
	}

	/**
	 * zeichnet die explosion
	 */
	public void draw(SpriteBatch batch, float ppuX, float ppuY){
		if (!isFinished()) {
			emitter.setPosition( x*ppuX,y*ppuY );
			emitter.draw(batch, Gdx.graphics.getDeltaTime());
		}
	}
	/**
	 * gibt an ob die explosion beendet ist
	 * @return isComplete der zustand der explosion
	 */
	public boolean isFinished(){
		return emitter.isComplete();
	}
}
