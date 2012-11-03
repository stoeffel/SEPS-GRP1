package ch.zhaw.arsphema.model;

import java.util.Random;

import ch.zhaw.arsphema.model.shot.Shot;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Planet extends AbstractSprite {

	private static final int ROWS = 4;
	private static final int COLS = 3;
	private TextureRegion[] planets;
	private int whichPlanet;

	private boolean shouldBeRemoved;

	public Planet(float x, float y, float width, float height,
			TextureRegion texture) {
		super(x, y, width, height, texture);
		TextureRegion[][] tmp = textureRegion.split(textureRegion.getRegionWidth() / COLS, textureRegion.getRegionHeight() / ROWS);
		planets = new TextureRegion[COLS * ROWS];
		speed = width / 8;
		int index = 0;
        for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                	planets[index++] = tmp[i][j];
                }
        }
        whichPlanet = new Random().nextInt(planets.length-1);
        planets[whichPlanet].getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 749176604995858563L;

	@Override
	public boolean move(float delta) {
		x -= speed * delta;
		if (this.x + this.width < 0) {
			shouldBeRemoved = true;
		}
		return true;
	}
	public boolean shouldBeRemoved() {
		
		return shouldBeRemoved;
	}

	@Override
	public Array<Shot> shoot(float delta) {
		return null;
	}

	@Override
	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		batch.draw(planets[whichPlanet], ppuX * this.x, ppuY * this.y, ppuX * this.width, ppuX * this.height);
	}

}
