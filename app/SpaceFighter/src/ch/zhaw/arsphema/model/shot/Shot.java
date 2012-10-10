package ch.zhaw.arsphema.model.shot;

import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.services.SoundManager.TyrianSound;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Shot extends Rectangle {

	private static final int COLS = 2;
	private static final int ROWS = 2;
	private int damage = 1;
	private float speed = 160;
	private Texture texture;
	private TextureRegion[] frames;
	private Animation animation;
	
	public Shot(float x, float y) {
		super(x,y, 1f, 1f);
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
	public TextureRegion getKeyFrame(float elapsed, boolean b) {
		return this.animation.getKeyFrame(elapsed,b);
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / COLS, texture.getHeight() / ROWS);
		frames = new TextureRegion[COLS * ROWS];

		int index = 0;
        for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                	
                        frames[index++] = tmp[i][j];
                }
        }
        animation = new Animation(1f, frames);
        animation.setPlayMode(Animation.LOOP_RANDOM);
	}
	public void draw(SpriteBatch batch, float delta, float elapsed, float ppuX,
			float ppuY) {
		batch.draw(this.getKeyFrame(elapsed, true), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		this.x += speed * delta;
	}
	
	

}
