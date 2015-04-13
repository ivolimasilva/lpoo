package lpoo_1.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import lpoo_1.logic.Dragon;
import lpoo_1.logic.Dragon.DragonStates;
import lpoo_1.logic.Hero;
import lpoo_1.logic.Hero.HeroStates;

/**
 * Game.java - represents the game with a maze and its elements
 * @author Mariana and Ivo
 *
 */

public class Game implements Serializable
{
	private static final long serialVersionUID = 1L;

	private char matrix[][];
	
	private int matrixSize;
	
	private Hero hero;
	private Sword sword;
	private Shield shield;
	private ArrayList<Dart> darts = new ArrayList<Dart>();
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	
	
	/**
	 * Game constructor
	 * @param size - dimension of the maze
	 * @param random - True if the user wants a random generated maze or False if the user wants the default maze
	 */
	public Game(int size, boolean random)
	{
		this.matrixSize = size;
		MazeBuilder Maze;
		if (random)
			Maze = new RandomMaze(size);
		else
			Maze = new DemoMaze(size);
		
		this.matrix = Maze.getMatrix();
		
		this.dragons = Maze.getDragons();
		for (Dragon dragon: dragons)
			this.matrix[dragon.getPosX()][dragon.getPosY()] = dragon.toChar();
		
		this.hero = Maze.getHero();
		this.matrix[hero.getPosX()][hero.getPosY()] = hero.toChar();
		
		this.sword = Maze.getSword();
		this.matrix[sword.getPosX()][sword.getPosY()] = sword.toChar();
		
		this.shield = Maze.getShield();
		this.matrix[shield.getPosX()][shield.getPosY()] = shield.toChar();
		
		this.darts = Maze.getDarts();
		for (Dart dart: darts)
			this.matrix[dart.getPosX()][dart.getPosY()] = dart.toChar();

	}
	
	/**
	 * Prints the matrix of the game
	 */
	public void printMatrix()
	{
		for (int i = 0; i < this.matrixSize; i ++)
		{
			for (int j = 0; j < this.matrixSize; j++)
				System.out.print(matrix[i][j] + " ");
			
			System.out.println();
		}
	}
	
	/**
	 * Returns the information about the matrix and its elements
	 * @return A char[][] data type
	 */
	public char[][] getMatrix()
	{
		System.out.print ("Armas: ");
		if (hero.getState() == HeroStates.ARMED)
			System.out.print ("Espada | ");
		System.out.print ("Dardos (" + hero.hasDarts() + ").\n");
		
		System.out.print ("Defesas: ");
		if (hero.isShielded())
			System.out.print ("Escudo.");
		
		System.out.print ("\n");
		return this.matrix;
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
	
	/**
	 * Verifies if the hero can move in the direction given as a parameter and if he can, moves the hero
	 * Returns True if the hero can move or False otherwise
	 * @param dir - direction in which we want the hero to move or if he wants to shoot a dart
	 * @return A boolean data type
	 */
	public boolean moveHero(char dir)
	{
		int new_x = hero.getPosX(), new_y = hero.getPosY();
		
		// Verificação das letras.
		if (dir == 'w')
		{
			new_x--;
			hero.changeDirection('^');
		}
		else if (dir == 's')
		{
			new_x++;
			hero.changeDirection('v');
		}
		else if (dir == 'd')
		{
			new_y++;
			hero.changeDirection('>');
		}
		else if (dir == 'a')
		{
			new_y--;
			hero.changeDirection('<');
		}
		else if (dir == '+') // disparar
		{
			if (hero.hasDarts() > 0)
				shootDart();
			else
				return false;
		}
		else
			return false;
		
		// Verificação da posição para onde quer ir.
		if (matrix[new_x][new_y] == 'X' || matrix[new_x][new_y] == 'd' || matrix[new_x][new_y] == 'D' || matrix[new_x][new_y] == 'f' || matrix[new_x][new_y] == 'F' || matrix[new_x][new_y] == '.' || matrix[new_x][new_y] == '_')
			return false;
		else if (matrix[new_x][new_y] == 'E')
		{
			hero.changeState(HeroStates.ARMED);
			sword.grabbed();
		}
		else if (matrix[new_x][new_y] == 'o')
		{		
			shield.grabbed();
			hero.grabShield();
		}
		else if (matrix[new_x][new_y] == '*')
		{
			hero.grabDart();
			grabDart(new_x, new_y);
		}
		else if (matrix[new_x][new_y] == 'S')
		{
			if (hero.getState() == HeroStates.ARMED)
				hero.changeState(HeroStates.WINNER);
			else
				return false;
		}
		
		// Limpar a matrix, mudar a posição do elemento, actualizar matrix;
		matrix[hero.getPosX()][hero.getPosY()] = ' ';
		hero.changePos(new_x, new_y);
		matrix[hero.getPosX()][hero.getPosY()] = hero.toChar();
		
		// Verificar se está adjacente a um dragão
		this.update();
		
		return true;
	}
	
	/**
	 * Verifies if the hero is in the same position as the dart and grabs it
	 * @param pos_x - x position of the hero
	 * @param pos_y - y position of the hero
	 */
	public void grabDart (int pos_x, int pos_y)
	{
		for (Dart dart: darts)
			if (dart.getPosX() == pos_x && dart.getPosY() == pos_y)
				dart.grabbed();
	}
	
	/**
	 * Verifies the hero's position and if he can, he shoots a dart
	 */
	public void shootDart ()
	{
		char line[] = new char[this.matrixSize];
		int position = 0, inc = 0, lineIndex = 0, colIndex = 0;
		
		hero.shootDart();
		
		if ((hero.toChar() == '<') || (hero.toChar() == '>') || (hero.toChar() == '1') || (hero.toChar() == '2') || (hero.toChar() == '4') || (hero.toChar() == '5') || (hero.toChar() == '7') || (hero.toChar() == '8'))
		{
			position = hero.getPosY();
			lineIndex = hero.getPosX();
			line = getRow(lineIndex);
			
			if ((hero.toChar() == '<')  || (hero.toChar() == '2') || (hero.toChar() == '5') || (hero.toChar() == '8'))
				inc = -1;
			else if ((hero.toChar() == '>') || (hero.toChar() == '1') || (hero.toChar() == '4') || (hero.toChar() == '7'))
				inc = 1;
		}
		else if ((hero.toChar() == '^') || (hero.toChar() == 'v') || (hero.toChar() == '3') || (hero.toChar() == '6') || (hero.toChar() == '9'))
		{
			position = hero.getPosX();
			colIndex = hero.getPosY();
			line = getCol(colIndex);
			
			if (hero.toChar() == '^')
				inc = -1;
			else if ((hero.toChar() == 'v') || (hero.toChar() == '3') || (hero.toChar() == '6') || (hero.toChar() == '9'))
				inc = 1;
		}

		for (int i = position ;  i > 0 && i < this.matrixSize; i += inc)
		{
			if (line[i] != ' ')
			{
				if (line[i] == 'D' || line[i] == 'd' || line[i] == 'F' || line[i] == 'f' || line[i] == '.' || line[i] == '_' || line[i] == 'º' || line[i] == 'ª')
				{
					if (lineIndex == 0)
						killDragon(i, colIndex);
					else if (colIndex == 0)
						killDragon(lineIndex, i);
					
					break;
				}
			}
		}
		
	}
	
	/**
	 * Returns the n row of the matrix
	 * @param n
	 * @return A char[] data type
	 */
	public char[] getRow(int n)
	{		
		return this.matrix[n];
	}
	
	/**
	 * Returns the n column of the matrix
	 * @param n
	 * @return A char[] data type
	 */
	public char[] getCol(int n)
	{
		char result[] = new char[this.matrixSize];
		
		for (int i = 0; i < this.matrixSize; i++)
			result[i] = this.matrix[i][n];
		
		return result;
	}
	
	/**
	 * Verifies if the dragon is not asleep or dead and if it isn't , it moves randomly
	 */
	public void moveDragon()
	{
		for (Dragon dragon: dragons)
		{
			if ((dragon.getState() == DragonStates.NORMAL) || (dragon.getState() == DragonStates.ONSWORD) || (dragon.getState() == DragonStates.ONDART))
			{
				Random positionGenerator = new Random();
				int position = positionGenerator.nextInt(5);
				
				if (position == 0 && matrix[dragon.getPosX() - 1][dragon.getPosY()] != 'X' && matrix[dragon.getPosX() - 1][dragon.getPosY()] != 'D' && matrix[dragon.getPosX() - 1][dragon.getPosY()] != 'd' && matrix[dragon.getPosX() - 1][dragon.getPosY()] != '_' && matrix[dragon.getPosX() - 1][dragon.getPosY()] != '.' && matrix[dragon.getPosX() - 1][dragon.getPosY()] != 'º'  && matrix[dragon.getPosX() - 1][dragon.getPosY()] != 'ª'  && matrix[dragon.getPosX() - 1][dragon.getPosY()] != 'S') // up
				{
					matrix[dragon.getPosX()][dragon.getPosY()] = ' ';
					dragon.changePos(dragon.getPosX() - 1, dragon.getPosY());
				}
				else if (position == 1 && matrix[dragon.getPosX()][dragon.getPosY() + 1] != 'X' && matrix[dragon.getPosX()][dragon.getPosY() + 1] != 'D'  && matrix[dragon.getPosX()][dragon.getPosY() + 1] != 'd'  && matrix[dragon.getPosX()][dragon.getPosY() + 1] != '_'  && matrix[dragon.getPosX()][dragon.getPosY() + 1] != '.'  && matrix[dragon.getPosX()][dragon.getPosY() + 1] != 'º'  && matrix[dragon.getPosX()][dragon.getPosY() + 1] != 'ª'  && matrix[dragon.getPosX()][dragon.getPosY() + 1] != 'S') // right
				{
					matrix[dragon.getPosX()][dragon.getPosY()] = ' ';
					dragon.changePos(dragon.getPosX(), dragon.getPosY() + 1);
				}
				else if (position == 2 && matrix[dragon.getPosX() + 1][dragon.getPosY()] != 'X' && matrix[dragon.getPosX() + 1][dragon.getPosY()] != 'D' && matrix[dragon.getPosX() + 1][dragon.getPosY()] != 'd' && matrix[dragon.getPosX() + 1][dragon.getPosY()] != '_' && matrix[dragon.getPosX() + 1][dragon.getPosY()] != '.' && matrix[dragon.getPosX() + 1][dragon.getPosY()] != 'º' && matrix[dragon.getPosX() + 1][dragon.getPosY()] != 'ª' && matrix[dragon.getPosX() + 1][dragon.getPosY()] != 'S') // down
				{
					matrix[dragon.getPosX()][dragon.getPosY()] = ' ';
					dragon.changePos(dragon.getPosX() + 1, dragon.getPosY());
				}
				else if (position == 3 && matrix[dragon.getPosX()][dragon.getPosY() - 1] != 'X' && matrix[dragon.getPosX()][dragon.getPosY() - 1] != 'D' && matrix[dragon.getPosX()][dragon.getPosY() - 1] != 'd' && matrix[dragon.getPosX()][dragon.getPosY() - 1] != '_' && matrix[dragon.getPosX()][dragon.getPosY() - 1] != '.' && matrix[dragon.getPosX()][dragon.getPosY() - 1] != 'º' && matrix[dragon.getPosX()][dragon.getPosY() - 1] != 'ª' && matrix[dragon.getPosX()][dragon.getPosY() - 1] != 'S') // left
				{
					matrix[dragon.getPosX()][dragon.getPosY()] = ' ';
					dragon.changePos(dragon.getPosX(), dragon.getPosY() - 1);
				}
				
				if (matrix[dragon.getPosX()][dragon.getPosY()] == 'E')
					dragon.changeState(DragonStates.ONSWORD);
				else if (matrix[dragon.getPosX()][dragon.getPosY()] == '*')
					dragon.changeState(DragonStates.ONDART);
				else if (matrix[dragon.getPosX()][dragon.getPosY()] == 'o')
					dragon.changeState(DragonStates.ONSHIELD);
				
				if (dragon.getState() == DragonStates.ONSWORD && matrix[dragon.getPosX()][dragon.getPosY()] == ' ')
				{
					matrix[sword.getPosX()][sword.getPosY()] = sword.toChar();
					dragon.changeState(DragonStates.NORMAL);
				}
				else if (dragon.getState() == DragonStates.ONDART && matrix[dragon.getPosX()][dragon.getPosY()] == ' ')
				{
					
					dragon.changeState(DragonStates.NORMAL);
					
					for (Dart dart: darts)
					{
						if (!dart.wasGrabbed())
							matrix[dart.getPosX()][dart.getPosY()] = dart.toChar();
					}
				}
				else if (position == 4)
				{
					if (dragon.getState() == DragonStates.NORMAL)
						dragon.changeState(DragonStates.SLEEP);
					else if (dragon.getState() == DragonStates.ONSWORD)
						dragon.changeState(DragonStates.SLEEPONSWORD);
					else if (dragon.getState() == DragonStates.ONDART)
						dragon.changeState(DragonStates.SLEEPONDART);
					else if (dragon.getState() == DragonStates.ONSHIELD)
						dragon.changeState(DragonStates.SLEEPONSHIELD);
				}
			}
			else if ((dragon.getState() == DragonStates.SLEEP) || (dragon.getState() == DragonStates.SLEEPONSWORD) || (dragon.getState() == DragonStates.SLEEPONDART) || (dragon.getState() == DragonStates.SLEEPONSHIELD))
			{
				Random positionGenerator = new Random();
				int position = positionGenerator.nextInt(2);
				
				if (position == 0)
				{
					if (dragon.getState() == DragonStates.SLEEP)
						dragon.changeState(DragonStates.NORMAL);
					else if (dragon.getState() == DragonStates.SLEEPONSWORD)
						dragon.changeState(DragonStates.ONSWORD);
					else if (dragon.getState() == DragonStates.SLEEPONDART)
						dragon.changeState(DragonStates.ONDART);
					else if (dragon.getState() == DragonStates.SLEEPONSHIELD)
						dragon.changeState(DragonStates.ONSHIELD);
				}
			}
			
			
			if (dragon.getState() == DragonStates.DEAD && dragon.getPosX() == hero.getPosX() && dragon.getPosY() == hero.getPosY())
				matrix[dragon.getPosX()][dragon.getPosY()] = hero.toChar();
			else if (dragon.getState() == DragonStates.DEAD && dragon.getPosX() == shield.getPosX() && dragon.getPosY() == shield.getPosY())
				matrix[dragon.getPosX()][dragon.getPosY()] = shield.toChar();
			else
				matrix[dragon.getPosX()][dragon.getPosY()] = dragon.toChar();
			
			/*if (hero.getPosX() == shield.getPosX() && hero.getPosY() == shield.getPosY())
				matrix[hero.getPosX()][hero.getPosY()] = hero.toChar();*/
			
		}
		
		this.update();
	}
	
	/**
	 * Verifies if the game is over
	 * Returns True if the hero won or False if the hero died
	 * @return A boolean data type
	 */
	public boolean gameOver()
	{
		if (hero.getState() == HeroStates.WINNER)
		{
			System.out.println("Ganhou!");
			return true;
		}
		if (hero.getState() == HeroStates.DEAD)
		{
			System.out.println("Perdeu!");
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Updates the state of the dragons and the hero after their moves
	 */
	public void update()
	{	
		// Verificar se está adjacente a um dragão
		for (Dragon dragon: dragons)
		{
			// Se está adjacente
			if (((hero.getPosX() == dragon.getPosX()) && (Math.abs(hero.getPosY() - dragon.getPosY()) == 1) || ((hero.getPosY() == dragon.getPosY()) && (Math.abs(hero.getPosX() - dragon.getPosX()) == 1))))
				if (hero.getState() == HeroStates.ARMED)
					killDragon (dragon.getPosX(), dragon.getPosY());
				else
				{
					if (dragon.getState() == DragonStates.NORMAL || dragon.getState() == DragonStates.ONSWORD || dragon.getState() == DragonStates.ONDART || dragon.getState() == DragonStates.ONSHIELD)
					{
						hero.changeState(HeroStates.DEAD);
						matrix[hero.getPosX()][hero.getPosY()] = '†';
					}
				}
			
			// Se está a menos de 3 casas (nao pode cuspir por cima das paredes)
			if (!hero.isShielded() && dragon.getState() != DragonStates.SLEEP && dragon.getState() != DragonStates.SLEEPONSWORD && dragon.getState() != DragonStates.SLEEPONDART && dragon.getState() != DragonStates.SLEEPONSHIELD)
			{
				if (Math.abs (hero.getPosX() - dragon.getPosX()) < 3) // horizontal
				{
					char line[] = new char[this.matrixSize];
					line = getRow(dragon.getPosX());
					
					int inc, fire_range = 3;
					
					if (hero.getPosX() > dragon.getPosX())
						inc = -1;
					else
						inc = 1;
						
					
					for (int i = dragon.getPosY() ;  i > 0 && i < this.matrixSize && fire_range > 0; i += inc)
					{
						System.out.print ("[" + line[i] + "]");
						
						if (line[i] == 'X')
							break;
						else if (line[i] == hero.toChar())
						{
							System.out.println("Killed by dragon (" + dragon.getPosX() + ", " + dragon.getPosY() + ").");
							hero.changeState(HeroStates.DEAD);
							matrix[hero.getPosX()][hero.getPosY()] = '†';
							break;
						}
						else
							fire_range--;
					}
				}
				else if (Math.abs (hero.getPosY() - dragon.getPosY()) < 3) // vertical
					{
						char line[] = new char[this.matrixSize];
						line = getCol(dragon.getPosY());
						int inc, fire_range = 3;
						
						if (hero.getPosY() > dragon.getPosY())
							inc = -1;
						else
							inc = 1;
							
						
						for (int i = dragon.getPosX() ;  i > 0 && i < this.matrixSize && fire_range > 0; i += inc)
						{
							if (line[i] == 'X')
								break;
							else if (line[i] == hero.toChar())
							{
								System.out.println("Killed by dragon (" + dragon.getPosX() + ", " + dragon.getPosY() + ").");
								hero.changeState(HeroStates.DEAD);
								matrix[hero.getPosX()][hero.getPosY()] = '†';
								break;
							}
							else
								fire_range--;
						}
					}
			}
		}
	}
	
	/**
	 * Changes the state of the dragon positioned in the coordinates given as a parameter to DEAD
	 * @param pos_x
	 * @param pos_y
	 */
	public void killDragon (int pos_x, int pos_y)
	{
		for (Dragon dragon: dragons)
		{
			if (dragon.getPosX() == pos_x && dragon.getPosY() == pos_y)
			{
				if (dragon.getState() == DragonStates.ONSWORD || dragon.getState() == DragonStates.SLEEPONSWORD)
					matrix[dragon.getPosX()][dragon.getPosY()] = 'E';
				else if (dragon.getState() == DragonStates.ONDART || dragon.getState() == DragonStates.SLEEPONDART)
					matrix[dragon.getPosX()][dragon.getPosY()] = '*';
				
				dragon.changeState(DragonStates.DEAD);
				
				
				System.out.println("Dragon(" + dragon.getPosX() + ", " + dragon.getPosY() + ") is dead.");
			}
		}
	}

	/**
	 * Loads to the current game the objects of another game
	 * @param newgame
	 */
	public void loadNewGame(Game newgame)
	{
		System.out.println("Size matrix: " + newgame.getMatrix().length);
		
		matrixSize = newgame.getMatrix().length;
		matrix = new char [newgame.getMatrix().length][newgame.getMatrix().length];
		matrix = newgame.getMatrix();
		hero = newgame.getHero();
		darts = newgame.getDarts();
		shield = newgame.getShield();
		dragons = newgame.getDragons();
		sword = newgame.getSword();
		
		printMatrix();
	}
}