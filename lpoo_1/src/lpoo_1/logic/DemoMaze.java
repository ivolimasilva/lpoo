package lpoo_1.logic;

import java.util.ArrayList;

import lpoo_1.logic.Hero;
import lpoo_1.logic.Dragon.DragonStates;

public class DemoMaze implements MazeBuilder
{
	private char[][] matrix;
	private int size;
	
	private Hero hero;
	private Sword sword;
	private ArrayList<Dart> darts = new ArrayList<Dart>();
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	private Shield shield;
	
	public DemoMaze(int size)
	{
		this.size = size;
		
		generateMatrix();
		
		/*Dragon dragon_aux = new Dragon(8, 3, DragonStates.NORMAL);
		dragons.add(dragon_aux);
		
		dragon_aux = new Dragon(3, 6, DragonStates.SLEEPONSHIELD);
		dragons.add(dragon_aux);*/
	}
	
	public void generateMatrix()
	{
		
		char aux_matrix[][] =
		{
			{ 'X', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' },
			{ '0', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ '1', 'X', 'H', ' ', 'o', ' ', '*', ' ', ' ', ' ', 'X' },
			{ '2', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ '3', 'X', '*', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ '4', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ '5', 'X', ' ', ' ', 'D', ' ', ' ', ' ', 'X', ' ', 'X' },
			{ '6', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ '7', 'X', ' ', 'X', 'X', ' ', 'X', 'd', 'X', ' ', 'S' },
			{ '8', 'X', 'E', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ '9', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }
		};
		
		for (int i = 0; i < this.size; i ++)
		{
			for (int j = 0; j < this.size; j++)
			{
				switch (aux_matrix[i][j])
				{
					case 'H':
						hero = new Hero(i, j);
						break;
						
					case 'E':
						sword = new Sword(i, j);
						break;
						
					case '*':
						darts.add(new Dart (i, j));
						break;
						
					case 'o':
						shield = new Shield(i, j);
						break;
					
					case 'D':
						dragons.add(new Dragon (i, j, DragonStates.NORMAL));
						break;
					
					case 'd':
						dragons.add(new Dragon (i, j, DragonStates.SLEEP));
						break;
						
					case 'F':
						dragons.add(new Dragon (i, j, DragonStates.ONSWORD));
						break;
						
					case 'f':
						dragons.add(new Dragon (i, j, DragonStates.SLEEPONSWORD));
						break;
						
					case 'º':
						dragons.add(new Dragon (i, j, DragonStates.ONSHIELD));
						break;
					
					case 'ª':
						dragons.add(new Dragon (i, j, DragonStates.SLEEPONSHIELD));
						break;
						
					case '.':
						dragons.add(new Dragon (i, j, DragonStates.ONDART));
						break;
					
					case '_':
						dragons.add(new Dragon (i, j, DragonStates.SLEEPONDART));
						break;
				}
				
			}
		}
		
		this.matrix = aux_matrix;
	}

	public char[][] getMatrix()
	{
		return matrix;
	}
	
	public Hero getHero()
	{
		return hero;
	}

	public ArrayList<Dragon> getDragons()
	{	
		return dragons;
	}

	public Sword getSword()
	{
		return sword;
	}

	public Shield getShield()
	{
		return shield;
	}
	
	public ArrayList<Dart> getDarts()
	{
		return darts;
	}
}
