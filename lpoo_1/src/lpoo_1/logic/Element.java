package lpoo_1.logic;

import java.io.Serializable;

/**
 * Element.java - represents an Element
 * @author Mariana and Ivo
 */
public abstract class Element implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected int pos_x, pos_y;
	
	/**
	 * Sets the x position for an element
	 * @param x
	 */
	public void setPosX (int x)
	{
		this.pos_x = x;
	}
	
	/**
	 * Returns the x position of an element
	 * @return An Integer data type
	 */
	public int getPosX ()
	{
		return pos_x;
	}
	
	/**
	 * Sets the y position of an element
	 * @param y
	 */
	public void setPosY (int y)
	{
		this.pos_y = y;
	}
	
	/**
	 * Returns the y position of an element
	 * @return An Integer data type
	 */
	public int getPosY ()
	{
		return pos_y;
	}
	
}