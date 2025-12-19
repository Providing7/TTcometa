package acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.EntregaDAO;
import model.*;

public class AtualizarEntrega implements Acao {
    @Override
    public String Executa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Entrega e = new Entrega();
            
            // Método auxiliar para evitar o erro "For input string: ''"
            e.setIdEntrega(lerIntSeguro(request.getParameter("idEntrega")));
            e.setQuantidade(lerIntSeguro(request.getParameter("quantidade")));
            
            String status = request.getParameter("status");
            e.setStatus(status != null && !status.isEmpty() ? status : "Pendente");

            Cliente rem = new Cliente();
            rem.setIdCliente(lerIntSeguro(request.getParameter("idRemetente")));
            e.setRemetente(rem);

            Cliente dest = new Cliente();
            dest.setIdCliente(lerIntSeguro(request.getParameter("idDestinatario")));
            e.setDestinatario(dest);

            Mercadoria merc = new Mercadoria();
            merc.setIdMercadoria(lerIntSeguro(request.getParameter("idMercadoria")));
            e.setMercadoria(merc);

            Endereco end = new Endereco();
            // Aqui estava o erro: se o idEndereco for vazio, usamos um padrão (ex: 1)
            int idEnd = lerIntSeguro(request.getParameter("idEndereco"));
            end.setId(idEnd > 0 ? idEnd : 1); 
            e.setEnderecoEntrega(end);

            new EntregaDAO().atualizar(e);

            return "redirect:canalentr?acao=ListarEntregas";

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException("Erro na atualização: " + ex.getMessage());
        }
    }

    // Função para tratar strings vazias e evitar o erro 500
    private int lerIntSeguro(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(valor.trim());
    }
}