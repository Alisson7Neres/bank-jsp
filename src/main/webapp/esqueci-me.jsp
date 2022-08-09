<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/esqueci-me.css">
<title>Esqueci-me</title>
</head>
<body>

	<ul>
		<li><a href="/bank/index.jsp">Início</a></li>
		<li><a href="cadastrar.jsp">Cadastrar</a></li>
		<li><a href="mailto:alisson.neres@hotmail.com">Contato</a></li>
		<li><a href="https://github.com/Alisson7Neres/bank-jsp"
			target="blank">About</a></li>
	</ul>

	<form action="<%=request.getContextPath()%>/BankServletEsqueciMe"
		method="POST">
		<div>
			<label>CPF</label> <input id="cpf" name="cpf" type="text" required>
			<label>RG</label> <input id="rg" name="rg" type="text" required>
			<label>Email</label> <input id="email" name="email" type="text"
				required> <input class="input" type="submit" value="Enviar">
		</div>
	</form>


	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
	<script>
		$('#cpf').mask('999.999.999-99');
		$('#rg').mask('9.999.999')
	</script>
</body>
</html>