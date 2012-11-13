package ch.zhaw.arsphema.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyTextures {

	public static final TextureRegion BLOB;
	public static final TextureRegion UFO;
	public static final TextureRegion SAUCER_EASY;
	public static final TextureRegion SAUCER_MEDIUM;
	public static final TextureRegion BOITUMELO;

	static {
		UFO = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.ENEMY_UFO, Texture.class));
		SAUCER_EASY = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.ENEMY_SAUCER_EASY, Texture.class));
		SAUCER_MEDIUM = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.ENEMY_SAUCER_MEDIUM, Texture.class));
		BLOB = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.ENEMY_BLOB, Texture.class));
		BOITUMELO = new TextureRegion(SpaceAssetManager.getInstance().get(Paths.ENEMY_BOITUMELO, Texture.class));
	}
}
