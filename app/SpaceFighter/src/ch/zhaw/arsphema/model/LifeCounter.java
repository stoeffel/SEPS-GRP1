package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class LifeCounter extends AbstractSprite {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3390110042341213551L;
	private static final int ROWS = 3;
	private static final int COLS = 1;
	private int lifes = 0;
	private int maxLifes = 0;
	private TextureRegion[] textures;
	private TextureRegion notUsed, used;

	public LifeCounter(float x, float y, float width, float height,
			TextureRegion texture) {
		super(x, y, width, height, texture);
		TextureRegion[][] tmp = texture.split( 
				texture.getRegionWidth() / COLS, texture.getRegionHeight() / ROWS);
		textures = new TextureRegion[COLS * ROWS];

		int index = 0;
        for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                        textures[index++] = tmp[i][j];
                }
        }
        notUsed = textures[0];
        used = textures[1];

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
	public void draw(SpriteBatch batch) {
		// active lifes
		for (int i = 0; i < lifes; i++) {
			batch.draw(notUsed, x + i * (width +2), y, width, height);
		}
		// used lifes
		for (int i = lifes; i < maxLifes; i++) {
			batch.draw(used, x + i * (width + 2), y, width, height);
		}

	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public int getMaxLifes() {
		return maxLifes;
	}

	public void setMaxLifes(int maxLifes) {
		this.maxLifes = maxLifes;
	}

	public void oneUp() {
		lifes++;
		if (lifes > maxLifes) {
			maxLifes++;
		}
	}

	@Override
	public void resize(final float oldPpuX, final float oldPpuY, final float newPpuX, final float newPpuY){
		x = x / oldPpuX * newPpuX;
		y = y / oldPpuY * newPpuY;
		width = Sizes.SHIP_COUNTER_WIDTH;
		height = Sizes.SHIP_COUNTER_HEIGHT;
	}
}
