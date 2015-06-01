package com.bump.screens;

import com.bump.assets.Assets;
import com.bump.game.Bump;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen extends ScreenAdapter
{
	private Bump game;
	OrthographicCamera guiCam;
	Rectangle playBounds;
	Vector3 touchPoint;

	public MenuScreen(Bump game)
	{
		this.game = game;
		guiCam = new OrthographicCamera(1280, 720);
		guiCam.position.set(1280 / 2, 720 / 2, 0);
		touchPoint = new Vector3();
		
		playBounds = new Rectangle(((1280 - 100) / 2) - (1280 / 2), ((720 - 30) / 2) - (720 / 2), 100, 30);
	}

	public void update()
	{
		if (Gdx.input.justTouched())
		{
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			System.out.print("Tocou aqui (" + touchPoint.x + ", " + touchPoint.y + ")");
			
			if (playBounds.contains(touchPoint.x, touchPoint.y))
			{
				System.out.print(" e acertou!\n");
				game.setScreen(new GameScreen(game));
			}
			else
				System.out.println();
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
		game.batcher.begin();
		game.batcher.draw(Assets.buttonPlay, (1280 - 100) / 2, (720 - 30) / 2);
		game.batcher.end();
	}

	public void render(float delta)
	{
		update();
		draw();
	}
}
