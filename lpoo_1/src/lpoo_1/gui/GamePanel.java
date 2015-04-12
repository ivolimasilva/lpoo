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
		System.out.println("Matrix loaded!");
		//printMatrix();
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
			System.out.println("1");
			printMatrix();
			repaint();
			
			// Encerra a janela
			//myMainWindow.dispatchEvent(new WindowEvent(myMainWindow, WindowEvent.WINDOW_CLOSING));
		}
		else
		{
			// Move dragões
			game.moveDragon();
			
			// Desenha matrix
			matrix = game.getMatrix();
			System.out.println("2");
			printMatrix();
			repaint();
		}
		
		// Verifica se o jogo acabou
		if (game.gameOver())
		{
			// Desenha matrix
			matrix = game.getMatrix();
			System.out.println("3");
			printMatrix();
			repaint();
			
			// Encerra a janela
			//myMainWindow.dispatchEvent(new WindowEvent(myMainWindow, WindowEvent.WINDOW_CLOSING));
		}
		else
		{
			// Desenha matrix
			matrix = game.getMatrix();
			System.out.println("4");
			printMatrix();
			repaint();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		Image img;
		
		super.paintComponent(g);
		
		for (int i = 1; i < matrixSize; i ++)
		{
			for (int j = 1; j < matrixSize; j++)
			{
				ImageIcon aux_img = new ImageIcon("img/map/floor.png");
				img = aux_img.getImage();
				g.drawImage(img, (i - 1) * 32, (j - 1) * 32, null);
				
				if (matrix[j][i] == 'X')
					aux_img = new ImageIcon("img/map/wall.png");
				
				else if (matrix[j][i] == '^')
					aux_img = new ImageIcon("img/hero/back.png");
				
				else if (matrix[j][i] == 'v')
					aux_img = new ImageIcon("img/hero/front.png");
				else if (matrix[j][i] == '>')
					aux_img = new ImageIcon("img/hero/right.png");
				else if (matrix[j][i] == '<')
					aux_img = new ImageIcon("img/hero/left.png");
				
				else if (matrix[j][i] == '1')
					aux_img = new ImageIcon("img/hero/right_sword.png");
				else if (matrix[j][i] == '2')
					aux_img = new ImageIcon("img/hero/left_sword.png");
				else if (matrix[j][i] == '3')
					aux_img = new ImageIcon("img/hero/front_sword.png");
				
				else if (matrix[j][i] == '4')
					aux_img = new ImageIcon("img/hero/right_shield.png");
				else if (matrix[j][i] == '5')
					aux_img = new ImageIcon("img/hero/left_shield.png");
				else if (matrix[j][i] == '6')
					aux_img = new ImageIcon("img/hero/front_shield.png");
				
				else if (matrix[j][i] == '7')
					aux_img = new ImageIcon("img/hero/right_sword_shield.png");
				else if (matrix[j][i] == '8')
					aux_img = new ImageIcon("img/hero/left_sword_shield.png");
				else if (matrix[j][i] == '9')
					aux_img = new ImageIcon("img/hero/front_sword_shield.png");
				
				else if (matrix[j][i] == 'W')
					aux_img = new ImageIcon("img/hero/winner.png");
				
				else if (matrix[j][i] == 'D' || matrix[j][i] == 'F' || matrix[j][i] == '.' || matrix[j][i] == 'º')
					aux_img = new ImageIcon("img/dragon/front.png");
				else if (matrix[j][i] == 'd' || matrix[j][i] == 'f' || matrix[j][i] == '_' || matrix[j][i] == 'ª')
					aux_img = new ImageIcon("img/dragon/back.png");
				
				else if (matrix[j][i] == 'S')
					aux_img = new ImageIcon("img/map/exit.png");
				else if (matrix[j][i] == 'E')
					aux_img = new ImageIcon("img/map/sword.png");
				else if (matrix[j][i] == 'o')
					aux_img = new ImageIcon("img/map/shield.png");
				else if (matrix[j][i] == '*')
					aux_img = new ImageIcon("img/map/dart.png");
				else if (matrix[j][i] == '†')
					aux_img = new ImageIcon("img/map/grave.png");
					
				img = aux_img.getImage();				
				g.drawImage(img, (i - 1) * 32, (j - 1) * 32, null);
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

	public void keyReleased(KeyEvent e)
	{
	}

	public void keyTyped(KeyEvent e)
	{
		System.out.println("Key down;");
		game.moveHero('+');
	}
}
