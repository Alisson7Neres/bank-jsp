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
 * Servlet implementation class BankServletEsqueciMe
 */
@WebServlet("/esquecir-me")
public class BankServletEsqueciMe extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BankServletEsqueciMe() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = new Cliente();
		DAOCliente daoCliente = new DAOCliente();
		
		try {
			request.setAttribute("cliente", daoCliente.mostrarCliente(cliente, cliente.getCpf()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("editar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cliente cliente = new Cliente();
		DAOCliente daoCliente = new DAOCliente();
		
		Bank bank = new Bank();
		DAOBank daoBank = new DAOBank();
		
		Endereco endereco = new Endereco();
		DAOEndereco daoEndereco = new DAOEndereco();
		
		// Pegando os parametros da tela de cadastro
		String cpf = request.getParameter("cpf");
		String rg = request.getParameter("rg");
		String email = request.getParameter("email");
		String tipo = request.getParameter("tipo");
		
		String cep = request.getParameter("cep");
		String logradouro = request.getParameter("logradouro");
		String complemento = request.getParameter("complemento");
		String bairro = request.getParameter("bairro");
		String localidade = request.getParameter("localidade");
		String uf = request.getParameter("uf");
		
		request.getSession().setAttribute("senha", cliente.getSenha());
		
		// Setando os parametros nos atributos
		cliente.setCpf(cpf);
		cliente.setRg(rg);
		cliente.setEmail(email);
		cliente.setSenha(cliente.getSenha());
		
		daoCliente.esqueciMe(cliente);
		
		bank.setTipo(tipo);
		
		request.getSession().getAttribute("tipo");
		bank.setTipo(tipo);
		request.getSession().setAttribute("tipo", bank.getTipo());
		
		endereco.setCep(cep);
		endereco.setLogradouro(logradouro);
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setLocalidade(localidade);
		endereco.setUf(uf);
		
		try {
				request.setAttribute("cliente", daoCliente.mostrarCliente(cliente, cliente.getCpf()));
				request.setAttribute("bank", daoBank.mostrarBank(bank, cliente));
				request.setAttribute("endereco", daoEndereco.mostrarEndereco(endereco, cliente));
				request.getRequestDispatcher("editar.jsp").forward(request, response);
		} catch (SQLException e) {
			e.getClass();
			request.getSession().setAttribute("msgError", "ERROR! Erro ao tentar cadastrar, contate o suporte!" + e);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
