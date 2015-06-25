package br.udesc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		//new Main().start();
		try {
			new Main().consulta();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void start() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Login login = new Login();
			login.setVisible(true);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// só exemplos
	private void consulta() throws SQLException{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/credconsult?" +
                        "user=root&password=root");
		PreparedStatement stm = con.prepareStatement("select * from A where id = ?");
		stm.setLong(1, 1);
		stm.execute();
		ResultSet set = stm.getResultSet();
		while (set.next()) {
			// coluna 1 do resultado
			set.getString(0);
		}
	}
}
