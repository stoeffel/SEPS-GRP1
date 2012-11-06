package ch.zhaw.arsphema.model.enemies;

import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class UfoBadBoy extends Ufo {

	private static final long serialVersionUID = -8679196122359337868L;
	protected float shotVelocity = -80;

	public UfoBadBoy(float x, float y, float offsetX, float offsetY,
			float width, float height, TextureRegion texture, int points) {
		super(x, y, offsetX, offsetY, width, height, texture, points);
		SHOT_FREQUENCY = 4;
	}
	
	@Override
	public Array<Shot> shoot(float delta) {
		shootFrequency -= delta;
		if(shootFrequency < 0)
		{
			final Array<Shot> shot = ShotFactory.createDirectedShotInArray(x + width, y, shotVelocity, ShotFactory.Type.STANDARD, true);
			resetShotFrequency();
			return shot;
		}
		return null;
	}

	@Override
	public boolean move(final float delta) {
		//TODO offset move
		return false;
	}

}
