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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

public class CreditsScreen extends UiScreen {

    private Table creditTable;
    private ScrollPane scrollPane;
    private Label lbTitle;
    private Button btnBack;

    public CreditsScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        ClickListener buttonListener = new CreditsButtonListener();

        creditTable = new Table();
        scrollPane = new ScrollPane(null, UiStyles.SCROLL_PANE_STYLE);
        lbTitle = new Label("Credits", UiStyles.getTitleLabelStyle(0));
        btnBack = new Button(new TextureRegion(UiStyles.UI_ICON_TEXTURE_REGION, 600, 0, 300, 300));
        btnBack.setClickListener(buttonListener);
    }

    @Override
    protected void setupGui() {
        super.setupGui();

        //Get the correct font sizes
        lbTitle.setStyle(UiStyles.getTitleLabelStyle(ppuY));

        wrapTable.add(lbTitle).padBottom((int) (5 * ppuY)).padTop((int) (5 * ppuY));
        wrapTable.row();

        creditTable.clear();
        creditTable.width((int) (70 * ppuX));


        addCreditTitle("= Project Team =");
        addCredit("Christoph Hermann");
        addCredit("Rafael Arizcorreta");
        addCredit("Raphael Spörri");
        addCredit("Daniel Magalhaes-Ferreira");
        addCredit("~");
        addCredit(" ");
        addCreditTitle("= Additional Graphics and Pictograms =");
        addCredit("Kolleg vom Stöffel");
        addCredit("Daniel Bruce - www.entypo.com");
        addCredit("~");
        addCredit(" ");

        scrollPane.clear();
        scrollPane.setWidget(creditTable);
        wrapTable.add(scrollPane);
        wrapTable.row();

        //Setup Button Row
        addToButtonRow(btnBack);
        wrapTable.row();
        wrapTable.add(buttonTable).bottom().expandY();
    }

    private void addCreditTitle(String credit) {
        creditTable.add(new Label(credit, UiStyles.getTextLabelStyle(ppuY))).padBottom((int) (1 * ppuY)).expandX().center();
        creditTable.row();
    }

    private void addCredit(String credit) {
        creditTable.add(new Label(credit, UiStyles.getCreditLabelStyle(ppuY))).expandX().center();
        creditTable.row();
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

