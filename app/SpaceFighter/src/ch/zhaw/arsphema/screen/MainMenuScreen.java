package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.util.Components;
import ch.zhaw.arsphema.util.Sizes;
import ch.zhaw.arsphema.util.UiStyles;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

public class MainMenuScreen extends UiScreen {

    private Table table;

    public MainMenuScreen(MyGdxGame game) {
        super(game);
    }

    private void setupGUI() {

        //Layouttable
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //Header
        table.add(new Label("Arsphema", UiStyles.LABEL_TITLE)).padBottom(20);

        //Buttons
        TextButton startButton = new TextButton("Start Game", UiStyles.BUTTON_DEFAULT, Components.BUTTON_GAME_START);
        startButton.setClickListener(uiController.createUiButtonListener());
        addTextButton(startButton);

        TextButton optionsButton = new TextButton("Options", UiStyles.BUTTON_DEFAULT, Components.BUTTON_SHOW_OPTIONS);
        optionsButton.setClickListener(uiController.createUiButtonListener());
        addTextButton(optionsButton);

        TextButton highscoreButton = new TextButton("Highscore", UiStyles.BUTTON_DEFAULT, Components.BUTTON_SHOW_HIGHSCORE);
        highscoreButton.setClickListener(uiController.createUiButtonListener());
        addTextButton(highscoreButton);

        TextButton creditsButton = new TextButton("Credits", UiStyles.BUTTON_DEFAULT, Components.BUTTON_SHOW_CREDITS);
        creditsButton.setClickListener(uiController.createUiButtonListener());
        addTextButton(creditsButton);

        TextButton quitButton = new TextButton("Quit", UiStyles.BUTTON_DEFAULT, Components.BUTTON_QUIT);
        quitButton.setClickListener(uiController.createUiButtonListener());
        addTextButton(quitButton);
    }

    private void addTextButton(TextButton button) {
        table.row();
        table.add(button).width((int) (Sizes.BUTTON_WIDTH * ppuX)).pad(5);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        setupGUI();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        //maybe for later use
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setCatchBackKey(false);
    }
}
