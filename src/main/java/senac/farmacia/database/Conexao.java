package senac.farmacia.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
//
	private static final String URL = "jdbc:mysql://localhost:3306/farmacia?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "root";
	private static Connection conexao;

	/**
	 * Chamada singlethon da conexão
	 * 
	 * @return
	 */
	public static Connection getConexao() {
		if (conexao == null) {
			try {
				conexao = DriverManager.getConnection(URL, USER, PASS);

				System.out.println("Conectou...");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conexao;
	}

}

/*
 * private static ConnectionFactory instance;
 * 
 * public static ConnectionFactory getInstance() { // PadrÃ£o Singleton if
 * (instance == null) { instance = new ConnectionFactory(); }
 * 
 * return instance; }
 * 
 * public Connection obterConexao() { // ConfiguraÃ§Ãµes da conexÃ£o String
 * nomeEsquema = "Pousada?serverTimezone=GMT-03:00&useSSL=false"; String
 * enderecoBanco = "jdbc:mysql://localhost/" + nomeEsquema; // String
 * enderecoBanco = "jdbc:postgresql://localhost/" + nomeEsquema; String usuario
 * = "root"; // String usuario = "postgres"; String senha = "admin"; // String
 * senha = "admin"; String driverJDBC = "com.mysql.jdbc.Driver"; // String
 * driverJDBC = "org.postgresql.Driver";
 * 
 * try { Class.forName(driverJDBC); Connection conexao =
 * DriverManager.getConnection(enderecoBanco, usuario, senha);
 * System.out.println("ConexÃ£o aberta"); return conexao; } catch (SQLException
 * | ClassNotFoundException e) {
 * System.out.println("Erro ao obter conexÃ£o com o banco: " + e.getMessage());
 * throw new RuntimeException(e); } }
 * 
 * public static void fecharConexao(Connection con) { try { con.close();
 * System.out.println("ConexÃ£o fechada"); } catch (SQLException e) {
 * System.out.println(e); throw new RuntimeException(e); } } }
 */