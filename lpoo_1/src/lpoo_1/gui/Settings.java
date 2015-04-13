package lpoo_1.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Settings extends JDialog
{
	public Settings ()
	{
		this.setTitle("Settings");
		this.setBounds(100, 100, 300, 175);
		this.getContentPane().setLayout(null);
		
		JLabel lblTipoDeJogo = new JLabel("Tipo de Jogo:");
		lblTipoDeJogo.setBounds(10, 10, 100, 15);
		getContentPane().add(lblTipoDeJogo);
		
		final JCheckBox chckbxDemo = new JCheckBox("Mapa de demonstração");
		final JCheckBox chckbxRandom = new JCheckBox("Mapa alatório");
		JTextField matrixSize = new JTextField();
		JButton btnOkay = new JButton("Okay");
		
		matrixSize.setBounds(50, 70, 50, 25);
		this.getContentPane().add(matrixSize);
		matrixSize.disable();
		
		chckbxDemo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(chckbxDemo.isSelected())
				{
					chckbxRandom.setSelected(false);
					matrixSize.disable();
				}
			}
		});
		chckbxDemo.setBounds(30, 30, 200, 15);
		getContentPane().add(chckbxDemo);
		
		chckbxRandom.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (chckbxRandom.isSelected())
				{
					chckbxDemo.setSelected(false);
					matrixSize.enable();
				}
			}
		});
		chckbxRandom.setBounds(30, 50, 200, 15);
		getContentPane().add(chckbxRandom);
		
		// Botão "Okay"
		btnOkay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (chckbxDemo.isSelected())
				{
					GameFrame.random = false;
					GameFrame.size = 10;
				}
				else if (chckbxRandom.isSelected() && !matrixSize.getText().isEmpty())
				{
					GameFrame.random = true;
					GameFrame.size = Integer.parseInt(matrixSize.getText());
				}
				else
				{
					GameFrame.random = false;
					GameFrame.size = 10;
				}
				
				dispose();
			}
		});
		btnOkay.setBounds((300 - 75) / 2, 100, 75, 30);
		getContentPane().add(btnOkay);
	}
}
