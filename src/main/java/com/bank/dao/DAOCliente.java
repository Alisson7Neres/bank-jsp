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

	// Abrindo conexão
	private Connection connection = null;

	public DAOCliente() {
		connection = SingleConnection.getConnection();
	}

	// Método que vai fazer a validação, se existe cpf: vai realizar o cadastro, se não, não realizar cadastro.
	public boolean existe(String cpf) throws SQLException {

		String sql = "select count(1) > 0 as existe from cliente where upper(cpf) = upper('" + cpf + "') ;";

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

		String sql = "insert into cliente(cpf, rg, nome, email, telefone, senha) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, cliente.getCpf());
		statement.setString(2, cliente.getRg());
		statement.setString(3, cliente.getNome());
		statement.setString(4, cliente.getEmail());
		statement.setString(5, cliente.getTelefone());
		statement.setString(6, cliente.getSenha());

		// Executa e grava no banco de dados.
		statement.execute();
		connection.commit();
		
		// Ao finalizar o cadastro, irá retornar os dados.
		return this.mostrarCliente(cliente, cliente.getCpf());

	}

	public Cliente atualizarCliente(Cliente cliente, String cpf) throws SQLException {

		try {

			String sql = "update cliente email set email = ?, telefone = ?, senha = ? "
					+ "where cpf = ('"
					+ cpf + "')";
			PreparedStatement statement = connection.prepareStatement(sql);

			// Nesses if's, eu faço a seguinte condição: Se o atributo estiver nulo, irá pegar no banco de dados e setar novamente.
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
			

			// Executa e grava no banco de dados.
			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Ao finalizar o cadastro, irá retornar os dados.
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

			// Grava a atualização no banco de dados
			connection.commit();

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
			e.printStackTrace();
		}

	}

}
