package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.controller.InGameController;
import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.util.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends AbstractScreen {

	private static final float DEFAULT_WORLD_WIDTH = 96;
	private static final float DEFAULT_WORLD_HEIGHT = 84;
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis
	private Hero hero;
	private InGameController controller;


	public GameScreen(MyGdxGame game) {
		super(game);
		
	}


	@Override
	public void show() {
		loadTextures();
		controller = new InGameController(hero);
	}

	private void loadTextures() {
		hero = new Hero(50, 50, new Texture(Gdx.files.internal(Paths.HERO)));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		controller.update(delta);
		batch.begin();
//		batch.disableBlending();
		
		
		batch.draw(hero.getTexture(), hero.x, hero.y, ppuX * hero.width, ppuY * hero.height);

	    
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
			ppuX = (float) width / DEFAULT_WORLD_WIDTH;
			ppuY = (float) height / DEFAULT_WORLD_HEIGHT;
		
	}

	@Override
	public void hide() {
		
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}