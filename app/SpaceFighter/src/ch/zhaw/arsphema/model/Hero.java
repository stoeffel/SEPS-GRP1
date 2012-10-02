package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Hero extends AbstractSprite {
	
	public Hero(float x, float y, Texture texture) {
		super(x, y, Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT);
		health = 3;
		this.texture = texture;
	}

	public Hero(Rectangle rect) {
		super(rect);
		// TODO Auto-generated constructor stub
	}

	public Hero(float x, float y, float width, float height) {
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
