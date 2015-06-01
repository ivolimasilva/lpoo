package com.bump.assets;

import com.badlogic.gdx.graphics.Texture;

public class Assets
{
	public static Texture
		backgroundMenu,
		backgroundGame,
		buttonPlay;

	public static void load()
	{
		backgroundMenu = new Texture("menu/background.jpg");
		backgroundGame = new Texture("game/background.jpg");
		buttonPlay = new Texture("menu/play.jpg");
	}
}
