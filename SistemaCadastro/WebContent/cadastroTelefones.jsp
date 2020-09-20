<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html ; charset=ISO-8859-1">
<title>Cadastro de Telefones</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
</head>
<body>
	<a href="acessoliberado.jsp"><img alt="Inicio"
		src="resources/img/home.png" width="50px" height="50px"></a>
	<a href="index.jsp"><img alt="Sair" src="resources/img/sair.png"
		width="50px" height="50px"></a>

	<center>
		<h1>Cadastro de Telefones</h1>
		<h3 style="color: rgba(255, 255, 0);">${msg }</h3>
		<form action="salvarTelefones" method="post" id="formfone"
			onsubmit="return validarCampo() ? true : false">
			<ul class="forn-style-1">
				<table>
					<tr>
						<td>User:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							class="form-control form-control-sm" value="${userEscolhido.id }"></td>

						<td><input type="text" readonly="readonly" id="nome"
							name="nome" class="form-control form-control-sm"
							value="${userEscolhido.nome }"></td>
					</tr>
					<tr>
						<td>Numero:</td>
						<td><input type="text" id="numero" name="numero"
							class="form-control form-control-sm" value="${fone.numero }"></td>

						<td><select id="tipo" name="tipo"
							class="form-control form-control-sm">
								<option>Escolha uma opção</option>
								<option>Casa</option>
								<option>Contato</option>
								<option>Celular</option>
						</select>
					</tr>
					<tr>
						<td />
						<td><input type="submit" value="Salvar"
							class="btn btn-primary" style="width: 100%"></td>
						<td><input type="submit" value="Voltar"
							class="btn btn-primary"
							onclick="document.getElementById('formFone').action='salvarTelefones?acao=vaoltar'"
							style="width: 100%"></td>
					</tr>
				</table>
			</ul>
		</form>
		<div class="table-wrapper">
			<caption>
				<h1>Telefones cadastrados</h1>
			</caption>
			<table class="fl-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>NÚMERO</th>
						<th>TIPO</th>
						<th>USUÁRIO</th>
						<th>EXCLUIR</th>
					</tr>
				</thead>
				<c:forEach items="${telefones}" var="fone">
					<tr>
						<td><c:out value="${fone.id }"></c:out></td>
						<td><c:out value="${fone.numero }"></c:out></td>
						<td><c:out value="${fone.tipo }"></c:out></td>
						<td><c:out value="${fone.usuario }"></c:out></td>

						<td><a
							href="salvarTelefones?acao=deleteFone&foneId=${fone.id }"
							onclick="return confirm('Confirmar a exclusão?')"><img
								alt="excluir" title="Excluir" src="resources/img/excluir.png"
								width="20px" height="20px"></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</center>
<!--	<script type="text/javascript">
		function validarCampo() {
			if (document.getElementById("numero").value == '') {
				alert('Informe o número');
				return false;
			} else if (document.getElementById("tipo").value == '') {
				alert('Informe o tipo');
				return false;
			}
			return true;
		}
	</script> -->
</body>
</html>