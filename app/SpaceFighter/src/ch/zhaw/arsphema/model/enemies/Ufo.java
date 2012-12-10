package ch.zhaw.arsphema.model.enemies;

import java.util.Random;

import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Ufo gegner, kommt in 3er gruppen und schiesst ein wenig 
 */
public class Ufo extends AbstractEnemy {
	private static final long serialVersionUID = -8679196122359337868L;
	private static final int COLLISION_DAMAGE = 1;
	protected float shotVelocity = -80;
	protected float shootFrequency;
	private Random shotRandom = new Random();
	
	
	/**
	 * Ufo  konstruktor
	 * @param points zu erhaltene punkte
	 */
	public Ufo(float x, float y, float offsetX, float offsetY, float width, float height,
			TextureRegion texture, final int points) {
		super(x, y, offsetX, offsetY, width, height, texture, points, COLLISION_DAMAGE,1);
		SHOT_FREQUENCY = 5;
		resetShotFrequency();
	}
	
	@Override
	public boolean move(float delta) {
		return false;
	}

	/**
	 * laesst gegner schiessen
	 */
	@Override
	public Array<Shot> shoot(float delta) {
		shootFrequency -= delta;
		if(shootFrequency < 0)
		{
			final Array<Shot> shot = ShotFactory.createShotInArray(x - shotVelocity * delta, y, shotVelocity, ShotFactory.Type.STANDARD, true);
			resetShotFrequency();
			return shot;
		}
		return null;
	}
	
	protected void resetShotFrequency(){
		shootFrequency = 1 + (SHOT_FREQUENCY * shotRandom.nextFloat());
	}

	/**
	 * zeichnet gegner
	 */
	@Override
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(textureRegion, x * ppuX, y * ppuY, width * ppuX, height * ppuY);
	}

	/**
	 * gibt an ob healthbar angezeigt wird
	 */
	@Override
	public boolean isShowHealthBar() {
		// TODO Auto-generated method stub
		return false;
	}

}
