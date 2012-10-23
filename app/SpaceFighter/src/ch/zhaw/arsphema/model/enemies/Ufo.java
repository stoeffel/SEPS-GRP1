package ch.zhaw.arsphema.model.enemies;

import java.util.Random;

import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Ufo extends AbstractEnemy {
	private static final long serialVersionUID = -8679196122359337868L;
	private static final int COLLISION_DAMAGE = 1;
	private float xMovement = 10;
	private float yMovement = 3;
	protected float shotVelocity = -80;
	private float shootFrequency = 2;
	private Random shotRandom = new Random();
	
	
	public Ufo(float x, float y, float offsetX, float offsetY, float width, float height,
			TextureRegion texture, final int points) {
		super(x, y, offsetX, offsetY, width, height, texture, points, COLLISION_DAMAGE);
		resetShotFrequency();
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
		shootFrequency -= delta;
		if(shootFrequency < 0)
		{
			final Array<Shot> shot = ShotFactory.createShotInArray(x - shotVelocity * delta, y, shotVelocity, 0, true);
			resetShotFrequency();
			return shot;
		}
		return null;
	}
	
	private void resetShotFrequency(){
		shootFrequency = 1 + (5 * shotRandom.nextFloat());
	}

	@Override
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(textureRegion, x * ppuX, y * ppuY, width * ppuX, height * ppuY);
	}

}
