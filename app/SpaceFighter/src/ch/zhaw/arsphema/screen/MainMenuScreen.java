package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.util.Paths;
import ch.zhaw.arsphema.util.Sizes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

/*
    Erster Entwurf des Hauptmenues
    Die Styles werden warscheinlich noch ausgelagert.
 */
public class MainMenuScreen extends AbstractScreen {

    private Table table;

    public MainMenuScreen(MyGdxGame game) {
        super(game);
        setupGUI();
    }

    private void setupGUI() {

        //Tabelle
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //Ueberschrift
        LabelStyle  labelStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.SPACE_FONT),false), Color.WHITE);
        table.add(new Label("Arsphema", labelStyle)).padBottom(20);

        //ButtonStyle
        TextButtonStyle style = new TextButtonStyle();
        style.font = new BitmapFont(Gdx.files.internal(Paths.BUTTON_FONT),false);
        style.fontColor = Color.DARK_GRAY;
        style.pressedOffsetY = 1f;
        style.up = new NinePatch(new Texture(Gdx.files.internal(Paths.BUTTON_TEXTURE)), 8, 8, 8, 8);

        //Buttons
        TextButton startButton = new TextButton("Start Game", style);
        startButton.setClickListener(new ClickListener() {
            @Override
            public void click(Actor actor, float x, float y) {
                game.setScreen(game.getGameScreen());
            }
        });
        addTextButton(startButton);

        TextButton optionsButton = new TextButton("Options", style);
        addTextButton(optionsButton);

        TextButton highscoreButton = new TextButton("Highscore", style);
        addTextButton(highscoreButton);
    }

    private void addTextButton(TextButton button) {
        table.row();
        table.add(button).width(Sizes.BUTTON_WIDTH).pad(5);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(stage);
    }
}
