package ch.zhaw.arsphema.model.enemies;

import java.util.Random;

import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Saucer extends AbstractEnemy {
	private static final long serialVersionUID = -8679196122359337868L;
	private Random shotRandom = new Random();
	
	
	public Saucer(float x, float y, float offsetX, float offsetY, float width, float height,
			TextureRegion texture, final int points) {
		super(x, y, offsetX, offsetY, width, height, texture, points);
	}
	
	@Override
	public boolean move(float delta) {
//		x -= xMovement * delta;
//		y -= yMovement * delta;
//		if(y < height || y > Sizes.DEFAULT_WORLD_HEIGHT)
//			yMovement *= -1;
//		//TODO yMovement
//		return x < -width;
		return false;
	}

	@Override
	public Array<Shot> shoot(float delta) {
		return null;
	}
	
	@Override
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(textureRegion, x * ppuX, y * ppuY, width * ppuX, height * ppuY);
	}

}
