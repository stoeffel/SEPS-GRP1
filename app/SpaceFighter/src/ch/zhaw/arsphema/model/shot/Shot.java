package ch.zhaw.arsphema.model.shot;

import java.util.Random;

import ch.zhaw.arsphema.model.AbstractSprite;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.services.SoundManager.TyrianSound;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Shot extends AbstractSprite {
	private static final long serialVersionUID = 4015891462645256011L;
	private static final int COLS = 2;
	private static final int ROWS = 2;
	private int damage = 1;
	private float speed = 160; //TODO in constructor? not every shot should be equally fast
	
	private float animationStateTime = 0f;
	private TextureRegion[] frames;
	private Animation animation;
	
	protected boolean isEnemyShot = false;
	private boolean shouldBeRemoved = false;
	
	
	public Shot(float x, float y, boolean isEnemyShot) {
		super(x,y, 1f, 1f, TextureRegions.SHOT);
		this.isEnemyShot = isEnemyShot;
		Services.getSoundManager().play(TyrianSound.SHOT,false);
		
		TextureRegion[][] tmp = textureRegion.split(textureRegion.getRegionWidth() / COLS, textureRegion.getRegionHeight() / ROWS);
		frames = new TextureRegion[COLS * ROWS];

		int index = 0;
        for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                	
                        frames[index++] = tmp[i][j];
                }
        }
        animation = new Animation(2f, frames);
        animation.setPlayMode(Animation.LOOP_RANDOM);
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


	public void draw(SpriteBatch batch, float ppuX,
			float ppuY) {
		animationStateTime += Gdx.graphics.getDeltaTime();
		batch.draw(animation.getKeyFrame(animationStateTime), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		if (this.x > Sizes.DEFAULT_WORLD_WIDTH) {
			shouldBeRemoved = true;
		}
	}
	
	@Override
	public boolean move(float delta) {
		x += speed * delta;
		return true;
	}
	
	@Override
	public Array<Shot> shoot(float delta) {
		return null; //should never be called
	}
	
	public boolean shouldBeRemoved() {
		
		return shouldBeRemoved;
	}
	

}
