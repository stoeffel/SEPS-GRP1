package ch.zhaw.arsphema.model.shot;

import java.util.Random;

import ch.zhaw.arsphema.model.AbstractSprite;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.services.SoundManager.TyrianSound;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Shot extends AbstractSprite {

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
        Random ran = new Random();
        this.textureRegion = frames[ran.nextInt(ROWS*COLS)];
	}
	public void draw(SpriteBatch batch, float delta, float elapsed, float ppuX,
			float ppuY) {
		batch.draw(this.getTextureRegion(), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		this.x += speed * delta;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}
	
	

}
