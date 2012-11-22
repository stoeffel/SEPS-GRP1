package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.MyGdxGame;
import ch.zhaw.arsphema.util.UiStyles;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class CreditsScreen extends UiScreen {

    private Label lbTitle;
    private Button btnBack;

    public CreditsScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        ClickListener buttonListener = new CreditsButtonListener();

        lbTitle = new Label("Credits", UiStyles.LABEL_SCREEN_HEADER);
        btnBack = new Button(new TextureRegion(UiStyles.UI_ICON_TEXTURE_REGION, 600, 0, 300, 300));
        btnBack.setClickListener(buttonListener);
    }

    @Override
    protected void setupGui() {
        super.setupGui();

        wrapTable.add(lbTitle).padBottom((int) (5 * ppuY)).padTop((int) (5 * ppuY));

        //Setup Button Row
        addToButtonRow(btnBack);
        wrapTable.row();
        wrapTable.add(buttonTable).bottom().expandY();
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        setupGui();
    }

    private class CreditsButtonListener implements ClickListener {

        @Override
        public void click(Actor actor, float x, float y) {
            if (btnBack.equals(actor)) {
                uiController.keyDown(Input.Keys.BACK);
            }
        }
    }
}

