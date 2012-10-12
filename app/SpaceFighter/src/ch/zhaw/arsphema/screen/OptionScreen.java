package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.util.Paths;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/*
   Screen um die Spieloptionen festzulegen
   Die Styles werden warscheinlich noch ausgelagert.
*/
public class OptionScreen extends UiScreen {

    public OptionScreen(MyGdxGame game) {
        super(game);
        setupGUI();
    }

    private void setupGUI() {
        //todo GUI erstellen

        //Nur temporär zu Orientierungs und Testzwecken
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.BUTTON_FONT), false), Color.WHITE);
        stage.addActor(new Label("OptionScreen", labelStyle));
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setCatchBackKey(true);
    }
}
