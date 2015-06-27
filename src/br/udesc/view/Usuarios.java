package br.udesc.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

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
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().showNovoUsuario();
			}
		});
		btnEditar = new JButton("Editar");
		
		contentPane.setLayout(new MigLayout("", "[grow][][][][grow]",
				"[grow][37.00]"));

		contentPane.add(btnNovo, "cell 1 1");
		contentPane.add(btnEditar, "cell 2 1");
	}

}
