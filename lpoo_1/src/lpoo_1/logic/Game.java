package lpoo_1.logic;

import java.util.ArrayList;
import java.util.Random;

import lpoo_1.logic.Dragon;
import lpoo_1.logic.Dragon.DragonStates;
import lpoo_1.logic.Hero;
import lpoo_1.logic.Hero.HeroStates;

public class Game
{
	private char matrix[][];
	
	private int matrixSize;
	
	private Hero hero;
	private Sword sword;
	private Shield shield;
	private ArrayList<Dart> darts = new ArrayList<Dart>();
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	
	public Game(int size, boolean random)
	{
		this.matrixSize = size;
		
		MazeBuilder Maze = new DemoMaze(size);
		
		this.matrix = Maze.getMatrix();
		this.dragons = Maze.getDragons();
		
		for (Dragon dragon: dragons)
		{
			this.matrix[dragon.getPosX()][dragon.getPosY()] = dragon.toChar();
		}
		
		this.hero = Maze.getHero();
		
		this.sword = Maze.getSword();
		
		this.shield = Maze.getShield();
		
		this.darts = Maze.getDarts();

	}
	
	public void printMatrix()
	{
		for (int i = 0; i < this.matrixSize; i ++)
		{
			for (int j = 0; j < this.matrixSize; j++)
				System.out.print(matrix[i][j] + " ");
			
			System.out.println();
		}
	}
	
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
	
	public void grabDart (int pos_x, int pos_y)
	{
		for (Dart dart: darts)
			if (dart.getPosX() == pos_x && dart.getPosY() == pos_y)
				dart.grabbed();
	}
	
	public void shootDart ()
	{
		char line[] = new char[this.matrixSize];
		int position = 0, inc = 0, lineIndex = 0, colIndex = 0;
		
		hero.shotDart();
		
		if ((hero.toChar() == '<') || (hero.toChar() == '>'))
		{
			position = hero.getPosY();
			lineIndex = hero.getPosX();
			line = getRow(lineIndex);
			
			if (hero.toChar() == '<')
				inc = -1;
			else if (hero.toChar() == '>')
				inc = 1;
		}
		else if ((hero.toChar() == '^') || (hero.toChar() == 'v'))
		{
			position = hero.getPosX();
			colIndex = hero.getPosY();
			line = getCol(colIndex);
			
			if (hero.toChar() == '^')
				inc = -1;
			else if (hero.toChar() == 'v')
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
		
		hero.changeState(HeroStates.NORMAL);
		
	}
	
	public char[] getRow(int n)
	{		
		return this.matrix[n];
	}
	
	public char[] getCol(int n)
	{
		char result[] = new char[this.matrixSize];
		
		for (int i = 0; i < this.matrixSize; i++)
			result[i] = this.matrix[i][n];
		
		return result;
	}
	
	public void moveDragon()
	{
		for (Dragon dragon: dragons)
		{
			if ((dragon.getState() == DragonStates.NORMAL) || (dragon.getState() == DragonStates.ONSWORD) || (dragon.getState() == DragonStates.ONDART))
			{
				Random positionGenerator = new Random();
				int position = positionGenerator.nextInt(5);
				
				if (position == 0 && matrix[dragon.getPosX() - 1][dragon.getPosY()] != 'X' && matrix[dragon.getPosX() - 1][dragon.getPosY()] != 'D' && matrix[dragon.getPosX() - 1][dragon.getPosY()] != 'd' && matrix[dragon.getPosX() - 1][dragon.getPosY()] != '_' && matrix[dragon.getPosX() - 1][dragon.getPosY()] != '.') // up
				{
					matrix[dragon.getPosX()][dragon.getPosY()] = ' ';
					dragon.changePos(dragon.getPosX() - 1, dragon.getPosY());
				}
				else if (position == 1 && matrix[dragon.getPosX()][dragon.getPosY() + 1] != 'X') // right
				{
					matrix[dragon.getPosX()][dragon.getPosY()] = ' ';
					dragon.changePos(dragon.getPosX(), dragon.getPosY() + 1);
				}
				else if (position == 2 && matrix[dragon.getPosX() + 1][dragon.getPosY()] != 'X') // down
				{
					matrix[dragon.getPosX()][dragon.getPosY()] = ' ';
					dragon.changePos(dragon.getPosX() + 1, dragon.getPosY());
				}
				else if (position == 3 && matrix[dragon.getPosX()][dragon.getPosY() - 1] != 'X') // left
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
			
			// Se está a menos de 3 casas
			if (((hero.getPosX() == dragon.getPosX()) && (Math.abs(hero.getPosY() - dragon.getPosY()) <= 3) || ((hero.getPosY() == dragon.getPosY()) && (Math.abs(hero.getPosX() - dragon.getPosX()) <= 3))))
			{
				if (!hero.isShielded() && (dragon.getState() == DragonStates.NORMAL || dragon.getState() == DragonStates.ONSWORD || dragon.getState() == DragonStates.ONDART || dragon.getState() == DragonStates.ONSHIELD))
				{
					hero.changeState(HeroStates.DEAD);
					matrix[hero.getPosX()][hero.getPosY()] = '†';
				}
			}
		}
	}
	
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
}