package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Hero extends AbstractSprite {
	
	private static final int UP = 1;
	private static final int DOWN = -1;
	private boolean stopped = true;
	private boolean movingUp = false;
	private boolean movingDown = false;

	public Hero(float x, float y, Texture texture) {
		super(x, y, Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT);
		health = 3;
		speed = 44;
		this.texture = texture;
	}

	public Hero(Rectangle rect) {
		super(rect);
		// TODO Auto-generated constructor stub
	}

	public Hero(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	public void move(float delta){
		if (movingUp){
			this.move(UP, delta);
		} else if (movingDown) {
			this.move(DOWN, delta);
		}
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub

	}

	public void moveUp() {
		stopped = false;
		movingUp = true;
		
	}
	
	public void moveDown() {
		stopped = false;
		movingDown = true;
		
	}
	
	private void move(int direction, float delta) {
		if (!stopped)
			this.y += direction * this.speed * delta;
	}

	public void stop() {
		this.setStopped(true);
	}

	public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
		this.movingUp = false;
		this.movingDown = false;
	}

}
