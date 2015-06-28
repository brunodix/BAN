package br.udesc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.udesc.core.ConnectionManager;
import br.udesc.core.Papeis;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class NovaConsulta extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDocumento;
	JComboBox comboBoxtipoDocumento = new JComboBox();
	JComboBox comboBoxIntegrador = new JComboBox();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaConsulta frame = new NovaConsulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NovaConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblDocumento = new JLabel("Documento");
		
		textFieldDocumento = new JTextField();
		textFieldDocumento.setColumns(10);
		
		JLabel lblIntegrador = new JLabel("Integrador");
		
		
		JLabel lblTipoDocumento = new JLabel("Tipo documento");
		
		//JComboBox comboBoxtipoDocumento = new JComboBox();
		
		comboBoxtipoDocumento.setMaximumRowCount(3);
		
		setTipo();
		setIntegrador();
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryInsert();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTipoDocumento)
								.addComponent(lblIntegrador)
								.addComponent(lblDocumento))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldDocumento, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(comboBoxtipoDocumento, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comboBoxIntegrador, 0, 93, Short.MAX_VALUE))))
						.addComponent(btnSalvar))
					.addContainerGap(97, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDocumento)
						.addComponent(textFieldDocumento, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDocumento)
						.addComponent(comboBoxtipoDocumento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIntegrador)
						.addComponent(comboBoxIntegrador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(btnSalvar)
					.addContainerGap(119, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	
	private void setTipo() {
		Object[] result = new Object[0];
		List<Object[]> list = new ArrayList<>();
		try {
			StringBuilder sbr = new StringBuilder();
			sbr.append(
					"select t.id, t.tipo, t.mascara")
					.append(" from tipo_documento t");
			PreparedStatement statement = ConnectionManager.getInstance()
					.getStatement(sbr.toString());
			ResultSet set = statement.executeQuery();
			
			
			while (set.next()) {
				Object[] obj = new Object[3];
				obj[0] = set.getInt(1);
				obj[1] = set.getString(2);
				Object[] itemData = new Object[] {obj[0], obj[1]};
				
				comboBoxtipoDocumento.addItem(obj[1]);
				list.add(obj);
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
				Object[] obj = new Object[3];
				obj[0] = set.getInt(1);
				obj[1] = set.getString(2);
				comboBoxIntegrador.addItem(obj[1]);
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
			stm.setInt(2, 1); // Passar Integrador
			stm.setString(3, Controller.getInstance().getEmailUsuario()); 
			stm.setInt(4, 1); // Passar id Tipo documento
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
