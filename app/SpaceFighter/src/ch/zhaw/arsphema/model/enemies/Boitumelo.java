package ch.zhaw.arsphema.model.enemies;

import java.util.Random;

import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;
import ch.zhaw.arsphema.model.shot.ShotFactory.Type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Boitumelo extends AbstractEnemy {
	private static final long serialVersionUID = -2931309233637206858L;
	private static final int COLLISION_DAMAGE = 1;
	protected float shotVelocity = -60;
	private float shootFrequency;
	private Random shotRandom = new Random();
	
	
	public Boitumelo(float x, float y, float offsetX, float offsetY, float width, float height,
			TextureRegion texture, final int points) {
		super(x, y, offsetX, offsetY, width, height, texture, points, COLLISION_DAMAGE);
		SHOT_FREQUENCY = 3;
		resetShotFrequency();
		health = 4;
	}
	
	@Override
	public boolean move(float delta) {
		return false;
	}

	@Override
	public Array<Shot> shoot(float delta) {
		shootFrequency -= delta;
		if(shootFrequency < 0)
		{
			Array<Shot> shots = new Array<Shot>();
			shots.add(ShotFactory.createShot(x - shotVelocity * delta, y + height / 2, shotVelocity, Type.STANDARD, true));
			shots.add(ShotFactory.createDiagonalShot(x, y, speed, Type.STANDARD, true, true));
			shots.add(ShotFactory.createDiagonalShot(x, y, speed, Type.STANDARD, true, false));
			resetShotFrequency();
			return shots;
		}
		return null;
	}
	
	private void resetShotFrequency(){
		shootFrequency = 1 + (SHOT_FREQUENCY * shotRandom.nextFloat());
	}

	@Override
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(textureRegion, x * ppuX, y * ppuY, width * ppuX, height * ppuY);
	}


}
