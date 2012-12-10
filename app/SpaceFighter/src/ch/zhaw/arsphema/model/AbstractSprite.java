package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 * klasse fur abstrakte sprite bilder
 * @author schtoeffel
 *
 */
public abstract class AbstractSprite extends Rectangle {
	
	private static final long serialVersionUID = -5268095085491479865L;
	protected int health;
	protected float shootingFrequency;
	protected float lastShot;
	protected TextureRegion textureRegion;
	
	protected float speed;
	
	
	/*
	 * ABSTRACT METHODES
	 */
	/**
	 * @param delta
	 * @return true if still alive
	 */
	abstract public boolean move(final float delta);
	abstract public Array<Shot> shoot(final float delta);
	abstract public void draw(final SpriteBatch batch, final float ppux, final float ppuy);
	
	public AbstractSprite(final float x, final float y, final float width, 
			final float height, final TextureRegion texture, final float speed) {
		super(x, y, width, height);
		this.speed = speed;
		this.textureRegion = texture;
	}
	public AbstractSprite(final float x, final float y, final float width, 
			final float height, final TextureRegion texture) {
		super(x, y, width, height);
		this.textureRegion = texture;
	}
	
	
	/**
	 * gibt leben zurueck
	 * @return health die leben
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * setzt die leben
	 * @param health die neuen leben
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	/**
	 * gibt schussfrequenz zurueck
	 * @return shootingFrequency die schussfrequenz
	 */
	public float getShootingFrequency() {
		return shootingFrequency;
	}
	/**
	 * setzt die schussfrequenz
	 * @param shootingFrequency die neue schussfrequenz
	 */
	public void setShootingFrequency(float shootingFrequency) {
		this.shootingFrequency = shootingFrequency;
	}
	/**
	 * gibt die geschwindigkeit zurueck
	 * @return speed die geschwindigkeit
	 */
	public float getSpeed() {
		return speed;
	}
	/**
	 * setzt die geschwindigkeit
	 * @param speed die neue geschwindigkeit
	 */
	public void setSpeed(final float speed) {
		this.speed = speed;
	}
	/**
	 * gibt die textur region zurueck
	 * @return die textur region
	 */
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}
	/**
	 * setzt die textur region
	 * @param textureRegion die neue textur region
	 */
	public void setTextureRegion(final TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
	}
	
}
