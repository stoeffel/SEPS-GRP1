package ch.zhaw.arsphema.model.shot;

import ch.zhaw.arsphema.model.AbstractSprite;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.Sounds;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * klasse fuer einen schuss
 */
public class Shot extends AbstractSprite {
	private static final long serialVersionUID = 4015891462645256011L;
	private int damage = 1;
	
	protected boolean isEnemyShot = false;
	private boolean shouldBeRemoved = false;
	private float rotation;
	private float ySpeed = 0;
	public boolean destroyOnHit;
	
	/**
	 * konstruktor
	 * @param isEnemyShot ob schuss von gegner kommt
	 * @param speed geschwindigkeit
	 */
	public Shot(float x, float y, boolean isEnemyShot, final float speed) {
		super(x,y, Sizes.SHOT_WIDTH, Sizes.SHOT_HEIGHT, TextureRegions.SHOT, speed);
		this.isEnemyShot = isEnemyShot;
		Services.getSoundManager().play(Sounds.SHOT,false);
		rotation = 0f;
		if (isEnemyShot) rotation = 180f;
		destroyOnHit = true;
		
	}
	
	/**
	 * gibt an das schuss nicht tötet
	 */
	public void dontDestroyOnHit (){
		destroyOnHit = false;
	}
	
	/**
	 * gibt den schaden zurueck
	 * @return damage der schaden
	 */
	public int getDamage() {
		return damage;
	}
	/**
	 * setzt den zu verursachenden schaden
	 * @param damage der schaden
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	/**
	 * gibt geschwindigkeitt zurueck
	 */
	public float getSpeed() {
		return speed;
	}
	
	/**
	 * zeichnet den schuss 
	 */
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(textureRegion, ppuX * x, ppuY * y, 0, 0, ppuX * width, ppuY * height, 1, 1, rotation);
	}
	
	/**
	 * bewegt den schuss
	 */
	@Override
	public boolean move(float delta) {
		x += speed * delta;
		if (this.x > Sizes.DEFAULT_WORLD_WIDTH) {
			shouldBeRemoved = true;
		}
		y += ySpeed * delta;
		return true;
	}
	
	@Override
	public Array<Shot> shoot(float delta) {
		return null; //should never be called
	}
	
	/**
	 * gibt an ob der schuss entfernt werden soll
	 * @return shouldBeRemoved den zustand des schusses
	 */
	public boolean shouldBeRemoved() {
		
		return shouldBeRemoved;
	}
	/**
	 * setzt die geschwindigkeit
	 * @param speed die geschwindigkeit
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void updateSpeed(final float xSpeed, final boolean up) {
		ySpeed = speed - xSpeed;
		if(!up)
			ySpeed *= -1;
		speed = xSpeed;
	}
	

}
