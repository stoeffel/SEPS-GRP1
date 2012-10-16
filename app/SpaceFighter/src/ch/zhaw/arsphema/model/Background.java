package ch.zhaw.arsphema.model;


import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Background extends AbstractSprite {
	
	private static final long serialVersionUID = -7914525757302190003L;

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
	public Array<Shot> shoot(float delta) {
		return null; // not used

	}
	
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(textureRegion, ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
	}

}
