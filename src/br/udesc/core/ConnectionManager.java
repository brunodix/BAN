package br.udesc.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static final ConnectionManager instance = new ConnectionManager();
	
	private ConnectionManager() {
	}
	
	public static final String JDBC_URL= "jdbc:mysql://localhost/credconsult";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	
	
	public PreparedStatement getStatement(String stm) throws SQLException {
		return getConnection().prepareStatement(stm);
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
	}
	
	public static final ConnectionManager getInstance() {
		return instance;
	}

}
