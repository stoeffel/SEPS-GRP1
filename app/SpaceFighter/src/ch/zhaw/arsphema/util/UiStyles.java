package ch.zhaw.arsphema.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class UiStyles {

    private static final int SIZE_SWITCH_POINT = 7;

    //Fonts
    public static final BitmapFont POINT_FONT_BIG = SpaceAssetManager.getInstance().get(Paths.POINT_FONT_BIG, BitmapFont.class);
    public static final BitmapFont POINT_FONT_SMALL = SpaceAssetManager.getInstance().get(Paths.POINT_FONT_SMALL, BitmapFont.class);
    public static final BitmapFont SPACE_FONT_BIG = SpaceAssetManager.getInstance().get(Paths.SPACE_FONT_BIG, BitmapFont.class);
    public static final BitmapFont SPACE_FONT_SMALL = SpaceAssetManager.getInstance().get(Paths.SPACE_FONT_SMALL, BitmapFont.class);
    public static final BitmapFont TEXT_FONT_BIG = SpaceAssetManager.getInstance().get(Paths.TEXT_FONT_BIG, BitmapFont.class);
    public static final BitmapFont TEXT_FONT_SMALL = SpaceAssetManager.getInstance().get(Paths.TEXT_FONT_SMALL, BitmapFont.class);
    public static final BitmapFont TITLE_FONT_BIG = SpaceAssetManager.getInstance().get(Paths.TITLE_FONT_BIG, BitmapFont.class);
    public static final BitmapFont TITLE_FONT_SMALL = SpaceAssetManager.getInstance().get(Paths.TITLE_FONT_SMALL, BitmapFont.class);
    public static final BitmapFont CREDIT_FONT_BIG = SpaceAssetManager.getInstance().get(Paths.CREDIT_FONT_BIG, BitmapFont.class);
    public static final BitmapFont CREDIT_FONT_SMALL = SpaceAssetManager.getInstance().get(Paths.CREDIT_FONT_SMALL, BitmapFont.class);

    //LabelStyles
    public static final Label.LabelStyle LABEL_GAME_BIG = new Label.LabelStyle(SPACE_FONT_BIG, Color.WHITE);
    public static final Label.LabelStyle LABEL_GAME_SMALL = new Label.LabelStyle(SPACE_FONT_SMALL, Color.WHITE);
    public static final Label.LabelStyle LABEL_TEXT_BIG = new Label.LabelStyle(TEXT_FONT_BIG, Color.WHITE);
    public static final Label.LabelStyle LABEL_TEXT_SMALL = new Label.LabelStyle(TEXT_FONT_SMALL, Color.WHITE);
    public static final Label.LabelStyle LABEL_TITLE_BIG = new Label.LabelStyle(TITLE_FONT_BIG, Color.WHITE);
    public static final Label.LabelStyle LABEL_TITLE_SMALL = new Label.LabelStyle(TITLE_FONT_SMALL, Color.WHITE);
    public static final Label.LabelStyle LABEL_POINT_BIG = new Label.LabelStyle(POINT_FONT_BIG, Color.WHITE);
    public static final Label.LabelStyle LABEL_POINT_SMALL = new Label.LabelStyle(POINT_FONT_SMALL, Color.WHITE);
    public static final Label.LabelStyle LABEL_CREDIT_BIG = new Label.LabelStyle(CREDIT_FONT_BIG, Color.WHITE);
    public static final Label.LabelStyle LABEL_CREDIT_SMALL = new Label.LabelStyle(CREDIT_FONT_SMALL, Color.WHITE);
    public static final Label.LabelStyle CH_BIG = new Label.LabelStyle(CREDIT_FONT_BIG, Color.GREEN);
    public static final Label.LabelStyle CH_SMALL = new Label.LabelStyle(CREDIT_FONT_SMALL, Color.GREEN);

    //Component Texture
    private static final Texture COMP_TEXTURES = new Texture(Gdx.files.internal(Paths.COMP_TEXTURES));

    //TextFieldStyle
    public static final TextField.TextFieldStyle TEXT_FIELD_BIG = new TextField.TextFieldStyle(
            TEXT_FONT_BIG,
            Color.WHITE,
            null,
            null,
            new NinePatch(new TextureRegion(COMP_TEXTURES, 51, 0, 2, 2), 0, 0, 0, 0),
            null,
            new NinePatch(new TextureRegion(COMP_TEXTURES, 0, 0, 50, 50), 6, 6, 6, 6)
    );

    public static final TextField.TextFieldStyle TEXT_FIELD_SMALL = new TextField.TextFieldStyle(
            TEXT_FONT_SMALL,
            Color.WHITE,
            null,
            null,
            new NinePatch(new TextureRegion(COMP_TEXTURES, 51, 0, 2, 2), 0, 0, 0, 0),
            null,
            new NinePatch(new TextureRegion(COMP_TEXTURES, 0, 0, 50, 50), 6, 6, 6, 6)
    );

    //SliderStyle
    public final static Slider.SliderStyle SLIDER_STYLE = new Slider.SliderStyle(
            new NinePatch(new TextureRegion(COMP_TEXTURES, 53, 0, 22, 3), 2, 2, 0, 0),
            new TextureRegion(COMP_TEXTURES, 53, 3, 22, 22)
    );

    //Scrollpane Style - Just vertical designed!
    public final static ScrollPane.ScrollPaneStyle SCROLL_PANE_STYLE = new ScrollPane.ScrollPaneStyle(
            null,
            new NinePatch(new TextureRegion(COMP_TEXTURES, 53, 47, 3, 3), 1, 1, 1, 1),   //No designed graphic (libgdx is not nullsafe)
            new NinePatch(new TextureRegion(COMP_TEXTURES, 53, 47, 3, 3), 1, 1, 1, 1), //No designed graphic (libgdx is not nullsafe)
            new NinePatch(new TextureRegion(COMP_TEXTURES, 53, 47, 22, 3), 9, 9, 1, 1),
            new NinePatch(new TextureRegion(COMP_TEXTURES, 53, 24, 22, 23), 0, 0, 11, 11)

    );
    

    //IconTexture
    private static final Texture UI_ICON_TEXTURE = new Texture(Gdx.files.internal(Paths.UI_ICONS));
    public static final TextureRegion UI_ICON_TEXTURE_REGION = new TextureRegion(UI_ICON_TEXTURE);
    public static final TextureRegion PLAY_BUTTON_TEXTURE_REGION = new TextureRegion(new Texture(Gdx.files.internal(Paths.PLAY_BUTTON_IMAGE)));


    public static Label.LabelStyle getSpaceLabelStyle(float ppuY) {
        return ppuY > SIZE_SWITCH_POINT ? LABEL_GAME_BIG : LABEL_GAME_SMALL;
    }

    public static Label.LabelStyle getTextLabelStyle(float ppuY) {
        return ppuY > SIZE_SWITCH_POINT ? LABEL_TEXT_BIG : LABEL_TEXT_SMALL;
    }

    public static Label.LabelStyle getTitleLabelStyle(float ppuY) {
        return ppuY > SIZE_SWITCH_POINT ? LABEL_TITLE_BIG : LABEL_TITLE_SMALL;
    }

    public static Label.LabelStyle getPointLabelStyle(float ppuY) {
        return ppuY > SIZE_SWITCH_POINT ? LABEL_POINT_BIG : LABEL_POINT_SMALL;
    }

    public static Label.LabelStyle getCreditLabelStyle(float ppuY) {
        return ppuY > SIZE_SWITCH_POINT ? LABEL_CREDIT_BIG : LABEL_CREDIT_SMALL;
    }

    public static TextField.TextFieldStyle getTextFieldStyle(float ppuY) {
        return ppuY > SIZE_SWITCH_POINT ? TEXT_FIELD_BIG : TEXT_FIELD_SMALL;
    }

	public static Label.LabelStyle getCHLabelStyle(float ppuY) {
		return ppuY > SIZE_SWITCH_POINT ? CH_BIG : CH_SMALL;
	}


}
