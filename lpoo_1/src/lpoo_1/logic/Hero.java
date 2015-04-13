package lpoo_1.logic;

import java.io.Serializable;

/**
 * Hero.java - represents a Hero
 * @author Mariana and Ivo
 * @see Element
 */
public class Hero extends Element implements Serializable
{
	private static final long serialVersionUID = 1L;

	public enum HeroStates {NORMAL, ARMED, WINNER, DEAD} // Armed tem espada
	private HeroStates State = HeroStates.NORMAL;
	
	private char direction = 'v';
	private boolean shielded = false;
	private int darts = 0;
	
	/**
	 * Hero constructor
	 * @param start_x - x initial position of the hero
	 * @param start_y - y initial position of the hero
	 */
	public Hero (int start_x, int start_y)
	{
		this.setPosX(start_x);
		this.setPosY(start_y);
	}
	
	/**
	 * Sets the parameter as the direction of the hero
	 * @param new_dir - new direction the hero will take
	 */
	public void changeDirection (char new_dir)
	{
		this.direction = new_dir;
	}
	
	/**
	 * Returns the char equivalent of the current state of the hero:
	 * 
	 * '^' if NORMAL_back
	 * '>' if NORMAL_right
	 * '<' if NORMAL_left
	 * 'v' if NORMAL_front
	 * 
	 * '1' if ARMED_right
	 * '2' if ARMED_front
	 * '3' if ARMED_left
	 * 
	 * '4' if SHIELDED_right
	 * '5' if SHIELDED_front
	 * '6' if SHIELDED_left
	 * 
	 * '7' if ARMED_SHIELDED_right
	 * '8' if ARMED_SHIELDED_front
	 * '9' if ARMED_SHIELDED_left
	 * 
	 * 'W' if WINNER
	 * '†' if DEAD
	 * @return A char data type
	 */
	public char toChar()
	{
		switch (State)
		{
			case NORMAL:
				if (!shielded)
					return this.direction;
				else
					switch (this.direction)
					{
						case '^':
							return '^';
						case '>':
							return '4';
						case '<':
							return '5';
						case 'v':
							return '6';
					}
			case ARMED:
				if (!shielded)
					switch (this.direction)
					{
						case '^':
							return '^';
						case '>':
							return '1';
						case '<':
							return '2';
						case 'v':
							return '3';
					}
				else
					switch (this.direction)
					{
						case '^':
							return '^';
						case '>':
							return '7';
						case '<':
							return '8';
						case 'v':
							return '9';
					}
			case WINNER:
				return 'W';
			case DEAD:
				return '†';
			default:
				return 'H';
		}
	}
	
	/**
	 * Returns true if the hero is with a Shield, false if otherwise
	 * @return A boolean data type
	 */
	public boolean isShielded()
	{
		return shielded;
	}
	
	/**
	 * The hero grabbed the shield
	 */
	public void grabShield()
	{
		shielded = true;
	}
	
	/**
	 * Returns the number of darts the hero has
	 * @return An Integer data type
	 */
	public int hasDarts()
	{
		return darts;
	}
	
	/**
	 * Increments the number of darts the hero grabbed
	 */
	public void grabDart()
	{
		darts++;
	}
	
	/**
	 * Decrements the number of darts after a shot
	 */
	public void shootDart()
	{
		darts--;
	}
	
	/**
	 * Returns the hero state
	 * @return A HeroStates data type
	 */
	public HeroStates getState ()
	{
		return State;
	}
	
	/**
	 * Changes the current state of the hero to NewState
	 * @param NewState - new state of the hero
	 */
	public void changeState (HeroStates NewState)
	{
		State = NewState;
	}
	
	/**
	 * Changes the position of the hero
	 * @param new_x - new x position of the hero
	 * @param new_y - new y position of the hero
	 */
	public void changePos (int new_x, int new_y)
	{
		this.setPosX(new_x);
		this.setPosY(new_y);
	}
	
}
