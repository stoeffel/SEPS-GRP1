package ch.zhaw.arsphema.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyTextures {

	public static final TextureRegion BLOB;
	public static TextureRegion UFO;
	public static TextureRegion SAUCER;

	static {
		UFO = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.ENEMY_UFO, Texture.class));
		SAUCER = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.ENEMY_SAUCER, Texture.class));
		BLOB = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.ENEMY_BLOB, Texture.class));
	}
}
