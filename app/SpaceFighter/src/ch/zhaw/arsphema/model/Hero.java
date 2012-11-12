package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.model.powerup.AbstractPowerUp;
import ch.zhaw.arsphema.model.shot.OverHeatBar;
import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;
import ch.zhaw.arsphema.model.shot.ShotFactory.Type;
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

	private TextureRegion[] textures;
	
	private OverHeatBar overheatbar;
	private float coolSpeed;
	private float heatSpeed;
	private float shootSpeed = 160;
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
	private Array<AbstractPowerUp> powerUps;

	public Hero(float x, float y, TextureRegion texture) {
		super(x, y, Sizes.SHIP_WIDTH, Sizes.SHIP_HEIGHT, texture);
		health = 3;
		speed = 66;
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

        
        overheatbar = new OverHeatBar(Sizes.DEFAULT_WORLD_WIDTH - 2, Sizes.DEFAULT_WORLD_HEIGHT/5*4, TextureRegions.OVERHEATBAR);
        
        emitters_burn_baby_burn = new Array<ParticleEmitter>(Effects.EXPLOSION_1.getEmitters());
		
		Effects.EXPLOSION_1.getEmitters().add(emitters_burn_baby_burn.get(0));
        
		emitters_jet = new Array<ParticleEmitter>(Effects.JET.getEmitters());
		
		Effects.JET.getEmitters().add(emitters_jet.get(0));
		emitters_jet.get(0).start();
		lifeCounter = new LifeCounter(Sizes.DEFAULT_WORLD_WIDTH/20, Sizes.DEFAULT_WORLD_HEIGHT - Sizes.DEFAULT_WORLD_HEIGHT/20, width/3, height/3, texture);
		lifeCounter.setLifes(health);
		lifeCounter.setMaxLifes(health);
		
		shotType = ShotFactory.Type.STANDARD;
		powerUps = new Array<AbstractPowerUp>();
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
		} else {
			heatGun(delta);
		}
		lastShot += delta;
		return null;
	}
	
	private void heatGun(final float delta)
	{
		if (overheatbar.heat(heatSpeed)) {
			lowerHealth(1);
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
		if (y + height >= Sizes.DEFAULT_WORLD_WIDTH && movingUp || y <= 0 && movingDown) {
			stop();
		}
		if (!stopped)
		{
			y += direction * speed * delta;
			if(y + height >= Sizes.DEFAULT_WORLD_HEIGHT)
				y = Sizes.DEFAULT_WORLD_HEIGHT - height;
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
	
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		
		Effects.JET.setPosition( (x)*ppuX,(y+height/2)*ppuY );
		Effects.JET.draw(batch, Gdx.graphics.getDeltaTime());
		
		if (isHurt && stateTime < 2){
			stateTime += Gdx.graphics.getDeltaTime();
			showExplotion(batch, Gdx.graphics.getDeltaTime(),ppuX, ppuY);
			batch.draw(blinkAnimation.getKeyFrame(stateTime), ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		} else {
			isHurt = false;
			stateTime = 0;
			batch.draw(currentTexture, ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		}
		lifeCounter.draw(batch, ppuX, ppuY);
		overheatbar.draw(batch, ppuX, ppuY);
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}

	public void lowerHealth(int damage) {
		if (isHurt) return; // don't hurt him again
		isHurt = true;
		if(!handlePowerUp()){
			health -= damage;
			lifeCounter.setLifes(health);
		}
	    Services.getSoundManager().play(Sounds.HURT, false);
	    
		dead = health <= 0;
	}


	/**
	 * @return true if had powerups
	 */
	private boolean handlePowerUp() {
		if(powerUps.size > 0){
			for (AbstractPowerUp pu : powerUps) {
				pu.undoSomething(this);
			}
			powerUps.clear();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * 
	 */
	public void oneUp() {
		health++;
		lifeCounter.oneUp();
	}
	
	private void showExplotion(SpriteBatch batch, float delta, float ppuX, float ppuY){
		if (emitters_burn_baby_burn.get(0).isComplete()){
			emitters_burn_baby_burn.get(0).reset();
			emitterStarted = false;
			return;
		}
		Effects.EXPLOSION_1.setPosition( (x+width/2)*ppuX,(y+height)*ppuY );

		if (!emitterStarted){
			emitters_burn_baby_burn.get(0).start();
			emitterStarted = true;
		}

		Effects.EXPLOSION_1.draw(batch, Gdx.graphics.getDeltaTime());
		
	}

	public boolean isDead() {
		return dead;
	}


	public void setShotGreen() {
		setShotType(ShotFactory.Type.GREEN);
		setShootingFrequency(0.05f);
	}

	public void setShotStd() {
		setShotType(ShotFactory.Type.STANDARD);
		setShootingFrequency(0.1f);
	}

	public void setShotType(Type green) {
		shotType = green;
	}



	public Array<AbstractPowerUp> getPowerUps() {
		return powerUps;
	}

	public void addPowerUps(AbstractPowerUp powerUp) {
		this.powerUps.add(powerUp);
		powerUp.doSomething(this);
	}

}
