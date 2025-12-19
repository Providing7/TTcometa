package acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MercadoriaDAO;
import model.Mercadoria;

public class ListarMercadorias implements Acao {

    @Override
    public String Executa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("[LOG] Listando mercadorias...");

        // 1. Instancia o DAO de Mercadoria
        MercadoriaDAO dao = new MercadoriaDAO();
        
        // 2. Busca a lista no banco de dados
        List<Mercadoria> lista = dao.listar(); // Certifique-se que o método no DAO tem esse nome
        
        // 3. Coloca a lista no request para o JSP acessar
        request.setAttribute("mercadorias", lista);

        // 4. Retorna o nome do JSP (Sem redirect, pois queremos levar os dados)
        return "listaMercadorias.jsp";
    }
}