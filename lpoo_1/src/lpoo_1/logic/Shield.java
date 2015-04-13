package lpoo_1.logic;

import java.io.Serializable;

/**
 * Shield.java - represents a Shield
 * @author Mariana and Ivo
 * @see Element
 */
public class Shield extends Element implements Serializable
{
	private static final long serialVersionUID = 1L;
	private boolean grabbed;
	
	/**
	 * Shield constructor
	 * @param start_x - x initial position of the shield
	 * @param start_y - y initial position of the shield
	 */
	public Shield (int start_x, int start_y)
	{
		this.pos_x = start_x;
		this.pos_y = start_y;
		this.grabbed = false;
	}
	
	/**
	 * Returns ' ' if the Shield was grabbed and 'o' if it wasn't
	 * @return A char data type
	 */
	public char toChar ()
	{
		if (!this.grabbed)
			return 'o';
		else
			return ' ';
	}
	
	/**
	 * The Shield is grabbed
	 */
	public void grabbed()
	{
		this.grabbed = true;
	}
	
	/**
	 * Returns true if the shield was grabbed or false otherwise
	 * @return A boolean data type
	 */
	public boolean wasGrabbed ()
	{
		if (this.grabbed)
			return true;
		else
			return false;
	}
}