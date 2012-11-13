package ch.zhaw.arsphema.model.enemies;

import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EnemyPaths{

	public static final float ZICK_ZACK_SPEED = 0.5f;
	public static Array<Vector2> ZICK_ZACK = new Array<Vector2>();;
	
	public static final float LURKING_SPEED = 0.6f;
	public static Array<Vector2> LURKING = new Array<Vector2>();;
	
	public static final float STRAIGHT_SAUCER_SPEED = 0.1f;
	public static final float STRAIGHT_ROCKET_SPEED = 0.25f;
	public static Array<Vector2> STRAIGHT = new Array<Vector2>();;
	
	
	static {
		// last path point has to be -Sizes.DEFAULT_WORLD_WIDTH
		
		ZICK_ZACK.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH/2 + Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT*3));
		ZICK_ZACK.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH/2 - Sizes.SHIP_WIDTH*2, Sizes.DEFAULT_WORLD_HEIGHT-4*Sizes.SHIP_HEIGHT ));
		ZICK_ZACK.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH/2 + Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT*3));
		ZICK_ZACK.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH/2 - Sizes.SHIP_WIDTH*2, Sizes.DEFAULT_WORLD_HEIGHT-4*Sizes.SHIP_HEIGHT ));
		ZICK_ZACK.add(new Vector2(-Sizes.DEFAULT_WORLD_WIDTH, Sizes.DEFAULT_WORLD_HEIGHT/3 ));

		LURKING.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH / 5 * 4, Sizes.DEFAULT_WORLD_HEIGHT / 5));
		LURKING.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH / 5 * 4, Sizes.DEFAULT_WORLD_HEIGHT / 5 * 4));
		
		STRAIGHT.add(new Vector2(-Sizes.DEFAULT_WORLD_WIDTH, Sizes.DEFAULT_WORLD_HEIGHT / 2));
	}
}
