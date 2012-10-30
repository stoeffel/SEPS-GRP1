package ch.zhaw.arsphema.model.enemies;

import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EnemyPaths{

	private static Array<Array<Vector2>> PATHS;
	public static float ZICK_ZACK_SPEED = 0.1f;
	public static Array<Vector2> ZICK_ZACK;
	
	public static float STRAIGHT_SAUCER_SPEED = 0.02f;
	public static Array<Vector2> STRAIGHT = new Array<Vector2>();;
	
	
	static {
		PATHS = new Array<Array<Vector2>>();
		// last path point has to be -Sizes.DEFAULT_WORLD_WIDTH
		ZICK_ZACK = new Array<Vector2>();
		ZICK_ZACK.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH/2 + Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT*3));
		ZICK_ZACK.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH/2 - Sizes.SHIP_WIDTH*2, Sizes.DEFAULT_WORLD_HEIGHT-4*Sizes.SHIP_HEIGHT ));
		ZICK_ZACK.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH/2 + Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT*3));
		ZICK_ZACK.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH/2 - Sizes.SHIP_WIDTH*2, Sizes.DEFAULT_WORLD_HEIGHT-4*Sizes.SHIP_HEIGHT ));
		ZICK_ZACK.add(new Vector2(-Sizes.DEFAULT_WORLD_WIDTH, Sizes.DEFAULT_WORLD_HEIGHT/3 ));
		STRAIGHT.add(new Vector2(-Sizes.DEFAULT_WORLD_WIDTH, Sizes.DEFAULT_WORLD_HEIGHT / 2));
		PATHS.add(ZICK_ZACK);
		PATHS.add(STRAIGHT);
	}
	
	public static void resize(final float oldPpuX, final float oldPpuY, final float newPpuX, final float newPpuY){
		for(Array<Vector2> path : PATHS){
			for(Vector2 pathPoint : path){
				pathPoint.x /= oldPpuX * newPpuX;
				pathPoint.y /= oldPpuY * newPpuY;
			}
		}
		ZICK_ZACK_SPEED /= oldPpuX * newPpuX;
		STRAIGHT_SAUCER_SPEED /= oldPpuX * newPpuX;
	}
	
	public static void setSize(final float newPpuX, final float newPpuY){
		for(Array<Vector2> path : PATHS){
			for(Vector2 pathPoint : path){
				pathPoint.x *= newPpuX;
				pathPoint.y *= newPpuY;
			}
		}
		ZICK_ZACK_SPEED *= newPpuX;
		STRAIGHT_SAUCER_SPEED *= newPpuX;
	}
}
