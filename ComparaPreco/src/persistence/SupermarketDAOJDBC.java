package persistence;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Supermarket;
import presentation.Printer;

public class SupermarketDAOJDBC extends ComparePriceDAOJDBC implements ComparePriceDAO<Supermarket> {

	private SQLHandler<model.Supermarket> sh = new SupermarketSQLHandler();
	Printer printer = new Printer();

	/*
	public void createTable() {
		String sql = sh.getCreateTable();
		int qdtEdited = super.executeQuery(sql);
		printer.printMsg("Tabela Supermarkets criada com sucesso");
	}
	*/

	public List<Supermarket> getAll() {
		String sql = sh.getSelectAll();
		List<Supermarket> supermarkets = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int code_supermarket = rs.getInt("code_supermarket");
				supermarkets.add(this.get(code_supermarket));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return supermarkets;
	}

	public void delete(Number codebar_item) {

		String sql = sh.getDeleteSQL() + codebar_item;
		int qtdRemovidos = 0;

		qtdRemovidos = executeQuery(sql);
		System.out.println("supermercado excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	@Override
	public void save(Supermarket object) {
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
		/*
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
		*/
		int code_supermarket = object.getCode();
		super.save(object, code_supermarket, sh);
	}

	@Override
	public Supermarket get(Number key) {
		String sql = sh.getSelectSQL();
		return (Supermarket) super.get(key, sh.getRowMapper(), sql);
	}

	@Override
	public boolean checksExistence(Number key) {
		boolean exist = super.checksExistence(key, sh.getRowMapper(), sh.getSelectSQL());
		return exist;
	}


}
