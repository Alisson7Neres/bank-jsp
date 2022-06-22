package com.bank.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String bancoUrl = "jdbc:postgresql://localhost:5432/bank?autoReconnect=true\"";
	private static String nome = "postgres";
	private static String senha = "admin";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	public SingleConnection() {
		connectar();
	}
	
	public static void connectar() {
		try {
			Class.forName(bancoUrl);
			connection = DriverManager.getConnection(bancoUrl, nome, senha);
			connection.setAutoCommit(false);
		} catch (Exception e) {
		}
	}
	
	

}
