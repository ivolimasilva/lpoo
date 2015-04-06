package lpoo_1.logic;

import java.util.ArrayList;

import java.util.Random;

import lpoo_1.logic.Dragon;
import lpoo_1.logic.Dragon.DragonStates;

import lpoo_1.logic.Hero;
import lpoo_1.logic.Hero.HeroStates;

public class Game
{
	private char matrix[][] =
	{
		{ 'X', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' },
		{ '0', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
		{ '1', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ '2', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ '3', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ '4', 'X', ' ', ' ', ' ', 'X', 'X', ' ', ' ', ' ', 'X' },
		{ '5', 'X', ' ', ' ', ' ', 'X', 'X', ' ', ' ', ' ', 'X' },
		{ '6', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ '7', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'S' },
		{ '8', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ '9', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }
	};
	
	private Hero hero;
	private Sword sword;
	private ArrayList<Dart> darts = new ArrayList<Dart>();
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	
	public Game()
	{
		hero = new Hero(2, 2);
		matrix[hero.getPosX()][hero.getPosY()] = hero.toChar();
		
		sword = new Sword(5, 2);
		matrix[sword.getPosX()][sword.getPosY()] = sword.toChar();
		
		Dart dart_aux = new Dart(3, 3);
		darts.add(dart_aux);
		
		for (Dart dart: darts)
		{
			matrix[dart.getPosX()][dart.getPosY()] = dart.toChar();
		}
		
		Dragon dragon_aux = new Dragon(5, 3, DragonStates.NORMAL);
		dragons.add(dragon_aux);
		
		dragon_aux = new Dragon(5, 7, DragonStates.NORMAL);
		dragons.add(dragon_aux);
		
		for (Dragon dragon: dragons)
		{
			matrix[dragon.getPosX()][dragon.getPosY()] = dragon.toChar();
		}
	}
	
	public void printMatrix()
	{
		for (int i = 0; i < 11; i ++)
		{
			for (int j = 0; j < 11; j++)
				System.out.print(matrix[i][j] + " ");
			
			System.out.println();
		}
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
			if (hero.getState() != HeroStates.WITHDART)
				return false;
			else
				shootDart();
		}
		else
			return false;
		
		// Verificação da posição para onde quer ir.
		if (matrix[new_x][new_y] == 'X')
			return false;
		else if (matrix[new_x][new_y] == 'E')
		{
			hero.changeState(HeroStates.WITHSWORD);
			sword.grabbed();
		}
		else if (matrix[new_x][new_y] == '*')
		{
			hero.changeState(HeroStates.WITHDART);
			grabDart(new_x, new_y);
		}
		else if (matrix[new_x][new_y] == 'S')
		{
			if (hero.getState() == HeroStates.WITHSWORD)
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
		{
			if (dart.getPosX() == pos_x && dart.getPosY() == pos_y)
			{
				dart.grabbed();
			}
		}
	}
	
	public void shootDart ()
	{
		
	}
	
	public void moveDragon()
	{
		for (Dragon dragon: dragons)
		{
			if ((dragon.getState() == DragonStates.NORMAL) || (dragon.getState() == DragonStates.ONSWORD))
			{
				Random positionGenerator = new Random();
				int position = positionGenerator.nextInt(4);
				
				if (position == 0 && matrix[dragon.getPosX() - 1][dragon.getPosY()] != 'X') // up
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
				
				if (dragon.getState() == DragonStates.ONSWORD && matrix[dragon.getPosX()][dragon.getPosY()] == ' ')
				{
					matrix[sword.getPosX()][sword.getPosY()] = sword.toChar();
					dragon.changeState(DragonStates.NORMAL);
				}
				
				matrix[dragon.getPosX()][dragon.getPosY()] = dragon.toChar();
			}
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
			if (((hero.getPosX() == dragon.getPosX()) && (Math.abs(hero.getPosY() - dragon.getPosY()) == 1) || ((hero.getPosY() == dragon.getPosY()) && (Math.abs(hero.getPosX() - dragon.getPosX()) == 1))))
				if (hero.getState() == HeroStates.WITHSWORD)
				{
					dragon.changeState(DragonStates.DEAD);
					
					matrix[dragon.getPosX()][dragon.getPosY()] = '†';
					
					System.out.println("Dragon is dead.");
				}
				else
				{
					hero.changeState(HeroStates.DEAD);
					matrix[hero.getPosX()][hero.getPosY()] = '†';
				}
		}
	}
}