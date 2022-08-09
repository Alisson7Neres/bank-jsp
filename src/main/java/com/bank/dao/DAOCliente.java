package com.bank.dao;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.bank.connection.SingleConnection;
import com.bank.model.Bank;
import com.bank.model.Cliente;

public class DAOCliente {

	private Connection connection = null;

	public DAOCliente() {
		connection = SingleConnection.getConnection();
	}

	public boolean existe(String cpf) throws SQLException {

		String sql = "select count(1) > 0 as existe from cliente where upper(cpf) =upper('" + cpf + "') ;";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet set = statement.executeQuery();

		if (set.next()) {
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

		String sql = "insert into cliente(cpf, rg, nome, email, telefone, senha, cep, logradouro, complemento, bairro, localidade, uf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, cliente.getCpf());
		statement.setString(2, cliente.getRg());
		statement.setString(3, cliente.getNome());
		statement.setString(4, cliente.getEmail());
		statement.setString(5, cliente.getTelefone());
		statement.setString(6, cliente.getSenha());
		statement.setString(7, cliente.getCep());
		statement.setString(8, cliente.getLogradouro());
		statement.setString(9, cliente.getComplemento());
		statement.setString(10, cliente.getBairro());
		statement.setString(11, cliente.getLocalidade());
		statement.setString(12, cliente.getUf());

		statement.execute();
		connection.commit();
		/*
		 * Bank bank = new Bank(); daoBank.gravarConta(bank, cliente);
		 */
		return this.mostrarCliente(cliente, cliente.getCpf());

	}

	public Cliente atualizarCliente(Cliente cliente, String cpf) throws SQLException {

		try {

			String sql = "update cliente email set email = ?, telefone = ?, senha = ?, "
					+ "cep = ?, logradouro = ?, complemento = ?, bairro = ?, localidade = ?, uf = ? where cpf = ('"
					+ cpf + "')";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, cliente.getEmail());
			if (cliente.getEmail() == null) {
				cliente.setEmail(cliente.getEmail());
			}
			statement.setString(2, cliente.getTelefone());
			if (cliente.getTelefone() == null) {
				cliente.setTelefone(cliente.getTelefone());
			}
			statement.setString(3, cliente.getSenha());
			if (cliente.getSenha() == null) {
				cliente.setSenha(cliente.getSenha());
			}
			statement.setString(4, cliente.getCep());
			if (cliente.getCep() == null) {
				cliente.setCep(cliente.getCep());
			}
			statement.setString(5, cliente.getLogradouro());
			if (cliente.getLogradouro() == null) {
				cliente.setLogradouro(cliente.getLogradouro());
			}
			statement.setString(6, cliente.getComplemento());
			if (cliente.getComplemento() == null) {
				cliente.setComplemento(cliente.getComplemento());
			}
			statement.setString(7, cliente.getBairro());
			if (cliente.getBairro() == null) {
				cliente.setBairro(cliente.getBairro());
			}
			statement.setString(8, cliente.getLocalidade());
			if (cliente.getLocalidade() == null) {
				cliente.setLocalidade(cliente.getLocalidade());
			}
			statement.setString(9, cliente.getUf());
			if (cliente.getUf() == null) {
				cliente.setUf(cliente.getUf());
			}

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.mostrarCliente(cliente, cliente.getCpf());
	}

	public Cliente mostrarCliente(Cliente cliente, String CPF) throws SQLException {

		String sql = "select distinct * from cliente where cpf = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, CPF);
		ResultSet result = statement.executeQuery();

		if (result.next()) {

			cliente.setCpf(result.getString("cpf"));
			cliente.setRg(result.getString("rg"));
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

	public Cliente mostrarCliente2(Cliente cliente) throws SQLException {

		String sql = "select * from cliente where cpf = cpf";

		String cpf = cliente.getCpf();

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		if (result.next()) {

			cliente.setCpf(cliente.getCpf());
			cliente.setRg(result.getString("rg"));
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

	public Cliente esqueciMe(Cliente cliente) {

		String cpf = cliente.getCpf();
		String rg = cliente.getRg();
		String email = cliente.getEmail();

		try {

			// Senha aleátoria

			// bind the length
			int i = 15;
			byte[] bytearray = new byte[256];
			String mystring;
			StringBuffer thebuffer;
			String theAlphaNumericS;

			new Random().nextBytes(bytearray);

			mystring = new String(bytearray, Charset.forName("UTF-8"));

			thebuffer = new StringBuffer();

			// remove all spacial char
			theAlphaNumericS = mystring.replaceAll("[^A-Z0-9]", "");

			// random selection
			for (int m = 0; m < theAlphaNumericS.length(); m++) {

				if (Character.isLetter(theAlphaNumericS.charAt(m)) && (i > 0)
						|| Character.isDigit(theAlphaNumericS.charAt(m)) && (i > 0)) {

					thebuffer.append(theAlphaNumericS.charAt(m));
					i--;
				}
			}

			// FIM

			String senha = thebuffer.toString();

			String sql = "update cliente senha set senha = ? where upper(cpf) = ? and upper(rg) = ? and email = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, senha);
			statement.setString(2, cpf);
			statement.setString(3, rg);
			statement.setString(4, email);
			statement.execute();

			connection.commit();
			;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cliente;
	}
	
	public void ClienteTitular(Cliente cliente, Bank bankTitular) {
		
		try {

			String sql = "select * from cliente inner join bank b on id_bank = cpf where numeroconta = ('"+bankTitular.getNumeroConta()+"') ";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			
			if (set.next()) {
				cliente.setNome(set.getString("nome"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ClienteDestino(Cliente clienteDestino, Bank bankDestino) {
		
		try {

			String sql = "select * from cliente inner join bank b on id_bank = cpf where numeroconta = ('"+bankDestino.getNumeroConta()+"') ";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			
			if (set.next()) {
				clienteDestino.setNome(set.getString("nome"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
