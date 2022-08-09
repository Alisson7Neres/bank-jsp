<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/conta.css">
<title>Transferido</title>
</head>
<body>


	<ul>
		<li><a href="/bank/index.jsp">Início</a></li>
		<li><a href="cadastrar.jsp">Cadastrar</a></li>
		<li><a href="mailto:alisson.neres@hotmail.com">Contato</a></li>
		<li><a href="login.jsp">Editar</a>
		<li><a href="https://github.com/Alisson7Neres/bank-jsp"
			target="blank">About</a></li>
	</ul>

	<div>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>
		<form>
		<h3>Transferencia concluída com sucesso!</h3>
			<div>
				<div>
					<label>Titular:</label>
				</div>
				<br />
				<div>
				<label>Nome</label> <span><%=session.getAttribute("nomeTitular") %></span>
				<br />
				<label>Conta:</label> <span> <%=session.getAttribute("numeroconta")%></span>
				</div>
			</div>
		</form>

		<form action="<%=request.getContextPath()%>/BankServletTransferir"
			method="get">
			<div>
				<label>Destino: </label>
			</div>
			<br />
			<div>
				<label>Nome</label> <span><%=session.getAttribute("nomeDestino") %></span>
				<br/>
				<label>Conta</label> <span><%=session.getAttribute("numeroContaDestino")%></span>
				<label>Valor</label> <span>R$ <%=session.getAttribute("saldoDestino") %></span>
			</div>
		</form>
	</div>

</body>
</html>