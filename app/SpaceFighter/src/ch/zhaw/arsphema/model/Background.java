package ch.zhaw.arsphema.model;


import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Background extends AbstractSprite {
	
	private static final long serialVersionUID = -7914525757302190003L;

	public Background(TextureRegion texture, float x, float y, float width, float height) {
		super(x, y, width, height, texture, 5);
	}

	/**
	 * bewegt die sterne und setzt die textur am ende des bildschirms sollte das ende erreicht werden
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
	 * zeichnet die textur zwei mal um ein endloses universum zu simulieren
	 */
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(textureRegion, ppuX * x, ppuY * y, ppuX * width, ppuY * height);
		if (x > 0) {
			batch.draw(textureRegion, ppuX * x-width*ppuX, ppuY * y, ppuX * width, ppuY * height);
		} else {
			batch.draw(textureRegion, ppuX * x+width*ppuX, ppuY * y, ppuX * width, ppuY * height);
		}
		
	}

}
