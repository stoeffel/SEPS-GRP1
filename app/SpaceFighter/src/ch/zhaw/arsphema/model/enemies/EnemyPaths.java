package ch.zhaw.arsphema.model.enemies;

import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EnemyPaths{
	
	public static Array<Vector2>ZICK_ZACK;
	
	
	static {
		ZICK_ZACK = new Array<Vector2>();
		ZICK_ZACK.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH/2 + Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT*3));
		ZICK_ZACK.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH/2 - Sizes.SHIP_WIDTH*2, Sizes.DEFAULT_WORLD_HEIGHT-4*Sizes.SHIP_HEIGHT ));
		ZICK_ZACK.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH/2 + Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT*3));
		ZICK_ZACK.add(new Vector2(Sizes.DEFAULT_WORLD_WIDTH/2 - Sizes.SHIP_WIDTH*2, Sizes.DEFAULT_WORLD_HEIGHT-4*Sizes.SHIP_HEIGHT ));
		ZICK_ZACK.add(new Vector2(-Sizes.DEFAULT_WORLD_WIDTH/3, Sizes.DEFAULT_WORLD_HEIGHT/3 ));
		
	}
}
