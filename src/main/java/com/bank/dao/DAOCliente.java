package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bank.connection.SingleConnection;
import com.bank.model.Cliente;

public class DAOCliente {

	private Connection connection = null;
	
	public DAOCliente() {
		connection = SingleConnection.getConnection();
	}
	
	public Cliente gravarCliente(Cliente cliente) throws SQLException {
		
		String sql = "insert into cliente(cpf, nome, email, telefone, senha, cep, logradouro, complemento, bairro, localidade, uf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, cliente.getCPF());
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
		
		return null;
		
	}
}
