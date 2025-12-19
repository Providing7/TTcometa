package dao;

import model.Mercadoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import factoryDB.ConnectionFactory;

public class MercadoriaDAO {

    // C
    public void inserir(Mercadoria mercadoria) {
        String sql = "INSERT INTO mercadoria (nome, peso, valor, volume) VALUES (?,?,?,?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, mercadoria.getNome());
            pstmt.setDouble(2, mercadoria.getPeso());
            pstmt.setDouble(3, mercadoria.getValor());
            pstmt.setDouble(4, mercadoria.getVolume());
            
            pstmt.executeUpdate(); 
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Falha ao inserir mercadoria: " + e.getMessage());
        }
    }

    // R
    public List<Mercadoria> listar() {
        List<Mercadoria> mercadorias = new ArrayList<>();
        // No Banco é "id", no Java vai para "idMercadoria"
        String sql = "SELECT id, nome, peso, valor, volume FROM mercadoria";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Mercadoria mercadoria = new Mercadoria();
                mercadoria.setIdMercadoria(rs.getInt("id")); // Nome da coluna no banco
                mercadoria.setNome(rs.getString("nome"));
                mercadoria.setPeso(rs.getDouble("peso"));
                mercadoria.setValor(rs.getDouble("valor"));
                mercadoria.setVolume(rs.getDouble("volume"));
                mercadorias.add(mercadoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Falha ao listar mercadoria", e);
        }
        return mercadorias;
    }

    // READ - Buscar por ID
    public Mercadoria buscarPorId(int id) {
        String sql = "SELECT id, nome, peso, valor, volume FROM mercadoria WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Mercadoria m = new Mercadoria();
                    m.setIdMercadoria(rs.getInt("id"));
                    m.setNome(rs.getString("nome"));
                    m.setPeso(rs.getDouble("peso"));
                    m.setValor(rs.getDouble("valor"));
                    m.setVolume(rs.getDouble("volume"));
                    return m;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar mercadoria por ID", e);
        }
        return null;
    }

    // U
    public void atualizar(Mercadoria mercadoria) {
        String sql = "UPDATE mercadoria SET nome = ?, peso = ?, valor = ?, volume = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, mercadoria.getNome());
            pstmt.setDouble(2, mercadoria.getPeso());
            pstmt.setDouble(3, mercadoria.getValor());
            pstmt.setDouble(4, mercadoria.getVolume());
            pstmt.setInt(5, mercadoria.getIdMercadoria());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Falha ao atualizar mercadoria: " + e.getMessage());
        }
    }

    // D
    public void deletar(int id) {
        String sql = "DELETE FROM mercadoria WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Falha ao deletar mercadoria: " + e.getMessage());
        }
    }
}