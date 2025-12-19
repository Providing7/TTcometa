package acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ClienteDAO;

public class DeletarCliente implements Acao {

    @Override
    public String Executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String paramId = request.getParameter("id");
        int id = Integer.parseInt(paramId);

        ClienteDAO dao = new ClienteDAO();
        dao.deletar(id);

        // Após deletar, ele não abre página, ele REDIRECIONA para a lista atualizada
        return "redirect:canalclie?acao=ListaClientes&sucesso=Removido+com+sucesso";
    }
}