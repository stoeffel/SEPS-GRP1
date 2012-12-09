package ch.zhaw.arsphema.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyTextures {

	public static final TextureRegion BLOB;
	public static final TextureRegion UFO;
	public static final TextureRegion SAUCER_EASY;
	public static final TextureRegion SAUCER_MEDIUM;
	public static final TextureRegion BOITUMELO;
	public static final TextureRegion HIDAI;
	public static final TextureRegion UFO_BAD_BOY;
	public static final TextureRegion ROCK;

	static {
		UFO = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("dalekRed");
		SAUCER_EASY = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("saucer");
		SAUCER_MEDIUM = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("saucerGolden");
		BLOB = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("blob");
		BOITUMELO = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("rocket");
		HIDAI = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("dalek");
		UFO_BAD_BOY = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("eye");
		ROCK = SpaceAssetManager.getInstance().get(Paths.ATLAS, TextureAtlas.class).findRegion("rock");
	}
}
