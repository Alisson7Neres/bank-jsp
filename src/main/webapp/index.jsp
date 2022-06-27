<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/index.css">
<title>Easy Bank</title>
</head>
<body>

	<ul>
		<li><a href="">Início</a></li>
		<li><a href="cadastrar.jsp">Cadastrar</a></li>
		<li><a href="">Contato</a></li>
		<li><a href="">About</a></li>
	</ul>

	<form action="<%=request.getContextPath()%>/BankServletCadastro"
		method="POST">
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">
		<div>
			<label>CPF</label> <input type="text" name="cpf"> <br /> <label>Senha</label>
			<input type="text" name="senha">
				<input class="input" type="submit" value="logar">
		</div>
		<a class="a2">Esqueceu a senha?</a>
	</form>

</body>
</html>