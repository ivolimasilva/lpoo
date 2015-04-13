package lpoo_1.logic;

import java.util.ArrayList;
import java.util.Scanner;

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
		this.size = 10;
		
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
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{'X', 'H', ' ', 'o', ' ', '*', ' ', ' ', ' ', 'X' },
			{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{'X', '*', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{'X', ' ', ' ', 'D', ' ', ' ', ' ', 'X', ' ', 'X' },
			{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{'X', ' ', 'X', 'X', ' ', 'X', 'd', 'X', ' ', 'S' },
			{'X', 'E', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }
		};
		
		System.out.println(aux_matrix.length);
		
		for (int i = 0; i < this.size; i ++)
		{
			for (int j = 0; j < this.size; j++)
			{
				switch (aux_matrix[i][j])
				{
					case 'H':
						hero = new Hero(i, j);
						aux_matrix[i][j] = ' ';
						break;
						
					case 'E':
						sword = new Sword(i, j);
						aux_matrix[i][j] = ' ';
						break;
						
					case '*':
						darts.add(new Dart (i, j));
						aux_matrix[i][j] = ' ';
						break;
						
					case 'o':
						shield = new Shield(i, j);
						aux_matrix[i][j] = ' ';
						break;
					
					case 'D':
						dragons.add(new Dragon (i, j, DragonStates.NORMAL));
						aux_matrix[i][j] = ' ';
						break;
					
					case 'd':
						dragons.add(new Dragon (i, j, DragonStates.SLEEP));
						aux_matrix[i][j] = ' ';
						break;
						
					case 'F':
						dragons.add(new Dragon (i, j, DragonStates.ONSWORD));
						aux_matrix[i][j] = ' ';
						break;
						
					case 'f':
						dragons.add(new Dragon (i, j, DragonStates.SLEEPONSWORD));
						aux_matrix[i][j] = ' ';
						break;
						
					case 'º':
						dragons.add(new Dragon (i, j, DragonStates.ONSHIELD));
						aux_matrix[i][j] = ' ';
						break;
					
					case 'ª':
						dragons.add(new Dragon (i, j, DragonStates.SLEEPONSHIELD));
						aux_matrix[i][j] = ' ';
						break;
						
					case '.':
						dragons.add(new Dragon (i, j, DragonStates.ONDART));
						aux_matrix[i][j] = ' ';
						break;
					
					case '_':
						dragons.add(new Dragon (i, j, DragonStates.SLEEPONDART));
						aux_matrix[i][j] = ' ';
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
