package com.bank.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	// Criando conexão com banco de dados
	private static String bancoUrl = "jdbc:postgresql://localhost:5432/bank?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		connectar();
	}
	
	public SingleConnection() {
		connectar();
	}
	
	public static void connectar() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(bancoUrl, user, password);
			connection.setAutoCommit(false);
		} catch (Exception e) {
		}
	}
	
	

}
