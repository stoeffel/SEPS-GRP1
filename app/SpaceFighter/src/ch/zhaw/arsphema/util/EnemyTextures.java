package ch.zhaw.arsphema.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyTextures {

	public static TextureRegion UFO;

	static {
		UFO = new TextureRegion(new Texture(Gdx.files.internal("images/badboys/ufo.png")));
	}
}
