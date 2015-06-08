package com.bump.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bump.assets.Assets;
import com.bump.screens.MenuScreen;

/**
 * Bump.java
 * @author Ivo and Mariana
 */

public class Bump extends Game
{
	public SpriteBatch
		batcher;

	/**
	 * Function called in the start of the application. Loads textures and sets the view for the menu.
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	public void create()
	{
		Assets.load();
		batcher = new SpriteBatch();
		setScreen (new MenuScreen (this));
	}

	/**
	 * Function called to paint the screen.
	 * @see com.badlogic.gdx.Game#render()
	 */
	public void render()
	{
		super.render();
	}
}
