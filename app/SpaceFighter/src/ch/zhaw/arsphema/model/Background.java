package ch.zhaw.arsphema.model;


import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Background extends AbstractSprite {
	
	private static final long serialVersionUID = -7914525757302190003L;

	public Background(TextureRegion texture, float x, float y, float width, float height) {
		super(x, y, width, height, texture, 1);
	}

	/**
	 * moves the stars and places the texture at the of the screen if it reaches the end
	 */
	@Override
	public boolean move(float delta) {
		x -= speed * delta;
		if ( x < -width){
			x = width;
		}
		return true;
	}

	@Override
	public Array<Shot> shoot(float delta) {return null;}
	
	/**
	 * draws the texture two times, to simulate a endless universe
	 */
	public void draw(SpriteBatch batch) {
		batch.draw(textureRegion, x, y, width, height);
		if (x > 0) {
			batch.draw(textureRegion, x - width, y, width, height);
		} else {
			batch.draw(textureRegion, x + width, y, width, height);
		}
		
	}

}
