package com.bump.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.bump.assets.Assets;
import com.bump.screens.GameScreen.PlayerTurn;

/**
 * Piece.java
 * @author Ivo and Mariana
 */

public abstract class Piece
{
	public Sprite
		sprite;
	public World
		world;
	public Body
		body;
	public PlayerTurn
		player;

	/**
	 * Function that checks if the area of the ball contains a given mouse coordinate
	 * @see com.bump.objects.Piece#contains(float, float)
	 * @param x Coordinate X of the mouse
	 * @param y Coordinate Y of the mouse
	 * @return True if the area of the ball contains the mouse coordinate or False if it doesn't
	 */
	public abstract boolean contains(float x, float y);

	/**
	 * Function that checks if piece can be played by a given player
	 * @param player Player that wants to play
	 * @return True if the player can play this piece or False if the player can't
	 */
	public boolean isPlayableBy(PlayerTurn player)
	{
		if (player == PlayerTurn.PlayerRed)
		{
			if (this.body.getPosition().x * Assets.PIXELS_TO_METERS < 150)
				return true;
		}
		else if (player == PlayerTurn.PlayerBlue)
		{
			if (this.body.getPosition().x * Assets.PIXELS_TO_METERS > 1130)
				return true;
		}
		return false;
	}

	/**
	 * Sets the Piece to outside of the walls and of the screen.
	 */
	public void setToWait()
	{
		this.body.setTransform(new Vector2(- 100f, - 100f), 0f);
	}

	/**
	 * Sets the Piece to the penalty position (Piece is about to be played).
	 * @param player Player who is playing this round
	 */
	public void setToPenalty(PlayerTurn player)
	{
		if (player == PlayerTurn.PlayerRed)
		{
			this.body.setTransform(Assets.penaltyPlayer1, (float) Math.toRadians(- 90));
		}
		else if (player == PlayerTurn.PlayerBlue)
		{
			this.body.setTransform(Assets.penaltyPlayer2, (float) Math.toRadians(90));
		}
	}

	/**
	 * Checks if this Piece is scoring a point
	 * @return True if the Piece is the zone with the same color or False if the Piece isn't
	 */
	public boolean checkPoints()
	{
		if (this.player == PlayerTurn.PlayerRed)
		{
			if (this.body.getPosition().x * Assets.PIXELS_TO_METERS > 640 && this.body.getPosition().x * Assets.PIXELS_TO_METERS < 1130)
				return true;
		}
		else if (this.player == PlayerTurn.PlayerBlue)
		{
			if (this.body.getPosition().x * Assets.PIXELS_TO_METERS < 640 && this.body.getPosition().x * Assets.PIXELS_TO_METERS > 150)
				return true;
		}
		return false;
	}

	/**
	 * Checks if this Piece must be returned/given to player (white areas)
	 * @return PlayerTurn of the player who the Piece will be returned/given or Null if the Piece isn't returned or given.
	 */
	public PlayerTurn checkReturn()
	{
		if (this.body.getPosition().x * Assets.PIXELS_TO_METERS > 1130 && this.body.getPosition().x * Assets.PIXELS_TO_METERS < 1280)
			return PlayerTurn.PlayerBlue;
		if (this.body.getPosition().x * Assets.PIXELS_TO_METERS > 0 && this.body.getPosition().x * Assets.PIXELS_TO_METERS < 150)
			return PlayerTurn.PlayerRed;
		else
			return null;
	}
}
