<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/cadastrar.css">
<title>Cadastro</title>
</head>
<body>

	<form action="<%= request.getContextPath() %>/BankServletCadastro" method="post">
		<div>
			<div>
				<label>Nome</label> 
				<input id="nome" name="nome" value="${ clienteCadastro.nome }">
			</div>
			<div>
				<label>CPF</label> 
				<input id="cpf" name="cpf" value="${ clienteCadastro.cpf }">
			</div>
			<div>
				<label>Email</label> 
				<input id="email" name="email" value="${ clienteCadastro.email }">
			</div>
			<div>
				<label>Telefone</label> 
				<input id="telefone" name="telefone" value="${ clienteCadastro.telefone }">
			</div>
			<div>
				<label>Senha</label> 
				<input id="senha" name="senha" type="password" value="${ clienteCadastro.senha }">
			</div>
		</div>
		<div><label>Endereço: </label></div>
		<br/>
		<div>
			<label>CEP</label>
			<input onblur="viaCep()" id="cep" name="cep" value="${ clienteCadastro.cep }">
		</div>
		<div>
			<label>Logradouro</label>
			<input id="logradouro" name="logradouro" value="${ clienteCadastro.logradouro }">
		</div>
		<div>
			<label>Complemento</label>
			<input id="complemento" name="complemento" value="${ clienteCadastro.complemento }">
		</div>
		<div>
			<label>Bairro</label>
			<input id="bairro" name="bairro" value="${ clienteCadastro.bairro }">
		</div>
		<div>
			<label>Localidade</label>
			<input id="localidade" name="localidade" value="${ clienteCadastro.localidade }">
		</div>
		<div>
			<label>UF</label>
			<input id="uf" name="uf" value="${ clienteCadastro.uf }">
		</div>
		<div>
			<button>Cadastrar</button>
		</div>
	</form>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
	<script>
	function viaCep() {
		  var cep = $('#cep').val();
	      var logradouro = $('#logradouro').val();
	      var complemento = $('#complemento').val();
	      var bairro = $('#bairro').val();
	      var localidade = $('#localidade').val();
	      var uf = $('#uf').val();
	      
	  	$.getJSON("https://viacep.com.br/ws/"+cep+"/json/?callback=?",function(dados) {
			
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
	</script>
</body>
</html>