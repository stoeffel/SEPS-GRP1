package ch.zhaw.arsphema.model;

import ch.zhaw.arsphema.model.shot.OverHeatBar;
import ch.zhaw.arsphema.model.shot.Shot;
import ch.zhaw.arsphema.model.shot.ShotFactory;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.Effects;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.Sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Hero extends AbstractSprite {
	private static final long serialVersionUID = 1L;
	private static final int UP = 1;
	private static final int DOWN = -1;
	
	private static final int ROWS = 1;
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
	private float shootSpeed = 160;
	private boolean isHurt = false;
	private boolean emitterStarted = false;
	private Array<ParticleEmitter> emitters_burn_baby_burn;
	private Array<ParticleEmitter> emitters_jet;
	private TextureRegion currentTexture;
	private LifeCounter lifeCounter;

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
        
        overheatbar = OverHeatBar.getInstance();
        
        emitters_burn_baby_burn = new Array<ParticleEmitter>(Effects.EXPLOSION_1.getEmitters());
		
		Effects.EXPLOSION_1.getEmitters().add(emitters_burn_baby_burn.get(0));
        
		emitters_jet = new Array<ParticleEmitter>(Effects.JET.getEmitters());
		
		Effects.JET.getEmitters().add(emitters_jet.get(0));
		emitters_jet.get(0).start();
		lifeCounter = new LifeCounter(Sizes.DEFAULT_WORLD_WIDTH/20, Sizes.DEFAULT_WORLD_HEIGHT - Sizes.DEFAULT_WORLD_HEIGHT/20, width/3, height/3, currentTexture);
		lifeCounter.setLifes(health);
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
		if (fire && lastShot > shootingFrequency) {
			lastShot = 0;
			heatGun(delta);
			return ShotFactory.createShotInArray(x + width, y + height/3, shootSpeed, ShotFactory.STANDARD, false);
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
		
		Effects.JET.setPosition( (x)*ppuX,(y+height/2)*ppuY );
		Effects.JET.draw(batch, Gdx.graphics.getDeltaTime());
		batch.draw(currentTexture, ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuY * this.height);
		if (isHurt){
			showExplotion(batch, Gdx.graphics.getDeltaTime(),ppuX, ppuY);
		}
		lifeCounter.draw(batch, ppuX, ppuY);
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}

	public void lowerHealth(int damage) {
		health -= damage; //TODO shield and stuff check
		lifeCounter.setLifes(health);
	    isHurt = true;
	    Services.getSoundManager().play(Sounds.HURT, false);
	    
		dead = health <= 0;
	}
	
	private void showExplotion(SpriteBatch batch, float delta, float ppuX, float ppuY){
		if (emitters_burn_baby_burn.get(0).isComplete()){
			isHurt = false;
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

}
