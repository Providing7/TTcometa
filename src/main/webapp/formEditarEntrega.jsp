<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Entrega - TTcometa</title>
</head>
<body>

    <p><a href="canalentr?acao=ListarEntregas">Voltar para a Lista</a></p>

    <h1>Editar Entrega #${entrega.idEntrega}</h1>

    <form action="canalentr?acao=AtualizarEntrega" method="post">
        
        <input type="hidden" name="idEntrega" value="${entrega.idEntrega}">
        <input type="hidden" name="idEndereco" value="${entrega.enderecoEntrega.id}">
        <input type="hidden" name="status" value="${entrega.status}">

        <p>
            <label>Remetente:</label><br>
            <select name="idRemetente" required>
                <c:forEach var="c" items="${clientes}">
                    <option value="${c.idCliente}" ${c.idCliente == entrega.remetente.idCliente ? 'selected' : ''}>
                        ${c.nome}
                    </option>
                </c:forEach>
            </select>
        </p>

        <p>
            <label>Destinatário:</label><br>
            <select name="idDestinatario" required>
                <c:forEach var="c" items="${clientes}">
                    <option value="${c.idCliente}" ${c.idCliente == entrega.destinatario.idCliente ? 'selected' : ''}>
                        ${c.nome}
                    </option>
                </c:forEach>
            </select>
        </p>

        <p>
            <label>Mercadoria:</label><br>
            <select name="idMercadoria" required>
                <c:forEach var="m" items="${mercadorias}">
                    <option value="${m.idMercadoria}" ${m.idMercadoria == entrega.mercadoria.idMercadoria ? 'selected' : ''}>
                        ${m.nome}
                    </option>
                </c:forEach>
            </select>
        </p>

        <p>
            <label>Quantidade:</label><br>
            <input type="number" name="quantidade" value="${entrega.quantidade}" min="1" required>
        </p>

        <p>
            <button type="submit">Salvar Alterações</button>
        </p>

    </form>

</body>
</html>