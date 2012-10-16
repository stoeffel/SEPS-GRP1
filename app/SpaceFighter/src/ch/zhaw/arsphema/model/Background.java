package ch.zhaw.arsphema.model;


import java.util.List;

import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background extends AbstractSprite {
	
	
	public Background(TextureRegion texture, float x, float y, float width, float height) {
		super(x, y, width, height, texture);
		speed = 50;
	}

	@Override
	public boolean move(float delta) {
		x -= speed * delta;
		if ( x < -width){
			x = width;
		}
		return true;
	}

	@Override
	public List<Shot> shoot(float delta) {
		return null; // not used

	}
	
	public void draw(SpriteBatch batch, float delta, float elapsed, float ppuX, float ppuY) {
		batch.disableBlending();
		
		batch.draw(textureRegion, ppuX * x, ppuY * y, ppuX * width, ppuY * height);
		
		if (x > 0){
			batch.draw(textureRegion, ppuX * x-width*ppuX, ppuY * y, ppuX * width, ppuY * height);
		} else {
			batch.draw(textureRegion, ppuX * x+width*ppuX, ppuY * y, ppuX * width, ppuY * height);
		}
		batch.enableBlending();
		move(delta);
	}

}
