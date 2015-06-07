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

	public PastGames(Bump game)
	{
		this.game = game;
		guiCam = new OrthographicCamera(Assets.windowWidth, Assets.windowHeight);
		guiCam.position.set(Assets.windowWidth / 2, Assets.windowHeight / 2, 0);

		readScores();

		Gdx.input.setInputProcessor(this);
	}

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

		printScores();
	}

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
			game.batcher.draw(Assets.labelRed, (Assets.windowWidth - 575f) / 2 + 0f, - line * 100 + (Assets.windowHeight - 250) + increment);
			game.batcher.draw(scoreRed, (Assets.windowWidth - 575f) / 2 + 204f, - line * 100 + (Assets.windowHeight - 250) + increment);
			game.batcher.draw(scoreBlue, (Assets.windowWidth - 575f) / 2 + 270f, - line * 100 + (Assets.windowHeight - 250) + increment);
			game.batcher.draw(Assets.labelBlue, (Assets.windowWidth - 575f) / 2 + 346f, - line * 100 + (Assets.windowHeight - 250) + increment);
			if (savedGame.winner == PlayerTurn.PlayerRed)
				game.batcher.draw(Assets.iconWinner, (Assets.windowWidth - 575f) / 2 - 100f, - line * 100 + (Assets.windowHeight - 250) + increment);
			else
				game.batcher.draw(Assets.iconWinner, (Assets.windowWidth - 575f) / 2 + 596f, - line * 100 + (Assets.windowHeight - 250) + increment);
			game.batcher.end();
			line++;
		}
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
