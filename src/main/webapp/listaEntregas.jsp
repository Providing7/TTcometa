<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Entregas - TTcometa</title>
</head>
<body>

    <h1>Relatório de Entregas</h1>

    <p>
        <a href="${pageContext.request.contextPath}/canalentr?acao=FormNovaEntrega"> + Nova Entrega </a>
        | 
        <a href="${pageContext.request.contextPath}/menu.jsp"> Voltar ao Menu </a>
    </p>

    <hr>

    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Remetente</th>
                <th>Destinatário</th>
                <th>Mercadoria</th>
                <th>Qtd</th>
                <th>Valor Unit.</th>
                <th>Status</th>
                <th>Ações</th> 
            </tr>
        </thead>
        <tbody>
            <c:forEach var="e" items="${entregas}">
                <tr>
                    <td>${e.idEntrega}</td>
                    <td>${e.remetente.nome}</td>
                    <td>${e.destinatario.nome}</td>
                    <td>${e.mercadoria.nome}</td>
                    <td>${e.quantidade}</td>
                    <td>R$ ${e.mercadoria.valor}</td>
                    <td style="color: ${e.status == 'Concluída' ? 'green' : 'red'}; font-weight: bold;">
                        ${e.status}
                    </td>
                    <td>
                        <%-- Botão Finalizar: só aparece se não estiver concluída --%>
                        <c:if test="${e.status != 'Concluída'}">
                            <a href="${pageContext.request.contextPath}/canalentr?acao=FinalizarEntrega&id=${e.idEntrega}">Concluir</a>
                            |
                        </c:if>

                        <%-- Padrão Editar e Excluir --%>
                        <a href="${pageContext.request.contextPath}/canalentr?acao=EditarEntrega&id=${e.idEntrega}">Editar</a>
                        |
                        <a href="${pageContext.request.contextPath}/canalentr?acao=DeletarEntrega&id=${e.idEntrega}" 
                           onclick="return confirm('Deseja realmente excluir a entrega de ID ${e.idEntrega}?');">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            
            <c:if test="${empty entregas}">
                <tr>
                    <td colspan="8" align="center">A lista está vazia no momento.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

</body>
</html>