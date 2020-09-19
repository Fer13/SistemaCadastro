<<jsp:useBean id="calcula" class="beans.BeansUsuarios"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center style="padding-top: 10%">
		<h1>Bem vindo ao Sistema</h1>
		<table>
			<tr>
				<td><a href="salvarUsuario?acao=listartodos"><img
						alt="usuario" title="Usuario" src="resources/img/usuario.png"
						width="200px" height="150px"></a></td>
				<td><a href="salvarProduto?acao=listartodos"><img
						alt="produto" title="Produto" src="resources/img/produto.png"
						width="200px" height="150px"></a></td>
			</tr>
			<tr>
				<td>Cad.Usuários</td>
				<td>Cad.Produtos</td>
			</tr>
		</table>
	</center>
</body>
</html>