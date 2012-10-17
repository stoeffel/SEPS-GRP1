package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.model.Planet;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.TextureRegions;

import com.badlogic.gdx.utils.Array;

public class PlanetManager {
	private Array<Planet> planets, planetsToRemove;
	
	private float lastTime,minInterval, maxInterval;

	private int maxPlanets;

	private float minRadius;

	private float maxRadius;

	
	public PlanetManager()
	{
		planets = new Array<Planet>();
		planetsToRemove = new Array<Planet>();
		minInterval = 2.5f;
		maxInterval = 15.5f;
		maxPlanets = 3;
		minRadius = 10f;
		maxRadius = 40f;
		lastTime = 0f;
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
			if (elapsed-lastTime > getRand(minInterval, maxInterval) || elapsed-lastTime > maxInterval){
				lastTime = elapsed;
				float r = getRand(minRadius, maxRadius);
				Planet planet = new Planet(Sizes.DEFAULT_WORLD_WIDTH + r , getRand(0, Sizes.DEFAULT_WORLD_HEIGHT), r, r, TextureRegions.PLANETS);
				planet.setSpeed(r); // um so kleiner um so weiter weg langsamer
				planets.add(planet);
			}
		}
		
	}
}
