package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.controller.UiController;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.Gdx;

abstract class UiScreen extends AbstractScreen {

    protected final UiController uiController;
	protected float ppuX; // pixels per unit on the X axis
	protected float ppuY; // pixels per unit on the Y axis

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
    
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		ppuX =  width / Sizes.DEFAULT_WORLD_WIDTH;
		ppuY = height / Sizes.DEFAULT_WORLD_HEIGHT;
	}
}
