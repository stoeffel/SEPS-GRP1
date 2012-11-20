package ch.zhaw.arsphema.model.enemies;

import java.util.Random;

import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Hidai extends AbstractEnemy {
	private static final long serialVersionUID = -8679196122359337868L;
	private static final int COLLISION_DAMAGE = 5;
	protected float shotVelocity = -60;
	protected float shootFrequencyA;
	protected float shootFrequencyB;
	private Random shotRandom = new Random();
	
	
	public Hidai(float x, float y, float offsetX, float offsetY, float width, float height,
			TextureRegion texture, final int points) {
		super(x, y, offsetX, offsetY, width, height, texture, points, COLLISION_DAMAGE,40);
		SHOT_FREQUENCY = 3;
		resetShotFrequency();
	}
	private boolean isShowHealthBar() {
		return true;
	}
	
	@Override
	public boolean move(float delta) {
		return false;
	}

	@Override
	public Array<Shot> shoot(float delta) {
		shootFrequencyA -= delta;
		final Array<Shot> shots = new Array<Shot>();
		if(shootFrequencyA < 0)
		{
			shots.add(ShotFactory.createShot(x - shotVelocity * delta, y, shotVelocity, ShotFactory.Type.STANDARD, true));
			shots.add(ShotFactory.createDiagonalShot(x - shotVelocity * delta, y, shotVelocity, ShotFactory.Type.STANDARD, true, true));
			shots.add(ShotFactory.createDiagonalShot(x - shotVelocity * delta, y, shotVelocity, ShotFactory.Type.STANDARD, true, false));
			resetShotFrequencyA();
		}
		shootFrequencyB -= delta;
		if(shootFrequencyB < 0)
		{
			shots.add(ShotFactory.createHeroDirectedShot(x - shotVelocity * delta, y, shotVelocity, ShotFactory.Type.STANDARD, true));
			resetShotFrequencyB();
		}
		return shots;
	}

	protected void resetShotFrequency(){
		resetShotFrequencyA();
		resetShotFrequencyB();
	}
	protected void resetShotFrequencyA(){
		shootFrequencyA = 1 + (SHOT_FREQUENCY * shotRandom.nextFloat());
	}
	protected void resetShotFrequencyB(){
		shootFrequencyB = 3 + (SHOT_FREQUENCY * shotRandom.nextFloat());
	}

	@Override
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(textureRegion, x * ppuX, y * ppuY, width * ppuX, height * ppuY);
	}

}
