package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class LifeCounter extends AbstractSprite {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3390110042341213551L;
	private int lifes = 0;

	public LifeCounter(float x, float y, float width, float height,
			TextureRegion texture) {
		super(x, y, width, height, texture);

	}

	@Override
	public boolean move(float delta) {
		
		return false;
	}

	@Override
	public Array<Shot> shoot(float delta) {
		
		return null;
	}

	@Override
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		for (int i = 0; i < lifes; i++) {
			batch.draw(textureRegion, ppuX*x+i*(width*ppuX+2), ppuY*y, width*ppuX, height*ppuY);
		}
		

	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

}
