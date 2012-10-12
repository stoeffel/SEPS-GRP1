package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class NavigationOverlay extends AbstractSprite {
	public static final int GAME = 1;
	public static final int START = 0;
	private static final int OVERLAY_ROWS = 2;
	protected Texture texture;
	private TextureRegion[] overlays;
	
	public NavigationOverlay(Texture texture) {
		super(0, 0, Sizes.DEFAULT_WORLD_WIDTH, Sizes.DEFAULT_WORLD_HEIGHT);
		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth(), texture.getHeight() / OVERLAY_ROWS);
		overlays = new TextureRegion[OVERLAY_ROWS];

        for (int i = 0; i < OVERLAY_ROWS; i++) {
    		overlays[i] = tmp[i][0];
        }
	}
	public TextureRegion getTexture(int which) {
		
		return overlays[which];
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void draw(SpriteBatch batch, float delta, float elapsed, float ppuX,
			float ppuY) {
		if (elapsed >= 5){ 
			batch.draw(this.getTexture(this.GAME), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		} else {
			batch.draw(this.getTexture(this.START), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		}
	}
	

}
