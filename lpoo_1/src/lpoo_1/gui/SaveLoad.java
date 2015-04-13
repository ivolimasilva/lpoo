package lpoo_1.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import lpoo_1.logic.Hero.HeroStates;

public class SaveLoad extends JDialog
{
	private JPanel contentPane;
	private JTextField savePath;
	private JTextField loadPath;
	
	public SaveLoad(char[][] matrix) //, HeroStates heroState, int darts)
	{
		this.setTitle("Save or Load games!");
		this.setBounds(100, 100, 300, 150);
		
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);
		
		JLabel lblSavePath = new JLabel("Select Name:");
		lblSavePath.setBounds(10, 11, 82, 14);
		getContentPane().add(lblSavePath);
		
		savePath = new JTextField();
		//savePath.setBounds(10, 10, 166, 20);
		this.getContentPane().add(savePath);
		savePath.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		//btnSave.setBounds(100, 10, 50, 20);
		getContentPane().add(btnSave);
		
		JLabel lblLoadPath = new JLabel("Select Name:");
		lblLoadPath.setBounds(10, 11, 82, 14);
		getContentPane().add(lblLoadPath);
		
		loadPath = new JTextField();
		//savePath.setBounds(10, 10, 166, 20);
		this.getContentPane().add(loadPath);
		loadPath.setColumns(10);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		//btnSave.setBounds(100, 10, 50, 20);
		getContentPane().add(btnLoad);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		//btnSave.setBounds(100, 10, 50, 20);
		getContentPane().add(btnCancel);
	}
}