package acao;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ClienteDAO;
import dao.MercadoriaDAO;
import model.Cliente;
import model.Mercadoria;

public class FormNovaEntrega implements Acao {

	@Override
	public String Executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. Busca os dados no banco
		ClienteDAO cDao = new ClienteDAO();
		List<Cliente> clientes = cDao.listar();

		MercadoriaDAO mDao = new MercadoriaDAO();
		List<Mercadoria> mercadorias = mDao.listar();

		// 2. "Pendura" no request com nomes claros
		request.setAttribute("listaClientes", clientes);
		request.setAttribute("listaMercadorias", mercadorias);

		// 3. Retorna o nome da página JSP
		return "entrega.jsp";
	}
}