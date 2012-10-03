package ch.zhaw.arsphema.model.shot;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Shot extends Rectangle {

	private int damage = 1;
	private float speed = 88;
	private Texture texture;
	
	public Shot(float x, float y, float width, float height,Texture texture) {
		super(x,y, width, height);
		this.texture = texture;
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
	
	

}
