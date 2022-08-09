<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/esqueci-me.css">
<title>Senha alterada</title>
</head>
<body>

	<ul>
		<li><a href="/">Início</a></li>
		<li><a href="/cadastrar.jsp">Cadastrar</a></li>
		<li><a href="mailto:alisson.neres@hotmail.com">Contato</a></li>
		<li><a href="https://github.com/Alisson7Neres/bank-jsp" target="blank">About</a></li>
	</ul>

	<h3>Sua nova senha:</h3>
	<form>
		<label>Senha</label> <input id="senha" name="senha"
			value="${ cliente.senha }" class="input2" disabled="disabled">
	</form>
</body>
</html>