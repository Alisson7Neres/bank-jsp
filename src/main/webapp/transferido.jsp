<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/conta.css">
<title>Transferido</title>

<!-- Seleciona o campo a ser imprimido -->
<style>
#printable {
	display: none;
}

@media print {
	#non-printable {
		display: none;
	}
	#printable {
		display: block;
	}
}
</style>

</head>
<body>


	<ul id="non-printable">
		<li><a href="/bank/index.jsp">Início</a></li>
		<li><a href="cadastrar.jsp">Cadastrar</a></li>
		<li><a href="conta.jsp">Conta</a></li>
		<li><a href="mailto:alisson.neres@hotmail.com">Contato</a></li>
		<li><a href="login.jsp">Editar</a>
		<li><a href="https://github.com/Alisson7Neres/bank-jsp"
			target="blank">About</a></li>
	</ul>

	<div  id="formMostrar">

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>
		<form>
			<h3 id="non-printable">Transferencia concluída com sucesso!</h3>
			<label>Data: </label><span><%=session.getAttribute("formattedDate")%></span>
			<br /> <label>Hora: </label><span><%=session.getAttribute("formattedTime")%></span>
			<br />
			<div>
				<div>
					<label>Titular:</label>
				</div>
				<br />
				<div>
					<label>Nome</label> <span><%=session.getAttribute("nomeTitular")%></span>
					<br /> <label>Conta:</label> <span> <%=session.getAttribute("numeroconta")%></span>
				</div>
			</div>
		</form>

		<form action="<%=request.getContextPath()%>/transferir"
			method="get" id="formMostrar">
			<div>
				<label>Destino: </label>
			</div>
			<br />
			<div>
				<label>Nome</label> <span><%=session.getAttribute("nomeDestino")%></span>
				<br /> <label>Conta</label> <span><%=session.getAttribute("numeroContaDestino")%></span>
				<label>Valor</label> <span>R$ <%=session.getAttribute("saldoDestino")%></span>
			</div>

			<div style="margin: 10px;">
				<input type="button" class="imprimir" value="Imprimir relatório"
					onclick="imprimir();" id="non-printable">
			</div>
		</form>
	</div>

	<script src="https://code.jquery.com/jquery-1.10.0.min.js"></script>
	<script>
		function imprimir(form) {

			var conteudo = $("form[id*=formMostrar]");

			window.print();

		}
	</script>
</body>

</html>