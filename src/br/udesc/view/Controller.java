package br.udesc.view;

import javax.swing.JDialog;

import br.udesc.core.Papeis;

public class Controller {
	
	private static final Controller instance = new Controller();
	
	public static Controller getInstance() {
		return instance;
	}
	
	private String emailUsuario;
	private Papeis papelUsuario;
	
	private Login login;	
	private MainWindow window;
	private Consultas consultas;
	private Usuarios usuarios;
	private NovoUsuario novousuario;
	private NovaConsulta novaconsulta;
	
	private Controller() {
	};
	
	public void showLogin() {
		login = new Login();
		login.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		login.setVisible(true);
	}
	
	public void hideLogin() {
		login.setVisible(false);
		login.dispose();
	}

	public void showMain() {
		window = new MainWindow();
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
	
	public void showNovoUsuario(){
		novousuario = new NovoUsuario();
		novousuario.setVisible(true);
	}
	
	public void hideNovoUsuario(){
		novousuario.setVisible(false);
		novousuario.dispose();
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
	public void showNovaConsulta(){
		novaconsulta = new NovaConsulta();
		novaconsulta.setVisible(true);
	}
	
	public void hideNovaConsulta(){
		novaconsulta.setVisible(false);
		novaconsulta.dispose();
	}

	public void showNovoUsuario(String value) {
		novousuario = new NovoUsuario();
		novousuario.setVisible(true);
		novousuario.edit(value);
	}

	public Papeis getPapelUsuario() {
		return papelUsuario;
	}

	public void setPapelUsuario(int papelUsuario) {
		this.papelUsuario = Papeis.valueOf(papelUsuario);
	}

}
