package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.connection.SingleConnection;
import com.bank.model.Bank;

public class DAOBank {
	
	private Connection connection = null;
	
	public DAOBank() {
		connection = SingleConnection.getConnection();
	}
	
	public void gravarConta() {
		
		try {
		String sql = "select*from cliente inner join bank on cpf = id_bank;";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Bank mostrarBank(Bank bank) {
		
		try {
		String sql = "select*from cliente inner join bank on cpf = id_bank;";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet set= statement.executeQuery();
		
		if (set.next()) {
			bank.setNumeroConta(set.getLong("numeroconta"));
			bank.setSaldo(set.getDouble("saldo"));
			bank.setAgencia(set.getString("agencia"));
			
		}
		
		statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bank;
		
	}

}
