package lpoo_1.logic;

import java.io.Serializable;
import java.util.ArrayList;
import lpoo_1.logic.Hero;
import lpoo_1.logic.Dragon.DragonStates;

/**
 * DemoMaze.java - generates the standard maze matrix
 * @author Mariana and Ivo
 */
public class DemoMaze implements MazeBuilder, Serializable
{
	private static final long serialVersionUID = 1L;
	private char[][] matrix;
	private int size;
	
	private Hero hero;
	private Sword sword;
	private ArrayList<Dart> darts = new ArrayList<Dart>();
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	private Shield shield;
	
	/**
	 * Default Maze constructor
	 * @param size - dimension of the maze
	 */
	public DemoMaze(int size)
	{
		this.size = 10;
		
		generateMatrix();
	}
	
	/**
	 * Returns the default maze matrix 
	 * @return A char[][] data type
	 */
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

	/**
	 * Returns the generated matrix
	 * @return A char[][] data type
	 */
	public char[][] getMatrix()
	{
		return matrix;
	}
	
	/**
	 * Returns the hero
	 * @return A Hero data type
	 */
	public Hero getHero()
	{
		return hero;
	}

	/**
	 * Returns the list of Dragons
	 * @return A ArrayList<Dragon> data type
	 */
	public ArrayList<Dragon> getDragons()
	{	
		return dragons;
	}

	/**
	 * Returns the sword
	 * @return A Sword data type
	 */
	public Sword getSword()
	{
		return sword;
	}

	/**
	 * Returns the shield
	 * @return A Shield data type
	 */
	public Shield getShield()
	{
		return shield;
	}
	
	/**
	 * Returns the list of existing darts
	 * @return A ArrayList<Dart> data type
	 */
	public ArrayList<Dart> getDarts()
	{
		return darts;
	}
}
