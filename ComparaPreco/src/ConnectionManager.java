import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	private static final String NOME_BANCO = "pricecompator;create=true";
	private static final String STR_CON = "jdbc:derby://localhost:1527/" + NOME_BANCO;
	private static final String USER = "aline";
	private static final String PASSWORD = "aline";

	public static Connection getConnection() throws ConnectionException {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(STR_CON, USER, PASSWORD);
			System.out.println("Conectado 2");
			System.out.println(conn.toString());
			System.out.printf("%s %s %n", conn.getMetaData().getDatabaseProductName(),
					conn.getMetaData().getDatabaseProductVersion());
		} catch (SQLException e) {
			throw new ConnectionException("Erro ao obter a conexao", e);
		}
		return conn;

	}

	// código omitido idêntico ao exemplo anterior
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionManager.close(conn);
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionManager.close(conn, stmt);
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
