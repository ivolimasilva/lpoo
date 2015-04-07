package lpoo_1.logic;

public class Hero extends Element
{
	public enum HeroStates {NORMAL, WITHSWORD, WITHDART, WITHSHIELD, WINNER, DEAD}
	private HeroStates State = HeroStates.NORMAL;
	
	private char direction = ' ';
	
	public Hero (int start_x, int start_y)
	{
		this.pos_x = start_x;
		this.pos_y = start_y;
	}
	
	public void changeDirection (char new_dir)
	{
		this.direction = new_dir;
	}
	
	public char toChar()
	{
		switch (State)
		{
			case NORMAL:
				return 'H';
			case WITHSWORD:
				return 'A';
			case WITHDART:
				return this.direction;
			case WITHSHIELD:
				return 'O';
			case WINNER:
				return 'W';
			case DEAD:
				return ' ';
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
