package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.connection.SingleConnection;
import com.bank.model.Bank;
import com.bank.model.Cliente;

public class DAOBank {

	// Pegando conexão
	private Connection connection = null;

	public DAOBank() {
		connection = SingleConnection.getConnection();
	}

	public Bank gravarConta(Bank bank, Cliente cliente) {

		try {
			String sql = "insert into bank (id_bank, agencia, numeroconta, saldo, tipo) values (?,?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Pegando cpf do cliente e setando no bank_id.
			String bank_id = bank.getId_bank();
			bank_id = cliente.getCpf();
			
			statement.setString(1, bank_id);
			statement.setInt(2, bank.getAgencia());
			// Verifica se o número da conta existe ou não.
			if (bank.getNumeroConta() <= 0) {
				long gerarConta = bank.Random();
				bank.setNumeroConta(gerarConta);
			}
			statement.setLong(3, bank.getNumeroConta());
			statement.setDouble(4, bank.getSaldo());
			statement.setString(5, bank.getTipo());

			// Executa e grava no banco de dados.
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Ao finalizar o cadastro, irá retornar os dados.
		return this.mostrarBank(bank, cliente);
	}
	
	// Nesse método irá realizar o depósito da conta de destino.
	public Bank TransferenciaDestino(Bank bank, Cliente cliente) {

		try {
			
			String sql = "update bank saldo set saldo = ? where numeroconta = ?";
	
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setDouble(1, bank.getSaldo());
			statement.setLong(2, bank.getNumeroConta());
			
			// Executa e grava no banco de dados.
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Ao finalizar, irá retornar os dados.
		return this.mostrarBank(bank, cliente);
	}
	
	// Esse método irá fazer o desconto do saldon referente ao valor da transferência.
	public Bank TransferenciaTitular(Bank bank, Cliente cliente) {

		try {
			
			String sql = "update bank saldo set saldo = ? where numeroconta = ?";
	
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setDouble(1, bank.getSaldo());
			statement.setLong(2, bank.getNumeroConta());

			// Executa e grava no banco de dados.
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Ao finalizar, irá retornar os dados.
		return this.mostrarBank(bank, cliente);
	}

	public Bank mostrarBank(Bank bank, Cliente cliente) {

		try {

			String sql = "select DISTINCT * from cliente  inner join bank on id_bank = ? where cpf = ?;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			String bank_id = bank.getId_bank();
			bank_id = cliente.getCpf();
			
			statement.setString(1, cliente.getCpf());
			statement.setString(2, bank_id);

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
	
	public Bank mostrarDestino(Bank bank, String numeroConta) {

		try {
			String sql = "select distinct * from bank where numeroconta = ?;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(1, bank.getNumeroConta());

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
