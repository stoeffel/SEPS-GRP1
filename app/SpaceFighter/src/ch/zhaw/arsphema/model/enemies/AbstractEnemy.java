package ch.zhaw.arsphema.model.enemies;

import ch.zhaw.arsphema.model.AbstractSprite;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public abstract class AbstractEnemy extends AbstractSprite {
	private static final long serialVersionUID = 1L;
	protected static float SHOT_FREQUENCY;
	protected int basePoints;
	protected float shotVelocity;
	protected float offsetY;
	protected float offsetX;
	protected final int collisionDamage;
	private float maxHealth;


	public AbstractEnemy(float x, float y, float offsetX, float offsetY
			, float width, float height, TextureRegion texture, final int points, 
			final int collisionDamage, int health) {
		super(x, y, width, height, texture);
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.collisionDamage = collisionDamage;
		basePoints = points;
		maxHealth = health;
		this.health = health;
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

	public int getCollisionDamage(){
		return collisionDamage;
	}
	
	public void drawHealthBar(ShapeRenderer shapeRenderer, float ppuX, float ppuY){

		if(isShowHealthBar())
		{
			shapeRenderer.begin(ShapeType.FilledRectangle);
			//border
			shapeRenderer.setColor(Color.YELLOW);
			shapeRenderer.filledRect(x * ppuX, (Sizes.ENEMY_HEALTHBAR_DISTANCE + y + height) * ppuY - 1, 
					width * ppuX, 1);
			shapeRenderer.filledRect(x * ppuX, (Sizes.HEALTHBAR_HEIGHT + Sizes.ENEMY_HEALTHBAR_DISTANCE + y + height) * ppuY, 
					width * ppuX, 1);
			//Total Health
			shapeRenderer.setColor(Color.RED);
			shapeRenderer.filledRect(x * ppuX, (Sizes.ENEMY_HEALTHBAR_DISTANCE + y + height) * ppuY, 
					width * ppuX, Sizes.HEALTHBAR_HEIGHT * ppuY);
			//health remaining
			shapeRenderer.setColor(Color.GREEN);
			shapeRenderer.filledRect(x * ppuX, (Sizes.ENEMY_HEALTHBAR_DISTANCE + y + height) * ppuY, 
					(float)(width * ppuX * getHealth() / getMaxHealth()), Sizes.HEALTHBAR_HEIGHT * ppuY);

			shapeRenderer.end();
		}
	}

	private float getMaxHealth() {
		return maxHealth;
	}

	private boolean isShowHealthBar() {
		return false;
	
	}
}
