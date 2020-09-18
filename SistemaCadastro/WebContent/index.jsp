<jsp:useBean id="calcula" class="beans.BeansUsuarios"
	type="beans.BeansUsuarios" scope="page"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/estilo.css">
<link rel="stylesheet" href="resources/css/bootstrap.mim.css">
</head>
<body>
	<div class="login-page">
		<center>
			<h1 style="font-family: border; color: rgba(139 105 105);">SISTEMA
				EM JSP</h1>
			<br />
		</center>
		<div class="form">
			<form action="loginServlet" method="post" id="login-form">
				<input type="text" id="login" name="login" class="form-control form-control-sm">
				<input type="password" id="senha" name="senha" class="form-control form-control-sm">
				<button type="submit" value="Logar" class="btn btn-primary">Logar</button>
			</form>
		</div>
		<center><h3 style="font-family: border; color: rgba(139 105 105);">Fomação Java Web</h3></center>
	</div>
</body>
</html>