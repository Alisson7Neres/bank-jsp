package com.bank.servlets;

import java.io.IOException;

import com.bank.dao.DAOBank;
import com.bank.dao.DAOCliente;
import com.bank.model.Bank;
import com.bank.model.Cliente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BankServletLogin
 */
@WebServlet("/BankServletLogin")
public class BankServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BankServletLogin() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getParameter("url");
		
		DAOCliente daoCliente = new DAOCliente();
		DAOBank daoBank = new DAOBank();
		
		Cliente clienteLogado = new Cliente();
		Bank bank = new Bank();
		
		String cpf = request.getParameter("cpf");
		String senha = request.getParameter("senha");

		if (cpf != null && !cpf.isEmpty() && senha != null && !senha.isEmpty()) {

			clienteLogado.setCpf(cpf);
			clienteLogado.setSenha(senha);

			if (daoCliente.validarLogin(clienteLogado)) {

				// Pega o login da sessão
				daoBank.mostrarBank(bank);
				request.getSession().setAttribute("numeroconta", bank.getNumeroConta());
				request.getSession().setAttribute("agencia", bank.getAgencia());
				request.getSession().setAttribute("saldo", bank.getSaldo());
				request.getSession().setAttribute("tipo", bank.getTipo());

				request.getSession().setAttribute("nome", clienteLogado.getNome());
				request.getSession().setAttribute("cpf", clienteLogado.getCpf());
				request.getSession().setAttribute("rg", clienteLogado.getRg());

				if (url == null || url.equals("null")) {
					url = "conta.jsp";
				}

				RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				redirecionar.forward(request, response);
			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe o cpf e senha CORRETAMENTE!");
				redirecionar.forward(request, response);
			}
		}
	}

}
