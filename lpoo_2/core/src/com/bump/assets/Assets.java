package com.bump.assets;

import com.badlogic.gdx.graphics.Texture;

public class Assets
{
	public static float
		windowWidth = 1280,
		windowHeight = 720,
		PIXELS_TO_METERS = 100f;

	public static Texture
		backgroundMenu,
		backgroundGame,
		backgroundHighscores,

		buttonBump,
		buttonPlay,
		buttonScores,
		buttonOptions,
		buttonExit,

		spriteBall,
		spriteTriangle,
		spriteSquare;

	public static void load()
	{
		backgroundMenu = new Texture("menu/background.jpg");
		backgroundGame = new Texture("game/background.jpg");
		backgroundHighscores = new Texture("highscores/background.jpg");

		buttonBump = new Texture("menu/MainTile.jpg");
		buttonPlay = new Texture("menu/PlayTile.jpg");
		buttonScores = new Texture("menu/ScoresTile.jpg");
		buttonOptions = new Texture("menu/OptionsTile.jpg");
		buttonExit = new Texture("menu/ExitTile.jpg");

		spriteBall = new Texture("game/red/ball.png");
		spriteTriangle = new Texture("game/red/triangle.png");
		spriteSquare = new Texture("game/red/square.png");
	}

	public static float distance(float x1, float y1, float x2, float y2)
	{
		return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}
