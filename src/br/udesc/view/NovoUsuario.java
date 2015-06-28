package br.udesc.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import br.udesc.core.ConnectionManager;
import br.udesc.core.Papeis;

public class NovoUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldCpf;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JPasswordField textFieldSenha;
	private JCheckBox chckbxAdministrador;
	private JComboBox<Papeis> comboBox;
	private boolean edit;

	/**
	 * Create the frame.
	 */
	public NovoUsuario() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 492, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lbNome = new JLabel("Nome Completo");
		
		JLabel lbCpf = new JLabel("CPF");
		
		JLabel lbEmail = new JLabel("Email");
		
		JLabel lbSenha = new JLabel("Senha");
		panel.setLayout(new MigLayout("", "[75px][12px][181.00,grow,fill][150]", "[20px][19px][19px][19px][23px][][25px,grow][]"));
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		panel.add(textFieldNome, "cell 2 0 2 1,grow");
		
		textFieldCpf = new JTextField();
		textFieldCpf.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldCpf.setColumns(10);
		panel.add(textFieldCpf, "cell 2 1,growx,aligny top");
		panel.add(lbEmail, "cell 0 2,growx,aligny center");
		panel.add(lbCpf, "cell 0 1,growx,aligny center");
		panel.add(lbNome, "cell 0 0,alignx left,aligny center");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldEmail.setColumns(10);
		panel.add(textFieldEmail, "cell 2 2,growx,aligny top");
		panel.add(lbSenha, "cell 0 3,growx,aligny center");
		
		textFieldSenha = new JPasswordField();
		textFieldSenha.setColumns(10);
		panel.add(textFieldSenha, "cell 2 3,growx,aligny top");
		
		chckbxAdministrador = new JCheckBox("Ativo");
		chckbxAdministrador.setSelected(true);
		panel.add(chckbxAdministrador, "cell 2 4,alignx left,aligny top");
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (edit) {
					tryUpdate();
				} else {
					tryInsert();
				}
			}
		});
		
		JLabel lblPapel = new JLabel("Papel");
		panel.add(lblPapel, "cell 0 5");
		
		comboBox = new JComboBox<>();
		comboBox.setMaximumRowCount(3);
		comboBox.setModel(new DefaultComboBoxModel<>(Papeis.values()));
		panel.add(comboBox, "cell 2 5,growx");
		panel.add(btnSalvar, "cell 3 7,alignx left,aligny top");
	}
	
	private void tryInsert() {
		if (textFieldEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O e-mail é obrigatório!");
			return;
		}
		if (textFieldCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O CPF é obrigatório!");
			return;
		}
		if (textFieldNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O nome é obrigatório!");
			return;
		}
		if (textFieldSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "A senha é obrigatória!");
			return;
		}
		if (comboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Selecione um papel!");
			return;
		}
		
		
		try {
			PreparedStatement stm = ConnectionManager.getInstance().getStatement("Insert into usuario (email, cpf, nome, senha, ativo, papel) values(?, ?, ?, ?, ?, ?)");
			stm.setString(1, textFieldEmail.getText());
			stm.setString(2, textFieldCpf.getText());
			stm.setString(3, textFieldNome.getText());
			stm.setString(4, String.valueOf(textFieldSenha.getPassword()));
			stm.setBoolean(5, chckbxAdministrador.isSelected());
			stm.setInt(6, ((Papeis) comboBox.getSelectedItem()).getValue());
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void tryUpdate() {
		if (textFieldEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O e-mail é obrigatório!");
			return;
		}
		if (textFieldCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O CPF é obrigatório!");
			return;
		}
		if (textFieldNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O nome é obrigatório!");
			return;
		}
		if (textFieldSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "A senha é obrigatória!");
			return;
		}
		if (comboBox.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Selecione um papel!");
			return;
		}
		
		
		try {
			PreparedStatement stm = ConnectionManager.getInstance().getStatement("update usuario set email=?, cpf=?, nome=?, senha=?, ativo=?, papel=? where email=?");
			stm.setString(1, textFieldEmail.getText());
			stm.setString(2, textFieldCpf.getText());
			stm.setString(3, textFieldNome.getText());
			stm.setString(4, String.valueOf(textFieldSenha.getPassword()));
			stm.setBoolean(5, chckbxAdministrador.isSelected());
			stm.setInt(6, ((Papeis) comboBox.getSelectedItem()).getValue());
			stm.setString(7, textFieldEmail.getText());
			if (stm.execute()) {
				JOptionPane.showMessageDialog(this, "Usuário atualizado!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void edit(String value) {
		edit = true;
		try {
			PreparedStatement stm = ConnectionManager.getInstance().getStatement("select email, cpf, nome, senha, ativo, papel from usuario where email = ?");
			stm.setString(1, value);
			ResultSet set = stm.executeQuery();
			set.next();
			textFieldEmail.setText(set.getString(1));
			textFieldCpf.setText(set.getString(2));
			textFieldNome.setText(set.getString(3));
			textFieldSenha.setText(set.getString(4));
			chckbxAdministrador.setSelected(set.getBoolean(5));
			comboBox.setSelectedItem(Papeis.valueOf(set.getInt(6)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
