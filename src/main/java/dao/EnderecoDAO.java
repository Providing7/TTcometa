package dao;

import model.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factoryDB.ConnectionFactory;

public class EnderecoDAO {

	// C
	public int inserir(Endereco endereco) {

		String sql = "INSERT INTO endereco (rua, numero, cep, bairro) VALUES (?,?,?,?)";
		int id = 0;

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, endereco.getRua());
			pstmt.setString(2, endereco.getNumero());
			pstmt.setString(3, endereco.getCep());
			pstmt.setString(4, endereco.getBairro());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						id = rs.getInt(1);
						// Define o ID gerado de volta no objeto para uso posterior
						endereco.setId(id);
					}
				}
			}

		} catch (SQLException e) {
			System.err.println("Erro ao inserir endereco: " + e.getMessage());
			e.printStackTrace();
			// Relançar para a camada superior saber que falhou
			throw new RuntimeException("Falha ao inserir endereço", e);
		}
		return id;
	}

	// --- R
	public List<Endereco> listar() {
		List<Endereco> enderecos = new ArrayList<>();

		String sql = "SELECT id, rua, numero, cep, bairro FROM endereco";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {

				Endereco endereco = new Endereco();

				endereco.setId(rs.getInt("id"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setCep(rs.getString("cep"));
				endereco.setBairro(rs.getString("bairro"));

				enderecos.add(endereco);
			}

		} catch (SQLException e) {
			System.err.println("Erro de SQL ao listar os endereços: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Falha ao acessar o BD para listar endereços", e);
		}
		return enderecos;
	}

	// --- U
	public void atualizar(Endereco endereco) {
		String sql = "UPDATE endereco SET rua = ?, numero = ?, cep = ?, bairro = ? WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, endereco.getRua());
			pstmt.setString(2, endereco.getNumero());
			pstmt.setString(3, endereco.getCep());

			pstmt.setString(4, endereco.getBairro());

			pstmt.setInt(5, endereco.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Erro de SQL ao atualizar o endereco: " + e.getMessage());
			e.printStackTrace();

			throw new RuntimeException("Falha ao atualizar o endereco" + e.getMessage());
		}
	}

	// --- D
	public void deletar(int id) {
		// CORREÇÃO: Tabela deve ser 'endereco'
		String sql = "DELETE FROM endereco WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Erro de SQL ao deletar endereco: " + e.getMessage());
			e.printStackTrace();

			throw new RuntimeException("Falha ao deletar o endereco" + e.getMessage());
		}
	}
}