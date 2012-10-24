package ch.zhaw.arsphema.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyTextures {

	public static final TextureRegion BLOB;
	public static TextureRegion UFO;
	public static TextureRegion SAUCER;

	static {
		UFO = new TextureRegion(new Texture(Gdx.files.internal(Paths.ENEMY_UFO)));
		SAUCER = new TextureRegion(new Texture(Gdx.files.internal(Paths.ENEMY_SAUCER)));
		BLOB = new TextureRegion(new Texture(Gdx.files.internal(Paths.ENEMY_BLOB)));
	}
}
