package lpoo_1.gui;

import lpoo_1.logic.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener
{
	
	private static int matrixSize = 11;
	private static char[][] matrix;
	DemoMaze Maze = new DemoMaze(matrixSize);
	Game game = new Game(matrixSize, false);
	
	JFrame myMainWindow;
	
	public GamePanel(JFrame frame)
	{
		super();
		
		myMainWindow = frame;
		
		this.addKeyListener(this);
		
		// Desenha matrix
		matrix = game.getMatrix();
		printMatrix();
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		// Move heroi
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_LEFT: 
				System.out.println("Key left;");
				game.moveHero('a');
				break;
				
			case KeyEvent.VK_RIGHT:
				System.out.println("Key right;");
				game.moveHero('d');
				break;
	
			case KeyEvent.VK_UP:
				System.out.println("Key up;");
				game.moveHero('w');
				break;
	
			case KeyEvent.VK_DOWN:
				System.out.println("Key down;");
				game.moveHero('s');
				break;
		}
	
		// Verifica se o jogo acabou
		if (game.gameOver())
		{
			// Desenha matrix
			matrix = game.getMatrix();
			printMatrix();
			repaint();
			
			// Encerra a janela
			myMainWindow.dispatchEvent(new WindowEvent(myMainWindow, WindowEvent.WINDOW_CLOSING));
		}
		else
		{
			// Move dragões
			game.moveDragon();
			
			// Desenha matrix
			matrix = game.getMatrix();
			printMatrix();
			repaint();
		}
		
		// Verifica se o jogo acabou
		if (game.gameOver())
		{
			// Desenha matrix
			matrix = game.getMatrix();
			printMatrix();
			repaint();
			
			// Encerra a janela
			myMainWindow.dispatchEvent(new WindowEvent(myMainWindow, WindowEvent.WINDOW_CLOSING));
		}
		else
		{
			// Desenha matrix
			matrix = game.getMatrix();
			printMatrix();
			repaint();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		Image img;
		
		super.paintComponent(g);
		
		for (int i = 0; i < matrixSize; i ++)
		{
			for (int j = 0; j < matrixSize; j++)
			{
				ImageIcon aux_img = new ImageIcon("img/floor.png");
				img = aux_img.getImage();				
				g.drawImage(img, (i)*16, (j)*16, null);
				
				if (matrix[j][i] == 'X')
					aux_img = new ImageIcon("img/wall.png");
				else if (matrix[j][i] == 'H')
					aux_img = new ImageIcon("img/hero.png");
				else if (matrix[j][i] == 'A')
					aux_img = new ImageIcon("img/hero.png");
				else if (matrix[j][i] == 'D')
					aux_img = new ImageIcon("img/dragon.png");
				else if (matrix[j][i] == 'd')
					aux_img = new ImageIcon("img/dragon.png");
				else if (matrix[j][i] == 'S')
					aux_img = new ImageIcon("img/exit.png");
				else if (matrix[j][i] == 'E')
					aux_img = new ImageIcon("img/sword.png");
					
				img = aux_img.getImage();				
				g.drawImage(img, (i)*16, (j)*16, null);
			}
			
		}
	}

	private void printMatrix()
	{
		for (int i = 0; i < matrixSize; i ++)
		{
			for (int j = 0; j < matrixSize; j++)
				System.out.print(matrix[i][j] + " ");
			
			System.out.println();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		//System.out.println("KeyReleased");
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		System.out.println("KeyTyped");
	}
}
