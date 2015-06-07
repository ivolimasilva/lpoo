package com.bump.screens;

import com.bump.assets.Assets;
import com.bump.game.Bump;
import com.bump.objects.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

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
	
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		//System.out.println("touchDown: " + (screenX - Assets.windowWidth / 2) + ", " + (Assets.windowHeight / 2 - screenY) + ", " + pointer + "," + button);
		//System.out.println("Red Quit: (" + buttonRedQuit.bounds.x + "," + buttonRedQuit.bounds.y + "; " + buttonRedQuit.bounds.width + ", " + buttonRedQuit.bounds.height + ").");

		if (playButton.bounds.contains((screenX - Assets.windowWidth / 2), (Assets.windowHeight / 2 - screenY)))
			game.setScreen(new GameScreen(game));
		else if (pastGamesButton.bounds.contains((screenX - Assets.windowWidth / 2), (Assets.windowHeight / 2 - screenY)))
			game.setScreen(new PastGames(game));
		else if (instructionsButton.bounds.contains((screenX - Assets.windowWidth / 2), (Assets.windowHeight / 2 - screenY)))
			System.out.println("Clicou em Options.");
		else if (exitButton.bounds.contains((screenX - Assets.windowWidth / 2), (Assets.windowHeight / 2 - screenY)))
			Gdx.app.exit();
		
		return true;
	}

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

	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	public boolean scrolled(int amount)
	{
		return false;
	}
}
