package com.bump.screens;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.bump.assets.Assets;
import com.bump.game.Bump;
import com.bump.objects.SavedGame;
import com.bump.screens.GameScreen.PlayerTurn;

/**
 * PastGames.java
 * @author Ivo and Mariana
 * @see com.badlogic.gdx.ScreenAdapter
 */
public class PastGames extends ScreenAdapter implements InputProcessor
{
	private Bump
		game;
	private OrthographicCamera
		guiCam;
	private boolean
		touchDraggedDetected = false;
	private int
		lastScreenY = 0;
	private ArrayList<SavedGame>
		savedGames = new ArrayList<SavedGame>();
	private int
		line = 1;
	private float
		increment = 0;

	/**
	 * Creates a PastGames Panel for a given Game
	 * @param game Game who's parent of this PastGames Panel
	 */
	public PastGames(Bump game)
	{
		this.game = game;
		guiCam = new OrthographicCamera(Assets.windowWidth, Assets.windowHeight);
		guiCam.position.set(Assets.windowWidth / 2, Assets.windowHeight / 2, 0);

		readScores();

		Gdx.input.setInputProcessor(this);
	}

	/**
	 * Reads a SavedGame to a file
	 * @see SavedGame.java
	 */
	private void readScores()
	{
		File folder = new File("BumpSavedGames/");
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++)
		{
			if (listOfFiles[i].isFile())
			{
				//System.out.println("File " + listOfFiles[i].getAbsolutePath());
				addFileToScope(listOfFiles[i].getAbsolutePath());
			}
		}
		
		Collections.reverse(savedGames);
	}

	/**
	 * Adds a SavedGame from a file to the Array of SavedGames
	 * @param fileName filepath of the file which contains a SavedGame
	 */
	private void addFileToScope(String fileName)
	{
		try
		{
			FileInputStream fin = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);
			savedGames.add((SavedGame) ois.readObject());
			ois.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException q)
		{
			q.printStackTrace();
		}
	}

	/**
	 * Function called to paint the screen.
	 * @see com.badlogic.gdx.Game#render()
	 */
	public void render(float delta)
	{
		guiCam.update();

		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundMenu, 0f, 0f);
		game.batcher.end();

		printScores();
		
		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.header, 0f, Assets.windowHeight - Assets.header.getHeight());
		game.batcher.draw(Assets.footerPastGames, 0f, 0f);
		game.batcher.end();
	}

	/**
	 * Function called to paint the scores.
	 */
	private void printScores()
	{
		Texture
			scoreRed,
			scoreBlue;
		line = 0;
		for (SavedGame savedGame: savedGames)
		{
			//System.out.println(savedGame.winner);
			//System.out.println("Red: " + savedGame.pointsRed + " - " + savedGame.pointsBlue + " :Blue");
			scoreRed = new Texture("pastgames/red/" + savedGame.pointsRed + ".png");
			scoreBlue = new Texture("pastgames/blue/" + savedGame.pointsBlue + ".png");
			game.batcher.enableBlending();
			game.batcher.begin();
			game.batcher.draw(Assets.labelPlayers, (Assets.windowWidth - Assets.labelPlayers.getWidth()) / 2, - line * 130f + (Assets.windowHeight - 260f) + increment);
			game.batcher.draw(scoreRed, 565f, - line * 130f + (Assets.windowHeight - 240f) + increment);
			game.batcher.draw(scoreBlue, 660f, - line * 130f + (Assets.windowHeight - 240f) + increment);
			if (savedGame.winner == PlayerTurn.PlayerRed)
				game.batcher.draw(Assets.iconWinner, 342f, - line * 130f + (Assets.windowHeight - 233f) + increment);
			else
				game.batcher.draw(Assets.iconWinner, 907f, - line * 130f + (Assets.windowHeight - 233f) + increment);
			game.batcher.end();
			line++;
		}
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#touchDown(int, int, int, int)
	 */
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		touchDraggedDetected = false;
		//System.out.println("> " + screenX + ", " + screenY);
		return true;
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
	 */
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if (!touchDraggedDetected)
		{
			//System.out.println("touchDown + touchUp!");
			game.setScreen(new MenuScreen(game));
		}
		return true;
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#touchDragged(int, int, int)
	 */
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

	/**
	 * @see com.badlogic.gdx.InputProcessor#scrolled(int)
	 */
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
