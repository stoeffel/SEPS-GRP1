package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.controller.UiController;
import ch.zhaw.arsphema.util.Sizes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

abstract class UiScreen extends AbstractScreen {

    protected final UiController uiController;
    protected float ppuX; // pixels per unit on the X axis
    protected float ppuY; // pixels per unit on the Y axis
    protected Table wrapTable;
    protected Table buttonTable;

    public UiScreen(MyGdxGame game) {
        super(game);

        //InputProcessor
        uiController = new UiController(game, stage);
        initComponents();
    }

    protected void initComponents() {
        wrapTable = new Table();
        wrapTable.setFillParent(true);
        buttonTable = new Table();
    }

    protected void setupGui() {
        stage.clear();
        wrapTable.clear();
        buttonTable.clear();
        stage.addActor(wrapTable);
        stage.addActor(wrapTable);
    }


    protected void addToButtonRow(Button button) {
        buttonTable.add(button).
                width((int) (Sizes.ICON_BUTTON_SIDE * ppuY)).
                height((int) (Sizes.ICON_BUTTON_SIDE * ppuY)).
                pad((int) (Sizes.ICON_BUTTON_PADDING * ppuY));
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(uiController);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ppuX = width / Sizes.DEFAULT_WORLD_WIDTH;
        ppuY = height / Sizes.DEFAULT_WORLD_HEIGHT;
    }
}
