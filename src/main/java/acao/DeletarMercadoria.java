package acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MercadoriaDAO;

public class DeletarMercadoria implements Acao {

	@Override
	public String Executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Pega o ID que vem da URL (canalmerc?acao=DeletarMercadoria&id=X)
		String paramId = request.getParameter("id");
		
		if (paramId != null && !paramId.isEmpty()) {
			int id = Integer.parseInt(paramId);

			// Chama o DAO para deletar do banco
			MercadoriaDAO dao = new MercadoriaDAO();
			dao.deletar(id);
			
			System.out.println(">>> LOG: Mercadoria ID " + id + " deletada com sucesso.");
		}

		// Redireciona para a lista para atualizar a tela
		// Uso o redirect: para evitar que o F5 tente deletar de novo
		return "redirect:canalmerc?acao=ListarMercadorias";
	}
}