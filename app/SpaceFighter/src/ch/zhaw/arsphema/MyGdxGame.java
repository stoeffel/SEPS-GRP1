package ch.zhaw.arsphema;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;

	private Music spaceAmbienteMusic;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		spaceAmbienteMusic = Gdx.audio.newMusic(Gdx.files.internal("SpaceAmbiente.mp3"));
	      
	      // start the playback of the background music immediately
		spaceAmbienteMusic.setLooping(true);
		spaceAmbienteMusic.play();
	}


	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
