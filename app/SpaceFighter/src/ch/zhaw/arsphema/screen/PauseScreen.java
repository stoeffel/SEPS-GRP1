package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.controller.UiController;
import ch.zhaw.arsphema.services.Services;
import ch.zhaw.arsphema.util.UiStyles;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class PauseScreen extends UiScreen {

    private Label lbTitle;
    private Label lbPause;
    private Button btnBack;
    private Button btnHome;
    private PauseUiController pauseUiController;

    public PauseScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        pauseUiController = new PauseUiController();
        ClickListener buttonListener = new PauseButtonListener();

        lbTitle = new Label("Arsphema", UiStyles.getSpaceLabelStyle(0));
        lbPause = new Label("Paused...", UiStyles.getTitleLabelStyle(0));
        btnBack = new Button(new TextureRegion(UiStyles.UI_ICON_TEXTURE_REGION, 600, 0, 300, 300));
        btnBack.setClickListener(buttonListener);
        btnHome = new Button(new TextureRegion(UiStyles.UI_ICON_TEXTURE_REGION, 600, 600, 300, 300));
        btnHome.setClickListener(buttonListener);
    }

    @Override
    protected void setupGui() {
        super.setupGui();

        //Get the correct font sizes
        lbTitle.setStyle(UiStyles.getSpaceLabelStyle(ppuY));
        lbPause.setStyle(UiStyles.getTitleLabelStyle(ppuY));

        wrapTable.add(lbTitle).padBottom((int) (10 * ppuY)).padTop((int) (10 * ppuY));
        wrapTable.row();
        wrapTable.add(lbPause).padBottom((int) (5 * ppuY)).padTop((int) (5 * ppuY));

        //Setup Button Row
        addToButtonRow(btnBack);
        addToButtonRow(btnHome);
        wrapTable.row();
        wrapTable.add(buttonTable).bottom().expandY();
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(pauseUiController);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        setupGui();
    }

    private void resumeGame() {
        game.setScreen(game.getGameScreen());
        game.resume();
    }

    private class PauseUiController extends UiController {

        boolean clickedInsideMe = false;

        public PauseUiController() {
            super(game, stage);
        }

        @Override
        public boolean keyDown(int i) {
            stage.keyDown(i);
            if (i == Input.Keys.BACK || i == Input.Keys.ESCAPE) {
                clickedInsideMe = true;
            }
            return false;
        }

        @Override
        public boolean keyUp(int i) {
            stage.keyUp(i);
            if (i == Input.Keys.BACK || i == Input.Keys.ESCAPE) {
                if (clickedInsideMe) {
                    resumeGame();
                    clickedInsideMe = false;
                }
            }
            return false;
        }
    }

    private class PauseButtonListener implements ClickListener {

        @Override
        public void click(Actor actor, float x, float y) {
            if (btnBack.equals(actor)) {
                resumeGame();
            } else if (btnHome.equals(actor)) {
                game.gameOver(0);
                Services.turnOffSound();
            }
        }
    }

}
