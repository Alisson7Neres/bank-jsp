package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.connection.SingleConnection;
import com.bank.model.Cliente;
import com.bank.model.Endereco;

public class DAOEndereco {

	// Abrindo conexão
	private Connection connection = null;

	public DAOEndereco() {
		connection = SingleConnection.getConnection();
	}
	
	public Endereco gravarEndereco(Endereco endereco, Cliente cliente) throws SQLException {
		
		try {
			String sql = "insert into endereco(id_endereco, cep, logradouro, complemento, bairro, localidade, uf) values (?,?,?,?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// Pegando cpf do cliente e setando no endereco_id.
			String endereco_id = endereco.getId_endereco();
			endereco_id = cliente.getCpf();
			
			statement.setString(1, endereco_id);
			statement.setString(2, endereco.getCep());
			statement.setString(3, endereco.getLogradouro());
			statement.setString(4, endereco.getComplemento());
			statement.setString(5, endereco.getBairro());
			statement.setString(6, endereco.getLocalidade());
			statement.setString(7, endereco.getUf());
			
			// Executa e grava no banco de dados.
			statement.execute();
			connection.commit();
			
		} catch (SQLException se)  {
			se.printStackTrace();
		}  catch(Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return this.mostrarEndereco(endereco, cliente);
	}
	
	public Endereco mostrarEndereco(Endereco endereco, Cliente cliente) {
		
		try {

			String sql = "select DISTINCT * from cliente  inner join endereco on id_endereco = ? where cpf = ?;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			String endereco_id = endereco.getId_endereco();
			endereco_id = cliente.getCpf();
			
			statement.setString(1, cliente.getCpf());
			statement.setString(2, endereco_id);

			ResultSet set = statement.executeQuery();
			if (set.next()) {
				endereco.setCep(set.getString("cep"));
				endereco.setLogradouro(set.getString("logradouro"));
				endereco.setComplemento(set.getString("complemento"));
				endereco.setBairro(set.getString("bairro"));
				endereco.setLocalidade(set.getString("localidade"));
				endereco.setUf(set.getString("uf"));

			}

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return endereco;
	}
	
	public Endereco atualizarEndereco(Endereco endereco, String id) {
		
		try {
			String sql = "update endereco cep set cep = ?, logradouro = ?, complemento = ?, bairro = ?, localidade = ?, uf = ? where id_endereco = ('"+ id +"')";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			//String id_endereco = endereco.getId_endereco();
			endereco.setId_endereco(id);
			statement.setString(1, endereco.getCep());
			if (endereco.getCep() == null) {
				endereco.setCep(endereco.getCep());
			}
			statement.setString(2, endereco.getLogradouro());
			if (endereco.getLogradouro() == null) {
				endereco.setLogradouro(endereco.getLogradouro());
			}
			statement.setString(3, endereco.getComplemento());
			if (endereco.getComplemento() == null) {
				endereco.setComplemento(endereco.getComplemento());
			}
			statement.setString(4, endereco.getBairro());
			if (endereco.getBairro() == null) {
				endereco.setBairro(endereco.getBairro());
			}
			statement.setString(5, endereco.getLocalidade());
			if (endereco.getLocalidade() == null) {
				endereco.setLocalidade(endereco.getLocalidade());
			}
			statement.setString(6, endereco.getUf());
			if (endereco.getUf() == null) {
				endereco.setUf(endereco.getUf());
			}
			
			statement.execute();
			connection.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return endereco;
	}
	
}
