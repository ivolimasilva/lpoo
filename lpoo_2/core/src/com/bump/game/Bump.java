package com.bump.game;

import com.badlogic.gdx.Game;
// import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bump.assets.Assets;
import com.bump.screens.MenuScreen;

public class Bump extends Game
{
	public SpriteBatch batcher;
	// public Texture img, img2;

	public void create()
	{
		Assets.load();
		batcher = new SpriteBatch();
		// img = new Texture("badlogic.jpg");
		// img2 = new Texture("daniela.jpg");
		setScreen (new MenuScreen (this));
	}

	public void render()
	{
		super.render();
	}
}
