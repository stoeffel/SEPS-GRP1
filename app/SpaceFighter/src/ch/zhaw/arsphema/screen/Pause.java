package ch.zhaw.arsphema.screen;

import ch.zhaw.arsphema.util.Paths;

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
		pause.width = Renderer.WORLD_WIDTH / 5;
		pause.height = Renderer.WORLD_HEIGHT / 5;
	}

	public void draw(SpriteBatch batch) {
		pause.x = Renderer.WORLD_WIDTH / 2 - pause.width /2;
		pause.y = Renderer.WORLD_HEIGHT / 2 - pause.height/2;
		pause.setText("Paused...");
		pause.draw(batch, 1);
	}

}
