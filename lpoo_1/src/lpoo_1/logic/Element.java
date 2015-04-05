package lpoo_1.logic;

public abstract class Element
{
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
