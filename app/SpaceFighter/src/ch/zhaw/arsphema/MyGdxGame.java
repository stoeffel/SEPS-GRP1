package ch.zhaw.arsphema;

import ch.zhaw.arsphema.screen.*;
import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

    private MainMenuScreen mainMenuScreen;
    private GameScreen gameScreen;
    private OptionScreen optionScreen;
    private HighscoreScreen highscoreScreen;
    private HighscoreInsertScreen highscoreInsertScreen;


    @Override
    public void create() {
        //Screens einmalig instanzieren
        mainMenuScreen = new MainMenuScreen(this);
        gameScreen = new GameScreen(this);
        optionScreen = new OptionScreen(this);
        highscoreScreen = new HighscoreScreen(this);
        highscoreInsertScreen = new HighscoreInsertScreen(this);

        setScreen(mainMenuScreen);
    }

    public MainMenuScreen getMainMenuScreen() {
        return mainMenuScreen;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public OptionScreen getOptionScreen() {
        return optionScreen;
    }

    public HighscoreScreen getHighscoreScreen() {
        return highscoreScreen;
    }

    public HighscoreInsertScreen getHighscoreInsertScreen() {
        return highscoreInsertScreen;
    }
}