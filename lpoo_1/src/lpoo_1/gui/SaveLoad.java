package lpoo_1.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import lpoo_1.logic.Game;
import lpoo_1.logic.Hero.HeroStates;

public class SaveLoad extends JDialog
{
	private JPanel contentPane;
	private JTextField savePath;
	private JTextField loadPath;
	
	public SaveLoad(JFrame frame)
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
				ObjectOutputStream file = null;
				try
				{
					file = new ObjectOutputStream (new FileOutputStream(savePath.getText() + ".dat"));
					file.writeObject(GamePanel.getGame());
					file.close();
					JOptionPane.showMessageDialog(null,	"Sucesso!");
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,	"Erro.");
				}
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
				try
				{
					FileInputStream fin = new FileInputStream(loadPath.getText() + ".dat");
					ObjectInputStream ois = new ObjectInputStream(fin);
					Game game = (Game) ois.readObject();
					ois.close();
					
					GamePanel.loadGame(game);

					JOptionPane.showMessageDialog(null,	"Sucesso.");
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,	"Erro.");
				}
				catch (ClassNotFoundException e2)
				{
					e2.printStackTrace();
				}
			}
		});
		getContentPane().add(btnLoad);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println ("Size: " + GamePanel.getMatrixSize());
				
				int size = GamePanel.getMatrixSize();
				if (size == 10)
					frame.setBounds((1920 - ((size + 2) * 32)) / 2, (1080 - ((size + 1) * 32 + 65)) / 2, (size + 2) * 32 + 30, (size + 1) * 32 + 65);
				else
					frame.setBounds((1920 - ((size + 1) * 32)) / 2, (1080 - ((size + 1) * 32 + 65)) / 2, (size + 1) * 32 + 30, (size + 1) * 32 + 65);
				
				dispose();
				
				JOptionPane.showMessageDialog(null, "No frame, clique numa tecla para actualizar a UI.", "Update UI", JOptionPane.OK_OPTION);
			}
		});
		getContentPane().add(btnOkay, BorderLayout.CENTER);
	}
}