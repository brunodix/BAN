package br.udesc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import br.udesc.core.ConnectionManager;
import javax.swing.JScrollPane;

public class Usuarios extends JFrame {

	
	public final static String COLUMN_NAMES[] = { "E-mail","Nome", "CPF" };
	
	private JPanel contentPane;
	private JButton btnEditar;
	private JTable table;
	private JButton btnRemover;
	private JScrollPane scrollPane;
	
	/**
	 * Create the frame.
	 */
	public Usuarios() {
		setTitle("Usuários");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][]", "[grow][37.00]"));
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().showNovoUsuario();
			}
		});
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0 4 1,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(getData(), COLUMN_NAMES));

		contentPane.add(btnNovo, "cell 1 1");
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String value = (String) table.getModel().getValueAt(row, 0);
				Controller.getInstance().showNovoUsuario(value);
			}
		});
		contentPane.add(btnEditar, "cell 2 1");
		
		btnRemover = new JButton("Remover");
		contentPane.add(btnRemover, "cell 3 1");
	}
	
	private Object[][] getData() {
		Object[][] result = new Object[0][0];
		List<Object[]> list = new ArrayList<>();
		try {
			StringBuilder sbr = new StringBuilder();
			sbr.append(
					"select email, nome, cpf")
					.append(" from usuario");
			PreparedStatement statement = ConnectionManager.getInstance()
					.getStatement(sbr.toString());
			ResultSet set = statement.executeQuery();
			
			
			while (set.next()) {
				Object[] obj = new Object[3];
				obj[0] = set.getString(1);
				obj[1] = set.getString(2);
				obj[2] = set.getString(3);
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() > 0) {
			result = new Object[list.size()][3];
			for (int i = 0; i < list.size(); i++) {
				result[i] = list.get(i);
			}
		}
		return result;
	}

}
