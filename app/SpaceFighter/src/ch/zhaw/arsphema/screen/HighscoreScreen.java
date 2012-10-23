package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.util.Paths;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

public class HighscoreScreen extends UiScreen {

    private Table wrapTable;

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
        Label.LabelStyle headerLabelStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.HEADER_FONT), false), Color.WHITE);
        wrapTable.add(new Label("Highscore", headerLabelStyle)).padBottom(20);
        wrapTable.row();

        //Highscore Table
        Table highscoreTable = new Table();
        highscoreTable.width(400);
        Label.LabelStyle entryLabelStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.BUTTON_FONT), false), Color.WHITE);
        for (int i = 0; i < 10; i++) {
            highscoreTable.add(new Label("Name", entryLabelStyle)).width(200);
            highscoreTable.add(new Label("0", entryLabelStyle)).expand().right();
            highscoreTable.row();
        }
        wrapTable.add(highscoreTable);

    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setCatchBackKey(true);
    }
}
