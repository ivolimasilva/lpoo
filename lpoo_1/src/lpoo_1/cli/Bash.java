package lpoo_1.cli;

import lpoo_1.logic.*;
import java.util.*;

public class Bash
{
	public static void main(String [] args)
	{
		System.out.println ("The Maze Game.\nThis is the console!");
		
		Game game = new Game();
		
		while (!game.gameOver())
		{
			// Update aos elementos (herói, dragões e espada).
			// game.update();
			
			// Imprime a matrix de jogo.
			game.printMatrix();
			
			// Move heroi.
			char dir = readMovement();
			game.moveHero(dir);
			if (game.gameOver())
				break;
			
			game.moveDragon();
			if (game.gameOver())
				break;
		}
		
		// Imprime a matrix de jogo.
		game.printMatrix();
	}
	
	public static char readChar()
	{
		System.out.print("Insira uma direcção (W, A, S, D): ");
		Scanner c = new Scanner(System.in);
		return c.next().charAt(0);
	}
	
	public static char readMovement()
	{
		boolean keyIsViable = false;
		char key = readChar();
		
		while (!keyIsViable)
		{						
			if (key != 'w' && key != 'd' && key != 's' && key != 'a')
			{
				System.out.println("Direcção inválida, insira outra.");
				key = readChar();
			}
			else
				keyIsViable = true;
		}
		
		return key;
	}
}
