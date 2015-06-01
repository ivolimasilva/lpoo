package com.bump.assets;

import com.badlogic.gdx.graphics.Texture;

public class Assets
{
	public static Texture
		backgroundMenu,
		backgroundGame;

	public static void load()
	{
		backgroundMenu = new Texture("menu/background.jpg");
		backgroundGame = new Texture("game/background.jpg");
	}
}
