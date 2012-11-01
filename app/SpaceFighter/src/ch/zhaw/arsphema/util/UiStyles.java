package ch.zhaw.arsphema.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class UiStyles {

    //LabelStyles
    public static final Label.LabelStyle LABEL_SCREEN_HEADER = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.HEADER_FONT), false), Color.WHITE);
    public static final Label.LabelStyle LABEL_TITLE = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.SPACE_FONT), false), Color.WHITE);
    public static final Label.LabelStyle LABEL_DEFAULT = new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Paths.BUTTON_FONT), false), Color.WHITE);

    //ButtonStyle
    public static final TextButton.TextButtonStyle BUTTON_STYLE = new TextButton.TextButtonStyle(
            null,
            new NinePatch(new Texture(Gdx.files.internal(Paths.BUTTON_TEXTURE)), 8, 8, 8, 8),
            null,
            1f,
            0f,
            0f,
            0f,
            new BitmapFont(Gdx.files.internal(Paths.BUTTON_FONT), false),
            Color.DARK_GRAY,
            null,
            null
    );
}
