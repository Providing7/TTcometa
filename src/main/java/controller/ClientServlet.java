package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import acao.Acao;
import acao.CadastroClientes;
import acao.EditarCliente;  
import acao.DeletarCliente;  
import acao.FormNovoCliente;
import acao.ListaClientes;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/canalclie")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClientServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("CHEGADA SERVLET");
		
		String paramAcao = request.getParameter("acao");
		String nomeDoDestino = null;
		Acao acao = null;

		if (paramAcao == null || paramAcao.isEmpty()) {
			paramAcao = "ListaClientes";
		}

		// ROTEAMENTO: Mapeia o texto da URL para a Classe Java
		if (paramAcao.equals("ListaClientes")) {
			acao = new ListaClientes();
		} else if (paramAcao.equals("CadastraClientes")) {
			acao = new CadastroClientes();
		} else if (paramAcao.equals("FormNovoCliente")) {
			acao = new FormNovoCliente();
		} else if (paramAcao.equals("EditarCliente")) {
			acao = new EditarCliente();
		} else if (paramAcao.equals("DeletarCliente")) {
			acao = new DeletarCliente();
		}

		// EXECUÇÃO E DESPACHO
		if (acao != null) {
			try {
				//A ação executa a lógica de banco e retorna uma String
				nomeDoDestino = acao.Executa(request, response);

				if (nomeDoDestino != null) {
					
					// Verifica se a String retornada contém o prefixo "redirect:"
					String[] tipoEEndereco = nomeDoDestino.split(":");

					if (tipoEEndereco[0].equals("redirect")) {
						// REDIRECT: Avisa o navegador para mudar de URL
						// Pega o que está depois do ":" (tipoEEndereco[1])
						response.sendRedirect(tipoEEndereco[1]);
					} else {
						//Apenas abre o arquivo JSP internamente
						RequestDispatcher rd = request.getRequestDispatcher(nomeDoDestino);
						rd.forward(request, response);
					}
				}
				
			} catch (Exception e) {
				throw new ServletException("Erro ao executar a ação " + paramAcao, e);
			}
		} else {
			// Se a ação não bater com nenhum 'if' acima
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ação não encontrada: " + paramAcao);
		}
	}
}