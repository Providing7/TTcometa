<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulário de Mercadoria</title>
</head>
<body>

	<h1>${not empty mercadoria.idMercadoria ? 'Editar Mercadoria' : 'Nova Mercadoria'}</h1>

	<form
		action="${pageContext.request.contextPath}/canalmerc?acao=CadastraMercadoria" method="POST">

		<input type="hidden" name="id" value="${mercadoria.idMercadoria}">

		<input type="hidden" name="acao" value="CadastraMercadoria">
		<p>
			<label>Nome da Mercadoria:</label><br> <input type="text"
				name="nome" value="${mercadoria.nome}" required>
		</p>

		<p>
			<label>Peso (kg):</label><br> <input type="text" name="peso"
				value="${mercadoria.peso}">
		</p>

		<p>
			<label>Valor (R$):</label><br> <input type="text" name="valor"
				value="${mercadoria.valor}">
		</p>

		<p>
			<label>Volume:</label><br> <input type="text" name="volume"
				value="${mercadoria.volume}">
		</p>

		<hr>

		<button type="submit">${not empty mercadoria.idMercadoria ? 'Salvar Alterações' : 'Cadastrar Mercadoria'}
		</button>

	</form>

	<br>
	<a
		href="${pageContext.request.contextPath}/canalmerc?acao=ListarMercadorias">
		Voltar para Lista</a>
	<p>
		<a href="${pageContext.request.contextPath}/menu.jsp">Voltar ao
			Menu</a>
	</p>

</body>
</html>