package br.udesc.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import br.udesc.core.ConnectionManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField pwdSenha;
	private JLabel lblMensagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Controller.getInstance().showLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		lblMensagem =  new JLabel("");
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagem.setForeground(Color.RED);
		setTitle("Login");
		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[78.00,left][72.00][grow][grow,right]", "[63.00,top][][23.00][23.00][grow,bottom]"));
		{
			contentPanel.add(lblMensagem, "cell 1 1 2 1");
		}
		{
			JLabel lblUsu = new JLabel("Usuário:");
			contentPanel.add(lblUsu, "cell 1 2,alignx trailing");
		}
		{
			txtUsuario = new JTextField();
			contentPanel.add(txtUsuario, "cell 2 2,growx");
			txtUsuario.setColumns(10);
		}
		{
			JLabel lblSenha = new JLabel("Senha:");
			lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblSenha, "cell 1 3,alignx trailing");
		}
		{
			pwdSenha = new JPasswordField();
			contentPanel.add(pwdSenha, "cell 2 3,growx");
		}
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tryLogin();
				}
			});
			okButton.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(okButton, "cell 2 4");
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	
	private void tryLogin() {
		try {
			PreparedStatement stm = ConnectionManager.getInstance().getStatement("select email from usuario where email=? and senha=?");
			stm.setString(1, txtUsuario.getText());
			stm.setString(2, String.valueOf(pwdSenha.getPassword()));
			ResultSet set = stm.executeQuery();
			if (set.next()) {
				System.out.println("Logado");
				Controller.getInstance().setEmailUsuario(set.getString(1));
				Controller.getInstance().hideLogin();
				Controller.getInstance().showMain();
			} else {
				lblMensagem.setText("Usuário ou senha inválido!");
			}
			set.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

