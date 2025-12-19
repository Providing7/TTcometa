//
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import db.ConnectionFactory; // Importa sua classe de conexão
//
//public class Main {
//
//	public static void main(String[] args) {
//		
//		Connection conn = null;
//		
//		try {
//			// Apenas tenta obter a conexão
//			System.out.println("Iniciando teste de conexão simples...");
//			conn = ConnectionFactory.getConnection(); 
//			
//			// Se chegamos aqui sem exceção, funcionou.
//			if (conn != null) {
//				System.out.println("✅ SUCESSO! Conexão obtida.");
//				System.out.println("Isso prova que o Driver e a URL estão corretos. O problema é o Tomcat/Deployment.");
//			}
//
//		} catch (SQLException e) {
//			// Se cair aqui, a conexão falhou (Driver não encontrado, senha errada, etc.)
//			System.err.println("❌ FALHA na Conexão SQL: " + e.getMessage());
//			e.printStackTrace();
//			
//		} catch (Exception e) {
//			System.err.println("❌ ERRO Geral: " + e.getMessage());
//			
//		} finally {
//			// Fechar a conexão
//			try {
//				if (conn != null) conn.close();
//				System.out.println("Conexão fechada.");
//			} catch (SQLException e) {
//				// Ignora erro ao fechar
//			}
//		}
//	}
//}