package lpoo_1.logic;

public class Hero extends Element
{
	private static final long serialVersionUID = 1L;

	public enum HeroStates {NORMAL, ARMED, WINNER, DEAD} // Armed tem espada
	private HeroStates State = HeroStates.NORMAL;
	
	private char direction = 'v';
	private boolean shielded = false;
	private int darts = 0;
	
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
				if (!shielded)
					return this.direction;
				else
					switch (this.direction)
					{
						case '^':
							return '^';
						case '>':
							return '4';
						case '<':
							return '5';
						case 'v':
							return '6';
					}
			case ARMED:
				if (!shielded)
					switch (this.direction)
					{
						case '^':
							return '^';
						case '>':
							return '1';
						case '<':
							return '2';
						case 'v':
							return '3';
					}
				else
					switch (this.direction)
					{
						case '^':
							return '^';
						case '>':
							return '7';
						case '<':
							return '8';
						case 'v':
							return '9';
					}
			case WINNER:
				return 'W';
			case DEAD:
				return ' ';
			default:
				return 'H';
		}
	}
	
	public boolean isShielded()
	{
		return shielded;
	}
	
	public void grabShield()
	{
		shielded = true;
	}
	
	public int hasDarts()
	{
		return darts;
	}
	
	public void grabDart()
	{
		darts++;
	}
	
	public void shootDart()
	{
		darts--;
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
