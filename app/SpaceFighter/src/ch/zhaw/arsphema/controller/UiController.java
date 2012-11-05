package ch.zhaw.arsphema.controller;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.screen.MainMenuScreen;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.services.SoundManager;
import ch.zhaw.arsphema.util.Components;
import ch.zhaw.arsphema.util.Sounds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;

/**
 * InputProcessor für das Userinterface ausserhalb des Gamescreens
 */
public class UiController extends AbstractController implements InputProcessor {

    private final MyGdxGame game;
    private final Stage stage;

    public UiController(MyGdxGame game, Stage stage) {
        this.game = game;
        this.stage = stage;
    }

    @Override
    public boolean keyDown(int i) {
        stage.keyDown(i);
        if (i == Input.Keys.BACK || i == Input.Keys.ESCAPE) {
            if (!(game.getScreen() instanceof MainMenuScreen)) {
                game.setScreen(game.getMainMenuScreen());
            }
        }

        return true;
    }

    @Override
    public boolean keyUp(int i) {
        return stage.keyUp(i);
    }

    @Override
    public boolean keyTyped(char c) {
        return stage.keyTyped(c);
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return stage.touchDown(i, i1, i2, i3);
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return stage.touchUp(i, i1, i2, i3);
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return stage.touchDragged(i, i1, i2);
    }

    @Override
    public boolean touchMoved(int i, int i1) {
        return stage.touchMoved(i, i1);
    }

    @Override
    public boolean scrolled(int i) {
        return stage.scrolled(i);
    }

    public void update(float delta) {
        //maybe for later use
    }


    public UiButtonListener createUiButtonListener() {
        return new UiButtonListener();
    }

    //todo Eventuell wieder in MainMenu verschieben.
    private class UiButtonListener implements ClickListener {

        @Override
        public void click(Actor actor, float v, float v1) {
            Services.setSoundManager(new SoundManager());
            Services.getSoundManager().play(Sounds.BEEP, false);
            if (Components.BUTTON_GAME_START.equals(actor.name)) {
                game.createNewGame();
                game.setScreen(game.getGameScreen());
            } else if (Components.BUTTON_SHOW_HIGHSCORE.equals(actor.name)) {
                game.setScreen(game.getHighscoreScreen());
            } else if (Components.BUTTON_SHOW_OPTIONS.equals(actor.name)) {
                game.setScreen(game.getOptionScreen());
            } else if (Components.BUTTON_SHOW_CREDITS.equals(actor.name)) {
                game.setScreen(game.getCreditsScreen());
            } else if (Components.BUTTON_QUIT.equals(actor.name)) {
                //todo Falls nötig noch zusätzliche Aktionen beim Beenden der Applikation implementieren
                Gdx.app.exit();
            }
        }
    }
}
