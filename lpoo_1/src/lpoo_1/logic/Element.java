package lpoo_1.logic;

import java.io.Serializable;

public abstract class Element implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected int pos_x, pos_y;
	
	public void setPosX (int x)
	{
		this.pos_x = x;
	}
	
	public int getPosX ()
	{
		return pos_x;
	}
	
	public void setPosY (int y)
	{
		this.pos_y = y;
	}
	
	public int getPosY ()
	{
		return pos_y;
	}
	
	//public abstract String toString (); 
}
