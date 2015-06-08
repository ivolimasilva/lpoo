package com.bump.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bump.assets.Assets;

/**
 * Button.java
 * @author Ivo and Mariana
 */

public class Button
{
	public Rectangle
		bounds;
	public Texture
		texture;
	public float
		x,
		y,
		width,
		height;

	/**
	 * Creates a button with a given texture and on a given position
	 * @param _texture Texture of the button
	 * @param _x Position on the X axis where the ball is created
	 * @param _y Position on the Y axis where the ball is created
	 */
	public Button(Texture _texture, float _x, float _y)
	{
		this.x = _x;
		this.y = _y;
		this.width = _texture.getWidth();
		this.height = _texture.getHeight();
		this.texture = _texture;

		this.bounds = new Rectangle(this.x - Assets.windowWidth / 2, this.y - Assets.windowHeight / 2, this.width, this.height);
	}

	/**
	 * Draws the button
	 * @param batcher Main batcher from the Game Class
	 */
	public void draw(SpriteBatch batcher)
	{
		batcher.begin();
		batcher.draw(this.texture, this.x, this.y, this.width, this.height);
		batcher.end();
	}
}
