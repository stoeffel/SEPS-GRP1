package ch.zhaw.arsphema.model.shot;

import ch.zhaw.arsphema.model.AbstractSprite;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.services.SoundManager.TyrianSound;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class OverHeatBar extends AbstractSprite {
	private static final long serialVersionUID = 7293341756258539914L;
	private static OverHeatBar instance;
	private float level;
	private boolean overheated;

	private OverHeatBar(float x, float y, TextureRegion texture) {
		super(x, y, Sizes.OVERHEATBAR_WIDTH, Sizes.OVERHEATBAR_HEIGHT, texture);
		this.level =100;
		this.overheated = false;
	}
	
	private static void createInstance()
    {
        instance = new OverHeatBar(3 * Sizes.DEFAULT_WORLD_WIDTH / 4 - Sizes.OVERHEATBAR_WIDTH / 2, Sizes.DEFAULT_WORLD_HEIGHT -2, TextureRegions.OVERHEATBAR);
    }
    
    public static OverHeatBar getInstance()
    {
    	if (instance == null){
    		createInstance();
    	}
        return instance;
    }

	@Override
	public boolean move(float delta) {
		return true;
	}

	@Override
	public Array<Shot> shoot(float delta) {
		return null;
	}

	@Override
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(textureRegion.getTexture(), x * ppuX, y * ppuY, width * ppuX, height * ppuY,0,0,(int)width/level,(int)height);
	}

	public float getLevel() {
		return level;
	}

	public void setLevel(float level) {
		this.level = level;
	}

	public void heat(float speed) {
		if (this.getLevel() > 0.1f) {
			this.setLevel(this.getLevel() - 1 * speed);
		} else {
			this.setLevel(0.1f);
			setOverheated(true);
		}
		
	}
	
	public void cool(float speed) {
		if (this.getLevel() < 100)
			this.setLevel(this.getLevel() + 1 * speed);
		else this.setLevel(100);
		setOverheated(false);
	}

	public boolean isOverheated() {
		return overheated;
	}

	private void setOverheated(boolean overheated) {
		if (overheated && !isOverheated()) {
			Services.getSoundManager().play(TyrianSound.DANGER, true);
		} else if (!overheated && isOverheated()) {
			Services.getSoundManager().stop(TyrianSound.DANGER);
		}
			
		this.overheated = overheated;
	}

}
