package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

	private static String NOME_BANCO;
	private static String STR_CON;
	private static String USER;
	private static String PASSWORD;

	ConnectionManager(String nameDatabase, String constant, String user, String password) {
		this.NOME_BANCO = nameDatabase;
		this.STR_CON = constant;
		this.USER = user;
		this.PASSWORD = password;
	}

	public static Connection getConnection() throws PersistenceException {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(STR_CON, USER, PASSWORD);
			System.out.println("Conectado 2");
			System.out.println(conn.toString());
			System.out.printf("%s %s %n", conn.getMetaData().getDatabaseProductName(),
					conn.getMetaData().getDatabaseProductVersion());
		} catch (SQLException e) {
			// throw new ConnectionException("Erro ao obter a conexao", e);
			throw new PersistenceException("Erro ao obter a conexao", e);
		}
		return conn;

	}

	public static void close(Connection conn) throws PersistenceException {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new PersistenceException("Não foi fechar a conexão (1)", e);
		}
	}

	public static void close(Connection conn, Statement stmt) throws PersistenceException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new PersistenceException("Não foi fechar a conexão (2)", e);
		}
		ConnectionManager.close(conn);
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) throws PersistenceException {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new PersistenceException("Não foi fechar a conexão (3)", e);
		}
		ConnectionManager.close(conn, stmt);
	}
	/*
	 * public static void close(ResultSet rs) throws PersistenceException { try { if
	 * (rs != null) { rs.close(); } } catch (Exception e) { //e.printStackTrace();
	 * throw new PersistenceException("Não foi fechar a conexão (4)",e); } }
	 */
}
