package ch.zhaw.arsphema.model.enemies;

import com.badlogic.gdx.math.Rectangle;

import ch.zhaw.arsphema.model.AbstractSprite;

public abstract class AbstractEnemy extends AbstractSprite {

	public AbstractEnemy() {
		// TODO Auto-generated constructor stub
	}

	public AbstractEnemy(Rectangle rect) {
		super(rect);
		// TODO Auto-generated constructor stub
	}

	public AbstractEnemy(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub

	}

}
