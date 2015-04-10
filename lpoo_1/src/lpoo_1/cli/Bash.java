package lpoo_1.cli;

import lpoo_1.logic.*;
import java.util.*;

public class Bash
{
	
	private static int matrixSize = 11;
	private static char[][] matrix;
	
	public static void main(String [] args)
	{
		System.out.println ("The Maze Game.\nThis is the console!");
		
		Game game = new Game(matrixSize, false);
		
		// Imprime a matrix de jogo.
		matrix = game.getMatrix();
		printMatrix();
		
		while (!game.gameOver())
		{	
			// Move heroi.
			char dir = readMovement();
			game.moveHero(dir);
			if (game.gameOver())
				break;
			
			game.moveDragon();
			if (game.gameOver())
				break;
			
			// Imprime a matrix de jogo.
			matrix = game.getMatrix();
			printMatrix();
		}
		
		// Imprime a matrix de jogo.
		matrix = game.getMatrix();
		printMatrix();

	}
	
	public static void printMatrix()
	{
		for (int i = 0; i < matrixSize; i ++)
		{
			for (int j = 0; j < matrixSize; j++)
				System.out.print(matrix[i][j] + " ");
			
			System.out.println();
		}
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
			if (key != 'w' && key != 'd' && key != 's' && key != 'a' && key != '+')
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
