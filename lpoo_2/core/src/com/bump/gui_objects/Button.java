package com.bump.gui_objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bump.assets.Assets;

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

	public Button(Texture _texture, float _x, float _y)
	{
		this.x = _x;
		this.y = _y;
		this.width = _texture.getWidth();
		this.height = _texture.getHeight();
		this.texture = _texture;

		this.bounds = new Rectangle(this.x - Assets.windowWidth / 2, this.y - Assets.windowHeight / 2, this.width, this.height);
	}

	public void draw(SpriteBatch batcher)
	{
		batcher.begin();
		batcher.draw(this.texture, this.x, this.y, this.width, this.height);
		batcher.end();
	}
}
