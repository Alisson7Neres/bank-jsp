<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/cadastrar.css">
<title>Editar</title>
</head>
<body>

	<ul>
		<li><a href="/bank/index.jsp">Início</a></li>
		<li><a href="cadastrar.jsp">Cadastrar</a></li>
		<li><a href="">Contato</a></li>
		<li><a href="">About</a></li>
	</ul>


	<form
		action="<%=request.getContextPath()%>/BankServletCadastroAtualizar"
		method="post">
		<div>
			<label>Nome</label> <input id="nome" name="nome"
				value="${ cliente.nome }" readonly="readonly">
		</div>
		<div>
			<label>CPF</label> <input id="cpf" name="cpf"
				value="${ cliente.cpf }" readonly="readonly">
		</div>
		<div>
			<label>RG</label> <input id="rg" name="rg" value="${ cliente.rg }"
				readonly="readonly">
		</div>

		<div>
			<div>
				<label>Email</label> <input id="email" name="email"
					value="${ cliente.email }">
			</div>
			<div>
				<label>Telefone</label> <input id="telefone" name="telefone"
					value="${ cliente.telefone }">
			</div>
			<div>
				<label>Senha</label> <input id="senha" name="senha" type="password"
					value="${ cliente.senha }">
			</div>
		</div>
		<br /> <br />
		<div>
			<label>Endereço: </label>
		</div>
		<br />
		<div>
			<label>CEP</label> <input onblur="viaCep()" id="cep" name="cep"
				value="${ cliente.cep }">
		</div>
		<div>
			<label>Logradouro</label> <input id="logradouro" name="logradouro"
				value="${ cliente.logradouro }">
		</div>
		<div>
			<label>Complemento</label> <input id="complemento" name="complemento"
				value="${ cliente.complemento }">
		</div>
		<div>
			<label>Bairro</label> <input id="bairro" name="bairro"
				value="${ cliente.bairro }">
		</div>
		<div>
			<label>Localidade</label> <input id="localidade" name="localidade"
				value="${ cliente.localidade }">
		</div>
		<div>
			<label>UF</label> <input id="uf" name="uf" value="${ cliente.uf }">
		</div>
		<br>
		<div>
			<label>Tipo da conta:</label>
			<input value="${bank.tipo}" disabled="disabled"> 
		</div>
		<div>
			<br />
			<button id="aplica" style="background-color: green; cursor: pointer;">Atualizar</button>
		</div>
	</form>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
	<script>
		$('#telefone').mask('(99) 9 9999-9999');
		$('#cep').mask('99999-999');
	</script>

</body>
</html>