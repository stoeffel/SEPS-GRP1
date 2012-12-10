package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.model.Controls;
import ch.zhaw.arsphema.model.Hero;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

/**
 * Steuerung des Heros
 * @author schtoeffel
 *
 */
public class JoyStickController extends AbstractController implements
		InputProcessor {

	private Hero hero;
	private float width, height;
	private int leftPointer, rightPointer;

	private static final int LEFT = 1, RIGHT = 2;
	private class Pos {
		protected float x,y;
		public Pos(float x, float y) {
			this.x = x; this.y = y;
		}
		public boolean isLower(Pos pos, float tolerance) {
			if (pos.y < this.y - tolerance) {
				return true;
			}
			return false;
		}
		public boolean isHigher(Pos pos, float tolerance) {
			if (pos.y > this.y + tolerance) {
				return true;
			}
			return false;
		}
	}
	private Pos start, current;
	private Controls controls;

	/**
	 * Konstruktor
	 * @param hero
	 * @param controls
	 */
	public JoyStickController(Hero hero, Controls controls) {
		this.hero = hero;
		this.controls = controls;
		leftPointer = -1;
		rightPointer = -1;
		for(IngameKeys ikeys : keys.keySet()){
			keys.put(ikeys, false);
		}
	}

	/**
	 * up gedrückt
	 */
	public void upPressed() {
		keys.put(IngameKeys.UP, true);
	}

	/**
	 * down gedrückt
	 */
	public void downPressed() {
		keys.put(IngameKeys.DOWN, true);
	}
	
	/**
	 * schuss gedrückt
	 */
	public void shotPressed() {
		keys.put(IngameKeys.SHOT, true);
	}

	/**
	 * schuss nicht mehr gedrückt
	 */
	public void shotReleased() {
		keys.put(IngameKeys.SHOT, false);
	}

	/**
	 * up nicht mehr gedrückt
	 */
	public void upReleased() {
		keys.put(IngameKeys.UP, false);
	}

	/**
	 * down nicht mehr gedrückt
	 */
	public void downReleased() {
		keys.put(IngameKeys.DOWN, false);
	}

	/**
	 * methode die im rendererloop aufgerufen wird
	 * @param delta
	 */
	public void update(float delta) {
		processInput(delta);
	}

	public void changeMenuItem(float change) {
	}

	/**
	 * führt die befehle im hero aus
	 * @param change
	 */
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
	 * check ob herunter gedrückt wurde
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
	 * check ob hoch gedrückt wurde
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
	 * 
	 */
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/**
	 * registriert touch down events
	 */
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (x <= width / 2) { // LEFT
			leftPointer = pointer;
			start = new Pos(x,y);
			controls.setY(y);
			controls.setCenter();
			if (start.isLower(new Pos(0,hero.lastY),controls.tolerance)){
				controls.setDown();
				downPressed();
			} else if (start.isHigher(new Pos(0,hero.lastY),controls.tolerance)){
				controls.setUp();
				upPressed();
			}
		} else { // RIGHT
			rightPointer = pointer;
			shotPressed();
		}
		return true;
	}

	/**
	 * registriert touch up events
	 */
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (x <= width / 2) {
			leftPointer = -1;
			stopMoving();
		} else {
			rightPointer = -1;
			shotReleased();
		}
		return false;
	}

	/**
	 * registriert touchdrag events
	 */
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (x <= width / 2 && pointer == leftPointer) { // dragged on the left side of the screen
			current = new Pos(x,y);
			if (start != null) {
				if (start.isLower(current,controls.tolerance)) {
					upPressed();
					downReleased();
					controls.setUp();
				} else if (start.isHigher(current,controls.tolerance)) {
					downPressed();
					upReleased();
					controls.setDown();
				}
			}
		} else if (x > width / 2 && pointer == rightPointer) {
			// do nothing
		} else { // stop actions because touch drag over the middle of the screeen
			if (x > width / 2) {
				rightPointer = -1;
				stopMoving();
			}
			if (x <= width / 2) {
				rightPointer = -1;
				shotReleased();
			}
		}
		return false;
	}

	/**
	 * stoppt den hero
	 */
	private void stopMoving() {
		start = null;
		upReleased();
		downReleased();
		controls.stop();
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
