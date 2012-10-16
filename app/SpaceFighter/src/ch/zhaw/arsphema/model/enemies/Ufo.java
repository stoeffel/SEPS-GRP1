package ch.zhaw.arsphema.model.enemies;

import java.util.Collections;
import java.util.List;

import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ufo extends AbstractEnemy {
	private float xMovement = 10;
	private float yMovement = 3;
	private float shotVelocity = 80;
	private float shootFrequency = 5;

	public Ufo(float x, float y, float width, float height,
			TextureRegion texture) {
		super(x, y, width, height, texture);
	}
	
	@Override
	public boolean move(float delta) {
		x -= xMovement * delta;
		y -= yMovement * delta;
		if(y < height || y > Sizes.DEFAULT_WORLD_HEIGHT)
			yMovement *= -1;
		//TODO yMovement
		return x < -width;
	}

	@Override
	public List<Shot> shoot(float delta) {
		shootFrequency -= delta;
		if(shootFrequency < 0)
		{
			final List<Shot> shot = Collections.singletonList(ShotFactory.getInstance().createShot(x - shotVelocity * delta, y, 0, true));
			shootFrequency = 3;
			return shot;
		}
		return Collections.emptyList();
	}

	@Override
	public void draw(SpriteBatch batch, float delta, float elapsed, float ppuX,
			float ppuY) {
		batch.draw(textureRegion, x * ppuX, y * ppuY, width * ppuX, height * ppuY);
	}

}
