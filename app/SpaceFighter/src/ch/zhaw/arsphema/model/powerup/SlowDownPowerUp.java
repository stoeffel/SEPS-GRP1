package ch.zhaw.arsphema.model.powerup;

import ch.zhaw.arsphema.model.Hero;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SlowDownPowerUp extends AbstractPowerUp {

	private static final long serialVersionUID = -3440934168948200706L;

	public SlowDownPowerUp(float x, float y, float width, float height,
			TextureRegion texture) {
		super(x, y, width, height, texture);
	}

	@Override
	public boolean doSomething(Hero hero) {
		//TODO slow down everything
		// via game screen, every movement apart from hero and shots receives a elapsed / 2
		return true;
	}

	@Override
	public void undoSomething(Hero hero) {
	}

}
