package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.model.HighscoreEntry;
import ch.zhaw.arsphema.model.PlayerProfile;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.Paths;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

public class HighscoreScreen extends UiScreen {

    private Table wrapTable;
    private Table highscoreTable;
    private LabelStyle entryLabelStyle;

    public HighscoreScreen(MyGdxGame game) {
        super(game);
        setupGUI();

    }

    private void setupGUI() {
        //Layout Table
        wrapTable = new Table();
        wrapTable.setFillParent(true);
        wrapTable.top().padTop(50);
        stage.addActor(wrapTable);

        //Header
        LabelStyle headerLabelStyle = new LabelStyle(new BitmapFont(Gdx.files.internal(Paths.HEADER_FONT), false), Color.WHITE);
        wrapTable.add(new Label("Highscore", headerLabelStyle)).padBottom(20);
        wrapTable.row();

        //Highscore Table
        highscoreTable = new Table();
        highscoreTable.width(400);
        entryLabelStyle = new LabelStyle(new BitmapFont(Gdx.files.internal(Paths.BUTTON_FONT), false), Color.WHITE);

        wrapTable.add(highscoreTable);

    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setCatchBackKey(true);

        //load player profile with current highscore
        PlayerProfile profile = Services.getProfileManager().loadPlayerProfile();

        //clear the table before adding the entries
        highscoreTable.clear();
        for (HighscoreEntry highscoreEntry : profile.getHighscore()) {
            highscoreTable.add(new Label(highscoreEntry.getName(), entryLabelStyle)).width(200);
            highscoreTable.add(new Label(String.valueOf(highscoreEntry.getScore()), entryLabelStyle)).expand().right();
            highscoreTable.row();
        }
    }
}
