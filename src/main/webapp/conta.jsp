<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/conta.css">
<title>Conta</title>
</head>
<body>

<div>

	<br><br><br><br><br><br><br><br><br>
		<form action="<%= request.getContextPath() %>/BankServletCadastro/conta" method="get">	
		<div>
			<div>
				<label>Conta:</label> 
					<span> <%= session.getAttribute("numeroconta") %></span>
			</div>
			<div>
				<label>Agência:</label> 
				<span> <%= session.getAttribute("agencia") %></span>
			</div>
			<div>
				<label>Saldo:</label> 
				<span> <%= session.getAttribute("saldo") %></span>
			</div>
			<div>
				<label>Tipo da conta:</label> 
				<span> <%= session.getAttribute("tconta") %></span>
			</div>
		</div>
	</form>
	
	<form>
	<div><label>Cliente: </label></div>
		<br/>
		<div>
			<label>Nome</label>
			<span><%= session.getAttribute("nome") %></span>
		</div>
		<div>
			<label>CPF</label>
			<span><%= session.getAttribute("cpf") %></span>
		</div>
	</form>
	
</div>

</body>
</html>