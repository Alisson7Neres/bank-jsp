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
 * Servlet implementation class BankServlet
 */
@WebServlet("/BankServletCadastro")
public class BankServletCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BankServletCadastro() {
    }
    
    DAOCliente daoCliente = new DAOCliente();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cliente clienteCadastro = new Cliente();
		
		// Pegando os parametros da tela de cadastro
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		String senha = request.getParameter("senha");
		
		String cep = request.getParameter("cep");
		String logradouro = request.getParameter("logradouro");
		String complemento = request.getParameter("complemento");
		String bairro = request.getParameter("bairro");
		String localidade = request.getParameter("localidade");
		String uf = request.getParameter("uf");
		
		// Setando os parametros nos atributos
		clienteCadastro.setNome(nome);
		clienteCadastro.setCPF(cpf);
		clienteCadastro.setEmail(email);
		clienteCadastro.setTelefone(telefone);
		clienteCadastro.setSenha(senha);
		
		clienteCadastro.setCep(cep);
		clienteCadastro.setLogradouro(logradouro);
		clienteCadastro.setComplemento(complemento);
		clienteCadastro.setBairro(bairro);
		clienteCadastro.setLocalidade(localidade);
		clienteCadastro.setUf(uf);
		
		try {
			request.setAttribute("clienteCadastro", daoCliente.gravarCliente(clienteCadastro));
			request.getRequestDispatcher("/index.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
