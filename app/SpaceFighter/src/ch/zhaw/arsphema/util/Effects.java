package ch.zhaw.arsphema.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class Effects {
	public static final ParticleEffect EXPLOSION_1;
	public static final ParticleEffect JET;
	
	static {
		EXPLOSION_1 = new ParticleEffect();
		EXPLOSION_1.load(Gdx.files.internal("data/explosion/def"),Gdx.files.internal("data/explosion"));
		JET = new ParticleEffect();
		JET.load(Gdx.files.internal("data/jet/def"),Gdx.files.internal("data/jet"));
	}
}
