package lpoo_1.logic;

public class Shield extends Element
{
	private static final long serialVersionUID = 1L;
	private boolean grabbed;
	
	public Shield (int start_x, int start_y)
	{
		this.pos_x = start_x;
		this.pos_y = start_y;
		this.grabbed = false;
	}
	
	public char toChar ()
	{
		if (!this.grabbed)
			return 'o';
		else
			return ' ';
	}
	
	public void grabbed()
	{
		this.grabbed = true;
	}
	
	public boolean wasGrabbed ()
	{
		if (this.grabbed)
			return true;
		else
			return false;
	}
}