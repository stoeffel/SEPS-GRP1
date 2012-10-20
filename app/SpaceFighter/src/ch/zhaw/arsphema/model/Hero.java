package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.model.shot.OverHeatBar;
import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

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
	
	private float animationStateTime = 0f;
	private TextureRegion[] frames;
	private Animation animation;
	
	private OverHeatBar overheatbar;
	private float coolSpeed;
	private float heatSpeed;
	private float shootSpeed = 160;

	public Hero(float x, float y, TextureRegion texture) {
		super(x, y, Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT, texture);
		health = 3;
		speed = 66;
		shootingFrequency = 0.2f;
		lastShot=0;
		coolSpeed = 2;
		heatSpeed = 5;
		TextureRegion[][] tmp = texture.split( 
				texture.getRegionWidth() / COLS, texture.getRegionHeight() / ROWS);
		frames = new TextureRegion[COLS * ROWS];

		int index = 3;
        for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                        frames[index--] = tmp[i][j];
                }
        }
        animation = new Animation(0.05f, frames);
        animation.setPlayMode(Animation.LOOP);
        
        overheatbar = OverHeatBar.getInstance();
	}

	public boolean move(float delta){
		if (movingUp){
			this.move(UP, delta);
		} else if (movingDown) {
			this.move(DOWN, delta);
		}
		return true;
	}

	@Override
	public Array<Shot> shoot(float delta) {
		if (fire && lastShot > shootingFrequency && !overheatbar.isOverheated()) {
			lastShot = 0;
			return ShotFactory.createShotInArray(x + width, y + height/3, shootSpeed, ShotFactory.STANDARD, false);
		}
		if (!fire)
		{
			overheatbar.cool(coolSpeed);
		} else {
			overheatbar.heat(heatSpeed); // no need of delta, since it's regulated by shootingFrequency ;)
			// sorry but i need the delta, because i have to update the bar every time
		}
		lastShot += delta;
		return null;
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
	
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		animationStateTime += Gdx.graphics.getDeltaTime();
		batch.draw(animation.getKeyFrame(animationStateTime), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}

	public boolean lowerHealth(int damage) {
		health -= damage; //TODO shield and stuff check
		return health <= 0;
	}

}
