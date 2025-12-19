<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Principal TTcometa</title>
</head>
<body>

	<h1>Menu de Cadastros</h1>

	<hr>

	<div>
		<h2>Clientes</h2>
		<ul>
			<li><a
				href="${pageContext.request.contextPath}/canalclie?acao=ListaClientes">LISTAR
					CLIENTES</a></li>
			<%-- <li><a
				href="${pageContext.request.contextPath}/canalclie?acao=FormNovoCliente">Novo
					Cliente</a></li> --%>
		</ul>
	</div>

	<hr>

	<div>
		<h2>Mercadorias</h2>
		<ul>
			<li><a
				href="${pageContext.request.contextPath}/canalmerc?acao=ListarMercadorias">LISTAR
					MERCADORIAS</a></li>
			<%-- <li><a
				href="${pageContext.request.contextPath}/canalmerc?acao=FormNovaMercadoria">Nova
					Mercadoria</a></li> --%>
		</ul>
	</div>

	<hr>

	<div>
		<h2>Entregas</h2>
		<ul>
			<li><a
				href="${pageContext.request.contextPath}/canalentr?acao=ListarEntregas">Listar
					Entregas</a></li>
			<li><a
				href="${pageContext.request.contextPath}/canalentr?acao=FormNovaEntrega">Nova
					Entrega</a></li>
		</ul>
	</div>

</body>
</html>