package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.util.UiStyles;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Screenklasse für die Anzeige des Hauptmenüs
 *
 * @author spoerriweb
 */
public class MainMenuScreen extends UiScreen {
    private Label lbTitle;

    //Buttons
    private Button btnPlay;
    private Button btnOptions;
    private Button btnHighscore;
    private Button btnInfo;
    private Button btnExit;

    /**
     * Konstruktor
     *
     * @param game Instanz der Game Klasse
     */
    public MainMenuScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        ClickListener buttonListener = new MainMenuListener();

        lbTitle = new Label("Arsphema", UiStyles.getSpaceLabelStyle(0));
        btnPlay = new Button(UiStyles.PLAY_BUTTON_TEXTURE_REGION);
        btnPlay.setClickListener(buttonListener);
        btnOptions = new Button(new TextureRegion(UiStyles.UI_ICON_TEXTURE_REGION, 600, 300, 300, 300));
        btnOptions.setClickListener(buttonListener);
        btnHighscore = new Button(new TextureRegion(UiStyles.UI_ICON_TEXTURE_REGION, 0, 0, 300, 300));
        btnHighscore.setClickListener(buttonListener);
        btnInfo = new Button(new TextureRegion(UiStyles.UI_ICON_TEXTURE_REGION, 300, 600, 300, 300));
        btnInfo.setClickListener(buttonListener);
        btnExit = new Button(new TextureRegion(UiStyles.UI_ICON_TEXTURE_REGION, 300, 0, 300, 300));
        btnExit.setClickListener(buttonListener);
    }

    @Override
    protected void setupGui() {
        super.setupGui();

        //Get the correct font sizes
        lbTitle.setStyle(UiStyles.getSpaceLabelStyle(ppuY));

        //Header
        wrapTable.add(lbTitle).padBottom((int) (10 * ppuY)).padTop((int) (10 * ppuY));

        wrapTable.row();

        //Add Play-Buttons
        wrapTable.add(btnPlay).width((int) (40 * ppuY)).height((int) (20 * ppuY));

        //Setup ButtonRow
        addToButtonRow(btnOptions);
        addToButtonRow(btnHighscore);
        addToButtonRow(btnInfo);
        addToButtonRow(btnExit);
        wrapTable.row();
        wrapTable.add(buttonTable).bottom().expandY();
    }

    @Override
    /**
     * Wird bei jeder Anpassung der Screengrösse aufgerufen.
     */
    public void resize(int width, int height) {
        super.resize(width, height);
        setupGui();
    }

    @Override
    /**
     * Wird beim Wechsel zu diesem Screen aufgerufen.
     */
    public void show() {
        super.show();
        Gdx.input.setCatchBackKey(false);
    }

    private class MainMenuListener implements ClickListener {

        @Override
        public void click(Actor actor, float v, float v1) {
            if (btnPlay.equals(actor)) {
                game.createNewGame();
                game.setScreen(game.getGameScreen());
            } else if (btnHighscore.equals(actor)) {
                game.setScreen(game.getHighscoreScreen());
            } else if (btnOptions.equals(actor)) {
                game.setScreen(game.getOptionScreen());
            } else if (btnInfo.equals(actor)) {
                game.setScreen(game.getCreditsScreen());
            } else if (btnExit.equals(actor)) {
                Gdx.app.exit(); // disposes assets
                System.exit(0); // needed since assets need to be reloaded on next start
            }
        }
    }
}
