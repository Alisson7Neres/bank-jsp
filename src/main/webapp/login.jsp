<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/index.css">
<title>Login</title>
</head>
<body>

	<ul>
		<li><a href="/bank/index.jsp">Início</a></li>
		<li><a href="cadastrar.jsp">Cadastrar</a></li>
		<li><a href="mailto:alisson.neres@hotmail.com">Contato</a></li>
		<li><a href="https://github.com/Alisson7Neres/bank-jsp"
			target="blank">About</a></li>
	</ul>

	<form action="<%=request.getContextPath()%>/esquecir-me"
		method="POST">
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">
		<div>
			<label>CPF</label> <input type="text" name="cpf"> <br /> <label>Senha</label>
			<input type="password" name="senha" required> <input
				class="input" type="submit" value="logar" required> <label
				style="color: red"><c:out value="${ msg }"></c:out></label>
		</div>
		<a class="a2" href="esqueci-me.jsp">Esqueceu a senha?</a>
	</form>

</body>
</html>