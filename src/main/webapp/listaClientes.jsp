<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Clientes</title>
</head>
<body>

    <h1>Clientes Cadastrados</h1>

    <p>
        <a href="${pageContext.request.contextPath}/canalclie?acao=FormNovoCliente"> + Novo Cliente </a>
        | 
        <a href="${pageContext.request.contextPath}/menu.jsp"> Voltar ao Menu </a>
    </p>

    <hr>

    <%-- border="1" cria as linhas, cellspacing="0" deixa a linha única e sólida --%>
    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>CNPJ</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.idCliente}</td>
                    <td>${cliente.nome}</td>
                    <td>${cliente.cpf}</td>
                    <td>${cliente.cnpj}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/canalclie?acao=EditarCliente&id=${cliente.idCliente}">Editar</a>
                        &nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/canalclie?acao=DeletarCliente&id=${cliente.idCliente}" 
                           onclick="return confirm('Deseja excluir o cliente: ${cliente.nome}?');">Deletar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>