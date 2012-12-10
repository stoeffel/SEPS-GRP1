package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Klasse fuer kontrollers
 */
public class Controls extends AbstractSprite {

	
	private static final long serialVersionUID = -3390110042341213551L;
	private static final int ROWS = 3;
	private static final int COLS = 1;
	private TextureRegion[] textures;
	private TextureRegion center, up, down;
	public float tolerance;
	public static enum DIR {
		UP,DOWN,CENTER, STOP
	}
	
	public static DIR dir;

	/**
	 * konstruktor
	 */
	public Controls(float x, float y, float width, float height,
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
        up = textures[0];
        center = textures[1];
        down = textures[2];
        dir = DIR.STOP;
        tolerance = 0;

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
		float Y = Sizes.DEFAULT_WORLD_HEIGHT * ppuY - y - ( height*ppuY /2);
		
		if (tolerance == 0)
			setTolerance(Sizes.DEFAULT_WORLD_HEIGHT * ppuY / 30);
		
		switch (dir) {
		case UP:
			batch.draw(up, ppuX*x, Y, width*ppuX, height*ppuY);
			break;
		case DOWN:
			batch.draw(down, ppuX*x, Y, width*ppuX, height*ppuY);
			break;
		case CENTER:
			batch.draw(center, ppuX*x, Y, width*ppuX, height*ppuY);
			break;
		default: 
			break;
		}
		
	
	}

	/**
	 * setzt das zentrum
	 */
	public void setCenter() {
		dir = DIR.CENTER;
	}
	/**
	 * setzt hinauf
	 */
	public void setUp() {
		dir = DIR.UP;
	}
	/**
	 * setzt herunter
	 */
	public void setDown() {
		dir = DIR.DOWN;
	}
	/**
	 * setzt stop
	 */
	public void stop() {
		dir = DIR.STOP;
	}
	/**
	 * gibt die toleranz zurueck
	 * @return tolerance die toleranz
	 */
	public float getTolerance() {
		return tolerance;
	}
	/**
	 * setzt die toleranz
	 * @param tolerance die neue toleranz
	 */
	public void setTolerance(float tolerance) {
		this.tolerance = tolerance;
	}


}
