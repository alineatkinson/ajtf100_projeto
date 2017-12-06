package persistence;

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
	Printer printer = new Printer();

	/*
	public void createTable() {
		String sql = sh.getCreateTable();
		int qdtEdited = super.executeQuery(sql);
		printer.printMsg("Tabela TakingPrices criada com sucesso");
	}
	*/

	public List<Item> getAll() {
		String sql = sh.getSelectAll();
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

		String sql = sh.getDeleteSQL() + codebar_item;
		int qtdRemovidos = 0;

		qtdRemovidos = executeQuery(sql);
		System.out.println("item exclu�do do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	@Override
	// pq n�o consigo colocar o par�metro como item?
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
		try {
			dbmd = conn.getMetaData();
			rs = dbmd.getTables(null, "ALINE", "ITEMS", null);
			if (!rs.next()) {
				this.createTable();
			}
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		*/

		int codebar_item = item.getBarCode();
		super.save(item, codebar_item, sh);
	}

	@Override
	public Item get(Number key) {
		String sql = sh.getSelectSQL();
		return (Item) super.get(key, sh.getRowMapper(), sql);
	}
	
	public Item get(String name) {
		String sql = sh.getSelectNameSQL();
		return (Item) super.get(name, sh.getRowMapper(), sql);
	}

	@Override
	public boolean checksExistence(Number key) {
		boolean exist = super.checksExistence(key, sh.getRowMapper(), sh.getSelectSQL());
		return exist;
	}

	public boolean checksExistence(String name) {
		boolean exist = super.checksExistence(name, sh.getRowMapper(), sh.getSelectNameSQL());
		return exist;
	}


}