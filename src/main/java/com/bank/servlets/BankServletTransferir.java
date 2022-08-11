package com.bank.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.bank.dao.DAOBank;
import com.bank.dao.DAOCliente;
import com.bank.model.Bank;
import com.bank.model.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BankServletTransferir")
public class BankServletTransferir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BankServletTransferir() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cliente cliente = new Cliente();
		Bank bank = new Bank();

		DAOCliente daoCliente = new DAOCliente();
		DAOBank daoBank = new DAOBank();

		String cpf = request.getParameter("cpf");
		try {
			request.setAttribute("cliente", daoCliente.mostrarCliente(cliente, cpf));
			request.setAttribute("bank", daoBank.mostrarBank(bank, cliente));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("transferir.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cliente cliente = new Cliente();
		Cliente clienteDestino = new Cliente();

		Bank bankTitular = new Bank();
		Bank bankDestino = new Bank();

		DAOCliente daoCliente = new DAOCliente();
		DAOBank daoBank = new DAOBank();

		try {

			// Pegando os valores da página
			String nomeTitular = request.getParameter("nomeTitular");
			String numeroContaTitular = request.getParameter("numeroContaTitular");
			String agenciaTitular = request.getParameter("agenciaTitular");
			String saldoTitular = request.getParameter("saldoTitular");

			String numeroContaDestino = request.getParameter("numeroContaDestino");
			// String saldoDestino = request.getParameter("saldoDestino");
			String saldoDestino = request.getParameter("saldoDestino");

			// Convertendo os parametros para os tipos de valores do model
			cliente.setNome(nomeTitular);

			bankTitular.setNumeroConta(java.lang.Long.parseLong(numeroContaTitular));
			bankTitular.setAgencia(java.lang.Integer.parseInt(agenciaTitular));
			bankTitular.setSaldo(java.lang.Double.parseDouble(saldoTitular));

			bankDestino.setNumeroConta(java.lang.Long.parseLong(numeroContaDestino));

			// Certificando se tem dinheiro na conta
			if (!saldoTitular.isEmpty()) {

				// Atribuindo valores do saldo para fazer a soma e subtração dos valores
				double saldoTitulo = java.lang.Double.parseDouble(saldoTitular);
				double saldoDestinatario = java.lang.Double.parseDouble(saldoDestino);

				// Conta titular
				double titularDesconto = saldoTitulo -= saldoDestinatario;
				double titularBank = bankTitular.getSaldo();
				titularBank = titularDesconto;
				bankTitular.setSaldo(titularBank);

				// Conta destino
				daoBank.mostrarBank(bankDestino, cliente);

				double saldoDestino2 = java.lang.Double.parseDouble(saldoDestino);

				daoBank.mostrarDestino(bankDestino, numeroContaDestino);

				double destinoDesconto = saldoDestino2 + bankDestino.getSaldo();
				double destinoBank = bankDestino.getSaldo();
				destinoBank = destinoDesconto;
				bankDestino.setSaldo(destinoBank);

				daoBank.TransferenciaDestino(bankDestino, cliente);
				daoBank.TransferenciaTitular(bankTitular, cliente);
				daoCliente.ClienteDestino(clienteDestino, bankDestino);
				daoCliente.ClienteTitular(cliente, bankTitular);

				request.getSession().setAttribute("numeroContaDestino", bankDestino.getNumeroConta());
				request.getSession().setAttribute("saldoDestino", saldoDestinatario);
				request.getSession().setAttribute("nomeDestino", clienteDestino.getNome());
				request.getSession().setAttribute("nomeTitular", cliente.getNome());

				// Mostrar data e hora
				LocalDateTime myDateObj = LocalDateTime.now();
				DateTimeFormatter data = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");

				String formattedDate = myDateObj.format(data);
				String formattedTime = myDateObj.format(hora);
				//

				String dataView = request.getParameter("formattedDate");
				String horaView = request.getParameter("formattedDate");
				
				request.getSession().setAttribute("formattedDate", formattedDate);
				request.getSession().setAttribute("formattedTime", formattedTime);
			}

			request.setAttribute("cliente", daoCliente.mostrarCliente(cliente, cliente.getCpf()));
			request.setAttribute("bank", daoBank.mostrarBank(bankTitular, cliente));
			request.getRequestDispatcher("transferido.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
