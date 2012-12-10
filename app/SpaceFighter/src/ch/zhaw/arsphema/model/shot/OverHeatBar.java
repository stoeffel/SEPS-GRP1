package ch.zhaw.arsphema.model.shot;

import ch.zhaw.arsphema.model.AbstractSprite;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.Sounds;
import ch.zhaw.arsphema.util.TextureRegions;
import ch.zhaw.arsphema.util.UiStyles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

/**
 * klasse fuer den ueberhitzungsbalken
 */
public class OverHeatBar extends AbstractSprite {
	private static final long serialVersionUID = 7293341756258539914L;
	private static final int COLS = 1;
	private static final int ROWS = 2;
	private static final int FRAME_COLS = 1;
	private static final int FRAME_ROWS = 2;
	private float level;
	private TextureRegion[][] regions;
	private TextureRegion border;
	private TextureRegion bar;
	private TextureRegion danger;
	private TextureRegion[] dangerFrames;
	private Animation dangerAnimation;
	private float stateTime;
	private TextureRegion crosshair;
	private Label crosshairLabel;

	/**
	 * konstruktor
	 */
	public OverHeatBar(final float x, final float y, final TextureRegion texture) {
		super(x, y, Sizes.OVERHEATBAR_WIDTH, Sizes.OVERHEATBAR_HEIGHT, texture);
		this.level = 0;
		regions = texture.split(texture.getRegionWidth() / COLS,
				textureRegion.getRegionHeight() / ROWS);
		bar = regions[1][0];
		border = regions[0][0];
		danger = TextureRegions.DANGER;
		TextureRegion[][] tmp = danger.split(danger.getRegionWidth()
				/ FRAME_COLS, danger.getRegionHeight() / FRAME_ROWS); // #10
		dangerFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				dangerFrames[index++] = tmp[i][j];
			}
		}
		dangerAnimation = new Animation(0.25f, dangerFrames); // #11
		stateTime = 0f;
		
		// crosshair
		crosshair = TextureRegions.CROSSHAIR;
		crosshairLabel = new Label("0", UiStyles.getCHLabelStyle(0));
	}

	@Override
	public boolean move(final float delta) {
		return true;
	}

	@Override
	public Array<Shot> shoot(final float delta) {
		return null;
	}

	@Override
	public void draw(final SpriteBatch batch, final float ppuX, final float ppuY) {
		batch.draw(bar, x * ppuX, y * ppuY, 0, 0, width * ppuX * level, height
				* ppuY, 1, 1, 90f);
		batch.draw(border, x * ppuX, y * ppuY, 0, 0, (width) * ppuX * 10,
				(height) * ppuY, 1, 1, 90f);
		batch.draw(crosshair, (x-width/2-Sizes.CROSSHAIR/2) * ppuX, (Sizes.CROSSHAIR/2) * ppuY, 0, 0, (Sizes.CROSSHAIR) * ppuX,
				(Sizes.CROSSHAIR) * ppuY, 1, 1, 0f);
		crosshairLabel.setStyle(UiStyles.getCHLabelStyle(ppuY));
        crosshairLabel.setText((int)getShootingFrequency() + " b/s");
        BitmapFont.TextBounds bounds = crosshairLabel.getStyle().font.getBounds(String.valueOf((int)getShootingFrequency() + " b/s"));
        crosshairLabel.x = (x-width/2-Sizes.CROSSHAIR) * ppuX - bounds.width;
        crosshairLabel.y = (Sizes.CROSSHAIR/2) * ppuY - bounds.height/4;
        crosshairLabel.draw(batch, 0.8f);
		if (level > 8) {
			stateTime += Gdx.graphics.getDeltaTime(); 
			batch.draw(dangerAnimation.getKeyFrame(stateTime, true), Sizes.DEFAULT_WORLD_WIDTH / 2 * ppuX
					- (ppuX * Sizes.DANGER_WIDTH / 2),
					Sizes.DEFAULT_WORLD_WIDTH / 5 * ppuY, Sizes.DANGER_WIDTH
							* ppuX, Sizes.DANGER_HEIGHT * ppuY);
		}
	}

	
    /**
     * kontrolliert die ueberhitzung und gibt die warnung aus
     * @param speed die geschwindigkeit
     * @return ob ueberhitzt wurde
     */
	public boolean heat(final float speed) {
		if (level < 10) {
			level += speed * Gdx.graphics.getDeltaTime();
			if (level > 8) {
				if (!Services.getSoundManager().isPlaying(Sounds.DANGER)) {
					Services.getSoundManager().play(Sounds.DANGER, true);
				}
			}
		} else {
			level = 8;
			return true;
		}
		return false;
	}

	/**
	 * lasst die waffe wieder abkuehlen
	 * @param speed die geschwindigkeit
	 */
	public void cool(final float speed) {
		if (level > 0) {
			level -= speed * Gdx.graphics.getDeltaTime();
			if (level < 8) {
				Services.getSoundManager().stop(Sounds.DANGER);
			}
		} else {
			level = 0;
		}
	}

	/**
	 * setzt die schussfrequenz
	 * @param shootingFrequency die schussfrequenz
	 */
	@Override
	public void setShootingFrequency(float shootingFrequency) {
		shootingFrequency *= 1000;
		shootingFrequency = 120 - shootingFrequency;
		super.setShootingFrequency(shootingFrequency);
	}

}
