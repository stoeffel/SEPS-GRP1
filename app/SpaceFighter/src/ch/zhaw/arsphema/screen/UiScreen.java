package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.controller.UiController;
import com.badlogic.gdx.Gdx;

/*
   Oberklasse aller UI-Screens für gemeinsame Funktionalitäten
 */
public class UiScreen extends AbstractScreen {

    protected final UiController uiController;

    public UiScreen(MyGdxGame game) {
        super(game);

        //InputProcessor
        uiController = new UiController(game, stage);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(uiController);
    }
}
