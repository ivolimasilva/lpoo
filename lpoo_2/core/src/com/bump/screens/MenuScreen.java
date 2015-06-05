package com.bump.screens;

import com.bump.assets.Assets;
import com.bump.game.Bump;
import com.bump.objects.Button;
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
		bumpButton,
		playButton,
		highScoresButton,
		optionButton,
		exitButton;

	public MenuScreen(Bump game)
	{
		this.game = game;
		guiCam = new OrthographicCamera(Assets.windowWidth, Assets.windowHeight);
		guiCam.position.set(Assets.windowWidth / 2, Assets.windowHeight / 2, 0);
		touchPoint = new Vector3();

		bumpButton = new Button(Assets.buttonBump, 30, Assets.windowHeight - Assets.buttonBump.getHeight() - 30);
		playButton = new Button(Assets.buttonPlay, 30, Assets.windowHeight - Assets.buttonPlay.getHeight() - 160);
		highScoresButton = new Button(Assets.buttonScores, 30, Assets.windowHeight - Assets.buttonScores.getHeight() - 290);
		optionButton = new Button(Assets.buttonOptions, 30, Assets.windowHeight - Assets.buttonOptions.getHeight() - 420);
		exitButton = new Button(Assets.buttonExit, 30, Assets.windowHeight - Assets.buttonExit.getHeight() - 550);
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
			else if (optionButton.bounds.contains(touchPoint.x, touchPoint.y))
				System.out.println("Clicou em Options.");
			else if (exitButton.bounds.contains(touchPoint.x, touchPoint.y))
				Gdx.app.exit();
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
		bumpButton.draw(game.batcher);
		playButton.draw(game.batcher);
		highScoresButton.draw(game.batcher);
		optionButton.draw(game.batcher);
		exitButton.draw(game.batcher);
	}

	public void render(float delta)
	{
		update();
		draw();
	}
}
