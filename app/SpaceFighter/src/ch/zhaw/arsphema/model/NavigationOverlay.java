package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class NavigationOverlay extends Rectangle{
	private static final long serialVersionUID = -1702457158486039622L;
	public static final int GAME = 1;
	public static final int START = 0;
	private static final int OVERLAY_ROWS = 2;
	protected Texture texture;
	private TextureRegion[] overlays;
	
	public NavigationOverlay(TextureRegion texture) {
		super(0, 0, Sizes.DEFAULT_WORLD_WIDTH, Sizes.DEFAULT_WORLD_HEIGHT);
		TextureRegion[][] tmp = texture.split(
				texture.getRegionWidth(), texture.getRegionHeight() / OVERLAY_ROWS);
		
		overlays = new TextureRegion[OVERLAY_ROWS];
		for (int i = 0; i < OVERLAY_ROWS; i++) {
    		overlays[i] = tmp[i][0];
        }
		this.width = Sizes.DEFAULT_WORLD_WIDTH;
		this.height = Sizes.DEFAULT_WORLD_HEIGHT;
	}
	public TextureRegion getTexture(int which) {
		TextureRegion tr = overlays[which];
		return tr;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void draw(SpriteBatch batch, float delta, float elapsed, float ppuX, float ppuY) {
		if (elapsed >= 5){ 
			batch.draw(getTexture(GAME).getTexture(), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		} else {
			batch.draw(getTexture(START).getTexture(), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		}
	}

}
