package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.model.Hero;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

/**
 * alternative steuerung
 * @author schtoeffel
 *
 */
public class HeroController extends AbstractController implements
		InputProcessor {

	private Hero hero;
	private float width, height;

	private int leftPointer, rightPointer;

	private static final int LEFT_TOP = 0, LEFT_BOTTOM = 1, RIGHT = 2;

	/**
	 * konstruktor
	 * @param hero
	 */
	public HeroController(Hero hero) {
		this.hero = hero;
		leftPointer = -1;
		rightPointer = -1;
		for(IngameKeys ikeys : keys.keySet()){
			keys.put(ikeys, false);
		}
	}

	/**
	 * sets UP
	 */
	public void upPressed() {
		keys.put(IngameKeys.UP, true);
	}

	/**
	 * sets DOWN
	 */
	public void downPressed() {
		keys.put(IngameKeys.DOWN, true);
	}
	
	/**
	 * sets SHOT
	 */
	public void shotPressed() {
		keys.put(IngameKeys.SHOT, true);
	}

	/**
	 * releases SHOT
	 */
	public void shotReleased() {
		keys.put(IngameKeys.SHOT, false);
	}

	/**
	 * releases UP
	 */
	public void upReleased() {
		keys.put(IngameKeys.UP, false);
	}

	/**
	 * releases DOWN
	 */
	public void downReleased() {
		keys.put(IngameKeys.DOWN, false);
	}

	/**
	 * methode die aus dem renderer aufgerufen wird
	 * @param delta
	 */
	public void update(float delta) {
		processInput(delta);
	}

	/**
	 * ...
	 * @param change
	 */
	public void changeMenuItem(float change) {
	}

	private void processInput(float change) {
		if (keys.get(IngameKeys.UP) && !keys.get(IngameKeys.DOWN)) {
			hero.moveUp();
		}
		else if (!keys.get(IngameKeys.UP) && keys.get(IngameKeys.DOWN)) {
			hero.moveDown();
		}
		else {
			hero.stop();
		}
		hero.setFire(keys.get(IngameKeys.SHOT));
	}

	/**
	 * checkt ob UP gedrückt wurde
	 */
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.UP)
			upPressed();
		else if (keycode == Keys.DOWN)
			downPressed();
		else if (keycode == Keys.RIGHT || keycode == Keys.SPACE)
			shotPressed();

		return true;
	}

	/**
	 * checkt ob DOWN gedrückt wurde
	 */
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.DOWN)
			downReleased();
		else if (keycode == Keys.UP)
			upReleased();
		else if (keycode == Keys.RIGHT || keycode == Keys.SPACE)
			shotReleased();

		return true;
	}

	/**
	 * ...
	 */
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/**
	 * check ob ob down getoucht wurde
	 */
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (x <= width / 2) {
			leftPointer = pointer;
			if (y <= height / 2) {
				touchedRegion(LEFT_BOTTOM);
			} else {
				touchedRegion(LEFT_TOP);
			}
		} else {
			rightPointer = pointer;
			touchedRegion(RIGHT);
		}
		return true;
	}

	/**
	 * check ob ob up getoucht wurde
	 */
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (x <= width / 2) {
			leftPointer = -1;
			touchedRegionEnd(LEFT_BOTTOM);
			touchedRegionEnd(LEFT_TOP);
		} else {
			rightPointer = -1;
			touchedRegionEnd(RIGHT);
		}
		return false;
	}

	/**
	 * registiert touch drag
	 */
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (x <= width / 2 && pointer == leftPointer) {
			if (y <= height / 2) {
				touchedRegion(LEFT_BOTTOM);
				touchedRegionEnd(LEFT_TOP);
				
			} else {
				touchedRegion(LEFT_TOP);
				touchedRegionEnd(LEFT_BOTTOM);
			}
		} else if (x > width / 2 && pointer == rightPointer) {

		} else {
			if (x > width / 2) {
				rightPointer = -1;
				touchedRegionEnd(LEFT_TOP);
				touchedRegionEnd(LEFT_BOTTOM);
			}
			if (x <= width / 2) {
				rightPointer = -1;
				touchedRegionEnd(RIGHT);
			}
		}
		return false;
	}

	/**
	 * wo wurde getouchet
	 * @param pos
	 */
	private void touchedRegion(int pos) {
		switch (pos) {
		case LEFT_TOP:
			downPressed();
			break;
		case LEFT_BOTTOM:
			upPressed();
			break;
		case RIGHT:
			shotPressed();
			break;
		default:
			break;
		}
	}

	/**
	 * wo endete der touch
	 * @param pos
	 */
	private void touchedRegionEnd(int pos) {
		switch (pos) {
		case LEFT_TOP:
			downReleased();
			break;
		case LEFT_BOTTOM:
			upReleased();
			break;
		case RIGHT:
			shotReleased();
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

	/**
	 * sets width, height on resize
	 * @param width
	 * @param height
	 */
	public void resize(float width, float height) {
		this.width = width;
		this.height = height;
	}

}
