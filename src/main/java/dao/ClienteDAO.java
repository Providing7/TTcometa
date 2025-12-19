package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factoryDB.ConnectionFactory;
import model.Cliente;
import model.Endereco;

public class ClienteDAO {

    // C - Inserir
    public int inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, cnpj, endereco_id) VALUES (?,?,?,?)";
        int id = 0;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cliente.getNome());

            if (cliente.getCpf() != null && !cliente.getCpf().trim().isEmpty()) {
                pstmt.setString(2, cliente.getCpf());
            } else {
                pstmt.setNull(2, java.sql.Types.VARCHAR);
            }

            if (cliente.getCnpj() != null && !cliente.getCnpj().trim().isEmpty()) {
                pstmt.setString(3, cliente.getCnpj());
            } else {
                pstmt.setNull(3, java.sql.Types.VARCHAR);
            }

            if (cliente.getEndereco() != null && cliente.getEndereco().getId() > 0) {
                pstmt.setInt(4, cliente.getEndereco().getId());
            } else {
                pstmt.setNull(4, java.sql.Types.INTEGER);
            }

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    // R - Listar Todos
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT c.id AS cliente_id, c.nome, c.cpf, c.cnpj, " +
                     "e.id AS endereco_id, e.rua, e.numero, e.cep, e.bairro " +
                     "FROM cliente c JOIN endereco e ON c.endereco_id = e.id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("cliente_id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setCnpj(rs.getString("cnpj"));

                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("endereco_id"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setCep(rs.getString("cep"));
                endereco.setBairro(rs.getString("bairro"));
                
                cliente.setEndereco(endereco);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao listar clientes", e);
        }
        return clientes;
    }

    // R - Buscar por ID (Necessário para a Acao EditarCliente)
    public Cliente buscaPorId(int id) {
        String sql = "SELECT c.id AS cliente_id, c.nome, c.cpf, c.cnpj, " +
                     "e.id AS endereco_id, e.rua, e.numero, e.cep, e.bairro " +
                     "FROM cliente c JOIN endereco e ON c.endereco_id = e.id " +
                     "WHERE c.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("cliente_id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setCnpj(rs.getString("cnpj"));

                    Endereco endereco = new Endereco();
                    endereco.setId(rs.getInt("endereco_id"));
                    endereco.setRua(rs.getString("rua"));
                    endereco.setNumero(rs.getString("numero"));
                    endereco.setCep(rs.getString("cep"));
                    endereco.setBairro(rs.getString("bairro"));

                    cliente.setEndereco(endereco);
                    return cliente;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID: " + id, e);
        }
        return null;
    }

    // U - Atualizar
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, cnpj = ?, endereco_id = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNome());
            
            // Tratamento de NULL para CPF e CNPJ na atualização
            if (cliente.getCpf() != null && !cliente.getCpf().trim().isEmpty()) {
                pstmt.setString(2, cliente.getCpf());
            } else {
                pstmt.setNull(2, java.sql.Types.VARCHAR);
            }

            if (cliente.getCnpj() != null && !cliente.getCnpj().trim().isEmpty()) {
                pstmt.setString(3, cliente.getCnpj());
            } else {
                pstmt.setNull(3, java.sql.Types.VARCHAR);
            }

            pstmt.setInt(4, cliente.getEndereco().getId());
            pstmt.setInt(5, cliente.getIdCliente());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao atualizar o cliente", e);
        }
    }

    // D - Deletar 
    public void deletar(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao deletar o cliente", e);
        }
    }
}