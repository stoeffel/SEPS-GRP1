package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.model.HighscoreEntry;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.UiLabels;
import ch.zhaw.arsphema.util.UiStyles;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

public class HighscoreInsertScreen extends UiScreen {

    private Table wrapTable;
    private TextField tfName;
    private int score = 0;

    public HighscoreInsertScreen(MyGdxGame game) {
        super(game);
        uiController.setButtonListener(new ComponentListener());
        setupGUI();
    }

    private void setupGUI() {
        //Layout Table
        wrapTable = new Table();
        wrapTable.setFillParent(true);
        wrapTable.top().padTop(50);
        stage.addActor(wrapTable);

        //Header
        wrapTable.add(new Label("New Highscore", UiStyles.LABEL_SCREEN_HEADER)).padBottom(20);
        wrapTable.row();

        //Components
        Table compTable = new Table();
        compTable.add(new Label("Please enter your Name", UiStyles.LABEL_DEFAULT)).colspan(2);
        compTable.row();
        tfName = new TextField("", "", UiStyles.TEXT_FIELD_DEFAULT, UiLabels.TEXTFIELD_HIGHSCORE_NAME);
        compTable.add(tfName).pad(10);
        TextButton btnSubmit = new TextButton("Submit", UiStyles.BUTTON_DEFAULT, UiLabels.BUTTON_SUBMIT_HIGHSCORE);
        btnSubmit.setClickListener(uiController.getButtonListener());
        compTable.add(btnSubmit);

        wrapTable.add(compTable);
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setCatchBackKey(true);
    }


    private class ComponentListener implements ClickListener {

        @Override
        public void click(Actor actor, float x, float y) {

            //load player profile with current highscore
            Services.getProfileManager().loadPlayerProfile().addHighscoreEntry(new HighscoreEntry(tfName.getText(), score));
            Services.getProfileManager().savePlayerProfile();
            game.setScreen(game.getHighscoreScreen());
        }
    }
}
