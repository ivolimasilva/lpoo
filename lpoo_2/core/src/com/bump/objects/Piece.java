package com.bump.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.bump.assets.Assets;
import com.bump.screens.GameScreen.PlayerTurn;

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

	public abstract boolean contains(float x, float y);
	public boolean isPlayableBy(PlayerTurn player)
	{
		if (player == PlayerTurn.Player1)
		{
			if (this.body.getPosition().x * Assets.PIXELS_TO_METERS < 150)
				return true;
		}
		else if (player == PlayerTurn.Player2)
		{
			if (this.body.getPosition().x * Assets.PIXELS_TO_METERS > 1130)
				return true;
		}
		return false;
	}
	public void setToWait()
	{
		this.body.setTransform(new Vector2(- 100f, - 100f), 0f);
	}
	public void setToPenalty(PlayerTurn player)
	{
		if (player == PlayerTurn.Player1)
		{
			this.body.setTransform(Assets.penaltyPlayer1, (float) Math.toRadians(- 90));
		}
		else if (player == PlayerTurn.Player2)
		{
			this.body.setTransform(Assets.penaltyPlayer2, (float) Math.toRadians(90));
		}
	}
	public boolean checkPoints()
	{
		if (this.player == PlayerTurn.Player1)
		{
			if (this.body.getPosition().x * Assets.PIXELS_TO_METERS > 640 && this.body.getPosition().x * Assets.PIXELS_TO_METERS < 1130)
				return true;
		}
		else if (this.player == PlayerTurn.Player2)
		{
			if (this.body.getPosition().x * Assets.PIXELS_TO_METERS < 640 && this.body.getPosition().x * Assets.PIXELS_TO_METERS > 150)
				return true;
		}
		return false;
	}
	public PlayerTurn checkReturn()
	{
		if (this.body.getPosition().x * Assets.PIXELS_TO_METERS > 1130 && this.body.getPosition().x * Assets.PIXELS_TO_METERS < 1280)
			return PlayerTurn.Player2;
		if (this.body.getPosition().x * Assets.PIXELS_TO_METERS > 0 && this.body.getPosition().x * Assets.PIXELS_TO_METERS < 150)
			return PlayerTurn.Player1;
		else
			return null;
	}
}
