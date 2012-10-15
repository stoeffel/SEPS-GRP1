package ch.zhaw.arsphema.model;

import java.util.Collections;
import java.util.List;

import ch.zhaw.arsphema.model.shot.OverHeatBar;
import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hero extends AbstractSprite {
	private static final long serialVersionUID = 1L;
	private static final int UP = 1;
	private static final int DOWN = -1;
	
	private static final int ROWS = 4;
	private static final int COLS = 1;
	
	private boolean stopped = true;
	private boolean movingUp = false;
	private boolean movingDown = false;
	private boolean fire = false;
	
	
	private TextureRegion[] frames;
	private Animation animation;
	
	private ShotFactory shotFactory;
	private OverHeatBar overheatbar;

	public Hero(float x, float y, Texture texture) {
		super(x, y, Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT, null);
		health = 3;
		speed = 66;
		shootingFrequency = 0.2f;
		lastShot=0;
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
        
        shotFactory = ShotFactory.getInstance();
        overheatbar = OverHeatBar.getInstance();
	}

	public void move(float delta){
		if (movingUp){
			this.move(UP, delta);
		} else if (movingDown) {
			this.move(DOWN, delta);
		}
	}

	@Override
	public List<Shot> shoot(float delta) {
		if (fire && lastShot > shootingFrequency && !overheatbar.isOverheated()) {
			lastShot = 0;
			return Collections.singletonList(ShotFactory.createShot(this.x + this.width, this.y+this.height/3, ShotFactory.STANDARD, false));
		}
		lastShot += delta;
		return Collections.emptyList();
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
	
	
	public void draw(SpriteBatch batch, float delta, float elapsed, float ppuX, float ppuY) {
		if (fire){
			overheatbar.heat(20*delta);
		} else {
			overheatbar.cool(20*delta);
		}
		batch.draw(getKeyFrame(elapsed, true), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		batch.draw(this.getKeyFrame(elapsed, true), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}

	public boolean lowerHealth(int damage) {
		health -= damage; //TODO shield and stuff check
		return health <= 0;
	}

}
