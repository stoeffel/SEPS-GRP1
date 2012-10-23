package ch.zhaw.arsphema.model.enemies;

import ch.zhaw.arsphema.model.AbstractSprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class AbstractEnemy extends AbstractSprite {
	private static final long serialVersionUID = 1L;
	protected int basePoints;
	protected float shotVelocity;
	protected float offsetY;
	protected float offsetX;


	public AbstractEnemy(float x, float y, float offsetX, float offsetY
			, float width, float height, TextureRegion texture, final int points) {
		super(x, y, width, height, texture);
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		basePoints = points;
	}

	public boolean lowerHealth(int damage) {
		health -= damage;
		return health <= 0;
	}

	public int getBasePoints() {
		return basePoints;
	}

	public void setBasePoints(int basePoints) {
		this.basePoints = basePoints;
	}
}
