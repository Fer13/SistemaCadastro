<%@page import="beans.BeansUsuarios"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuarios</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/js/jquery-3.5.1.min.js">


<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
</head>
<body>
	<a href="acessoliberado.jsp"><img alt="Inicio"
		src="resources/img/home.png" width="50px" height="50px"></a>
	<a href="index.jsp"><img alt="Sair" src="resources/img/sair.png"
		width="50" height="50px"></a>

	<center>
		<h1>Cadastro de Usuario</h1>
		<h3 style="color: rgba(255, 255, 0);">${msg }</h3>
		<form action="salvarUsuario" method="post" id="formUser"
			onsubmit="return validarCampo() ? true : false;"
			enctype="multipart/form-data">
			<ul class="form-style-1">
				<table>
					<tr>
						<td>Codigo:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id }" class="form-control form-control-sm"
							placeholder="O id não precisa editar"></td>

						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep"
							class="form-control form-control-sm" value="${user.cep }"
							placeholder="Informe o cep"></td>
					</tr>

					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							class="form-control form-control-sm" value="${user.login }"
							placeholder="Informe o login"></td>
						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua"
							class="form-control form-control-sm" value="${user.rua }"
							placeholder="Informe a rua">
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							class="form-control form-control-sm" value="${user.senha }"
							placeholder="Informe a senha"></td>
						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"
							class="form-control form-control-sm" value="${user.bairro }"
							placeholder="Informe o bairro">
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							class="form-control form-control-sm" value="${user.nome }"
							placeholder="Informe o nome"></td>
						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"
							class="form-control form-control-sm" value="${user.cidade }"
							placeholder="Informe a cidade"></td>
					</tr>

					<tr>
						<td>Ibge:</td>
						<td><input type="text" id="ibge" name="ibge"
							class="form-control form-control-sm" value="${user.ibge }"
							placeholder="Informe o ibge"></td>
						<td>Estado:</td>
						<td><input type="text" id="estado" name="estado"
							class="form-control form-control-sm" value="${user.estado }"
							placeholder="Informe o estado">
					</tr>
					<tr>
						<td>Ativo:</td>
						<td><input type="checkbox" id="ativo" name="ativo"
							<%if (request.getAttribute("user") != null) {
	BeansUsuarios user = (BeansUsuarios) request.getAttribute("user");
	if (user.isAtivo()) {
		out.print(" ");
		out.print("checked=\"checked\"");
		out.print(" ");
	}
}%>></td>
					</tr>
					<tr>
						<td>Foto:</td>
						<td><input type="file" name="foto"></td>
						<td>Sexo:</td>
						<td><input type="radio" name="sexo"
							<%if (request.getAttribute("user") != null) {
	BeansUsuarios user = (BeansUsuarios) request.getAttribute("user");
	if (user.getSexo().equalsIgnoreCase("masculino")) {
		out.print(" ");
		out.print("checked=\"checked\"");
		out.print(" ");
	}
}%>
							value="masculino">Masculino <input type="radio"
							name="sexo"
							<%if (request.getAttribute("user") != null) {
	BeansUsuarios user = (BeansUsuarios) request.getAttribute("user");
	if (user.getSexo().equalsIgnoreCase("feminino")) {
		out.print(" ");
		out.print("checked=\"checked\"");
		out.print(" ");
	}
}%>
							value="feminino">Feminino</td>
					</tr>
					<tr>
						<td>Curriculo:</td>
						<td><input type="file" name="curriculo" value="curriculo"></td>
						<td>Perfil:</td>
						<td><select id="perfil" name="perfil"
							class="form-control form-control-sm">
								<option value="nao_informado">[--SELECIONE--]</option>
								<option value="administrador"
									<%if (request.getAttribute("user") != null) {
	BeansUsuarios user = (BeansUsuarios) request.getAttribute("user");
	if (user.getPerfil().equalsIgnoreCase("administrador")) {
		out.print(" ");
		out.print("selected=\"selected\"");
		out.print(" ");
	}
}%>>Administrador</option>
								<option value="secretario"
									<%if (request.getAttribute("user") != null) {
	BeansUsuarios user = (BeansUsuarios) request.getAttribute("user");
	if (user.getPerfil().equalsIgnoreCase("secretario")) {
		out.print(" ");
		out.print("selected=\"selected\"");
	}
}%>>Secretário</option>
								<option value="gerente"
									<%if (request.getAttribute("user") != null) {
	BeansUsuarios user = (BeansUsuarios) request.getAttribute("user");
	if (user.getPerfil().equalsIgnoreCase("gerente")) {
		out.print(" ");
		out.print("selected=\"selected\"");
		out.print(" ");
	}
}%>>Gerente</option>
								<option value="funcionario"
									<%if (request.getAttribute("user") != null) {
	BeansUsuarios user = (BeansUsuarios) request.getAttribute("user");
	if (user.getPerfil().equalsIgnoreCase("funcionario")) {
		out.print(" ");
		out.print("selected=\"selected\"");
		out.print(" ");
	}
}%>>Funcionário</option>
						</select></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"
							class="btn btn-primary" style="width: 49%"> <input
							type="submit" value="Cancelar" class="btn btn-primary"
							onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"
							style="width: 49%"></td>
					</tr>
				</table>
			</ul>
		</form>
		<form action="pesquisaServlet" method="post">
			<ul class="form-style-1">
				<li>
					<table>
						<tr>
							<td>Descrição</td>
							<td><input type="text" id="descricaoconsulta"
								name="descricaoconsulta" class="form-control form-control"></td>
							<td><input type="submit" value="Pesquisar"
								class="btn btn-primary"></td>
						</tr>
					</table>
				</li>
			</ul>
		</form>
		<div class="table-wrapper">
			<caption>
				<h1>Usuaros Cadastrados</h1>
			</caption>
			<table class="fl-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>FOTO</th>
						<th>CURRICULO</th>
						<th>NOME</th>
						<th>DELETE</th>
						<th>EDITE</th>
						<th>FONE</th>
					</tr>
				</thead>
				<c:forEach items="${usuarios }" var="user">
					<tr>
						<td><c:out value="${user.id }"></c:out></td>

						<c:if test="${user.fotoBase64Miniatura != null}">
							<td><a
								href="salvarUsuario?acao=download&tipo=imagem&user=${user.id }"><img
									src='<c:out value="${user.fotoBase64Miniatura }"></c:out>'
									width="25px" height="25" /></a></td>
						</c:if>
						<c:if test="${user.fotoBase64Miniatura == null}">
							<td><img alt="Imagem padrão"
								src="resources/img/fotopadrao.png" width="25px" height="25px"
								onclick="alert('Não possui foto')"></td>
						</c:if>

						<c:if test="${user.curriculoBase64 != null}">
							<td><a
								href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id }"><img
									alt="curriculo" src="resources/img/pdf.png" width="25px"
									height="25px"></a></td>
						</c:if>
						<c:if test="${user.curriculoBase64 == null}">
							<td><img alt="naopdf" src="resources/img/naopdf.jpg"
								width="25px" height="25px"
								onclick="alert('Não possui curriculo')"></td>
						</c:if>

						<td><c:out value="${user.nome }"></c:out></td>

						<td><a href="salvarUsuario?acao=delete&user=${user.id }"
							onclick="return confirm('Confirmar a exclusão?')"><img
								alt="excluir" title="Excluir" src="resources/img/excluir.png"
								width="25px" height="25px"></a></td>
						<td><a href="salvarUsuario?acao=editar&user=${user.id }"><img
								alt="editar" title="Editar" src="resources/img/editar1.png"
								width="25px" height="25px"></a></td>
						<td><a href="salvarTelefones?acao=addFone&user=${user.id }"><img
								alt="telefones" title="Telefones" src="resources/img/fone.png"
								width="25px" height="25px"></a>
					</tr>
				</c:forEach>
			</table>
		</div>
	</center>
	<script type="text/javascript">
		function validarCampo() {
			if (document.getElementById('login').value == '') {
				alert('Informe o login');
				return false;
			} else if (document.getElementById('senha').value == '') {
				alert('Informe o senha');
				return false;
			} else if (document.getElementById('nome').value == '') {
				alert('Informe o nome');
				return false;
			} else if (document.getElementById('fone').value == '') {
				alert('Informe o telefone');
				return false;
			}
			return true;
		}
		function consultaCep() {
			var cep = $("#cep").val();

			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {
						if (!("erro" in dados)) {
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#estado").val(dados.uf);
							$("#ibge").val(dados.ibge);
						} else {
							$("#cep").val('');
							$("#rua").val('');
							$("#bairro").val('');
							$("#cidade").val('');
							$("#estado").val('');
							$("#ibge").val('');
							alert("CEP não encontrado.")
						}
					});
		}
	</script>
</body>
</html>