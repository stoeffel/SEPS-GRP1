package ch.zhaw.arsphema.model;

import junit.framework.Assert;

import org.junit.Test;

public class HeroTest {

	@Test
	public void testShipMovement() {
		Hero hero = new Hero(0, 0);
		hero.moveUp();
		hero.move(1);
		Assert.assertEquals(hero.getSpeed(), hero.getY());
		hero.moveDown();
		hero.move(1);
		Assert.assertEquals(0f, hero.getY());
	}

}
