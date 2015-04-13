package lpoo_1.logic;

import java.io.Serializable;

/**
 * Dragon.java - represents a Dragon
 * @author Mariana and Ivo
 * @see Element
 */
public class Dragon extends Element implements Serializable
{
	private static final long serialVersionUID = 1L;
	public enum DragonStates {NORMAL, ONSWORD, ONDART, ONSHIELD, SLEEP, SLEEPONSWORD, SLEEPONDART, SLEEPONSHIELD, DEAD}
	private DragonStates State;
	
	/**
	 * Dragon constructor
	 * @param start_x - x initial position of the dragon
	 * @param start_y - y initial position of the dragon
	 * @param start_state - state of the dragon
	 * Dragon States: NORMAL for awake and ready to attack
	 * 				  ONSWORD for when the dragon is on the same place as the sword
	 * 				  ONDART for when the dragon is on the same place as the dart
	 * 				  ONSHIELD for when the dragon is on the same place as the shield
	 * 			  	  SLEEP for when the dragon is asleep
	 * 				  SLEEPONSWORD for when the dragon is asleep and on the same place as the sword
	 * 				  SLEEPONDART for when the dragon is asleep and on the same place as the dart
	 * 				  SLEEPONSHIELD for when the dragon is asleep and on the same place as the shield
	 * 				  DEAD for when the dragon was killed by the hero
	 */
	public Dragon (int start_x, int start_y, DragonStates start_state)
	{
		this.pos_x = start_x;
		this.pos_y = start_y;
		this.State = start_state;
	}
	
	/**
	 * 'D' when the state is NORMAL
	 * 'F' when it is ONSWORD
	 * '.' when it is ONDART
	 * 'º' when it is ONSHIELD
	 * 'd' when it is SLEEP
	 * 'f' when it is SLEEPONSHIELD
	 * '_' when it is SLEEPONDART
	 * 'ª' when it is SLEEPONSHIELD
	 * '†' when it is DEAD
	 * @return A char data type
	 */
	public char toChar()
	{
		switch (State)
		{
			case NORMAL:
				return 'D';
			case ONSWORD:
				return 'F';
			case ONDART:
				return '.';
			case ONSHIELD:
				return 'º';
			case SLEEP:
				return 'd';
			case SLEEPONSWORD:
				return 'f';
			case SLEEPONDART:
				return '_';
			case SLEEPONSHIELD:
				return 'ª';
			case DEAD:
				return '†';
			default:
				return ' ';
		}
	}
	
	/**
	 * Returns the dragon state
	 * @return A DragonStates data type
	 */
	public DragonStates getState ()
	{
		return State;
	}
	
	/**
	 * Changes the current dragon state to the state given as a parameter 
	 * @param NewState
	 */
	public void changeState (DragonStates NewState)
	{
		State = NewState;
	}
	
	/**
	 * Changes the position of the dragon
	 * @param new_x - new x position of the dragon
	 * @param new_y - new y position of the dragon
	 */
	public void changePos (int new_x, int new_y)
	{
		this.setPosX(new_x);
		this.setPosY(new_y);
	}

}