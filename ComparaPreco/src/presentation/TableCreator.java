package presentation;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import persistence.ConnectionException;
import persistence.ConnectionManager;

public class TableCreator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;
		// DatabaseMetaData dbmd; // apagar
		// String schemas; // apagar

		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
		} catch (ConnectionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		DatabaseMetaData dbmd = null;
		try {
			dbmd = conn.getMetaData();
			rs = dbmd.getTables(null, "ALINE", "SUPERMARKETS", null);
			if (!rs.next()) {
				this.createTable();
			}
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

	}

	public void createTable() {
		String sql = sh.getCreateTable();
		int qdtEdited = super.executeQuery(sql);
		printer.printMsg("Tabela Supermarkets criada com sucesso");
	}
}
