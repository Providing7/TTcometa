package acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.EntregaDAO;

public class DeletarEntrega implements Acao {
    @Override
    public String Executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        new EntregaDAO().deletar(id);
        
        return "redirect:canalentr?acao=ListarEntregas";
    }
}