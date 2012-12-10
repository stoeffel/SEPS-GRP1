package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.model.HighscoreEntry;
import ch.zhaw.arsphema.model.PlayerProfile;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.UiStyles;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

/**
 * Screenklasse für die Auflistung der Highscore
 *
 * @author spoerriweb
 */
public class HighscoreScreen extends UiScreen {

    private Label lbTitle;
    private Table highscoreTable;
    private ScrollPane scrollPane;
    private Button btnBack;

    /**
     * Konstruktor
     *
     * @param game Instanz der Game Klasse
     */
    public HighscoreScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        ClickListener buttonListener = new HighscoreButtonListener();

        lbTitle = new Label("Highscore", UiStyles.getTitleLabelStyle(0));
        highscoreTable = new Table();
        scrollPane = new ScrollPane(null, UiStyles.SCROLL_PANE_STYLE);
        btnBack = new Button(new TextureRegion(UiStyles.UI_ICON_TEXTURE_REGION, 600, 0, 300, 300));
        btnBack.setClickListener(buttonListener);
    }

    @Override
    protected void setupGui() {
        super.setupGui();

        lbTitle.setStyle(UiStyles.getTitleLabelStyle(ppuY));

        //Header
        wrapTable.add(lbTitle).padBottom((int) (5 * ppuY)).padTop((int) (5 * ppuY));
        wrapTable.row();

        //Highscore Table
        highscoreTable.width((int) (70 * ppuX));
        buildHighscoreTable();
        scrollPane.clear();
        scrollPane.setWidget(highscoreTable);
        scrollPane.setCullingArea(new Rectangle(0, 0, 90 * ppuX, 50 * ppuY));
        wrapTable.add(scrollPane);

        //Setup Button Row
        addToButtonRow(btnBack);
        wrapTable.row();
        wrapTable.add(buttonTable).bottom().expandY();

    }

    private void buildHighscoreTable() {
        //load player profile with current highscore
        PlayerProfile profile = Services.getProfileManager().loadPlayerProfile();

        //clear the table before adding the entries
        highscoreTable.clear();
        int rank = 1;
        for (HighscoreEntry highscoreEntry : profile.getHighscore()) {
            highscoreTable.add(new Label(rank + ".", UiStyles.getTextLabelStyle(ppuY))).width(40).padRight((int) (3 * ppuY)).right();
            highscoreTable.add(new Label(highscoreEntry.getName(), UiStyles.getTextLabelStyle(ppuY))).width(200);
            highscoreTable.add(new Label(String.valueOf(highscoreEntry.getScore()), UiStyles.getTextLabelStyle(ppuY))).padRight(50).expand().right();
            highscoreTable.row();
            rank++;
        }
    }

    @Override
    /**
     * Wird beim Wechsel zu diesem Screen aufgerufen.
     */
    public void show() {
        super.show();
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    /**
     * Wird bei jeder Anpassung der Screengrösse aufgerufen.
     */
    public void resize(int width, int height) {
        super.resize(width, height);
        setupGui();
    }

    private class HighscoreButtonListener implements ClickListener {

        @Override
        public void click(Actor actor, float x, float y) {
            if (btnBack.equals(actor)) {
                uiController.keyDown(Input.Keys.BACK);
            }
        }
    }
}
