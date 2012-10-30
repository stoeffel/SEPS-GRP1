package ch.zhaw.arsphema.model.powerup;

import ch.zhaw.arsphema.controller.PowerUpManager;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ShotGreen extends AbstractPowerUp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3440934168948200706L;

	public ShotGreen(float x, float y, float width, float height,
			TextureRegion texture) {
		super(x, y, width, height, texture);
	}

	@Override
	public void doSomething(PowerUpManager pum) {
		pum.setShotGreen();
	}

	@Override
	public void undoSomething(PowerUpManager pum) {
		pum.setShotStd();
	}

}
