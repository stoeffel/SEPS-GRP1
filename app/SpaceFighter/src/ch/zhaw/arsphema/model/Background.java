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
		this.x -= this.speed * delta;
		if ( this.width < (-1)*this.x){
			this.x = this.width;
		}
		return true;
	}

	@Override
	public List<Shot> shoot(float delta) {
		return null; // not used

	}
	
	public void draw(SpriteBatch batch, float delta, float elapsed, float ppuX, float ppuY) {
		batch.draw(textureRegion, ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		this.move(delta);
	}

}
