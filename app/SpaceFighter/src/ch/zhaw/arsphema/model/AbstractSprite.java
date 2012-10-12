/**
 * 
 */
package ch.zhaw.arsphema.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author schtoeffel
 *
 */
public abstract class AbstractSprite extends Rectangle {
	
	protected int health;
	protected float shootingFrequency;
	protected float lastShot;
	protected Texture texture;
	protected TextureRegion textureRegion;
	
	protected float speed;
	
	
	/**
	 * ABSTRACT METHODES
	 */
	abstract public void move();
	abstract public void shoot();
	
	abstract public void draw(SpriteBatch batch, float delta, float elapsed, float ppuX, float ppuY);
	
	
	
	/**
	 * 
	 */
	public AbstractSprite() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rect
	 */
	public AbstractSprite(Rectangle rect) {
		super(rect);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public AbstractSprite(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	

	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public float getShootingFrequency() {
		return shootingFrequency;
	}
	public void setShootingFrequency(float shootingFrequency) {
		this.shootingFrequency = shootingFrequency;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}
	public void setTextureRegion(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
	}

}
