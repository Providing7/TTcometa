package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acao.Acao;
import acao.CadastrarEntrega;
import acao.FormNovaEntrega;
import acao.ListarEntregas;
import acao.FinalizarEntrega;
import acao.DeletarEntrega;
import acao.EditarEntrega;
import acao.AtualizarEntrega; 

@WebServlet("/canalentr")
public class EntregaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String paramAcao = request.getParameter("acao");
        String nomeDoDestino = null;
        Acao acao = null;

        if (paramAcao == null || paramAcao.isEmpty()) {
            paramAcao = "ListarEntregas";
        }

        // Roteamento das ações de entrega
        if (paramAcao.equals("ListarEntregas")) {
            acao = new ListarEntregas();
        } else if (paramAcao.equals("FormNovaEntrega")) {
            acao = new FormNovaEntrega();
        } else if (paramAcao.equals("CadastrarEntrega")) {
            acao = new CadastrarEntrega();
        } else if (paramAcao.equals("FinalizarEntrega")) {
            acao = new FinalizarEntrega();
        } else if (paramAcao.equals("DeletarEntrega")) {
            acao = new DeletarEntrega();
        } else if (paramAcao.equals("EditarEntrega")) {  
            acao = new EditarEntrega();
        } else if (paramAcao.equals("AtualizarEntrega")) { 
            acao = new AtualizarEntrega();
        }

        if (acao != null) {
            try {
                nomeDoDestino = acao.Executa(request, response);

                
                if (nomeDoDestino == null) {
                    nomeDoDestino = "redirect:canalentr?acao=ListarEntregas";
                }

                String[] tipoEEndereco = nomeDoDestino.split(":");

                if (tipoEEndereco.length > 1 && tipoEEndereco[0].equals("redirect")) {
                    response.sendRedirect(tipoEEndereco[1]);
                } else {
                    request.getRequestDispatcher(nomeDoDestino).forward(request, response);
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        }
    }
}