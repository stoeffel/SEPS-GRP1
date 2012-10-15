package ch.zhaw.arsphema.model;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.zhaw.arsphema.model.shot.Shot;

public class PowerUp extends AbstractSprite {
	public PowerUp(float x, float y, float width, float height) {
		super(x, y, width, height, null); //TODO textureregion!!!
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	enum PowerUpTypes {
		ShotSpeed, KillAAAALLLTheEnemies, SlowDownDude //TODO room for improvement...
	}

	@Override
	public boolean move(float delta) {
		//TODO
		return true;
	}

	@Override
	public List<Shot> shoot(float delta) {
		return null;
	}

	@Override
	public void draw(SpriteBatch batch, float delta, float elapsed, float ppuX, float ppuY) {
		batch.draw(textureRegion, x * ppuX, y * ppuY, width * ppuX, height * ppuY);
	}

}
