package br.udesc.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EmptyBorder;

public class Usuarios extends JFrame {

	
	public final static String COLUMN_NAMES[] = { "Email", "CPF" };
	
	private JPanel contentPane;
	private JTable table;
	private JButton btnEditar;
	
	/**
	 * Create the frame.
	 */
	public Usuarios() {
		setTitle("Usuários");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}

}
