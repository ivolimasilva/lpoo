package com.bump.assets;

import com.badlogic.gdx.graphics.Texture;

public class Assets
{
	public static float
		windowWidth = 1280,
		windowHeight = 720;

	public static Texture
		backgroundMenu,
		backgroundGame,
		backgroundHighscores,

		buttonBump,
		buttonPlay,
		buttonScores,
		buttonOptions,
		buttonExit,

		spriteBall;

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

		spriteBall = new Texture("game/ball.jpg");
	}
}
