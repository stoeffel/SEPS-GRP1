package ch.zhaw.arsphema;

import ch.zhaw.arsphema.screen.GameScreen;

import ch.zhaw.arsphema.screen.MainMenuScreen;
import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

    private MainMenuScreen mainMenuScreen;
    private GameScreen gameScreen;


    @Override
	public void create() {
        //Screens einmalig instanzieren
        mainMenuScreen = new MainMenuScreen(this);
        gameScreen = new GameScreen(this);

  		setScreen(mainMenuScreen);
	}

    public MainMenuScreen getMainMenuScreen() {
        return mainMenuScreen;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }
}