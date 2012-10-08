package ch.zhaw.arsphema.model.shot;

import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.services.SoundManager.TyrianSound;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Shot extends Rectangle {

	private int damage = 1;
	private float speed = 160;
	private Texture texture;
	
	public Shot(float x, float y) {
		super(x,y, 0.5f, 0.5f);
		Services.getSoundManager().play(TyrianSound.SHOT);
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public void draw(SpriteBatch batch, float delta, float elapsed, float ppuX,
			float ppuY) {
		batch.draw(this.getTexture(), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		this.x += speed * delta;
	}
	
	

}
