package lpoo_1.logic;

import java.io.Serializable;

/**
 * Sword.java - represents a Sword
 * @author Mariana and Ivo
 * @see Element
 */
public class Sword extends Element implements Serializable
{
	private static final long serialVersionUID = 1L;
	private boolean grabbed = false;
	
	/**
	 * Sword constructor
	 * @param start_x - x initial position of the sword
	 * @param start_y - y initial position of the sword 
	 */
	public Sword (int start_x, int start_y)
	{
		this.pos_x = start_x;
		this.pos_y = start_y;
	}
	
	/**
	 * Returns ' ' if the Sword was grabbed and 'E' if not
	 * @return A char data type
	 */
	public char toChar ()
	{
		if (!this.grabbed)
			return 'E';
		else
			return ' ';
	}
	
	/**
	 * The Sword is being grabbed
	 */
	public void grabbed()
	{
		grabbed = true;
	}
	
	/**
	 * Returns true if the sword was grabbed or false otherwise
	 * @return A boolean data type
	 */
	public boolean wasGrabbed ()
	{
		if (grabbed)
			return true;
		else
			return false;
	}
}
