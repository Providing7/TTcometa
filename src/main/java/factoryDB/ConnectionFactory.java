
package factoryDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {
    // Configurações de conexão (substitua pelos valores reais)
	private static final String URL = "jdbc:postgresql://localhost:5432/brincadeira";
	private static final String USER = "postgres";
	private static final String PASSWORD = "1234";
//    private static final String URL = "jdbc:postgresql://localhost:5432/seu_banco"; // Exemplo: localhost:5432/meu_db
//    private static final String USER = "seu_usuario";
//    private static final String PASSWORD = "sua_senha";
    // Método para obter uma conexão
    public static Connection getConnection() throws SQLException {
        try {
            // Carrega o driver (opcional em versões recentes do JDBC)
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado", e);
        }
    }
}