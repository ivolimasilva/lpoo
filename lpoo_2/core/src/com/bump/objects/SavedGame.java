package com.bump.objects;

import java.io.Serializable;

import com.bump.screens.GameScreen.PlayerTurn;

public class SavedGame implements Serializable
{
	private static final long
		serialVersionUID = 8367090774644287471L;
	public PlayerTurn
		winner;
	public int
		pointsRed,
		pointsBlue;

	public SavedGame(PlayerTurn _winner, int _pointsRed, int _pointsBlue)
	{
		this.winner = _winner;
		this.pointsRed = _pointsRed;
		this.pointsBlue = _pointsBlue;
	}
}