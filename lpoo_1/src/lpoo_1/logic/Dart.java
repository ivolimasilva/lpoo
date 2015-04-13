package lpoo_1.logic;

import java.io.Serializable;

/**
 * Dart.java - represents a Dart
 * @author Mariana and Ivo
 * @see Element
 */
public class Dart extends Element implements Serializable
{
	private static final long serialVersionUID = 1L;
	private boolean grabbed;
	
	/**
	 * Dart constructor
	 * @param start_x - x initial position of the dart
	 * @param start_y - y initial position of the dart 
	 */
	public Dart (int start_x, int start_y)
	{
		this.pos_x = start_x;
		this.pos_y = start_y;
		this.grabbed = false;
	}
	

	/**
	 * Returns a * when the dart wasn't grabbed and a space when it was
	 * @return A char data type
	 */
	public char toChar ()
	{
		if (!this.grabbed)
			return '*';
		else
			return ' ';
	}
	
	/**
	 * The boolean 'grabbed' is set as true
	 */
	public void grabbed()
	{
		this.grabbed = true;
	}
	
	/**
	 * Returns true if the dart was grabbed and false otherwise
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
