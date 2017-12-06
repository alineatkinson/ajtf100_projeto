package presentation;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import persistence.ConnectionException;
import persistence.ConnectionManager;

public class TableCreator {

	public static void main(String[] args) {
		//Criação tabela Items
		TableCreator tc = new TableCreator();
		String sql = tc.getCreateTableItems();
		tc.createTable(sql);
		
		//Criação tabela Users
		sql = tc.getCreateTableUsers();
		tc.createTable(sql);
		
		// Criação tabela TakingPrices
		sql = tc.getCreateTableTakingPrices();
		tc.createTable(sql);
		
		// Criação tabela Supermarkets
		sql = tc.getCreateTableSupermarkets();
		tc.createTable(sql);
	}

	public void createTable(String sql) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int qtdRemovidos = 0;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(sql);
			// stmt.setInt(1, code_supermarket);
			qtdRemovidos = stmt.executeUpdate();
			System.out.println("objeto excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar remover supermercado de code_supermarket ", e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
		System.out.println(" Total de linhas removidas: " + qtdRemovidos);
	}

	public String getCreateTableItems() {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE items");
		sql.append(" (codebar_item int NOT NULL,");
		sql.append(" name varchar(100) NOT NULL, ");
		sql.append(" description varchar(500))");
		return sql.toString();
	}

	public String getCreateTableUsers() {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE users");
		sql.append(" (cpf varchar(12) NOT NULL,");
		sql.append(" name varchar(300) NOT NULL)");

		return sql.toString();
	}

	public String getCreateTableTakingPrices() {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE taking_prices");
		sql.append(" (codebar_item int NOT NULL,");
		sql.append(" code_supermarket int NOT NULL, ");
		sql.append(" price DECIMAL(18,2) NOT NULL, ");
		sql.append(" date timestamp )");
		return sql.toString();
	}

	public String getCreateTableSupermarkets() {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE supermarkets");
		sql.append(" (code_supermarket  int NOT NULL, ");
		sql.append(" name varchar(100) NOT NULL, ");
		sql.append(" cep int NOT NULL)");
		return sql.toString();
	}
}
