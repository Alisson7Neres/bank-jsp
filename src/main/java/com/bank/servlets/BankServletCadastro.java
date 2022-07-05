package com.bank.servlets;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class BankServlet
 */
@WebServlet("/BankServletCadastro")
public class BankServletCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BankServletCadastro() {
    }
    
    DAOCliente daoCliente = new DAOCliente();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		Cliente clienteCadastro = new Cliente();
		if (acao.equalsIgnoreCase("conta")) {
			try {
				
			daoCliente.mostrarCliente(clienteCadastro, clienteCadastro.getCpf());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

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
		clienteCadastro.setCpf(cpf);
		clienteCadastro.setEmail(email);
		clienteCadastro.setTelefone(telefone);
		clienteCadastro.setSenha(senha);
		
		clienteCadastro.setCep(cep);
		clienteCadastro.setLogradouro(logradouro);
		clienteCadastro.setComplemento(complemento);
		clienteCadastro.setBairro(bairro);
		clienteCadastro.setLocalidade(localidade);
		clienteCadastro.setUf(uf);
		
 		String url = request.getParameter("url");
		
		try {
			
			if (!cpf.equalsIgnoreCase(clienteCadastro.getCpf())) {
			request.setAttribute("clienteCadastro", daoCliente.gravarCliente(clienteCadastro));
			request.getRequestDispatcher("cadastrado.jsp").forward(request, response);
			doGet(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (cpf != null && !cpf.isEmpty() && senha != null && !senha.isEmpty()) {
				Cliente clienteLogado = new Cliente();
				clienteLogado.setCpf(cpf);
				clienteLogado.setSenha(senha);
				
				Bank bank  = new Bank();
				DAOBank daoBank = new  DAOBank();
				
				if (daoCliente.validarLogin(clienteLogado)) {
					
					// Pega o login da sessão
					daoBank.mostrarBank(bank);
					request.getSession().setAttribute("numeroconta", bank.getNumeroConta());
					request.getSession().setAttribute("agencia", bank.getAgencia());
					request.getSession().setAttribute("saldo", bank.getSaldo());
					
					request.getSession().setAttribute("nome", clienteLogado.getNome());
					request.getSession().setAttribute("cpf", clienteLogado.getCpf());

					
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
