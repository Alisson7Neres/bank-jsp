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

@WebServlet("/atualizar")
public class BankServletCadastroAtualizar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BankServletCadastroAtualizar() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cliente clienteCadastro = new Cliente();
		Endereco enderecoCadastro = new Endereco();
		Bank bank = new Bank();
		
		DAOCliente daoCliente = new DAOCliente();
		DAOEndereco daoEndereco = new DAOEndereco();
		DAOBank daoBank = new DAOBank();
		
		request.getSession().setAttribute("cpf", clienteCadastro.getCpf());
		request.getSession().setAttribute("id_endereco", enderecoCadastro.getId_endereco());
		
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
		
		String id_endereco = clienteCadastro.getCpf();
		
		String tipo = request.getParameter("tipo");
		
		bank.setTipo(tipo);
		request.getSession().setAttribute("tipo", bank.getTipo());
		
		try {
			request.setAttribute("clienteCadastro", daoCliente.atualizarCliente(clienteCadastro, clienteCadastro.getCpf()));
			request.setAttribute("bank", daoBank.mostrarBank(bank, clienteCadastro));
			request.setAttribute("enderecoCadastro", daoEndereco.atualizarEndereco(enderecoCadastro, id_endereco));
			request.getRequestDispatcher("cadastrado.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
