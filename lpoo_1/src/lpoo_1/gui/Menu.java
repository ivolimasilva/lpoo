package lpoo_1.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Image;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu
{

	private JFrame windowMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Menu window = new Menu();
					window.windowMenu.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		// JFrame();
		windowMenu = new JFrame();
		windowMenu.setTitle("The Maze Game!");
		windowMenu.setBounds(1440/2 - 300/2, 900/2 - 400/2, 300, 400);
		windowMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowMenu.getContentPane().setLayout(null);
		windowMenu.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		// JButton();
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// to-do function
			}
		});
		btnPlay.setBounds(0, 0, 100, 35);
		windowMenu.getContentPane().add(btnPlay);
		
		// JButton();
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// to-do function
			}
		});
		btnSettings.setBounds(0, 35, 100, 35);
		windowMenu.getContentPane().add(btnSettings);

		// JButton();
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				windowMenu.setVisible(false);
				System.exit(0);
			}
		});
		btnClose.setBounds(0, 70, 100, 35);
		windowMenu.getContentPane().add(btnClose);
	}
}