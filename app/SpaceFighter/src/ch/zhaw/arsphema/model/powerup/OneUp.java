package ch.zhaw.arsphema.model.powerup;

import ch.zhaw.arsphema.model.Hero;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * das lebens powerup
 */
public class OneUp extends AbstractPowerUp {

	private static final long serialVersionUID = -1360335066820417584L;

	/**
	 * konstruktor
	 */
	public OneUp(float x, float y, float width, float height,
			TextureRegion texture) {
		super(x, y, width, height, texture);
	}

	/**
	 * addiert ein leben oder stellt ein leben wieder her
	 */
	@Override
	public boolean doSomething(Hero hero) {
		hero.oneUp();
		return false;
	}

	@Override
	public void undoSomething(Hero hero) {
		// nothing to undo
	}

}
