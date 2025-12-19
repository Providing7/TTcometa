<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Cliente</title>
<style>
    /* CSS para manter os campos visíveis se houver dados (Edição) */
    #camposPessoaFisica, #camposPessoaJuridica {
        display: ${not empty cliente.cpf or not empty cliente.cnpj ? 'block' : 'none'};
    }
</style>
</head>
<body onload="mostrarCampos()"> <h1>${not empty cliente.idCliente ? 'Editar Cliente' : 'Cadastro de Novo Cliente'}</h1>

    <form action="${pageContext.request.contextPath}/canalclie?acao=CadastraClientes" method="POST">
        
        <input type="hidden" name="idCliente" value="${cliente.idCliente}">

        <fieldset>
            <legend>Dados do Cliente</legend>

            <p>
                <label for="nome">Nome/Razão Social:</label> 
                <input type="text" id="nome" name="nome" required value="${cliente.nome}">
            </p>

            <p>
                <strong>Tipo de Cliente:</strong> 
                <input type="radio" id="tipoPF" name="tipoPessoa" value="PF" onchange="mostrarCampos()" 
                       ${not empty cliente.cpf ? 'checked' : ''}> 
                <label for="tipoPF">Pessoa Física (PF)</label> 

                <input type="radio" id="tipoPJ" name="tipoPessoa" value="PJ" onchange="mostrarCampos()"
                       ${not empty cliente.cnpj ? 'checked' : ''}>
                <label for="tipoPJ">Pessoa Jurídica (PJ)</label>
            </p>

            <hr>

            <div id="camposPessoaFisica" style="${not empty cliente.cpf ? 'display:block' : 'display:none'}">
                <h4>Dados Pessoa Física</h4>
                <p>
                    <label for="cpf">CPF:</label> 
                    <input type="text" id="cpf" name="cpf" value="${cliente.cpf}">
                </p>
            </div>

            <div id="camposPessoaJuridica" style="${not empty cliente.cnpj ? 'display:block' : 'display:none'}">
                <h4>Dados Pessoa Jurídica</h4>
                <p>
                    <label for="cnpj">CNPJ:</label> 
                    <input type="text" id="cnpj" name="cnpj" value="${cliente.cnpj}">
                </p>
                <p>
                    <label for="razaoSocial">Razão Social:</label> 
                    <input type="text" id="razaoSocial" name="razaoSocial" value="${cliente.nome}">
                </p>
            </div>
        </fieldset>

        <fieldset>
            <legend>Dados de Endereço</legend>
            <p>
                <label for="rua">Rua:</label> 
                <input type="text" id="rua" name="rua" required value="${cliente.endereco.rua}">
            </p>

            <p>
                <label for="numero">Número:</label> 
                <input type="text" id="numero" name="numero" required value="${cliente.endereco.numero}">
            </p>

            <p>
                <label for="cep">CEP:</label> 
                <input type="text" id="cep" name="cep" required value="${cliente.endereco.cep}">
            </p>

            <p>
                <label for="bairro">Bairro:</label> 
                <input type="text" id="bairro" name="bairro" required value="${cliente.endereco.bairro}">
            </p>
        </fieldset>

        <hr>

        <p>
            <a href="${pageContext.request.contextPath}/canalclie?acao=ListaClientes">Voltar à lista</a>
        </p>
        <button type="submit">${not empty cliente.idCliente ? 'Salvar Alterações' : 'Salvar Novo Cliente'}</button>

    </form>

    <script>
        function mostrarCampos() {
            const tipoPF = document.getElementById('tipoPF').checked;
            const tipoPJ = document.getElementById('tipoPJ').checked;

            const camposPF = document.getElementById('camposPessoaFisica');
            const camposPJ = document.getElementById('camposPessoaJuridica');

            if (tipoPF) {
                camposPF.style.display = 'block';
                camposPJ.style.display = 'none';
                document.getElementById('cpf').setAttribute('required', 'required');
                document.getElementById('cnpj').removeAttribute('required');
            } else if (tipoPJ) {
                camposPF.style.display = 'none';
                camposPJ.style.display = 'block';
                document.getElementById('cpf').removeAttribute('required');
                document.getElementById('cnpj').setAttribute('required', 'required');
            }
        }
    </script>
</body>
</html>