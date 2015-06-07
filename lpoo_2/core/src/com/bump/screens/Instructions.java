package com.bump.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.bump.assets.Assets;
import com.bump.game.Bump;

public class Instructions extends ScreenAdapter implements InputProcessor
{
	private Bump
		game;
	private OrthographicCamera
		guiCam;
	private boolean
		touchDraggedDetected = false;
	private int
		lastScreenY = 0;
	private float
		increment = 0;

	public Instructions(Bump _game)
	{
		this.game = _game;
		guiCam = new OrthographicCamera(Assets.windowWidth, Assets.windowHeight);
		guiCam.position.set(Assets.windowWidth / 2, Assets.windowHeight / 2, 0);

		Gdx.input.setInputProcessor(this);
	}

	public void render(float delta)
	{
		guiCam.update();

		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundMenu, 0f, 0f);
		game.batcher.draw(Assets.tileBump, (Assets.windowWidth - Assets.tileBump.getWidth()) / 2, Assets.windowHeight - Assets.tileBump.getHeight());
		game.batcher.end();
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		touchDraggedDetected = false;
		//System.out.println("> " + screenX + ", " + screenY);
		return true;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if (!touchDraggedDetected)
		{
			//System.out.println("touchDown + touchUp!");
			game.setScreen(new MenuScreen(game));
		}
		return true;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		touchDraggedDetected = true;
		if (screenY > lastScreenY)
		{
			increment -= 10;
			//System.out.println("Roda para baixo");
		}
		else
		{
			increment += 10;
			//System.out.println("Roda para cima");
		}
		lastScreenY = screenY;
		return true;
	}

	public boolean scrolled(int amount)
	{
		if (amount > 0)
		{
			increment += 25;
			//System.out.println("Roda para baixo");
		}
		else
		{
			increment -= 25;
			//System.out.println("Roda para cima");
		}
		return true;
	}

	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	public boolean keyDown(int keycode)
	{
		return false;
	}

	public boolean keyUp(int keycode)
	{
		return false;
	}

	public boolean keyTyped(char character)
	{
		return false;
	}
}