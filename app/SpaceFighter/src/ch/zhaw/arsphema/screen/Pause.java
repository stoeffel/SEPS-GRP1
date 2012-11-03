package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.util.Paths;
import ch.zhaw.arsphema.util.Sizes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Pause {

	private Label pause;

	public Pause() {
		LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(
				Gdx.files.internal(Paths.SPACE_FONT), false), Color.WHITE);
		pause = new Label("Arsphema", labelStyle);
		pause.width = Sizes.DEFAULT_WORLD_WIDTH / 5;
		pause.height = Sizes.DEFAULT_WORLD_HEIGHT / 5;
	}

	public void draw(SpriteBatch batch, float ppuX, float ppuY) {
		pause.x = Sizes.DEFAULT_WORLD_WIDTH*ppuX / 2 - pause.width*ppuX/2;
		pause.y = Sizes.DEFAULT_WORLD_HEIGHT*ppuY / 2 - pause.height*ppuY/2;
		pause.setText("Paused...");
		pause.draw(batch, 1);
	}

}
