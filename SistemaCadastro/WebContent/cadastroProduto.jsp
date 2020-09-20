<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Produtos</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/cadastro.css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/js/jquery.min.js">
<link rel="stylesheet" href="resources/js/jquery.maskMoney.min.js">
</head>
<body>

	<a href="acessoliberado.jsp"><img alt="Inicio"
		src="resources/img/home.png" width="50px" height="50px"></a>
	<a href="index.jsp"><img alt="Sair" src="resources/img/sair.png"
		width="50" height="50px"></a>

	<center>
		<h1>Cadastro de produtos</h1>
		<form action="salvarProduto" method="post" id="formProduto"
			onsubmit="return validarCampo() ? true : false">
			<ul class="form-style-1">
				<table>
					<tr>
						<td>Codigo: </td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							class="form-control form-control-sm" value="${produto.id }"></td>
					</tr>
					<tr>
						<td>Nome: </td>
						<td><input type="text" id="nome" name="nome"
							class="form-control form-control-sm" value="${produto.nome }"></td>
					</tr>
					<tr>
						<td>Quantidade: </td>
						<td><input type="text" id="quantidade" name="quantidade"
							class="form-control form-control-sm"
							value="${produto.quantidade }"></td>
					</tr>
					<!-- <tr>
						<td>Valor:</td>
						<td><input type="text" id="valor" name="valor"
							class="form-control form-control-sm" value="${produto.valor }"></td>
					</tr>-->
					<tr>
						<td>Valor: </td>
						<td><input type="text" name="valor"
							class="form-control form-control-sm" value="${produto.valor }"></td>
					</tr>
					<tr>
						<td>Categorias: </td>
						<td><select id="categorias" name="categoria_id"
							class="form-control form-control-sm">
								<c:forEach items="${categorias }" var="cat">
									<option id="${cat.id }" value="${cat.id }"
										<c:if test="${cat.id == produto.categoria_id }">
											<c:out value = "selected=selected" />
										</c:if>>${cat.nome }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"
							class="btn btn-primary"> <input type="submit"
							value="Cancelar" class="btn btn-primary"
							onclick="document.getElementById('formProduto').action='salvarProduto?acao=reset'"></td>
					</tr>
				</table>
			</ul>
		</form>
		<div class="table-wrapper">
			<caption>
				<h1>Produtos Cadastrados</h1>
			</caption>
			<table class="fl-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>NOME</th>
						<th>QUANTIDADE</th>
						<th>VALOR</th>
						<th>EDITAR</th>
						<th>DELETE</th>
					</tr>
				</thead>
				<c:forEach items="${produtos }" var="produto">
					<tr>
						<td><c:out value="${produto.id }"></c:out></td>
						<td><c:out value="${produto.nome }"></c:out></td>
						<td><c:out value="${produto.quantidade }"></c:out></td>
						<td><c:out value="${produto.valor }"></c:out></td>

						<td><a
							href="salvarProduto?acao=editar&produto=${produto.id }"><img
								alt="editar" title="Editar" src="resources/img/editar.jpg"
								width="20px" height="20px"></a></td>

						<td><a
							href="salvarProduto?acao=delete&produto=${produto.id }"
							onclick="return confirm('Confirmar a exclusão?')"><img
								alt="excluir" title="Excluir" src="resources/img/excluir.png"
								width="20px" height="20px"></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</center>
	<script type="text/javascript">
		function validarCampo() {
			if (document.getElementById('nome').value == '') {
				alert("Informe o nome");
				return false;
			} else if (document.getElementById('quantidade').value == '') {
				alert("Informe a quantidade");
				return false;
			} else if (document.getElementById('valor').value == '') {
				alert("Informe o valor");
				return false;
			}
		}
	</script>
</body>
<script type="text/javascript">
	$(function() {
		$('#valor').maskMoney();
	})

	$(document).ready(function() {
		$("#quantidade").keyup(function() {
			$("#quantidade").value(this.value.match(/[0-9]*/));
		});
	});
</script>
</html>