package acao;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.EntregaDAO;
import model.Entrega;

public class ListarEntregas implements Acao {

    @Override
    public String Executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        EntregaDAO dao = new EntregaDAO();
        
        List<Entrega> entregas = dao.listar(); 

        // Pendura a lista para o JSP ler
        request.setAttribute("entregas", entregas);

        // Retorna o caminho do seu JSP de relatório
        return "listaEntregas.jsp"; 
    }
}