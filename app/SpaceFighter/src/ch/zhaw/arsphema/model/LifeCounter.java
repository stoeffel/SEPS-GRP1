package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Klasse fuer Lebenspunkte zaehler
 */
public class LifeCounter extends AbstractSprite {

	private static final long serialVersionUID = -3390110042341213551L;
	private static final int ROWS = 3;
	private static final int COLS = 1;
	private int lifes = 0;
	private int maxLifes = 0;
	private TextureRegion[] textures;
	private TextureRegion notUsed, used;

	/**
	 * konstruktor
	 */
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
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		// active lifes
		for (int i = 0; i < lifes; i++) {
			batch.draw(notUsed, ppuX*x+i*(width*ppuX+2), ppuY*y, width*ppuX, height*ppuY);
		}
		// used lifes
		for (int i = lifes; i < maxLifes; i++) {
			batch.draw(used, ppuX*x+i*(width*ppuX+2), ppuY*y, width*ppuX, height*ppuY);
		}

	}

	/**
	 * gibt anzahl lebenspunkte zurueck
	 * @return lifes die lebenspunkte
	 */
	public int getLifes() {
		return lifes;
	}
	/**
	 * setzt die lebenspunkte
	 * @param lifes die neuen lebenspunkte
	 */
	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
	/**
	 * gibt die maximalen lebenspunkte zurueck
	 * @return maxLifes die maximalen lebenspunkte
	 */
	public int getMaxLifes() {
		return maxLifes;
	}
	/**
	 * setzt die maximale lebenspunkte
	 * @param maxLifes die neuen maximalen lebenspunkte
	 */
	public void setMaxLifes(int maxLifes) {
		this.maxLifes = maxLifes;
	}

	/**
	 * addiert ein lebenspunkt zu den maximalen oder heilt ein lebenspunkt
	 */
	public void oneUp() {
		lifes++;
		if (lifes > maxLifes) {
			maxLifes++;
		}
	}

}
