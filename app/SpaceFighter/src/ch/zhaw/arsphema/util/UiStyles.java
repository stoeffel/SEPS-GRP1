package ch.zhaw.arsphema.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class UiStyles {

    //LabelStyles
    public static final Label.LabelStyle LABEL_SCREEN_HEADER = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.HEADER_FONT), false), Color.WHITE);
    public static final Label.LabelStyle LABEL_TITLE = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.SPACE_FONT), false), Color.WHITE);
    public static final Label.LabelStyle LABEL_DEFAULT = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.BUTTON_FONT), false), Color.WHITE);
    public static final BitmapFont POINT_FONT = new BitmapFont(Gdx.files.internal(Paths.COUNTER_FONT), false);
    public static final Label.LabelStyle LABEL_POINTS = new Label.LabelStyle(POINT_FONT, Color.WHITE);

    //Component Texture
    private static final Texture COMP_TEXTURES = new Texture(Gdx.files.internal(Paths.COMP_TEXTURES));

    //TextFieldStyle
    public static final TextField.TextFieldStyle TEXT_FIELD_DEFAULT = new TextField.TextFieldStyle(
            new BitmapFont(Gdx.files.internal(Paths.BUTTON_FONT), false),
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

    //IconTexture
    private static final Texture UI_ICON_TEXTURE = new Texture(Gdx.files.internal(Paths.UI_ICONS));
    public static final TextureRegion UI_ICON_TEXTURE_REGION = new TextureRegion(UI_ICON_TEXTURE);
    public static final TextureRegion PLAY_BUTTON_TEXTURE_REGION = new TextureRegion(new Texture(Gdx.files.internal(Paths.PLAY_BUTTON_IMAGE)));
}
