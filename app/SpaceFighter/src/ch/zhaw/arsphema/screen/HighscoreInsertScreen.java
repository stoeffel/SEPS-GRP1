package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.model.HighscoreEntry;
import ch.zhaw.arsphema.model.PlayerProfile;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.UiStyles;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

/**
 * Screenklasse für das Eintragen einer neuen Highscore.
 *
 * @author spoerriweb
 */
public class HighscoreInsertScreen extends UiScreen {

    private Table compTable;
    private Label lbTitle;
    private Label lbPoints;
    private Label lbText;
    private TextField tfName;
    private Button btnAccept;
    private Button btnBack;
    private int score = 0;
    private PlayerProfile profile;

    /**
     * Konstruktor
     *
     * @param game Instanz der Game Klasse
     */
    public HighscoreInsertScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        ClickListener buttonListener = new HighscoreInsertButtonListener();

        compTable = new Table();

        lbTitle = new Label("New Highscore", UiStyles.getTitleLabelStyle(0));
        lbPoints = new Label("0", UiStyles.getPointLabelStyle(ppuY));
        lbText = (new Label("Please enter your Name", UiStyles.getTextLabelStyle(0)));
        tfName = new TextField("", "", UiStyles.getTextFieldStyle(0));

        btnAccept = new Button(new TextureRegion(UiStyles.UI_ICON_TEXTURE_REGION, 0, 600, 300, 300));
        btnAccept.setClickListener(buttonListener);
        btnBack = new Button(new TextureRegion(UiStyles.UI_ICON_TEXTURE_REGION, 600, 0, 300, 300));
        btnBack.setClickListener(buttonListener);
    }

    @Override
    protected void setupGui() {
        super.setupGui();

        //Get the correct font sizes
        lbTitle.setStyle(UiStyles.getTitleLabelStyle(ppuY));
        lbPoints.setStyle(UiStyles.getPointLabelStyle(ppuY));
        lbText.setStyle(UiStyles.getTextLabelStyle(ppuY));
        tfName.setStyle(UiStyles.getTextFieldStyle(ppuY));

        wrapTable.add(lbTitle).padBottom((int) (5 * ppuY)).padTop((int) (5 * ppuY));
        wrapTable.row();

        lbPoints.setText(String.valueOf(score));

        compTable.clear();
        compTable.add(lbPoints).pad((int) (3 * ppuY));
        compTable.row();
        compTable.add(lbText).pad((int) (3 * ppuY));
        compTable.row();
        compTable.add(tfName).width((int) (50 * ppuY)).height((int) (7 * ppuY));

        wrapTable.add(compTable);

        //Setup Button Row
        addToButtonRow(btnBack);
        addToButtonRow(btnAccept);
        wrapTable.row();
        wrapTable.add(buttonTable).bottom().expandY();
    }


    public void setScore(int score) {
        this.score = score;
    }

    @Override
    /**
     * Wird beim Wechsel zu diesem Screen aufgerufen.
     */
    public void show() {
        super.show();
        Gdx.input.setCatchBackKey(true);
        profile = Services.getProfileManager().loadPlayerProfile();
        tfName.setText(profile.getPlayerName());
    }

    @Override
    /**
     * Wird bei jeder Anpassung der Screengrösse aufgerufen.
     */
    public void resize(int width, int height) {
        super.resize(width, height);
        setupGui();
    }

    private class HighscoreInsertButtonListener implements ClickListener {

        @Override
        public void click(Actor actor, float x, float y) {
            if (btnAccept.equals(actor)) {
                //load player profile with current highscore
                profile.addHighscoreEntry(new HighscoreEntry(tfName.getText(), score));
                Services.getProfileManager().savePlayerProfile();
                game.setScreen(game.getHighscoreScreen());
            } else if (btnBack.equals(actor)) {
                uiController.keyDown(Input.Keys.BACK);
            }
        }
    }
}
