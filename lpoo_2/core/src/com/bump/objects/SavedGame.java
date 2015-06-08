package com.bump.objects;

import java.io.Serializable;
import com.bump.screens.GameScreen.PlayerTurn;

/**
 * SavedGame.java
 * @author Ivo and Mariana
 */

public class SavedGame implements Serializable
{
	private static final long
		serialVersionUID = 8367090774644287471L;
	public PlayerTurn
		winner;
	public int
		pointsRed,
		pointsBlue;

	/**
	 * Creates an object with the information of a past game
	 * @param _winner Player who won the game
	 * @param _pointsRed Points of the Player Red
	 * @param _pointsBlue Points of the Player Blue
	 */
	public SavedGame(PlayerTurn _winner, int _pointsRed, int _pointsBlue)
	{
		this.winner = _winner;
		this.pointsRed = _pointsRed;
		this.pointsBlue = _pointsBlue;
	}
}