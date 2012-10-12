package ch.zhaw.arsphema.model;

import java.util.List;

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
	public void move(float delta) {
		
	}

	@Override
	public List<Shot> shoot(float delta) {
		return null; //should never be called...
	}

}
