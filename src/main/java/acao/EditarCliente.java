package acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ClienteDAO;
import model.Cliente;

public class EditarCliente implements Acao {

	@Override
	public String Executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Pega o ID que veio no link
		String paramId = request.getParameter("id");
		int id = Integer.parseInt(paramId);

		// Busca o cliente no banco para carregar o formulário
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.buscaPorId(id);

		// Coloca o cliente no request para o JSP ler
		request.setAttribute("cliente", cliente);

		// Manda para o formulário (o mesmo de cadastro)
		return "cliente.jsp";
	}
}