/**
 * 
 */
package ch.zhaw.arsphema.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author schtoeffel
 *
 */
public abstract class AbstractSprite extends Rectangle {
	
	protected int health;
	protected int shootingFrequency;
	protected Texture texture;
	protected float speed;
	
	
	/**
	 * ABSTRACT METHODES
	 */
	abstract public void move();
	abstract public void shoot();
	
	
	
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
	public int getShootingFrequency() {
		return shootingFrequency;
	}
	public void setShootingFrequency(int shootingFrequency) {
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

}
