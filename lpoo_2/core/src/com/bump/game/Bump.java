package com.bump.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bump.assets.Assets;
import com.bump.screens.MenuScreen;

public class Bump extends Game
{
	public SpriteBatch
		batcher;

	public void create()
	{
		Assets.load();
		batcher = new SpriteBatch();
		setScreen (new MenuScreen (this));
	}

	public void render()
	{
		super.render();
	}
}
