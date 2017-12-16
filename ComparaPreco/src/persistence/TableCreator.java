package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class TableCreator {

	static void main(String[] args) throws IOException {
		ReadFileProperties rfp = new ReadFileProperties();
		TableCreator tc = new TableCreator();

		String sql = rfp.getQuery("createTableItems");
		tc.createTable(sql, "ITEMS");

		sql = rfp.getQuery("createTableUsers");
		tc.createTable(sql, "USERS");

		sql = rfp.getQuery("createTableTakingPrices");
		tc.createTable(sql, "TAKING_PRICES");

		sql = rfp.getQuery("createTableSupermarkets");
		tc.createTable(sql, "SUPERMARKETS");

	}

	void createTable(String sql, String nomeTabela) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int qtdRemovidos = 0;

		try {
			conn = ConnectionManager.getConnection();
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet rs = dbmd.getTables(null, "ALINE", nomeTabela, null);
			if (!rs.next()) {

				try {

					stmt = conn.prepareStatement(sql);
					qtdRemovidos = stmt.executeUpdate();
					System.out.println("objeto criado no banco com sucesso!" + qtdRemovidos);

				} catch (SQLException e) {
					throw new RuntimeException("Erro ao executar sql " + sql, e);
				} finally {
					ConnectionManager.close(conn, stmt);
				}
			}
		} catch (SQLException e3) {
			System.out.println("Erro ao tentar conexão!");
			e3.printStackTrace();
		}
	}

}
