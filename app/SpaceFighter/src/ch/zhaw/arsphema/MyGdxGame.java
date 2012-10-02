package ch.zhaw.arsphema;

import ch.zhaw.arsphema.screen.GameScreen;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

	

	@Override
	public void create() {
		// TODO Auto-generated method stu
		setScreen(new GameScreen(this));
	}
	
}