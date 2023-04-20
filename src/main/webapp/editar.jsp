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
		<li><a href="conta.jsp">Conta</a></li>
		<li><a href="mailto:alisson.neres@hotmail.com">Contato</a></li>
		<li><a href="https://github.com/Alisson7Neres/bank-jsp"
			target="blank">About</a></li>
	</ul>


	<form
		action="<%=request.getContextPath()%>/atualizar"
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
					value="${ cliente.email }" required>
			</div>
			<div>
				<label>Telefone</label> <input id="telefone" name="telefone"
					value="${ cliente.telefone }" required>
			</div>
			<div>
				<label>Senha</label> <input id="senha" name="senha" type="password"
					value="${ cliente.senha }" required>
			</div>
		</div>
		<br /> <br />
		<div>
			<label>Endereço: </label>
		</div>
		<br />
		<div>
			<label>CEP</label> <input onblur="viaCep()" id="cep" name="cep"
				value="${ endereco.cep }" required>
		</div>
		<div>
			<label>Logradouro</label> <input id="logradouro" name="logradouro"
				value="${ endereco.logradouro }" required>
		</div>
		<div>
			<label>Complemento</label> <input id="complemento" name="complemento"
				value="${ endereco.complemento }" required>
		</div>
		<div>
			<label>Bairro</label> <input id="bairro" name="bairro"
				value="${ endereco.bairro }" required>
		</div>
		<div>
			<label>Localidade</label> <input id="localidade" name="localidade"
				value="${ endereco.localidade }" required>
		</div>
		<div>
			<label>UF</label> <input id="uf" name="uf" value="${ endereco.uf }" required>
		</div>
		<br>
		<div>
			<label>Tipo da conta:</label> <input value="${bank.tipo}"
				disabled="disabled">
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
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
	<script>
		function viaCep() {
			var cep = $('#cep').val();
			var logradouro = $('#logradouro').val();
			var complemento = $('#complemento').val();
			var bairro = $('#bairro').val();
			var localidade = $('#localidade').val();
			var uf = $('#uf').val();

			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							$("#logradouro").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#localidade").val(dados.localidade);
							$("#uf").val(dados.uf);
						} else {
							//CEP pesquisado não foi encontrado.
							limpa_formulário_cep();
							alert("CEP não encontrado.");
						}
					});
		}

		function limpa_formulário_cep() {
			// Limpa valores do formulário de cep.
			$("#logradouro").val("");
			$("#complemento").val("");
			$("#cidade").val("");
			$("#bairro").val("");
			$("#localidade").val("");
			$("#uf").val("");
		}

		var checar = document.getElementsByName('tipo');
		var numElementos = checar.length;
		var bt = document.getElementById('aplica');
		for (var x = 0; x < numElementos; x++) {
			checar[x].onclick = function() {

				if (document.getElementById('aplica').disabled == false) {
					document.getElementById('aplica').style.backgroundColor = 'grey';
					document.getElementById('aplica').style.cursor = 'none';
				} else {
					document.getElementById('aplica').style.backgroundColor = '#4CAF50';
					document.getElementById('aplica').style.cursor = 'pointer';
				}
				var cont = document
						.querySelectorAll("input[name='tipo']:checked").length;
				// Ternário que verifica se há algum checado.
				// Se não há, retorna o (false), logo desabilita o botão.
				bt.disabled = cont ? false : true;
			}
		}

		var elem = document.getElementsByTagName('input')[0];

		if (elem.hasAttribute('required')) {
		}

		$('#cpf').mask('999.999.999-99');
		$('#rg').mask('9.999-999');
		$('#telefone').mask('(99) 9 9999-9999');
		$('#cep').mask('99999-999');
	</script>

</body>
</html>