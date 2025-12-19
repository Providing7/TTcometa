package acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MercadoriaDAO;
import model.Mercadoria;

public class EditarMercadoria implements Acao {

    @Override
    public String Executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Pega o ID que veio da tabela (listaMercadorias.jsp)
        String paramId = request.getParameter("id");
        int id = Integer.parseInt(paramId);

        // Chama o DAO para buscar o objeto completo
        MercadoriaDAO dao = new MercadoriaDAO();
        Mercadoria mercadoria = dao.buscarPorId(id);

        // Pendura o objeto no request para o JSP conseguir ler
        request.setAttribute("mercadoria", mercadoria);

        // Não uso redirect aqui porque o objeto 'mercadoria' morreria no caminho
        return "mercadoria.jsp";
    }
}