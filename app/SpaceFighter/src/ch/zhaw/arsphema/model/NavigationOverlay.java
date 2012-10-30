package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.screen.Renderer;

import com.badlogic.gdx.graphics.Texture;
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
		super(0, 0, Renderer.WORLD_WIDTH, Renderer.WORLD_HEIGHT);
		TextureRegion[][] tmp = TextureRegion.split(texture.getTexture(),
				texture.getTexture().getWidth(), texture.getTexture().getHeight() / OVERLAY_ROWS);
		
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
	

}
