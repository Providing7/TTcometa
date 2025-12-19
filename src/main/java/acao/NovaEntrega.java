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

public class NovaEntrega implements Acao {
	@Override
	public String Executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Busca os dados necessários nos DAOs
		ClienteDAO cDao = new ClienteDAO();
		MercadoriaDAO mDao = new MercadoriaDAO();

		List<Cliente> listaClientes = cDao.listar();
		List<Mercadoria> listaMercadorias = mDao.listar();

		//Pendura as listas na requisição para o JSP enxergar
		request.setAttribute("clientes", listaClientes);
		request.setAttribute("mercadorias", listaMercadorias);

		// Encaminha para o form
		return "entrega.jsp";
	}
}