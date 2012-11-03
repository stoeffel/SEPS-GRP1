package ch.zhaw.arsphema.controller;

import java.util.Comparator;

import ch.zhaw.arsphema.model.Planet;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.utils.Array;

public class PlanetManager {
	private Array<Planet> planets, planetsToRemove;
	
	private float lastTime, nextTime, minInterval, maxInterval;

	private int maxPlanets;

	private float minRadius;

	private float maxRadius;

	
	public PlanetManager()
	{
		planets = new Array<Planet>();
		planetsToRemove = new Array<Planet>();
		minInterval = 10;
		maxInterval = 30;
		maxPlanets = 3;
		minRadius = 5f;
		maxRadius = 25f;
		lastTime = 0f;
		nextTime = getRand(minInterval, maxInterval);
	}

	public void cleanUpPlanets() {
		removePlanets(planets);
		planetsToRemove.clear();		
	}
	
	private void removePlanets(final Array<Planet> planetArray) {
		for(final Planet planet : planetsToRemove)
		{
			planetArray.removeValue(planet, false);
		}
	}
	
	public void movePlanets(float delta) {
		for(final Planet planet : planets)
		{
			planet.move(delta);
		}
	}

	public Array<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(Array<Planet> planets) {
		this.planets = planets;
	}

	public Array<Planet> getPlanetsToRemove() {
		return planetsToRemove;
	}

	public void setPlanetsToRemove(Array<Planet> planetsToRemove) {
		this.planetsToRemove = planetsToRemove;
	}

	private float getRand(float min, float max){
		return (float) (Math.random() * (max - min + 1));
	}
	
	public void createPlanet(float elapsed) {
		if (planets.size < maxPlanets) { 
			if (elapsed-lastTime > nextTime || elapsed-lastTime > maxInterval){
				lastTime = elapsed;
				nextTime = getRand(minInterval, maxInterval);
				float r = getRand(minRadius, maxRadius);
				Planet planet = new Planet(Sizes.DEFAULT_WORLD_WIDTH + r , getRand(0, Sizes.DEFAULT_WORLD_HEIGHT), r, r, TextureRegions.PLANETS);
				planets.add(planet);
				planets.sort(new Comparator<Planet>() {
					
					@Override
					public int compare(Planet p1, Planet p2) {
						if (p1.getWidth() < p2.getWidth()) {
							return -1;
						} else {
							return 1;
						}
					}
				});
				
			}
		}
		
	}

}
