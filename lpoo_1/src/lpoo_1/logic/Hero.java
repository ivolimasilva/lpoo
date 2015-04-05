package lpoo_1.logic;

public class Hero extends Element
{
	public enum HeroStates {NORMAL, ARMED, WINNER, DEAD}
	private HeroStates State = HeroStates.NORMAL;
	
	public Hero (int start_x, int start_y)
	{
		this.pos_x = start_x;
		this.pos_y = start_y;
	}
	
	public char toChar()
	{
		switch (State)
		{
			case NORMAL:
				return 'H';
			case ARMED:
				return 'A';
			case WINNER:
				return 'W';
			case DEAD:
				return '†';
			default:
				return 'H';
		}
	}
	
	public HeroStates getState ()
	{
		return State;
	}
	
	public void changeState (HeroStates NewState)
	{
		State = NewState;
	}
	
	public void changePos (int new_x, int new_y)
	{
		this.setPosX(new_x);
		this.setPosY(new_y);
	}
	
}
