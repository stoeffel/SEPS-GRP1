package ch.zhaw.arsphema.model.powerup;

import ch.zhaw.arsphema.model.Hero;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * das killAll powerup welches den bildschirm leer fegt
 */
public class KillEmAllPowerUp extends AbstractPowerUp {

	private static final long serialVersionUID = -3440934168948200706L;

	/**
	 * konstruktor
	 */
	public KillEmAllPowerUp(float x, float y, float width, float height,
			TextureRegion texture) {
		super(x, y, width, height, texture);
	}

	/**
	 * setzt das power up ein und tötet alle gegner auf dem bildschirm
	 */
	@Override
	public boolean doSomething(Hero hero) {
		hero.useTheUltimateWeapon();
		return false;
	}

	@Override
	public void undoSomething(Hero hero) {
	}

}
