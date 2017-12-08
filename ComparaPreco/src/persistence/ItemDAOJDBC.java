package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import presentation.Printer;

import model.Item;

public class ItemDAOJDBC extends ComparePriceDAOJDBC implements ComparePriceByNameDAO<Item> {
	private ItemSQLHandler sh = new ItemSQLHandler();
	private Printer printer = new Printer();

	/*
	 * public void createTable() { String sql = sh.getCreateTable(); int qdtEdited =
	 * super.executeQuery(sql);
	 * printer.printMsg("Tabela TakingPrices criada com sucesso"); }
	 */

	public List<Item> getAll() {
		// TODO MELHORAR EXCEÇÃO
		String sql = null;
		try {
			sql = sh.getSelectAll();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Item> items = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = ConnectionManager.getConnection();
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.print(rs.toString());

			while (rs.next()) {
				int codebar_item = rs.getInt("codebar_item");
				System.out.println(codebar_item);
				items.add(this.get(codebar_item));
			}
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return items;
	}

	public void delete(Number codebar_item) {
		// TODO MELHORAR EXCEÇÃO
		String sql = null;
		try {
			sql = sh.getDeleteSQL() + codebar_item;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int qtdRemovidos = 0;

		qtdRemovidos = executeQuery(sql);
		System.out.println("item excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	@Override
	// pq não consigo colocar o parâmetro como item?
	public void save(Object object) {
		Item item = (Item) object;
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;

		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
		} catch (ConnectionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		DatabaseMetaData dbmd = null;

		/*
		 * try { dbmd = conn.getMetaData(); rs = dbmd.getTables(null, "ALINE", "ITEMS",
		 * null); if (!rs.next()) { this.createTable(); } } catch (SQLException e3) { //
		 * TODO Auto-generated catch block e3.printStackTrace(); }
		 */

		int codebar_item = item.getBarCode();
		super.save(item, codebar_item, sh);
	}

	@Override
	public Item get(Number key) {
		// TODO MELHORAR EXCEÇÃO
		String sql = null;
		try {
			sql = sh.getSelectSQL();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Item) super.get(key, sh.getRowMapper(), sql);
	}

	public Item get(String name) {
		// TODO MELHORAR EXCEÇÃO
		String sql = null;
		try {
			sql = sh.getSelectNameSQL();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Item) super.get(name, sh.getRowMapper(), sql);
	}

	@Override
	public boolean checksExistence(Number key) {
		// TODO MELHORAR EXCEÇÃO
		boolean exist = false;
		try {
			exist = super.checksExistence(key, sh.getRowMapper(), sh.getSelectSQL());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exist;
	}

	public boolean checksExistence(String name) {
		// TODO MELHORAR EXCEÇÃO
		boolean exist = false;
		try {
			exist = super.checksExistence(name, sh.getRowMapper(), sh.getSelectNameSQL());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exist;
	}

}