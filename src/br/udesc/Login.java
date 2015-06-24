package br.udesc;

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

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField pwdSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setTitle("Login");
		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[78.00,left][72.00][grow][grow,right]", "[63.00,top][23.00][23.00][grow,bottom]"));
		{
			JLabel lblUsu = new JLabel("Usuário:");
			contentPanel.add(lblUsu, "cell 1 1,alignx trailing");
		}
		{
			txtUsuario = new JTextField();
			contentPanel.add(txtUsuario, "cell 2 1,growx");
			txtUsuario.setColumns(10);
		}
		{
			JLabel lblSenha = new JLabel("Senha:");
			lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblSenha, "cell 1 2,alignx trailing");
		}
		{
			pwdSenha = new JPasswordField();
			contentPanel.add(pwdSenha, "cell 2 2,growx");
		}
		{
			JButton okButton = new JButton("OK");
			okButton.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(okButton, "cell 2 3");
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
