package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.model.HighscoreEntry;
import ch.zhaw.arsphema.model.PlayerProfile;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.UiStyles;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

public class HighscoreScreen extends UiScreen {

    private Table wrapTable;
    private Table highscoreTable;

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
        wrapTable.add(new Label("Highscore", UiStyles.LABEL_SCREEN_HEADER)).padBottom(20);
        wrapTable.row();

        //Highscore Table
        highscoreTable = new Table();
        highscoreTable.width(400);

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
        int rank = 1;
        for (HighscoreEntry highscoreEntry : profile.getHighscore()) {
            highscoreTable.add(new Label(rank + ".", UiStyles.LABEL_DEFAULT)).width(40).left();
            highscoreTable.add(new Label(highscoreEntry.getName(), UiStyles.LABEL_DEFAULT)).width(200);
            highscoreTable.add(new Label(String.valueOf(highscoreEntry.getScore()), UiStyles.LABEL_DEFAULT)).expand().right();
            highscoreTable.row();
            rank++;
        }
    }
}
