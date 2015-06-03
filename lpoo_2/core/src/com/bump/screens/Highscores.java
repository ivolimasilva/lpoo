package com.bump.screens;

import com.bump.assets.Assets;
import com.bump.game.Bump;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Highscores extends ScreenAdapter
{
	private Bump game;
	Rectangle playBounds;
	Vector3 touchPoint;

	public Highscores(Bump game)
	{
		this.game = game;
		touchPoint = new Vector3 ();
	}

	public void update()
	{
		if (Gdx.input.justTouched())
		{
			game.setScreen(new MenuScreen(game));
		}
	}

	public void draw()
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batcher.begin();
		game.batcher.draw(Assets.backgroundHighscores, 0, 0);
		game.batcher.end();
	}

	public void render(float delta)
	{
		update();
		draw();
	}
}
