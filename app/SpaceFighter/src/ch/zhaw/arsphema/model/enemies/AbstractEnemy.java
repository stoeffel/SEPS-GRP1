package ch.zhaw.arsphema.model.enemies;

import java.util.List;

import ch.zhaw.arsphema.model.AbstractSprite;
import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class AbstractEnemy extends AbstractSprite {
	private static final long serialVersionUID = 1L;
	protected int basePoints;


	public AbstractEnemy(float x, float y, float width, float height, TextureRegion texture) {
		super(x, y, width, height, texture);
	}

	@Override
	abstract public void move(float delta);
	@Override
	abstract public List<Shot> shoot(float delta);

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
