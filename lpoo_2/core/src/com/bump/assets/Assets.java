package com.bump.assets;

import com.badlogic.gdx.graphics.Texture;

public class Assets
{
	public static float
		width = 1280,
		height = 720;

	public static Texture
		backgroundMenu,
		backgroundGame,
		backgroundHighscores,
		buttonPlay,
		spriteBall;

	public static void load()
	{
		backgroundMenu = new Texture("menu/background.jpg");
		backgroundGame = new Texture("game/background.jpg");
		backgroundHighscores = new Texture("highscores/background.jpg");

		buttonPlay = new Texture("menu/play.jpg");

		spriteBall = new Texture("game/ball.jpg");
	}
}
