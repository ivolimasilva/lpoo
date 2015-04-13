package lpoo_1.logic;

public class Sword extends Element
{
	private static final long serialVersionUID = 1L;
	private boolean grabbed = false;
	
	public Sword (int start_x, int start_y)
	{
		this.pos_x = start_x;
		this.pos_y = start_y;
	}
	
	public char toChar ()
	{
		if (!this.grabbed)
			return 'E';
		else
			return ' ';
	}
	
	public void grabbed()
	{
		grabbed = true;
	}
	
	public boolean wasGrabbed ()
	{
		if (grabbed)
			return true;
		else
			return false;
	}
}
