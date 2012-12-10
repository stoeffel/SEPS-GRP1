package ch.zhaw.arsphema.model.powerup;

import ch.zhaw.arsphema.model.AbstractSprite;
import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * klasse fuer abstraktes power up
 */
public abstract class AbstractPowerUp extends AbstractSprite {
	/**
	 * konstruktor
	 */
	public AbstractPowerUp(float x, float y, float width, float height, TextureRegion texture) {
		super(x, y, width, height, texture, 20);
	}

	private static final long serialVersionUID = 1L;

	/**
	 * bewegt powerupo ueber den bildschirm
	 * @param delta redering delta
	 */
	@Override
	public boolean move(float delta) {
		x -= speed * delta;
		return true;
	}

	@Override
	public Array<Shot> shoot(float delta) {
		return null;
	}

	/**
	 * zeichnet das pwoerup
	 */
	@Override
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(textureRegion, x * ppuX, y * ppuY, width * ppuX, height * ppuY);
	}
	
	/**
	 * setzt das power up
	 * @param hero der spieler avatar
	 * @return 
	 */
	abstract public boolean doSomething(Hero hero);
	
	/**
	 * setzt das poewr up zurueck
	 * @param hero der spieler avatar
	 */
	abstract public void undoSomething(Hero hero);

}
