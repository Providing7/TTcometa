package acao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.EntregaDAO;
import model.Entrega;
import model.Cliente;
import model.Endereco;
import model.Mercadoria;

public class CadastrarEntrega implements Acao {

    @Override
    public String Executa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // --- ALTERAÇÃO AQUI: Captura com proteção contra campos vazios/nulos ---
        String paramRemetente = request.getParameter("idRemetente");
        String paramDestinatario = request.getParameter("idDestinatario");
        String paramMercadoria = request.getParameter("idMercadoria");
        String paramQuantidade = request.getParameter("quantidade");

        // Converte apenas se os valores não forem nulos, evitando o NumberFormatException
        int idRemetente = (paramRemetente != null) ? Integer.parseInt(paramRemetente) : 0;
        int idDestinatario = (paramDestinatario != null) ? Integer.parseInt(paramDestinatario) : 0;
        int idMercadoria = (paramMercadoria != null) ? Integer.parseInt(paramMercadoria) : 0;
        int qtd = (paramQuantidade != null) ? Integer.parseInt(paramQuantidade) : 1;
        
        int idEndereco = 1; 

        // Monta o objeto Entrega
        Entrega entrega = new Entrega();
        
        Cliente rem = new Cliente();
        rem.setIdCliente(idRemetente);
        entrega.setRemetente(rem);
        
        Cliente dest = new Cliente();
        dest.setIdCliente(idDestinatario);
        entrega.setDestinatario(dest);
        
        Endereco end = new Endereco();
        end.setId(idEndereco);
        entrega.setEnderecoEntrega(end);

        // Instancia a Mercadoria para evitar NullPointerException
        Mercadoria merc = new Mercadoria();
        merc.setIdMercadoria(idMercadoria);
        entrega.setMercadoria(merc);
        
        entrega.setQuantidade(qtd);
        entrega.setStatus("Não Concluída");

        // Salva no Banco
        EntregaDAO dao = new EntregaDAO();
        dao.inserir(entrega);

        return "canalentr?acao=ListarEntregas";
    }
}