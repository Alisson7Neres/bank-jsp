<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/conta.css">
<title>Conta</title>
</head>
<body>


	<ul>
		<li><a href="/bank/index.jsp">Início</a></li>
		<li><a href="cadastrar.jsp">Cadastrar</a></li>
		<li><a href="">Contato</a></li>
		<li><a href="login.jsp">Editar</a>
		<li><a href="">About</a></li>
	</ul>

	<div>

		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<form>
			<div>
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
			</div>
		</form>

		<form action="<%=request.getContextPath()%>/BankServletTransferir"
			method="get">
			<div>
				<label>Cliente: </label>
			</div>
			<br />
			<div>
				<label>Nome</label> <span><%=session.getAttribute("nome")%></span>
			</div>
			<div>
				<label>CPF</label> <span><%=session.getAttribute("cpf")%></span>
				<input id="cpf" name="cpf" value="${ cliente.cpf }" hidden="">
			</div>
			<div>
				<label>RG</label> <span><%=session.getAttribute("rg")%></span>
			</div>
			<button class="button">Transferir</button>
		</form>
	</div>

</body>
</html>