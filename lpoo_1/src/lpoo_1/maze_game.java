package lpoo_1;

import java.util.*;

public class maze_game
{
	
	// Variáveis do labirinto
	
	static char maze_map[][] =
	{
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
		{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', 'D', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', 'E', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }
	};
	
	// Variáveis do Herói
	
	static int hero_x = 1, hero_y = 1;
	static char hero_state = 'H'; // pode ser 'H' (normal), 'A' (armado) ou ' ' (morto);
	
	// Variáveis da Saída
	
	static int exit_x = 5, exit_y = 9;
	
	// Variáveis da Espada
	
	static int sword_x = 8, sword_y = 1;
	
	// Variáveis do Dragão
	
	static int dragon_x = 3, dragon_y = 1;
	static char dragon_state = 'D'; // pode ser 'D' (normal), 'F' em cima da espada ou ' ' (morto);
	
	// Funções
	
	public static void main(String[] args)
	{
		while (!gameOver())
		{
			printMaze();
			moveHero();
		}
		printMaze();
		System.out.println("Fim de jogo!");
	}
	
	public static boolean gameOver()
	{		
		if (hero_state == 'W')
			return true;
		if (hero_state != 'A' && ((hero_x == dragon_x && Math.abs(hero_y - dragon_y) == 1) || (hero_y == dragon_y && Math.abs(hero_x - dragon_x) == 1)))
		{
			System.out.println(hero_state);
			return true;
		}
		else
			return false;
	}
	
	public static void printMaze()
	{
		for (int i = 0; i < 10; i ++)
		{
			for (int j = 0; j < 10; j++)
				System.out.print(maze_map[i][j]);
			
			System.out.println();
		}	
	}
	
	public static void moveHero()
	{
		boolean keyIsViable = false;
		char key;
		while (!keyIsViable)
		{
			key = readKey();
			
			keyIsViable = true;
			
			if (key == 'w' && movementViable(hero_x - 1, hero_y))
			{
				maze_map[hero_x][hero_y] = ' ';
				hero_x--;
				maze_map[hero_x][hero_y] = hero_state;
			}
			else if (key == 'd' && movementViable(hero_x, hero_y + 1))
			{
				maze_map[hero_x][hero_y] = ' ';
				hero_y++;
				maze_map[hero_x][hero_y] = hero_state;
			}
			else if (key == 's' && movementViable(hero_x + 1, hero_y))
			{
				maze_map[hero_x][hero_y] = ' ';
				hero_x++;
				maze_map[hero_x][hero_y] = hero_state;
			}
			else if (key == 'a' && movementViable(hero_x, hero_y - 1))
			{
				maze_map[hero_x][hero_y] = ' ';
				hero_y--;
				maze_map[hero_x][hero_y] = hero_state;
			}
			else if (key != 'w' && key != 'd' && key != 's' && key != 'a')
			{
				System.out.println("Direcção inválida, insira outra.");
				keyIsViable = false;
			}
		}
	}
	
	public static boolean movementViable(int pos_x, int pos_y)
	{
		if (maze_map[pos_x][pos_y] == ' ')
			return true;
		else if (maze_map[pos_x][pos_y] == 'E')
		{
			hero_state = 'A';
			maze_map[exit_x][exit_y] = 'S';
			return true;
		}
		else if (maze_map[pos_x][pos_y] == 'S')
		{
			hero_state = 'W';
			return true;
		}
		else if (maze_map[pos_x][pos_y] == 'D' && hero_state == 'A')
		{
			dragon_state = ' ';
			maze_map[dragon_x][dragon_y] = dragon_state;
			return true;
		}
		else
			return false;
	}
	
	public static char readKey()
	{
		System.out.print("Insira uma direcção (W, A, S, D): ");
		Scanner c = new Scanner(System.in);
		return c.next().charAt(0);
	}
}