package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import com.badlogic.gdx.Gdx;

/*
   Screen um einen neuen Highscore-Eintrag hinzuzufügen
   Die Styles werden warscheinlich noch ausgelagert.
*/
public class HighscoreInsertScreen extends UiScreen {

    public HighscoreInsertScreen(MyGdxGame game) {
        super(game);
        setupGUI();
    }

    private void setupGUI() {
        //todo GUI erstellen
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setCatchBackKey(true);
    }
}
