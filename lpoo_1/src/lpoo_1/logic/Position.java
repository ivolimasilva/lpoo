package lpoo_1.logic;

/**
 * Position.java - represents a Position with x and y coordinates
 * @author Mariana and Ivo
 *
 */
class Position
{
	int x, y;
	
	/**
	 * Position constructor
	 * @param xi - x coordinate
	 * @param yi - y coordinate
	 */
	public Position (int xi, int yi)
	{
		x = xi;
		y = yi;
	}

	/**
	 * Returns the x coordinate
	 * @return An Integer data type
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Sets the x coordinate as the parameter given
	 * @param i - new x coordinate
	 */
	public void setX(int i)
	{
		x = i;
	}

	/**
	 * 	Returns the y coordinate
	 * @return An Integer data type
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Sets the y coordinate as the parameter given
	 * @param i - new y coordinate
	 */
	public void setY(int i)
	{
		y = i;
	}
}
