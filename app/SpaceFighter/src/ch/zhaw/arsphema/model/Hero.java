package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Hero extends AbstractSprite {
	
	private static final int UP = 1;
	private static final int DOWN = -1;
	private static final int ROWS = 4;
	private static final int COLS = 1;
	private boolean stopped = true;
	private boolean movingUp = false;
	private boolean movingDown = false;
	private TextureRegion[] frames;
	private Animation animation;

	public Hero(float x, float y, Texture texture) {
		super(x, y, Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT);
		health = 3;
		speed = 66;
		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / COLS, texture.getHeight() / ROWS);
		frames = new TextureRegion[COLS * ROWS];

		int index = 3;
        for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                	
                        frames[index--] = tmp[i][j];
                }
        }
        animation = new Animation(0.005f, frames);
        animation.setPlayMode(Animation.LOOP);
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
		stop();
		stopped = false;
		movingUp = true;
		
	}
	
	public void moveDown() {
		stop();
		stopped = false;
		movingDown = true;
		
	}
	
	private void move(int direction, float delta) {
		if (this.y + this.height >= Sizes.DEFAULT_WORLD_HEIGHT && movingUp || this.y <= 0 && movingDown) {
			this.stop();
		}
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

	public TextureRegion getKeyFrame(float elapsed, boolean b) {
		return this.animation.getKeyFrame(elapsed,b);
	}
	
	public float getRotation(){
		float rotation = 0;
		if (movingUp){
			rotation = 5f;
		}
		if (movingDown){
			rotation = -5f;
		}
		return rotation;
	}

}
