package ch.zhaw.arsphema;

import ch.zhaw.arsphema.screen.*;
import ch.zhaw.arsphema.services.Services;
import com.badlogic.gdx.Game;

/**
 * Main Klasse des Games
 * @author schtoeffel
 *
 */
public class MyGdxGame extends Game {

    private MainMenuScreen mainMenuScreen;
    private GameScreen gameScreen;
    private OptionScreen optionScreen;
    private HighscoreScreen highscoreScreen;
    private HighscoreInsertScreen highscoreInsertScreen;
    private CreditsScreen creditsScreen;
    private PauseScreen pauseScreen;
    private LoadingScreen loadingScreen;


    /**
     * methode die von libgdx aufgerufen wurde
     */
    @Override
    public void create() {
        //loader screens
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }

    /**
     * initialisiert alle screens
     */
    public void initScreens() {
        mainMenuScreen = new MainMenuScreen(this);
        createNewGame();
        optionScreen = new OptionScreen(this);
        highscoreScreen = new HighscoreScreen(this);
        highscoreInsertScreen = new HighscoreInsertScreen(this);
        creditsScreen = new CreditsScreen(this);
        pauseScreen = new PauseScreen(this);


//        Services.getProfileManager().createTestProfile(); //testing
        //load profile
        Services.getProfileManager().loadPlayerProfile();
    }

    /**
     * klasse die bei gameover aufgerufen wird
     * @param score
     */
    public void gameOver(int score) {
        if (score > 0 && score > Services.getProfileManager().loadPlayerProfile().getMinimalScore()) {
            highscoreInsertScreen.setScore(score);
            setScreen(highscoreInsertScreen);
        } else {
            setScreen(mainMenuScreen);
        }
    }

    /**
     * getter für mainmenuscreen
     * @return
     */
    public MainMenuScreen getMainMenuScreen() {
        return mainMenuScreen;
    }

    /**
     * erstellt ein neues game
     */
    public void createNewGame() {
        gameScreen = new GameScreen(this);
        gameScreen.initGame();
    }

    /**
     * getter für gameScreen
     * @return
     */
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    /**
     * getter für optionScreen
     * @return
     */
    public OptionScreen getOptionScreen() {
        return optionScreen;
    }

    /**
     * getter für highscoreScreen
     * @return
     */
    public HighscoreScreen getHighscoreScreen() {
        return highscoreScreen;
    }

    /**
     * getter für highscoreInsertScreen
     * @return
     */
    public HighscoreInsertScreen getHighscoreInsertScreen() {
        return highscoreInsertScreen;
    }

    /**
     * getter für creditsScreen
     * @return
     */
    public CreditsScreen getCreditsScreen() {
        return creditsScreen;
    }

    /**
     * getter für pauseScreen
     * @return
     */
    public PauseScreen getPauseScreen() {
        return pauseScreen;
    }

    /**
     * methode die von libgdx bei dispose aufgerufen wird
     */
    @Override
    public void dispose() {
        super.dispose();
        loadingScreen.dispose();
        highscoreInsertScreen.dispose();
        highscoreScreen.dispose();
        optionScreen.dispose();
        creditsScreen.dispose();
        gameScreen.dispose();
        mainMenuScreen.dispose();
    }
}