package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.util.Paths;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

public class CreditsScreen extends UiScreen {

    private Table wrapTable;

    public CreditsScreen(MyGdxGame game) {
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
        wrapTable.add(new Label("Credits", headerLabelStyle)).padBottom(20);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setCatchBackKey(true);
    }
}

