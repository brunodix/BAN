package br.udesc;

import javax.swing.JDialog;

import br.udesc.view.Login;

public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		new Main().start();
	}

	private void start() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Login login = new Login();
		login.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		login.setVisible(true);
		
	}
}
