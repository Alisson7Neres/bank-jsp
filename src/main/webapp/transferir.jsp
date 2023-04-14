<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/conta.css">
<title>Transferir</title>
</head>
<body>

	<ul>
		<li><a href="index.jsp">Início</a></li>
		<li><a href="cadastrar.jsp">Cadastrar</a></li>
		<li><a href="conta.jsp">Conta</a></li>
		<li><a href="mailto:alisson.neres@hotmail.com">Contato</a></li>
		<li><a href="https://github.com/Alisson7Neres/bank-jsp"
			target="blank">About</a></li>
	</ul>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<form action="<%=request.getContextPath()%>/transferir"
		method="POST">
		<div>
		<label style="color: red"><c:out value="${ msg }"></c:out></label>
		<br/>
			<input id="nomeTitular" name="nomeTitular" value="${ cliente.nome }"
				hidden=""> <input id="cpfTitular" name="cpfTitular"
				value="${ cliente.cpf }" hidden=""> <input
				id="numeroContaTitular" name="numeroContaTitular"
				value="${ bank.numeroConta }" hidden=""> <input
				id="agenciaTitular" name="agenciaTitular" value="${ bank.agencia }"
				hidden=""> <input id="saldoTitular" name="saldoTitular"
				value="${ bank.saldo }" hidden=""> <input id="tipoTitular"
				name="tipoTitular" value="${ bank.tipo }" hidden="">
			<div>
				<label>CPF:</label> <span id="nome"> <%=session.getAttribute("cpf")%></span>
				<label>Nome:</label> <span id="nome"> <%=session.getAttribute("nome")%></span>
			</div>
			<br>
			<div>
				<label>Conta:</label> <span> <%=session.getAttribute("numeroconta")%></span>
			</div>
			<div>
				<label>Agência:</label> <span> <%=session.getAttribute("agencia")%></span>
			</div>
			<div>
				<label>Saldo:</label> <span> <%=session.getAttribute("saldo")%></span>
			</div>
			<div>
				<label>Tipo da conta:</label> <span> <%=session.getAttribute("tipo")%></span>
			</div>
			<br /> <br /> <br /> <label>Conta destino</label> <input
				id="numeroContaDestino" name="numeroContaDestino"
				value="${ bank.numeroConta }"> <label>Valor</label> <input
				id="saldoDestino" name="saldoDestino" value="saldoDestino">
		</div>
		<input class="button2" type="submit" value="Transferir">
	</form>



	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>

	<script>
		$('#numeroconta').mask('99999999');
		$('#saldo').mask('R$ 999.999,999');
	</script>
</body>
</html>