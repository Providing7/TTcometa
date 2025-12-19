package acao;

import dao.ClienteDAO;
import model.Cliente;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaClientes implements Acao{
	
	//agr tenho meu metodo executa, que implementa a interface do Acao
	@Override
	public String Executa(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		System.out.println("Ação listar clientes");
		
		//inicializa o ClienteDao
		ClienteDAO clienteDao = new ClienteDAO();
		
		try {
			List<Cliente> clientes = clienteDao.listar();
			request.setAttribute("clientes", clientes);
			
			return "listaClientes.jsp";
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "erro ao carregar a lista de clientes: " + e.getMessage());
			
			return "menu.jsp";
		}
		
	}
	
}
