package com.bump.screens;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bump.game.Bump;
import com.bump.objects.Wall;
import com.bump.objects.Ball;
import com.bump.assets.Assets;
import com.bump.objects.Button;
import com.bump.objects.Piece;
import com.bump.objects.SavedGame;
import com.bump.objects.Square;
import com.bump.objects.Triangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * GameScreen.java
 * @author Ivo and Mariana
 * @see com.badlogic.gdx.ScreenAdapter
 */
public class GameScreen extends ScreenAdapter implements InputProcessor
{
	// General vars
	private Bump
		game;
	OrthographicCamera
		guiCam;
	private Wall
		wallTop,
		wallBottom,
		wallLeft,
		wallRight;
	ArrayList<Piece>
		piecesGlobal = new ArrayList<Piece>();
	Piece
		selectedPiece,
		pieceToPlay;
	World
		world;
	Box2DDebugRenderer
		debugRenderer;
	Matrix4
		debugMatrix;
	public enum
		PlayerTurn {PlayerRed, PlayerBlue, NULL}
	PlayerTurn
		playerTurn;
	float
		torque = 0.0f;
	int
		state = 0,
		startX,
		startY;
	boolean
		nextPiece = false;
	Button
		buttonGameOver;
	PlayerTurn
		winner;
	
	// Player 1 vars
	ArrayList<Piece>
		piecesPlayer1 = new ArrayList<Piece>();
	Ball
		ball1;
	Triangle
		triangle1;
	Square
		square1;
	Sprite
		spritePlayer1Square,
		spritePlayer1Ball,
		spritePlayer1Triangle;
	Button
		buttonRedQuit;
	Integer
		points1 = 0,
		ronda1 = 0,
		nrRondas1 = 3;
	Texture
		score1;

	// Player 2 vars
	ArrayList<Piece>
		piecesPlayer2 = new ArrayList<Piece>();
	Ball
		ball2;
	Triangle
		triangle2;
	Square
		square2;
	Sprite
		spritePlayer2Square,
		spritePlayer2Ball,
		spritePlayer2Triangle;
	Button
		buttonBlueQuit;
	Integer
		points2 = 0,
		ronda2 = 0,
		nrRondas2 = 3;
	Texture
		score2;

	/**
	 * Creates a GameScreen for a given Game
	 * @param game Game who's parent of this GameScreen
	 */
	public GameScreen(Bump game)
	{
		this.game = game;
		guiCam = new OrthographicCamera(Assets.windowWidth, Assets.windowHeight);
		guiCam.position.set(Assets.windowWidth / 2, Assets.windowHeight / 2, 0);
		
		buttonRedQuit = new Button(Assets.buttonRedQuit, 25, 25);
		buttonBlueQuit = new Button(Assets.buttonBlueQuit, Assets.windowWidth - 125, Assets.windowHeight - 125);
		
		buttonGameOver = new Button(Assets.buttonDraw, - Assets.windowWidth, - Assets.windowHeight);

		spritePlayer1Square = new Sprite(Assets.spriteRedSquare);
		spritePlayer2Square = new Sprite(Assets.spriteBlueSquare);
		spritePlayer1Ball = new Sprite(Assets.spriteRedBall);
		spritePlayer2Ball = new Sprite(Assets.spriteBlueBall);
		spritePlayer1Triangle = new Sprite(Assets.spriteRedTriangle);
		spritePlayer2Triangle = new Sprite(Assets.spriteBlueTriangle);

		score1 = new Texture("game/red/score/" + points1 + ".png");
		score2 = new Texture("game/blue/score/" + points2 + ".png");

		world = new World(new Vector2(0, 0f), true);
		
		createWalls();
		createPlayer1Pieces();
		createPlayer2Pieces();

		playerTurn = PlayerTurn.PlayerRed;
		pieceToPlay = piecesPlayer1.get(ronda1);
		pieceToPlay.setToPenalty(playerTurn);
		
		Gdx.input.setInputProcessor(this);

		debugRenderer = new Box2DDebugRenderer();
	}

	/**
	 * Creates the pieces of Player Red
	 */
	public void createPlayer1Pieces()
	{
		square1 = new Square(PlayerTurn.PlayerRed, world, spritePlayer1Square, -100f, -100f);
		piecesPlayer1.add(square1);
		ball1 = new Ball(PlayerTurn.PlayerRed, world, spritePlayer1Ball, -100f, -100f);
		piecesPlayer1.add(ball1);
		triangle1 = new Triangle(PlayerTurn.PlayerRed, world, spritePlayer1Triangle, -100f, -100f);
		piecesPlayer1.add(triangle1);

		piecesGlobal.add(square1);
		piecesGlobal.add(ball1);
		piecesGlobal.add(triangle1);
	}

	/**
	 * Creates the pieces of Player Blue
	 */
	public void createPlayer2Pieces()
	{
		square2 = new Square(PlayerTurn.PlayerBlue, world, spritePlayer2Square, -100f, -100f);
		piecesPlayer2.add(square2);
		ball2 = new Ball(PlayerTurn.PlayerBlue, world, spritePlayer2Ball, -100f, -100f);
		piecesPlayer2.add(ball2);
		triangle2 = new Triangle(PlayerTurn.PlayerBlue, world, spritePlayer2Triangle, -100f, -100f);
		piecesPlayer2.add(triangle2);

		piecesGlobal.add(square2);
		piecesGlobal.add(ball2);
		piecesGlobal.add(triangle2);
	}

	/**
	 * Creates the walls for the world of this GameScreen
	 */
	public void createWalls()
	{
		wallTop = new Wall();
		wallTop.createWall(world, Assets.windowWidth / Assets.PIXELS_TO_METERS, Assets.windowHeight / Assets.PIXELS_TO_METERS, Assets.windowWidth / Assets.PIXELS_TO_METERS, 0);

		wallBottom = new Wall();
		wallBottom.createWall(world, Assets.windowWidth / Assets.PIXELS_TO_METERS, 0, Assets.windowWidth / Assets.PIXELS_TO_METERS, 0);

		wallLeft = new Wall();
		wallLeft.createWall(world, 0, 0, 0, Assets.windowHeight / Assets.PIXELS_TO_METERS);
		
		wallRight = new Wall();
		wallRight.createWall(world, Assets.windowWidth / Assets.PIXELS_TO_METERS, 0, 0, Assets.windowHeight / Assets.PIXELS_TO_METERS);
	}

	/**
	 * Function called to paint the screen.
	 * @see com.badlogic.gdx.Game#render()
	 */
	public void render(float delta)
	{
		guiCam.update();
		world.step(1f / 60f, 6, 2);

		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundGame, 0f, 0f);
		game.batcher.draw(score1, 25f, Assets.windowHeight - 125f);
		game.batcher.draw(score2, Assets.windowWidth - 125f, 25f);
		game.batcher.end();
		
		buttonRedQuit.draw(game.batcher);
		buttonBlueQuit.draw(game.batcher);

		for (Piece piece: piecesGlobal)
		{
			piece.body.applyTorque(torque, true);
			piece.sprite.setPosition((piece.body.getPosition().x * Assets.PIXELS_TO_METERS) - piece.sprite.getWidth() / 2, (piece.body.getPosition().y * Assets.PIXELS_TO_METERS) - piece.sprite.getHeight() / 2);
			piece.sprite.setRotation((float) Math.toDegrees(piece.body.getAngle()));
		}

		game.batcher.setProjectionMatrix(guiCam.combined);

		debugMatrix = game.batcher.getProjectionMatrix().cpy().scale(Assets.PIXELS_TO_METERS, Assets.PIXELS_TO_METERS, 0);

		game.batcher.enableBlending();
		game.batcher.begin();
		
		for (Piece piece: piecesGlobal)
		{
			game.batcher.draw(piece.sprite, piece.sprite.getX(), piece.sprite.getY(), piece.sprite.getOriginX(), piece.sprite.getOriginY(), piece.sprite.getWidth(), piece.sprite.getHeight(), piece.sprite.getScaleX(), piece.sprite.getScaleY(), piece.sprite.getRotation());
		}

		game.batcher.end();

		if (arePiecesStopped() && nextPiece)
		{
			checkPoints();
			checkReturns();
			if (ronda1 == nrRondas1 && ronda2 == nrRondas2)
				gameOver();
			else
			{
				if (playerTurn == PlayerTurn.PlayerRed)
				{
					//System.out.println("Foi a vez do Jogador 1");
					if (ronda1 < nrRondas1)
						ronda1++;
					playerTurn = PlayerTurn.PlayerBlue;
					if (ronda2 == nrRondas2)
						nextPiece = true;
					else if (ronda2 < nrRondas2)
					{
						pieceToPlay = piecesPlayer2.get(ronda2);
						pieceToPlay.setToPenalty(playerTurn);
						nextPiece = false;
					}
				}
				else if (playerTurn == PlayerTurn.PlayerBlue)
				{
					//System.out.println("Foi a vez do Jogador 2");
					if (ronda2 < nrRondas2)
						ronda2++;
					playerTurn = PlayerTurn.PlayerRed;
					if (ronda1 == nrRondas1)
						nextPiece = true;
					else if (ronda1 < nrRondas1)
					{
						pieceToPlay = piecesPlayer1.get(ronda1);
						pieceToPlay.setToPenalty(playerTurn);
						nextPiece = false;
					}
				}
				System.out.println("Rondas:\nJogador 1: " + ronda1 + "/" + nrRondas1 + "\nJogador 2: " + ronda2 + "/" + nrRondas2);
			}
		}
		//debugRenderer.render(world, debugMatrix);
	}

	/**
	 * Checks which Pieces must be returned/given to player (white areas)
	 */
	private void checkReturns()
	{
		for (Piece piece: piecesGlobal)
		{
			PlayerTurn returnPlayer;
			returnPlayer = piece.checkReturn();

			if (returnPlayer == PlayerTurn.PlayerBlue)
			{
				System.out.println("Removida " + piece.getClass().getSimpleName());

				if (piece.player == PlayerTurn.PlayerRed)
				{
					piecesPlayer1.remove(piece);
					ronda1--;
					nrRondas1--;
				}
				else if (piece.player == PlayerTurn.PlayerBlue)
				{
					piecesPlayer2.remove(piece);
					ronda2--;
					nrRondas2--;
				}

				if (piece.getClass().getSimpleName().equals("Square"))
				{
					Square
						square;
					Sprite
						sprite;
					sprite = new Sprite(Assets.spriteBlueSquare);
					square = new Square(returnPlayer, world, sprite, - 100f, - 100f);
					piecesPlayer2.add(square);
				}
				else if (piece.getClass().getSimpleName().equals("Ball"))
				{
					Ball
						ball;
					Sprite
						sprite;
					sprite = new Sprite(Assets.spriteBlueBall);
					ball = new Ball(returnPlayer, world, sprite, - 100f, - 100f);
					piecesPlayer2.add(ball);
				}
				else if (piece.getClass().getSimpleName().equals("Triangle"))
				{
					Triangle
						triangle;
					Sprite
						sprite;
					sprite = new Sprite(Assets.spriteBlueTriangle);
					triangle = new Triangle(returnPlayer, world, sprite, - 100f, - 100f);
					piecesPlayer2.add(triangle);
				}
				
				nrRondas2++;

				world.destroyBody(piece.body);
			}
			else if (returnPlayer == PlayerTurn.PlayerRed)
			{
				System.out.println("Removida " + piece.getClass().getSimpleName());

				if (piece.player == PlayerTurn.PlayerBlue)
				{
					piecesPlayer2.remove(piece);
					ronda2--;
					nrRondas2--;
				}
				else if (piece.player == PlayerTurn.PlayerRed)
				{
					piecesPlayer1.remove(piece);
					ronda1--;
					nrRondas1--;
				}

				if (piece.getClass().getSimpleName().equals("Square"))
				{
					Square
						square;
					Sprite
						sprite;
					sprite = new Sprite(Assets.spriteRedSquare);
					square = new Square(returnPlayer, world, sprite, - 100f, - 100f);
					piecesPlayer1.add(square);
				}
				else if (piece.getClass().getSimpleName().equals("Ball"))
				{
					Ball
						ball;
					Sprite
						sprite;
					sprite = new Sprite(Assets.spriteRedBall);
					ball = new Ball(returnPlayer, world, sprite, - 100f, - 100f);
					piecesPlayer1.add(ball);
				}
				else if (piece.getClass().getSimpleName().equals("Triangle"))
				{
					Triangle
						triangle;
					Sprite
						sprite;
					sprite = new Sprite(Assets.spriteRedTriangle);
					triangle = new Triangle(returnPlayer, world, sprite, - 100f, - 100f);
					piecesPlayer1.add(triangle);
				}
				
				nrRondas1++;

				world.destroyBody(piece.body);
			}
		}
		
		piecesGlobal.clear();

		//System.out.print("Peças do jogador 1 (" + piecesPlayer1.size() + "):");
		for (Piece piece: piecesPlayer1)
		{
			piecesGlobal.add(piece);
			//System.out.print(" +");
		}
		//System.out.println();
		//System.out.print("Peças do jogador 2 (" + piecesPlayer2.size() + "):");
		for (Piece piece: piecesPlayer2)
		{
			piecesGlobal.add(piece);
			//System.out.print(" +");
		}
		//System.out.println();
		//System.out.println("Jogador 1 já jogou " + ronda1 + "/" + nrRondas1 + ".");
		//System.out.println("Jogador 2 já jogou " + ronda2 + "/" + nrRondas2 + ".");
	}

	/**
	 * Shows the end game label
	 */
	private void gameOver()
	{
		game.batcher.enableBlending();
		game.batcher.begin();
		if (points1 > points2)
		{
			winner = PlayerTurn.PlayerRed;
			game.batcher.draw(Assets.windowRedWin, (Assets.windowWidth - Assets.windowRedWin.getWidth()) / 2, (Assets.windowHeight - Assets.windowRedWin.getHeight()) / 2);
			buttonGameOver = new Button(Assets.buttonRedWin, (Assets.windowWidth - Assets.buttonRedWin.getWidth()) / 2, (Assets.windowHeight - 300) / 2);
		}
		else if (points1 < points2)
		{
			winner = PlayerTurn.PlayerBlue;
			game.batcher.draw(Assets.windowBlueWin, (Assets.windowWidth - Assets.windowBlueWin.getWidth()) / 2, (Assets.windowHeight - Assets.windowBlueWin.getHeight()) / 2);
			buttonGameOver = new Button(Assets.buttonBlueWin, (Assets.windowWidth - Assets.buttonBlueWin.getWidth()) / 2, (Assets.windowHeight - 300) / 2);
		}
		else
		{
			winner = PlayerTurn.NULL;
			game.batcher.draw(Assets.windowDraw, (Assets.windowWidth - Assets.windowBlueWin.getWidth()) / 2, (Assets.windowHeight - Assets.windowBlueWin.getHeight()) / 2);
			buttonGameOver = new Button(Assets.buttonDraw, (Assets.windowWidth - Assets.buttonBlueWin.getWidth()) / 2, (Assets.windowHeight - 300) / 2);
		}
		game.batcher.end();
		buttonGameOver.draw(game.batcher);
	}

	/**
	 * Updates the number of points of each player and the textures of the counters
	 */
	public void checkPoints()
	{
		points1 = 0;
		points2 = 0;
		for (Piece piece: piecesGlobal)
		{
			if (piece.checkPoints())
			{
				if (piece.player == PlayerTurn.PlayerRed)
					points1++;
				else if (piece.player == PlayerTurn.PlayerBlue)
					points2++;
			}
		}

		score1 = new Texture("game/red/score/" + points1 + ".png");
		score2 = new Texture("game/blue/score/" + points2 + ".png");

		//System.out.println("Player 1 (" + points1 + ") - (" + points2 + ") Player 2.");
	}

	/**
	 * Checks if all pieces in the board are stopped
	 * @return True if all pieces are stopped or False if pieces are moving
	 */
	public boolean arePiecesStopped()
	{
		Vector2
			nullVector = new Vector2(0f, 0f);
		for (Piece piece: piecesGlobal)
		{
			if (!piece.body.getLinearVelocity().equals(nullVector))
				return false;
		}
		//System.out.println("As peças estão todas paradas.");
		return true;
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
		//System.out.println("touchDown: " + (screenX - Assets.windowWidth / 2) + ", " + (Assets.windowHeight / 2 - screenY) + ", " + pointer + "," + button);
		state = -1;
		for (Piece piece: piecesGlobal)
		{
			if (piece.contains(screenX, Assets.windowHeight - screenY) && piece.isPlayableBy(playerTurn))
			{
				selectedPiece = piece;
				state = 0;
				startX = screenX;
				startY = screenY;
			}
		}
		return true;
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
	 */
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if (buttonRedQuit.bounds.contains((screenX - Assets.windowWidth / 2), (Assets.windowHeight / 2 - screenY)))
		{
			winner = PlayerTurn.PlayerBlue;
			saveScore();
			game.setScreen(new MenuScreen(game));
		}
		else if (buttonBlueQuit.bounds.contains((screenX - Assets.windowWidth / 2), (Assets.windowHeight / 2 - screenY)))
		{
			winner = PlayerTurn.PlayerRed;
			saveScore();
			game.setScreen(new MenuScreen(game));
		}
		else if (buttonGameOver.bounds.contains((screenX - Assets.windowWidth / 2), (Assets.windowHeight / 2 - screenY)))
		{
			if (points1 > points2)
				winner = PlayerTurn.PlayerRed;
			else if (points1 < points2)
				winner = PlayerTurn.PlayerBlue;
			else
				winner = PlayerTurn.NULL;
			saveScore();
			game.setScreen(new MenuScreen(game));
		}

		//System.out.println ("touchUp: " + screenX + ", " + screenY + ", " + pointer + "," + button);
		if (state == 1)
		{
			selectedPiece.body.applyAngularImpulse(0.1f, true);
			selectedPiece.body.applyForceToCenter((float) 0.5 * (screenX - startX), (float) 0.5 * (startY - screenY), true);
			System.out.println ("Movimento: (" + (screenX - startX) + ", " + (screenY - startY) + ").");
			nextPiece = true;
		}
		return true;
	}

	/**
	 * @see com.badlogic.gdx.InputProcessor#touchDragged(int, int, int)
	 */
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		//System.out.println ("Dragged: " + screenX + ", " + screenY + ", " + pointer);
		if (state == 0)
			state = 1;
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
	 * @see com.badlogic.gdx.InputProcessor#scrolled(int)
	 */
	public boolean scrolled(int amount)
	{
		return false;
	}

	/**
	 * Writes a SavedGame to a file
	 * @see com.bump.objects.SavedGame
	 */
	public void saveScore()
	{
		SavedGame savedGame = new SavedGame(winner, points1, points2);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date date = new Date();
		System.out.println("Jogo gravado como: " + dateFormat.format(date) + ".dat");

		ObjectOutputStream file = null;
		try
		{
			file = new ObjectOutputStream (new FileOutputStream("BumpSavedGames/" + dateFormat.format(date) + ".dat"));
			file.writeObject(savedGame);
			file.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
