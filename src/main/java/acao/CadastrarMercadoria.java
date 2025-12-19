package acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MercadoriaDAO;
import model.Mercadoria;

public class CadastrarMercadoria implements Acao {

    @Override
    public String Executa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String paramId = request.getParameter("id");
        String nome = request.getParameter("nome");
        String paramPeso = request.getParameter("peso");
        String paramValor = request.getParameter("valor");
        String paramVolume = request.getParameter("volume");

        Mercadoria mercadoria = new Mercadoria();
        mercadoria.setNome(nome);
        
        try {
            if (paramPeso != null) mercadoria.setPeso(Double.parseDouble(paramPeso.replace(",", ".")));
            if (paramValor != null) mercadoria.setValor(Double.parseDouble(paramValor.replace(",", ".")));
            if (paramVolume != null) mercadoria.setVolume(Double.parseDouble(paramVolume.replace(",", ".")));
        } catch (Exception e) {
            System.out.println("Erro na conversão: " + e.getMessage());
        }

        MercadoriaDAO dao = new MercadoriaDAO();

        if (paramId != null && !paramId.isEmpty()) {
            int id = Integer.parseInt(paramId);
            mercadoria.setIdMercadoria(id);
            dao.atualizar(mercadoria); 
        } else {
            dao.inserir(mercadoria);
        }

        return "redirect:canalmerc?acao=ListarMercadorias";
    }
}