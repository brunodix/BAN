package br.udesc.view;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frmTelaInicial;

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTelaInicial = new JFrame();
		frmTelaInicial.setTitle("Tela inicial");
		frmTelaInicial.setBounds(100, 100, 450, 300);
		frmTelaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTelaInicial.getContentPane().setLayout(new MigLayout("", "[grow][110.00][33.00][110.00][grow,right]", "[grow][92.00][grow]"));
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().showUsuarios();
			}
		});
		frmTelaInicial.getContentPane().add(btnUsuarios, "cell 1 1,grow");
		
		JButton btnConsultas = new JButton("Consultas");
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().showConsultas();
			}
		});
		frmTelaInicial.getContentPane().add(btnConsultas, "cell 3 1,grow");
	}
	
	public void showFrame() {
		frmTelaInicial.setVisible(true);
	}

}
