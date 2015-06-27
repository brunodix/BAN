package br.udesc.view;

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

import net.miginfocom.swing.MigLayout;
import br.udesc.core.ConnectionManager;

public class Consultas extends JFrame {

	public final static String COLUMN_NAMES[] = { "Documento", "T. Documento",
			"Hora", "Integrador", "Usuário" };

	private JPanel contentPane;
	private JTable table;
	private JButton btnEditar;

	/**
	 * Create the frame.
	 */
	public Consultas() {
		setTitle("Consultas");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][][grow]",
				"[grow][37.00]"));

		Object object[][] = new Object[0][2];
		table = new JTable(getData(), COLUMN_NAMES);
		contentPane.add(table, "cell 0 0 5 1,grow");

		JButton btnNovo = new JButton("Novo");
		contentPane.add(btnNovo, "cell 1 1");

		btnEditar = new JButton("Editar");
		contentPane.add(btnEditar, "cell 2 1");
	}

	private Object[][] getData() {
		Object[][] result = new Object[0][0];
		List<Object[]> list = new ArrayList<>();
		try {
			StringBuilder sbr = new StringBuilder();
			sbr.append(
					"select c.documento, doc.TIPO, c.HORA_CONSULTA, it.NOME, c.usuario_email")
					.append(" from consulta c")
					.append(" join integrador it on c.integrador_id = it.id")
					.append(" join tipo_documento doc on doc.id = c.tipo_documento_id");
			PreparedStatement statement = ConnectionManager.getInstance()
					.getStatement(sbr.toString());
			ResultSet set = statement.executeQuery();
			
			
			while (set.next()) {
				Object[] obj = new Object[5];
				obj[0] = set.getString(1);
				obj[1] = set.getString(2);
				obj[2] = set.getDate(3);
				obj[3] = set.getString(4);
				obj[4] = set.getString(5);
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size() > 0) {
			result = new Object[list.size()][5];
			for (int i = 0; i < list.size(); i++) {
				result[i] = list.get(i);
			}
		}
		return result;
	}

}
