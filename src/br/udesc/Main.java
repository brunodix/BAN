package br.udesc;

import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import br.udesc.view.Controller;
import br.udesc.view.Login;

public class Main {

	public static void main(String[] args) throws Exception {
		new Main().start();
	}

	private void start() throws Exception {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	        if ("Nimbus".equals(info.getName())) {
	            UIManager.setLookAndFeel(info.getClassName());
	            break;
	        }
		}
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Controller.getInstance().showLogin();
	}
}
