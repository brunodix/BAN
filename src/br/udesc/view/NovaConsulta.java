package br.udesc.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import br.udesc.core.ConnectionManager;

public class NovaConsulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDocumento;
	JComboBox<ChaveValor> comboBoxtipoDocumento = new JComboBox<>();
	JComboBox<ChaveValor> comboBoxIntegrador = new JComboBox<>();

	/**
	 * Create the frame.
	 */
	public NovaConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblDocumento = new JLabel("Documento");
		
		JLabel lblIntegrador = new JLabel("Integrador");
		
		
		JLabel lblTipoDocumento = new JLabel("Tipo documento");
		
		setTipo();
		setIntegrador();
		panel.setLayout(new MigLayout("", "[75px][12px][181.00,grow,fill][150]", "[20px][19px][19px][19px,grow][23px]"));
		
		textFieldDocumento = new JTextField();
		textFieldDocumento.setColumns(10);
		panel.add(textFieldDocumento, "cell 2 0,grow");
		panel.add(lblTipoDocumento, "cell 0 1,alignx left,aligny center");
		
		comboBoxtipoDocumento.setMaximumRowCount(3);
		panel.add(comboBoxtipoDocumento, "cell 2 1,alignx left,aligny top");
		panel.add(lblIntegrador, "cell 0 2,alignx left,aligny center");
		panel.add(lblDocumento, "cell 0 0,alignx left,aligny center");
		panel.add(comboBoxIntegrador, "cell 2 2,alignx left,aligny top");
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryInsert();
			}
		});
		panel.add(btnConsultar, "cell 3 4,alignx left,aligny top");
	}
	
	private void setTipo() {
		try {
			StringBuilder sbr = new StringBuilder();
			sbr.append(
					"select t.id, t.tipo, t.mascara")
					.append(" from tipo_documento t");
			PreparedStatement statement = ConnectionManager.getInstance()
					.getStatement(sbr.toString());
			ResultSet set = statement.executeQuery();
			
			
			while (set.next()) {
				comboBoxtipoDocumento.addItem(new ChaveValor(set.getInt(1), set.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setIntegrador() {
		try {
			StringBuilder sbr = new StringBuilder();
			sbr.append(
					"select i.id, i.nome")
					.append(" from integrador i");
			PreparedStatement statement = ConnectionManager.getInstance()
					.getStatement(sbr.toString());
			ResultSet set = statement.executeQuery();
			
			
			while (set.next()) {
				comboBoxIntegrador.addItem(new ChaveValor(set.getInt(1), set.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void tryInsert() {
		if (textFieldDocumento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "O documento é obrigatório!");
			return;
		}		
		try {
			PreparedStatement stm = ConnectionManager.getInstance().getStatement("Insert into consulta (hora_consulta, documento, integrador_id, usuario_email, tipo_documento_id) values(NOW(), ?, ?, ?, ?)");
			stm.setString(1, textFieldDocumento.getText());
			stm.setInt(2, ((ChaveValor)comboBoxIntegrador.getSelectedItem()).getChave()); // Passar Integrador
			stm.setString(3, Controller.getInstance().getEmailUsuario()); 
			stm.setInt(4, ((ChaveValor)comboBoxtipoDocumento.getSelectedItem()).getChave()); // Passar id Tipo documento
			if (stm.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(this, "Consulta realizada!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
