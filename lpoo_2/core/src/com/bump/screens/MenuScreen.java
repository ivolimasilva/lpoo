package com.bump.screens;

import com.bump.assets.Assets;
import com.bump.game.Bump;
import com.bump.gui_objects.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen extends ScreenAdapter
{
	private Bump
		game;
	OrthographicCamera
		guiCam;
	Vector3
		touchPoint;
	Button
		playButton,
		highScoresButton;

	public MenuScreen(Bump game)
	{
		this.game = game;
		guiCam = new OrthographicCamera(Assets.width, Assets.height);
		guiCam.position.set(Assets.width / 2, Assets.height / 2, 0);
		touchPoint = new Vector3();

		playButton = new Button(Assets.buttonPlay, (Assets.width - 250) / 2, (Assets.height + 150) / 2, 250, 100);
		highScoresButton = new Button(Assets.buttonPlay, (Assets.width - 250) / 2, (Assets.height - 100) / 2, 250, 100);
	}

	public void update()
	{
		if (Gdx.input.justTouched())
		{
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			//System.out.println ("Tocou em (" + touchPoint.x + ", " + touchPoint.y + ").");

			if (playButton.bounds.contains(touchPoint.x, touchPoint.y))
				game.setScreen(new GameScreen(game));
			else if (highScoresButton.bounds.contains(touchPoint.x, touchPoint.y))
				game.setScreen(new Highscores(game));
		}
	}

	public void draw()
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundMenu, 0, 0);
		game.batcher.end();
		
		game.batcher.disableBlending();
		playButton.draw(game.batcher);
		highScoresButton.draw(game.batcher);
	}

	public void render(float delta)
	{
		update();
		draw();
	}
}
