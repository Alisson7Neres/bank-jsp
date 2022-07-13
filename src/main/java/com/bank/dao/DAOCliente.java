package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.connection.SingleConnection;
import com.bank.model.Bank;
import com.bank.model.Cliente;

public class DAOCliente {

	private Connection connection = null;

	public DAOCliente() {
		connection = SingleConnection.getConnection();
	}
	

	public boolean existe(String cpf) throws SQLException {
		
			String sql = "select count(1) > 0 as existe from cliente where upper(cpf) =upper('"+cpf+"') ;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			
			if(set.next()) {
				return set.getBoolean("existe");
			}
			return false;
	
	}

	public boolean validarLogin(Cliente cliente) {

		try {
		String sql = "select * from cliente where cpf = ? and senha = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cliente.getCpf());
			statement.setString(2, cliente.getSenha());
			this.mostrarCliente(cliente, cliente.getCpf());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return true; // autenticado
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public Cliente gravarCliente(Cliente cliente) throws SQLException {

		String sql = "insert into cliente(cpf, nome, email, telefone, senha, cep, logradouro, complemento, bairro, localidade, uf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, cliente.getCpf());
		statement.setString(2, cliente.getNome());
		statement.setString(3, cliente.getEmail());
		statement.setString(4, cliente.getTelefone());
		statement.setString(5, cliente.getSenha());
		statement.setString(6, cliente.getCep());
		statement.setString(7, cliente.getLogradouro());
		statement.setString(8, cliente.getComplemento());
		statement.setString(9, cliente.getBairro());
		statement.setString(10, cliente.getLocalidade());
		statement.setString(11, cliente.getUf());

		statement.execute();
		connection.commit();
		/*
		Bank bank = new Bank();
		daoBank.gravarConta(bank, cliente);
		*/
		return this.mostrarCliente(cliente, cliente.getCpf());

	}

	public Cliente mostrarCliente(Cliente cliente, String CPF) throws SQLException {

		String sql = "select * from cliente where cpf = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, CPF);
		ResultSet result = statement.executeQuery();

		if (result.next()) {

			cliente.setCpf(result.getString("cpf"));
			cliente.setNome(result.getString("nome"));
			cliente.setEmail(result.getString("email"));
			cliente.setTelefone(result.getString("telefone"));
			cliente.setSenha(result.getString("senha"));
			cliente.setCep(result.getString("cep"));
			cliente.setLogradouro(result.getString("logradouro"));
			cliente.setComplemento(result.getString("complemento"));
			cliente.setBairro(result.getString("bairro"));
			cliente.setLocalidade(result.getString("localidade"));
			cliente.setUf(result.getString("uf"));

		}

		statement.execute();
		return cliente;
	}
}
