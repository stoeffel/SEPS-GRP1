package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.model.Hero;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class InGameController extends AbstractController implements
		InputProcessor {

	private Hero hero;
	private float width, height;
	
	private static final int LEFT_TOP = 0, LEFT_BOTTOM = 1, RIGHT = 2;

	public InGameController(Hero hero) {
		this.hero = hero;
	}

	public void upPressed() {
		keys.put(IngameKeys.UP, true);
	}

	public void downPressed() {
		keys.put(IngameKeys.DOWN, true);
	}

	public void upReleased() {
		keys.put(IngameKeys.UP, false);
	}

	public void downReleased() {
		keys.put(IngameKeys.DOWN, false);
	}

	public void update(float delta) {
		
		processInput(delta);
	}

	public void changeMenuItem(float change) {
	}

	private void processInput(float change) {
		if (keys.get(IngameKeys.UP) && !keys.get(IngameKeys.DOWN)) {
			hero.moveUp();
		}
		if (!keys.get(IngameKeys.UP) && keys.get(IngameKeys.DOWN)) {
			hero.moveDown();
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.UP)
			upPressed();
		if (keycode == Keys.DOWN)
			downPressed();
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.DOWN)
			downReleased();
		if (keycode == Keys.UP)
			upReleased();
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (x <= width / 2) {
			if (y <= height / 2) {
				touchedRegion(LEFT_BOTTOM);
			} else {
				touchedRegion(LEFT_TOP);
			}
		} else {
			touchedRegion(RIGHT);
		}
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (x <= width / 2) {
			if (y <= height / 2) {
				touchedRegionEnd(LEFT_BOTTOM);
			} else {
				touchedRegionEnd(LEFT_TOP);
			}
		} else {
			touchedRegionEnd(RIGHT);
		}
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (x <= width / 2) {
			if (y <= height / 2) {
				touchedRegion(LEFT_BOTTOM);
			} else {
				touchedRegion(LEFT_TOP);
			}
		}
		return false;
	}
	/**
	 * 
	 * @param pos
	 */
	private void touchedRegion(int pos){
		switch (pos) {
		case LEFT_TOP:
			hero.moveDown();
			break;
		case LEFT_BOTTOM:
			hero.moveUp();
			break;
		case RIGHT:
			// TODO F I R E
			break;
		default:
			break;
		}
	}
	
	private void touchedRegionEnd(int pos){
		switch (pos) {
		case LEFT_TOP: case LEFT_BOTTOM:
			hero.stop();
			break;
		
		case RIGHT:
			// TODO F I R E
			break;
		default:
			break;
		}
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	public void resize(float width, float height) {
		this.width = width;
		this.height = height;
	}

}
