package com.bump.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Piece
{
	public Sprite
		sprite;
	public World
		world;
	public Body
		body;
	public int
		id;

	public abstract boolean contains(float x, float y);
}
