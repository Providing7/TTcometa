package acao;

import dao.ClienteDAO;
import dao.EnderecoDAO;
import model.Cliente;
import model.Endereco;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroClientes implements Acao {

	private ClienteDAO clienteDao = new ClienteDAO();
	private EnderecoDAO enderecoDao = new EnderecoDAO();

	private Cliente buildCliente(HttpServletRequest request) {

		// 1. Alteração: Capturar o ID do cliente (0 para novo, > 0 para edição)
		String paramId = request.getParameter("idCliente");
		int idCliente = (paramId != null && !paramId.isEmpty()) ? Integer.parseInt(paramId) : 0;

		Cliente cliente = new Cliente();
		cliente.setIdCliente(idCliente); // Seta o ID no modelo
		cliente.setNome(request.getParameter("nome"));

		String tipoPessoa = request.getParameter("tipoPessoa");
		if ("PF".equals(tipoPessoa)) {
			cliente.setCpf(request.getParameter("cpf"));
			cliente.setCnpj(null);
		} else if ("PJ".equals(tipoPessoa)) {
			cliente.setCnpj(request.getParameter("cnpj"));
			cliente.setCpf(null);
		}

		Endereco endereco = new Endereco();
		endereco.setRua(request.getParameter("rua"));
		endereco.setNumero(request.getParameter("numero")); 
		endereco.setCep(request.getParameter("cep"));
		endereco.setBairro(request.getParameter("bairro"));

		try {
			if (idCliente > 0) {
				// Alteração: Se for edição, precisamos recuperar o ID do endereço original
				Cliente clienteNoBanco = clienteDao.buscaPorId(idCliente);
				endereco.setId(clienteNoBanco.getEndereco().getId());
				enderecoDao.atualizar(endereco); // Você deve ter esse método no EnderecoDAO
			} else {
				// Se for novo, insere e recupera o ID gerado
				int idEnderecoGerado = enderecoDao.inserir(endereco);
				endereco.setId(idEnderecoGerado);
			}
		} catch (Exception e) {
			throw new RuntimeException("Falha ao processar o endereço: " + e.getMessage(), e);
		}

		cliente.setEndereco(endereco);
		return cliente;
	}

	@Override
	public String Executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String mensagem = "";

		try {
			Cliente cliente = buildCliente(request);

			// Alteração: Lógica de decisão entre INSERT e UPDATE
			if (cliente.getIdCliente() > 0) {
				clienteDao.atualizar(cliente);
				mensagem = URLEncoder.encode("Cliente atualizado com sucesso!", "UTF-8");
			} else {
				clienteDao.inserir(cliente);
				mensagem = URLEncoder.encode("Cliente cadastrado com sucesso!", "UTF-8");
			}

			String urlRedirecionamento = request.getContextPath() + "/canalclie?acao=ListaClientes&sucesso=" + mensagem;
			response.sendRedirect(urlRedirecionamento);
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			mensagem = URLEncoder.encode(
					"Falha no processamento: " + (e.getMessage() != null ? e.getMessage() : "Erro desconhecido."),
					"UTF-8");

			response.sendRedirect(request.getContextPath() + "/canalclie?acao=ListaClientes&erro=" + mensagem);
			return null;
		}
	}
}