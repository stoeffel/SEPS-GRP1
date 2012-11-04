package ch.zhaw.arsphema.model.powerup;

import ch.zhaw.arsphema.controller.PowerUpManager;
import ch.zhaw.arsphema.model.Hero;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class OneUp extends AbstractPowerUp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1360335066820417584L;

	public OneUp(float x, float y, float width, float height,
			TextureRegion texture) {
		super(x, y, width, height, texture);
	}

	@Override
	public void doSomething(Hero hero) {
		hero.oneUp();
	}

	@Override
	public void undoSomething(Hero hero) {
		// nothing to undo
	}

}
