package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;

import com.badlogic.gdx.graphics.Color;

public class GameScreen extends AbstractScreen {

	private static final float DEFAULT_WORLD_WIDTH = 96;
	private static final float DEFAULT_WORLD_HEIGHT = 84;
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis


	public GameScreen(MyGdxGame game) {
		super(game);
		
	}

	private Color c;

	@Override
	public void show() {
		
	}

	private void loadTextures() {
	}

	@Override
	public void render(float delta) {

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
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