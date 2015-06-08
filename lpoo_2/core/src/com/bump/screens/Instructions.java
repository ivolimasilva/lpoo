package com.bump.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.bump.assets.Assets;
import com.bump.game.Bump;

public class Instructions extends ScreenAdapter implements InputProcessor
{
	private Bump
		game;
	private OrthographicCamera
		guiCam;
	private int
		currentFrame = 237;
	Texture
		animation;
	private boolean
		animationActive;

	public Instructions(Bump _game)
	{
		this.game = _game;
		guiCam = new OrthographicCamera(Assets.windowWidth, Assets.windowHeight);
		guiCam.position.set(Assets.windowWidth / 2, Assets.windowHeight / 2, 0);

		Gdx.input.setInputProcessor(this);
	}

	public void render(float delta)
	{
		animation = new Texture("instructions/frames/frame_(" + currentFrame + ").bmp");
		if (currentFrame == 237)
			animationActive = false;
		if (animationActive)
			currentFrame++;
		guiCam.update();

		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundInstructions, 0f, 0f);
		
		game.batcher.draw(animation, 160f, 300f);
		
		game.batcher.draw(Assets.header, 0f, Assets.windowHeight - Assets.header.getHeight());
		game.batcher.draw(Assets.footerInstructions, 0f, 0f);
		game.batcher.end();
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		Rectangle bounds = new Rectangle(160f, 300f, animation.getWidth(), animation.getHeight());
		if (bounds.contains(screenX, screenY))
		{
			currentFrame = 0;
			animationActive = true;
		}
		else
			game.setScreen(new MenuScreen(game));
		
		return true;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	public boolean scrolled(int amount)
	{
		return false;
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