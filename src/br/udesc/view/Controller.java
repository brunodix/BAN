package br.udesc.view;

import javax.swing.JDialog;

public class Controller {
	
	private static final Controller instance = new Controller();
	
	public static Controller getInstance() {
		return instance;
	}
	
	private Login login;	
	private Main window;
	private Consultas consultas;
	private Usuarios usuarios;
	
	private Controller() {
	};
	
	public void showLogin() {
		login = new Login();
		login.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		login.setVisible(true);
	}
	
	public void hideLogin() {
		login.setVisible(false);
		login.dispose();
	}

	public void showMain() {
		window = new Main();
		window.showFrame();
	}
	
	public void showConsultas(){
		consultas = new Consultas();
		consultas.setVisible(true);
	}
	
	public void hideConsultas(){
		consultas.setVisible(false);
		consultas.dispose();
	}
	
	public void showUsuarios(){
		usuarios = new Usuarios();
		usuarios.setVisible(true);
	}

}
