package lpoo_1.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GameFrame extends JFrame
{

	private JPanel contentPane;
	static JPanel gamePanel;
	static GameFrame frame;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("The Maze Game!");
		setBounds((1920 - 350) / 2, (1080 - 405) / 2, 350, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		gamePanel = new GamePanel(this);
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
					
					//0=YES , 1=NO
					if (JOptionPane.showConfirmDialog(null, "Começar jogo Novo?", "New Game?",JOptionPane.YES_NO_OPTION) == 0)
					{
						System.out.println("NewGame!");
						gamePanel = new GamePanel (frame);
						frame.setVisible(true);
						contentPane.add(gamePanel, BorderLayout.CENTER);
						gamePanel.requestFocusInWindow();
						
						gamePanel.repaint();
						gamePanel.requestFocusInWindow();
					}
				}
			}
		);
		btnPanel.add(btnNewGame);
		
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
