<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nova Entrega</title>
</head>
<body>

    <a href="menu.jsp">Voltar para o Menu</a>

    <h1>Cadastrar Entrega</h1>

    <form action="canalentr?acao=CadastrarEntrega" method="post">

        <p>
            <label>Remetente:</label><br>
            <select name="idRemetente" required>
                <option value="">Selecione o remetente</option>
                <c:forEach var="c" items="${listaClientes}">
                    <option value="${c.idCliente}">${c.nome}</option>
                </c:forEach>
            </select>
        </p>

        <p>
            <label>Destinatário:</label><br>
            <select name="idDestinatario" required>
                <option value="">Selecione o destinatário</option>
                <c:forEach var="c" items="${listaClientes}">
                    <option value="${c.idCliente}">${c.nome}</option>
                </c:forEach>
            </select>
        </p>

        <p>
            <label>Mercadoria:</label><br>
            <select name="idMercadoria" required>
                <option value="">Selecione a mercadoria</option>
                <c:forEach var="m" items="${listaMercadorias}">
                    <option value="${m.idMercadoria}">${m.nome}</option>
                </c:forEach>
            </select>
        </p>

        <p>
            <label>Quantidade:</label><br>
            <input type="number" name="quantidade" value="1" min="1" required>
        </p>

        <p>
            <button type="submit">Gravar Entrega</button>
        </p>

    </form>

</body>
</html>