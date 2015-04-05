package lpoo_1.logic;

public class Sword extends Element
{
	private boolean grabbed = false;
	
	public Sword (int start_x, int start_y)
	{
		this.pos_x = start_x;
		this.pos_y = start_y;
	}
	
	public char toChar ()
	{
		return 'E';
	}
	
	public void grabbed()
	{
		this.grabbed = true;
	}
	
	public boolean wasGrabbed ()
	{
		if (grabbed)
			return true;
		else
			return false;
	}
}
