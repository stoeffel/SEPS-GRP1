package ch.zhaw.arsphema.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class UiStyles {

    //LabelStyles
    public static final Label.LabelStyle LABEL_SCREEN_HEADER = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.HEADER_FONT), false), Color.WHITE);
    public static final Label.LabelStyle LABEL_TITLE = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.SPACE_FONT), false), Color.WHITE);
    public static final Label.LabelStyle LABEL_DEFAULT = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.BUTTON_FONT), false), Color.WHITE);

    //ButtonStyle
    private static final Texture UI_TEXTURE = new Texture(Gdx.files.internal(Paths.UI_TEXTURES));
    public static final TextButton.TextButtonStyle BUTTON_DEFAULT = new TextButton.TextButtonStyle(
            new NinePatch(new TextureRegion(UI_TEXTURE,102, 0, 50, 50), 8, 8, 8, 8),
            new NinePatch(new TextureRegion(UI_TEXTURE,0, 0, 50, 50), 8, 8, 8, 8),
            new NinePatch(new TextureRegion(UI_TEXTURE,51, 0, 50, 50), 8, 8, 8, 8),
            1f,
            0f,
            0f,
            0f,
            new BitmapFont(Gdx.files.internal(Paths.BUTTON_FONT), false),
            Color.WHITE,
            null,
            null
    );

    //TextFieldStyle
    public static final TextField.TextFieldStyle TEXT_FIELD_DEFAULT = new TextField.TextFieldStyle(
            new BitmapFont(Gdx.files.internal(Paths.BUTTON_FONT), false),
            Color.DARK_GRAY,
            null,
            null,
            new NinePatch(new TextureRegion(UI_TEXTURE, 204, 0, 1, 1), 0, 0, 0, 0),
            null,
            new NinePatch(new TextureRegion(UI_TEXTURE, 152, 0, 50, 50), 8, 8, 8, 8)
    );
}