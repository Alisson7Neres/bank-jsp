package com.bank.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.bank.dao.DAOBank;
import com.bank.dao.DAOCliente;
import com.bank.model.Bank;
import com.bank.model.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BankServletCadastroAtualizar")
public class BankServletCadastroAtualizar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BankServletCadastroAtualizar() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cliente clienteCadastro = new Cliente();
		DAOCliente daoCliente = new DAOCliente();
		
		Bank bank = new Bank();
		DAOBank daoBank = new DAOBank();
		
		request.getSession().setAttribute("cpf", clienteCadastro.getCpf());
		
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
		
		clienteCadastro.setNome(nome);
		clienteCadastro.setCpf(cpf);
		clienteCadastro.setRg(rg);
		clienteCadastro.setEmail(email);
		clienteCadastro.setTelefone(telefone);
		clienteCadastro.setSenha(senha);
		
		clienteCadastro.setCep(cep);
		clienteCadastro.setLogradouro(logradouro);
		clienteCadastro.setComplemento(complemento);
		clienteCadastro.setBairro(bairro);
		clienteCadastro.setLocalidade(localidade);
		clienteCadastro.setUf(uf);
		
		String tipo = request.getParameter("tipo");
		
		bank.setTipo(tipo);
		request.getSession().setAttribute("tipo", bank.getTipo());
		
		try {
			request.setAttribute("clienteCadastro", daoCliente.atualizarCliente(clienteCadastro, clienteCadastro.getCpf()));
			request.setAttribute("bank", daoBank.mostrarBank(bank));
			request.getRequestDispatcher("cadastrado.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
