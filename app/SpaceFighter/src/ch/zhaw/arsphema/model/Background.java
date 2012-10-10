package ch.zhaw.arsphema.model;


import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Background extends AbstractSprite {
	
	
	public Background() {
		// TODO Auto-generated constructor stub
	}

	public Background(Rectangle rect) {
		super(rect);
		// TODO Auto-generated constructor stub
	}

	public Background(Texture texture, float x, float y, float width, float height) {
		super(x, y, width, height);
		speed = 50;
		this.setTexture(texture);
	}

	@Override
	public void move() {
		
	}
	
	public void move(float delta) {
		this.x -= this.speed * delta;
		if ( this.width < (-1)*this.x){
			this.x = this.width;
		}
	}

	@Override
	public void shoot() {
		// not used

	}
	
	public void draw(SpriteBatch batch, float delta, float elapsed, float ppuX, float ppuY) {
		
		batch.draw(this.getTexture(), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		this.move(delta);
	}

}
