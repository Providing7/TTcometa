package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acao.Acao;
import acao.ListarMercadorias;
import acao.CadastrarMercadoria;
import acao.EditarMercadoria;
import acao.DeletarMercadoria;
import acao.FormNovaMercadoria;

@WebServlet("/canalmerc") 
public class MercadoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String paramAcao = request.getParameter("acao");
        String nomeDoDestino = null;
        Acao acao = null;

        // CORREÇÃO 1: Adicionado o "r" para bater com o if abaixo
        if (paramAcao == null || paramAcao.isEmpty()) {
            paramAcao = "ListarMercadorias"; 
        }

        System.out.println(">>> LOG MERCADORIA: Executando " + paramAcao);

        // CORREÇÃO 2: Verifique se os nomes batem 100% com o que vem do JSP
        if (paramAcao.equals("ListarMercadorias")) {
            acao = new ListarMercadorias();
        } else if (paramAcao.equals("CadastraMercadoria")) { // Se no JSP estiver CadastraMercadoria, deixe assim.
            acao = new CadastrarMercadoria();
        } else if (paramAcao.equals("FormNovaMercadoria")) {
            acao = new FormNovaMercadoria();
        } else if (paramAcao.equals("EditarMercadoria")) {
            acao = new EditarMercadoria();
        } else if (paramAcao.equals("DeletarMercadoria")) {
            acao = new DeletarMercadoria();
        }

        if (acao != null) {
            try {
                nomeDoDestino = acao.Executa(request, response);
                
                if (nomeDoDestino != null) {
                    if (nomeDoDestino.startsWith("redirect:")) {
                        String url = nomeDoDestino.substring(9); 
                        response.sendRedirect(url);
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher(nomeDoDestino);
                        rd.forward(request, response);
                    }
                }
            } catch (Exception e) {
                throw new ServletException("Erro na ação de mercadoria: " + paramAcao, e);
            }
        } else {
            // Se cair aqui, imprima no console qual foi a palavra exata que causou o erro
            System.out.println("ERRO: Ação '" + paramAcao + "' não foi reconhecida no Servlet!");
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação não encontrada: " + paramAcao);
        }
    }
}