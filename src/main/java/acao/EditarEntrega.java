package acao;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.*;
import model.*;

public class EditarEntrega implements Acao {
    @Override
    public String Executa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        EntregaDAO entregaDao = new EntregaDAO();
        Entrega entrega = entregaDao.buscarPorId(id);
        
        List<Cliente> clientes = new ClienteDAO().listar();
        List<Mercadoria> mercadorias = new MercadoriaDAO().listar();
        
        request.setAttribute("entrega", entrega);
        request.setAttribute("clientes", clientes);
        request.setAttribute("mercadorias", mercadorias);

        return "formEditarEntrega.jsp";
    }
}