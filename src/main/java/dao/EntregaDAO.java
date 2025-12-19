package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factoryDB.ConnectionFactory;
import model.Entrega;
import model.Mercadoria;
import model.Cliente;
import model.Endereco;

public class EntregaDAO {

    // C - Inserir
    public int inserir(Entrega entrega) {
        String sql = "INSERT INTO entrega (remetente_id, destinatario_id, endereco_entrega_id, mercadoria_id, quantidade, status) VALUES (?,?,?,?,?,?)";
        int id = 0;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, entrega.getRemetente().getIdCliente());
            pstmt.setInt(2, entrega.getDestinatario().getIdCliente());
            pstmt.setInt(3, entrega.getEnderecoEntrega().getId());
            pstmt.setInt(4, entrega.getMercadoria().getIdMercadoria()); 
            pstmt.setInt(5, entrega.getQuantidade());                   
            pstmt.setString(6, entrega.getStatus());                   
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                    entrega.setIdEntrega(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao inserir entrega", e);
        }
        return id;
    }

    // R - Buscar por ID (Para carregar no Editar)
    public Entrega buscarPorId(int id) {
        String sql = "SELECT e.id, e.quantidade, e.status, e.remetente_id, e.destinatario_id, e.mercadoria_id, e.endereco_entrega_id, "
                + "r.nome AS remetente_nome, d.nome AS destinatario_nome, m.nome AS mercadoria_nome, "
                + "a.rua, a.numero, a.cep, a.bairro " 
                + "FROM entrega e "
                + "JOIN cliente r ON e.remetente_id = r.id "
                + "JOIN cliente d ON e.destinatario_id = d.id "
                + "JOIN mercadoria m ON e.mercadoria_id = m.id "
                + "JOIN endereco a ON e.endereco_entrega_id = a.id " 
                + "WHERE e.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Entrega entrega = new Entrega();
                    entrega.setIdEntrega(rs.getInt("id"));
                    entrega.setQuantidade(rs.getInt("quantidade"));
                    entrega.setStatus(rs.getString("status"));

                    Cliente rem = new Cliente();
                    rem.setIdCliente(rs.getInt("remetente_id"));
                    rem.setNome(rs.getString("remetente_nome"));
                    entrega.setRemetente(rem);

                    Cliente dest = new Cliente();
                    dest.setIdCliente(rs.getInt("destinatario_id"));
                    dest.setNome(rs.getString("destinatario_nome"));
                    entrega.setDestinatario(dest);

                    Mercadoria merc = new Mercadoria();
                    merc.setIdMercadoria(rs.getInt("mercadoria_id"));
                    merc.setNome(rs.getString("mercadoria_nome"));
                    entrega.setMercadoria(merc);

                    return entrega;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar entrega", e);
        }
        return null;
    }

    // R - Listar
    public List<Entrega> listar() {
        List<Entrega> entregas = new ArrayList<>();
        String sql = "SELECT e.id, e.quantidade, e.status, r.nome as rem_nome, d.nome as dest_nome, m.nome as merc_nome, m.valor as merc_valor " +
                     "FROM entrega e " +
                     "INNER JOIN cliente r ON e.remetente_id = r.id " + 
                     "INNER JOIN cliente d ON e.destinatario_id = d.id " + 
                     "INNER JOIN mercadoria m ON e.mercadoria_id = m.id";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Entrega entrega = new Entrega();
                entrega.setIdEntrega(rs.getInt("id")); 
                entrega.setQuantidade(rs.getInt("quantidade"));
                entrega.setStatus(rs.getString("status"));
                
                Cliente rem = new Cliente(); rem.setNome(rs.getString("rem_nome"));
                entrega.setRemetente(rem);
                
                Cliente dest = new Cliente(); dest.setNome(rs.getString("dest_nome"));
                entrega.setDestinatario(dest);
                
                Mercadoria merc = new Mercadoria(); merc.setNome(rs.getString("merc_nome"));
                merc.setValor(rs.getDouble("merc_valor"));
                entrega.setMercadoria(merc);
                
                entregas.add(entrega);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar", e);
        }
        return entregas;
    }

    // U - Atualizar (Usado pelo Salvar da Edição)
    public void atualizar(Entrega entrega) {
        String sql = "UPDATE entrega SET remetente_id = ?, destinatario_id = ?, mercadoria_id = ?, quantidade = ?, status = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, entrega.getRemetente().getIdCliente());
            pstmt.setInt(2, entrega.getDestinatario().getIdCliente());
            pstmt.setInt(3, entrega.getMercadoria().getIdMercadoria());
            pstmt.setInt(4, entrega.getQuantidade());
            pstmt.setString(5, entrega.getStatus());
            pstmt.setInt(6, entrega.getIdEntrega());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar", e);
        }
    }

    // D - Deletar
    public void deletar(int id) {
        String sql = "DELETE FROM entrega WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar", e);
        }
    }

    // Finalizar (Ação rápida da lista)
    public void finalizar(int id) {
        String sql = "UPDATE entrega SET status = 'Concluída' WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao finalizar", e);
        }
    }
}