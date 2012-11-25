package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.model.Controls;
import ch.zhaw.arsphema.model.Hero;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

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

	public JoyStickController(Hero hero, Controls controls) {
		this.hero = hero;
		this.controls = controls;
		leftPointer = -1;
		rightPointer = -1;
		for(IngameKeys ikeys : keys.keySet()){
			keys.put(ikeys, false);
		}
	}

	public void upPressed() {
		keys.put(IngameKeys.UP, true);
	}

	public void downPressed() {
		keys.put(IngameKeys.DOWN, true);
	}
	
	public void shotPressed() {
		keys.put(IngameKeys.SHOT, true);
	}

	public void shotReleased() {
		keys.put(IngameKeys.SHOT, false);
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
		else if (!keys.get(IngameKeys.UP) && keys.get(IngameKeys.DOWN)) {
			hero.moveDown();
		}
		else {
			hero.stop();
		}
		hero.setFire(keys.get(IngameKeys.SHOT));
	}

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

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

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
				} else {
					downReleased();
					upReleased();
					controls.setCenter();
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
