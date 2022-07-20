package com.bank.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.bank.dao.DAOCliente;
import com.bank.model.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BankServletEsqueciMe
 */
@WebServlet("/BankServletEsqueciMe")
public class BankServletEsqueciMe extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BankServletEsqueciMe() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cliente cliente = new Cliente();
		DAOCliente daoCliente = new DAOCliente();
		
		String cpf = request.getParameter("cpf");
		String rg = request.getParameter("rg");
		String email = request.getParameter("email");
		
		request.getSession().setAttribute("senha", cliente.getSenha());
		
		cliente.setCpf(cpf);
		cliente.setRg(rg);
		cliente.setEmail(email);
		cliente.setSenha(cliente.getSenha());
		
		daoCliente.esqueciMe(cliente);
		
		try {
			request.setAttribute("cliente", daoCliente.mostrarCliente(cliente, cpf));
			request.getRequestDispatcher("alterado.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
