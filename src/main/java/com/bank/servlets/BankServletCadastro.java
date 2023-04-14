package com.bank.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.bank.dao.DAOBank;
import com.bank.dao.DAOCliente;
import com.bank.dao.DAOEndereco;
import com.bank.model.Bank;
import com.bank.model.Cliente;
import com.bank.model.Endereco;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BankServlet
 */
@WebServlet("/BankServletCadastro")
public class BankServletCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BankServletCadastro() {
	}

	DAOCliente daoCliente = new DAOCliente();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		Cliente clienteCadastro = new Cliente();
		if (acao.equalsIgnoreCase("conta")) {
			try {

				daoCliente.mostrarCliente(clienteCadastro, clienteCadastro.getCpf());
				request.getSession().setAttribute("msgExito", "Cadastro realizado com sucesso!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cliente clienteCadastro = new Cliente();
		Endereco enderecoCadastro = new Endereco();
		Bank bank = new Bank();
		
		DAOBank daoBank = new DAOBank();
		DAOEndereco daoEndereco = new DAOEndereco();
		
		// Pegando os parametros da tela de cadastro
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String rg = request.getParameter("rg");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		String senha = request.getParameter("senha");

		String cep = request.getParameter("cep");
		String logradouro = request.getParameter("logradouro");
		String complemento = request.getParameter("complemento");
		String bairro = request.getParameter("bairro");
		String localidade = request.getParameter("localidade");
		String uf = request.getParameter("uf");

		String tipo = request.getParameter("tipo");

		// Setando os parametros nos atributos
		clienteCadastro.setNome(nome);
		clienteCadastro.setCpf(cpf);
		clienteCadastro.setRg(rg);
		clienteCadastro.setEmail(email);
		clienteCadastro.setTelefone(telefone);
		clienteCadastro.setSenha(senha);

		enderecoCadastro.setCep(cep);
		enderecoCadastro.setLogradouro(logradouro);
		enderecoCadastro.setComplemento(complemento);
		enderecoCadastro.setBairro(bairro);
		enderecoCadastro.setLocalidade(localidade);
		enderecoCadastro.setUf(uf);
		
		bank.setTipo(tipo);
		request.getSession().setAttribute("tipo", bank.getTipo());

		try {

			if (daoCliente.existe(clienteCadastro.getCpf())) {
				request.setAttribute("msgCpfExite", "Usuário já cadastrado!");
				request.getRequestDispatcher("cadastrar.jsp").forward(request, response);
			} else {
				request.setAttribute("clienteCadastro", daoCliente.gravarCliente(clienteCadastro));
				request.setAttribute("bank", daoBank.gravarConta(bank, clienteCadastro));
				request.setAttribute("enderecoCadastro", daoEndereco.gravarEndereco(enderecoCadastro, clienteCadastro));
				request.getSession().setAttribute("msgExito", "Cadastro realizado com sucesso!");
				request.getRequestDispatcher("cadastrado.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.getClass();
			request.getSession().setAttribute("msgError", "ERROR! Erro ao tentar cadastrar, contate o suporte!" + e);
			request.getRequestDispatcher("cadastrar.jsp").forward(request, response);
		}

	}

}
