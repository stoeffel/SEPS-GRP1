package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.screen.GameScreen;
import ch.zhaw.arsphema.screen.GameScreen.GameState;

import com.badlogic.gdx.InputProcessor;


public class GameController extends AbstractController implements
InputProcessor {

	private GameScreen game;
	public GameController(GameScreen game) {
		this.game = game;
	}

	@Override
	public boolean keyDown(int keycode) {
		game.gameState = GameState.RESUMED;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		game.gameState = GameState.RESUMED;
		return false;
	}

	public void update(float delta) {
	}

}
