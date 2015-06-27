package br.udesc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JButton;

public class Consultas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnEditar;

	/**
	 * Create the frame.
	 */
	public Consultas() {
		setTitle("Consultas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][][grow]", "[grow][37.00]"));
		
		table = new JTable();
		contentPane.add(table, "cell 0 0 5 1,grow");
		
		JButton btnNovo = new JButton("Novo");
		contentPane.add(btnNovo, "cell 1 1");
		
		btnEditar = new JButton("Editar");
		contentPane.add(btnEditar, "cell 2 1");
	}

}
