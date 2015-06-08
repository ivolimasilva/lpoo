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

/**
 * Instructions.java
 * @author Ivo and Mariana
 * @see com.badlogic.gdx.ScreenAdapter
 */
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

	/**
	 * Creates a Instructions Panel for a given Game
	 * @param _game Game who's parent of this Instructions Panel
	 */
	public Instructions(Bump _game)
	{
		this.game = _game;
		guiCam = new OrthographicCamera(Assets.windowWidth, Assets.windowHeight);
		guiCam.position.set(Assets.windowWidth / 2, Assets.windowHeight / 2, 0);

		Gdx.input.setInputProcessor(this);
	}

	/**
	 * Function called to paint the screen.
	 * @see com.badlogic.gdx.Game#render()
	 */
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

	/**
	 * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
	 */
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		Rectangle bounds = new Rectangle(160f - Assets.windowWidth / 2, 300f - Assets.windowHeight / 2, animation.getWidth(), animation.getHeight());
		if (bounds.contains((screenX - Assets.windowWidth / 2), (Assets.windowHeight / 2 - screenY)))
		{
			currentFrame = 0;
			animationActive = true;
		}
		else
			game.setScreen(new MenuScreen(game));
		
		return true;
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#touchDown(int, int, int, int)
	 */
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#touchDragged(int, int, int)
	 */
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#scrolled(int)
	 */
	public boolean scrolled(int amount)
	{
		return false;
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#mouseMoved(int, int)
	 */
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#keyDown(int)
	 */
	public boolean keyDown(int keycode)
	{
		return false;
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#keyUp(int)
	 */
	public boolean keyUp(int keycode)
	{
		return false;
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#keyTyped(char)
	 */
	public boolean keyTyped(char character)
	{
		return false;
	}
}