package com.bump.screens;

import com.bump.assets.Assets;
import com.bump.game.Bump;
import com.bump.objects.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * MenuScreen.java
 * @author Ivo and Mariana
 * @see com.badlogic.gdx.ScreenAdapter
 */
public class MenuScreen extends ScreenAdapter implements InputProcessor
{
	private Bump
		game;
	OrthographicCamera
		guiCam;
	Button
		bumpButton,
		playButton,
		pastGamesButton,
		instructionsButton,
		exitButton;

	/**
	 * Creates a MenuScreen Panel for a given Game
	 * @param game Game who's parent of this MenuScreen Panel
	 */
	public MenuScreen(Bump game)
	{
		this.game = game;
		guiCam = new OrthographicCamera(Assets.windowWidth, Assets.windowHeight);
		guiCam.position.set(Assets.windowWidth / 2, Assets.windowHeight / 2, 0);

		bumpButton = new Button(Assets.buttonBump, 30, Assets.windowHeight - Assets.buttonBump.getHeight() - 30);
		playButton = new Button(Assets.buttonPlay, 30, Assets.windowHeight - Assets.buttonPlay.getHeight() - 160);
		pastGamesButton = new Button(Assets.buttonPastGames, 30, Assets.windowHeight - Assets.buttonPastGames.getHeight() - 290);
		instructionsButton = new Button(Assets.buttonInstructions, 30, Assets.windowHeight - Assets.buttonInstructions.getHeight() - 420);
		exitButton = new Button(Assets.buttonExit, 30, Assets.windowHeight - Assets.buttonExit.getHeight() - 550);
		
		Gdx.input.setInputProcessor(this);
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
	 */
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		//System.out.println("touchDown: " + (screenX - Assets.windowWidth / 2) + ", " + (Assets.windowHeight / 2 - screenY) + ", " + pointer + "," + button);
		//System.out.println("Red Quit: (" + buttonRedQuit.bounds.x + "," + buttonRedQuit.bounds.y + "; " + buttonRedQuit.bounds.width + ", " + buttonRedQuit.bounds.height + ").");

		if (playButton.bounds.contains((screenX - Assets.windowWidth / 2), (Assets.windowHeight / 2 - screenY)))
			game.setScreen(new GameScreen(game));
		else if (pastGamesButton.bounds.contains((screenX - Assets.windowWidth / 2), (Assets.windowHeight / 2 - screenY)))
			game.setScreen(new PastGames(game));
		else if (instructionsButton.bounds.contains((screenX - Assets.windowWidth / 2), (Assets.windowHeight / 2 - screenY)))
			game.setScreen(new Instructions(game));
		else if (exitButton.bounds.contains((screenX - Assets.windowWidth / 2), (Assets.windowHeight / 2 - screenY)))
			Gdx.app.exit();
		
		return true;
	}

	/**
	 * Function called to paint the screen.
	 * @see com.badlogic.gdx.Game#render()
	 */
	public void render(float delta)
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
		pastGamesButton.draw(game.batcher);
		instructionsButton.draw(game.batcher);
		exitButton.draw(game.batcher);
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
	 * @see com.badlogic.gdx.InputProcessor#mouseMoved(int, int)
	 */
	public boolean mouseMoved(int screenX, int screenY)
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
}
