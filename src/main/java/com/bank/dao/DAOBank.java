package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.connection.SingleConnection;
import com.bank.model.Bank;
import com.bank.model.Cliente;

public class DAOBank {

	private Connection connection = null;

	public DAOBank() {
		connection = SingleConnection.getConnection();
	}

	public Bank gravarConta(Bank bank, Cliente cliente) {

		try {
			String sql = "insert into bank (id_bank, agencia, numeroconta, saldo, tipo) values (?, ? ,? , ? , ?);";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, cliente.getCpf());
			statement.setInt(2, bank.getAgencia());
			if (bank.getNumeroConta() <= 0) {
				long gerarConta = bank.Random();
				bank.setNumeroConta(gerarConta);
			}
			statement.setLong(3, bank.getNumeroConta());
			statement.setDouble(4, bank.getSaldo());
			statement.setString(5, bank.getTipo());

			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.mostrarBank(bank);
	}

	public Bank mostrarBank(Bank bank) {

		try {
			String sql = "select*from cliente inner join bank on cpf = id_bank;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			if (set.next()) {
				bank.setNumeroConta(set.getLong("numeroconta"));
				bank.setSaldo(set.getDouble("saldo"));
				bank.setAgencia(set.getInt("agencia"));
				bank.setTipo(set.getString("tipo"));

			}

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bank;

	}

}
