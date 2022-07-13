<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/cadastrar.css">
<title>Cadastro</title>
</head>
<body>

	<form action="<%=request.getContextPath()%>/BankServletCadastro"
		method="get">
		<div>
			<div>
				<label>Nome</label> <input id="nome" name="nome"
					value="${ clienteCadastro.nome }" disabled="disabled">
			</div>
			<div>
				<label>CPF</label> <input id="cpf" name="cpf"
					value="${ clienteCadastro.cpf }" disabled="disabled">
			</div>
			<div>
				<label>Email</label> <input id="email" name="email"
					value="${ clienteCadastro.email }" disabled="disabled">
			</div>
			<div>
				<label>Telefone</label> <input id="telefone" name="telefone"
					value="${ clienteCadastro.telefone }" disabled="disabled">
			</div>
			<div>
				<label>Senha</label> <input id="senha" name="senha" type="password"
					value="${ clienteCadastro.senha }" disabled="disabled">
			</div>
		</div>
		<div>
			<label>Endereço: </label>
		</div>
		<br />
		<div>
			<label>CEP</label> <input onblur="viaCep()" id="cep" name="cep"
				value="${ clienteCadastro.cep }" disabled="disabled">
		</div>
		<div>
			<label>Logradouro</label> <input id="logradouro" name="logradouro"
				value="${ clienteCadastro.logradouro }" disabled="disabled">
		</div>
		<div>
			<label>Complemento</label> <input id="complemento" name="complemento"
				value="${ clienteCadastro.complemento }" disabled="disabled">
		</div>
		<div>
			<label>Bairro</label> <input id="bairro" name="bairro"
				value="${ clienteCadastro.bairro }" disabled="disabled">
		</div>
		<div>
			<label>Localidade</label> <input id="localidade" name="localidade"
				value="${ clienteCadastro.localidade }" disabled="disabled">
		</div>
		<div>
			<label>UF</label> <input id="uf" name="uf"
				value="${ clienteCadastro.uf }" disabled="disabled">
		</div>
		<br>
		<div>
			<label>Tipo da conta:</label> <span> <%=session.getAttribute("tipo")%></span>
		</div>
	</form>

</body>
</html>