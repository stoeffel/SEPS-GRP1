package ch.zhaw.arsphema.model.powerup;

import ch.zhaw.arsphema.model.Hero;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class KillEmAllPowerUp extends AbstractPowerUp {

	private static final long serialVersionUID = -3440934168948200706L;

	public KillEmAllPowerUp(float x, float y, float width, float height,
			TextureRegion texture) {
		super(x, y, width, height, texture);
	}

	@Override
	public void doSomething(Hero hero) {
		//TODO kill all enemies registred in enemymanager
		// could be done via game screen
	}

	@Override
	public void undoSomething(Hero hero) {
	}

}