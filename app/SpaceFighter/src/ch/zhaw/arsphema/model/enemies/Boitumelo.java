package ch.zhaw.arsphema.model.enemies;

import java.util.Random;

import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * @author Rofus
 * Rocket monster
 */
public class Boitumelo extends AbstractEnemy {
	private static final long serialVersionUID = -8679196122359337868L;
	private static final int COLLISION_DAMAGE = 1;
	protected float shotVelocity = -80;
	protected float shootFrequency;
	private Random shotRandom = new Random();
	
	
	public Boitumelo(float x, float y, float offsetX, float offsetY, float width, float height,
			TextureRegion texture, final int points) {
		super(x, y, offsetX, offsetY, width, height, texture, points, COLLISION_DAMAGE,1);
		SHOT_FREQUENCY = 3;
		resetShotFrequency();
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
			final Array<Shot> shots = new Array<Shot>();
			shots.add(ShotFactory.createShot(x - shotVelocity * delta, y, shotVelocity, ShotFactory.Type.STANDARD, true));
			shots.add(ShotFactory.createDiagonalShot(x - shotVelocity * delta, y, shotVelocity, ShotFactory.Type.STANDARD, true, true));
			shots.add(ShotFactory.createDiagonalShot(x - shotVelocity * delta, y, shotVelocity, ShotFactory.Type.STANDARD, true, false));
			resetShotFrequency();
			return shots;
		}
		return null;
	}
	
	protected void resetShotFrequency(){
		shootFrequency = 1 + (SHOT_FREQUENCY * shotRandom.nextFloat());
	}

	@Override
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(textureRegion, x * ppuX, y * ppuY, width * ppuX, height * ppuY);
	}

	@Override
	public boolean isShowHealthBar() {
		// TODO Auto-generated method stub
		return false;
	}

}
