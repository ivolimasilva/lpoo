package lpoo_1.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import lpoo_1.gui.GamePanel;

public class GameFrame extends JFrame
{

	private JPanel contentPane;
	static JPanel gamePanel;
	static GameFrame frame;
	
	public static int size;
	public static boolean random;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					frame = new GameFrame();
					//frame.pack();
					frame.setVisible(true);
					gamePanel.requestFocusInWindow();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public GameFrame()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("The Maze Game!");
		
		this.size = 10;
		this.random = false;
		
		if (size == 10)
			setBounds((1920 - ((size + 2) * 32)) / 2, (1080 - ((size + 1) * 32 + 65)) / 2, (size + 2) * 32, (size + 1) * 32 + 65);
		else
			setBounds((1920 - ((size + 1) * 32)) / 2, (1080 - ((size + 1) * 32 + 65)) / 2, (size + 1) * 32, (size + 1) * 32 + 65);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		gamePanel = new GamePanel(random, size);
		contentPane.add(gamePanel, BorderLayout.CENTER);
		
		JPanel btnPanel = new JPanel();
		contentPane.add(btnPanel, BorderLayout.NORTH);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					if (JOptionPane.showConfirmDialog(null, "Começar jogo Novo?", "New Game?", JOptionPane.YES_NO_OPTION) == 0)
					{
						((GamePanel) gamePanel).LoadNewMatrix(random, size);
						contentPane.add(gamePanel, BorderLayout.CENTER);
						
						if (size == 10)
							setBounds((1920 - ((size + 2) * 32)) / 2, (1080 - ((size + 1) * 32 + 65)) / 2, (size + 2) * 32, (size + 1) * 32 + 65);
						else
							setBounds((1920 - ((size + 1) * 32)) / 2, (1080 - ((size + 1) * 32 + 65)) / 2, (size + 1) * 32, (size + 1) * 32 + 65);
						
						contentPane.repaint();
						contentPane.setVisible(true);
						gamePanel.requestFocusInWindow();
					}
				}
			}
		);
		btnPanel.add(btnNewGame);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					Settings settings = new Settings();
					settings.setVisible(true);
					settings.setLocationRelativeTo(null);
					settings.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				}
				
			}
		);
		btnPanel.add(btnSettings);
		
		JButton btnSaveLoad = new JButton("Save/Load");
		btnSaveLoad.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					SaveLoad saveload = new SaveLoad(frame);
					saveload.setVisible(true);
					saveload.setLocationRelativeTo(null);
					saveload.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								
					gamePanel.requestFocusInWindow();
				}
				
			}
		);
		btnPanel.add(btnSaveLoad);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					if (JOptionPane.showConfirmDialog(null, "Sair?", "Exit",JOptionPane.YES_NO_OPTION) == 0)
					{
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					}
				}
			}
		);
		btnPanel.add(btnExit);
	}

}
