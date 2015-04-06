package lpoo_1.logic;

public class Dragon extends Element
{
	public enum DragonStates {NORMAL, ONSWORD, SLEEP, SLEEPONSWORD, DEAD}
	private DragonStates State;
	
	public Dragon (int start_x, int start_y, DragonStates start_state)
	{
		this.pos_x = start_x;
		this.pos_y = start_y;
		this.State = start_state;
	}
	
	public char toChar()
	{
		switch (State)
		{
			case NORMAL:
				return 'D';
			case ONSWORD:
				return 'F';
			case SLEEP:
				return 'd';
			case SLEEPONSWORD:
				return 'f';
			case DEAD:
				return '�';
			default:
				return ' ';
		}
	}
	
	public DragonStates getState ()
	{
		return State;
	}
	
	public void changeState (DragonStates NewState)
	{
		State = NewState;
	}
	
	public void changePos (int new_x, int new_y)
	{
		this.setPosX(new_x);
		this.setPosY(new_y);
	}

}