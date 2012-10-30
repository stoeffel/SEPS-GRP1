package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.model.shot.OverHeatBar;
import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;
import ch.zhaw.arsphema.model.shot.ShotFactory.Type;
import ch.zhaw.arsphema.screen.Renderer;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.Effects;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.Sounds;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Hero extends AbstractSprite {
	private static final long serialVersionUID = 1L;
	private static final int UP = 1;
	private static final int DOWN = -1;
	
	private static final int ROWS = 3;
	private static final int COLS = 1;
	
	private boolean stopped = true;
	private boolean movingUp = false;
	private boolean movingDown = false;
	private boolean fire = false;
	private boolean dead = false;

//	private float overheatCountDown = 1;
//	private static final float OVERHEAT_DAMAGE_DELAY = 1;
	
	private TextureRegion[] textures;
	
	private OverHeatBar overheatbar;
	private float coolSpeed;
	private float heatSpeed;
	private float shootSpeed = 80;
	private ShotFactory.Type shotType;
	private boolean isHurt = false;
	private boolean emitterStarted = false;
	private Array<ParticleEmitter> emitters_burn_baby_burn;
	private Array<ParticleEmitter> emitters_jet;
	private TextureRegion currentTexture;
	private LifeCounter lifeCounter;
	private TextureRegion[] blinkFrames;
	private Animation blinkAnimation;
	private float stateTime;

	public Hero(float x, float y, TextureRegion texture) {
		super(x, y, Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT, texture, 10);
		health = 3;
		shootingFrequency = 0.1f;
		lastShot=0;
		coolSpeed = 4;
		heatSpeed = 2;
		TextureRegion[][] tmp = texture.split( 
				texture.getRegionWidth() / COLS, texture.getRegionHeight() / ROWS);
		textures = new TextureRegion[COLS * ROWS];

		int index = 0;
        for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                        textures[index++] = tmp[i][j];
                }
        }
        currentTexture = textures[0];
        
        // blink animation
        blinkFrames = new TextureRegion[2];
        blinkFrames[0] = textures[0];
        blinkFrames[1] = textures[2];
        blinkAnimation = new Animation(0.25f, blinkFrames);
        blinkAnimation.setPlayMode(Animation.LOOP);

        
		overheatbar = new OverHeatBar(Sizes.DEFAULT_WORLD_WIDTH - 2, Sizes.DEFAULT_WORLD_HEIGHT / 5 * 4, TextureRegions.OVERHEATBAR);
        
        emitters_burn_baby_burn = new Array<ParticleEmitter>(Effects.EXPLOSION_1.getEmitters());
		Effects.EXPLOSION_1.getEmitters().add(emitters_burn_baby_burn.get(0));
		emitters_jet = new Array<ParticleEmitter>(Effects.JET.getEmitters());
		
		Effects.JET.getEmitters().add(emitters_jet.get(0));
		emitters_jet.get(0).start();
		lifeCounter = new LifeCounter(Sizes.DEFAULT_WORLD_WIDTH / 20,
				Sizes.DEFAULT_WORLD_HEIGHT - Sizes.DEFAULT_WORLD_HEIGHT / 20,
				width / 3, height / 3, texture);
		lifeCounter.setLifes(health);
		lifeCounter.setMaxLifes(health);
		
		shotType = ShotFactory.Type.STANDARD;
	}
	
	

	public boolean move(float delta){
		if (movingUp){
			move(UP, delta);
		} else if (movingDown) {
			move(DOWN, delta);
		}
		return true;
	}

	@Override
	public Array<Shot> shoot(float delta) {
		if (fire && lastShot > shootingFrequency) {
			lastShot = 0;
			heatGun(delta);
			return ShotFactory.createShotInArray(x + width*5/6, y + height/3, shootSpeed, shotType, false);
		}
		if (!fire)
		{
			overheatbar.cool(coolSpeed);
//			overheatCountDown = OVERHEAT_DAMAGE_DELAY;
		} else {
			heatGun(delta);
		}
		lastShot += delta;
		return null;
	}
	
	private void heatGun(final float delta)
	{
		if (overheatbar.heat(heatSpeed)) {
//			System.out.println(overheatCountDown + " - " + delta); // TODO check if stoeffel what approach is nicer
//			overheatCountDown -= delta;
//			System.out.println(overheatCountDown);
//			if (overheatCountDown < 0) {
				lowerHealth(1);
//				overheatCountDown += OVERHEAT_DAMAGE_DELAY;
//			}
		}
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
		if (y + height >= Renderer.WORLD_WIDTH && movingUp || y <= 0 && movingDown) {
			stop();
		}
		if (!stopped)
		{
			y += direction * speed * delta;
			if(y + height >= Renderer.WORLD_HEIGHT)
				y = Renderer.WORLD_HEIGHT - height;
			else if(y <= 0 && movingDown)
				y = 0;
		}
		
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
	
	public void draw(final SpriteBatch batch) {
		Effects.JET.setPosition(x, y + height / 2);
		Effects.JET.draw(batch, Gdx.graphics.getDeltaTime());
		
		if (isHurt && stateTime < 2){
			stateTime += Gdx.graphics.getDeltaTime();
			showExplotion(batch, Gdx.graphics.getDeltaTime());
			batch.draw(blinkAnimation.getKeyFrame(stateTime), x, y, width, height);
		} else {
			isHurt = false;
			stateTime = 0;
			batch.draw(currentTexture, x, y, width, height);
		}
		lifeCounter.draw(batch);
		overheatbar.draw(batch);
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}

	public void lowerHealth(int damage) {
		if (isHurt) return; // don't hurt him again
		isHurt = true;
		health -= damage; //TODO shield and stuff check
		lifeCounter.setLifes(health);
	    Services.getSoundManager().play(Sounds.HURT, false);
		dead = health <= 0;
	}
	
	/**
	 * 
	 */
	public void oneUp() {
		health++;
		lifeCounter.oneUp();
	}
	
	private void showExplotion(SpriteBatch batch, float delta){
		if (emitters_burn_baby_burn.get(0).isComplete()){
			emitters_burn_baby_burn.get(0).reset();
			emitterStarted = false;
			return;
		}
		Effects.EXPLOSION_1.setPosition(x + width / 2, y + height);

		if (!emitterStarted){
			emitters_burn_baby_burn.get(0).start();
			emitterStarted = true;
		}

		Effects.EXPLOSION_1.draw(batch, Gdx.graphics.getDeltaTime());
		
	}

	public boolean isDead() {
		return dead;
	}

	public void setShotType(Type green) {
		shotType = green;
	}
	
	@Override
	public void resize(final float oldPpuX, final float oldPpuY, final float newPpuX, final float newPpuY){
		x = x / oldPpuX * newPpuX;
		y = y / oldPpuY * newPpuY;
		width = Sizes.SHIP_WIDTH;
		height = Sizes.SHIP_HEIGHT;
		lifeCounter.resize(oldPpuX, oldPpuY, newPpuX, newPpuY);
		overheatbar.resize(oldPpuX, oldPpuY, newPpuX, newPpuY);
	}

}
