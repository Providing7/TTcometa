package acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.EntregaDAO;

public class FinalizarEntrega implements Acao {

    @Override
    public String Executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            new EntregaDAO().finalizar(id);
        }

        // Retorna um REDIRECT para a listagem. 
        return "redirect:canalentr?acao=ListarEntregas";
    }
}