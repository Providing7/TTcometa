<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Estoque de Mercadorias - TTcometa</title>
</head>
<body>

    <h1>Mercadorias em Estoque</h1>

    <p>
        <a href="${pageContext.request.contextPath}/canalmerc?acao=FormNovaMercadoria"> + Cadastrar Nova </a>
        | 
        <a href="${pageContext.request.contextPath}/menu.jsp"> Voltar ao Menu </a>
    </p>

    <hr>

    <%-- border="1" ativa as linhas, cellspacing="0" junta as linhas duplas --%>
    <table border="1" cellspacing="0" cellpadding="5">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Peso (kg)</th>
                <th>Valor (R$)</th>
                <th>Volume</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="m" items="${mercadorias}">
                <tr>
                    <td>${m.idMercadoria}</td>
                    <td>${m.nome}</td>
                    <td>${m.peso}</td>
                    <td>${m.valor}</td>
                    <td>${m.volume}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/canalmerc?acao=EditarMercadoria&id=${m.idMercadoria}">Editar</a>
                        |
                        <a href="${pageContext.request.contextPath}/canalmerc?acao=DeletarMercadoria&id=${m.idMercadoria}" 
                           onclick="return confirm('Deseja excluir ${m.nome}?');">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            
            <c:if test="${empty mercadorias}">
                <tr>
                    <td colspan="6" align="center">Nenhuma mercadoria encontrada no banco de dados.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

</body>
</html>