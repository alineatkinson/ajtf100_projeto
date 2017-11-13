import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ItemDAOJDBC implements ComparePriceDAO<Item> {

	public void createTable() {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
		} catch (ConnectionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Statement stmt = null;

		String sql = "CREATE TABLE items";
		sql += " (codebar_item int NOT NULL,";
		sql += " name varchar(100) NOT NULL, ";
		sql += " description varchar(500))";

		System.out.println("Entrou no create table");

		try {
			stmt = conn.createStatement();
			System.out.println("Criou objeto statement");
			stmt.executeUpdate(sql);
			System.out.println("Tabela 'items' criada com sucesso");
		} catch (SQLException e) {
			throw new RuntimeException("Erro na criacao da tabela 'supermarkets'", e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public void save(Item item) {
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

		Item itemGet = null;
		itemGet = (Item) get(item.getBarCode());

		if (itemGet == null) {
			System.out.println("Entrou no if do save");
			sql = "INSERT INTO items (codebar_item, name, description) ";
			sql += "  VALUES (" + item.getBarCode() + ", ";
			sql += "'" + item.getName() + "',";
			sql += "'" + item.getDescription() + "' )";

		} else {
			System.out.println("Entrou no else");
			sql = "UPDATE items SET name = '" + item.getName() + "', ";
			sql += "description = '" + item.getDescription() + "'";
			sql += " WHERE codebar_item = " + item.getBarCode();

		}

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("SQL = " + sql);

		} catch (SQLException e) {
			throw new RuntimeException("Erro na criacao da tabela 'supermarkets'", e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public Item get(Number codebar_item) {
		String sql = "SELECT * FROM items WHERE codebar_item = " + codebar_item;
		return (Item) executeQueryMap(sql, new ItemRowMapper());
	}

	public Map getAll() {
		String sql = "SELECT * FROM taking_prices";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Map<Number, Item> items = new ConcurrentHashMap<>();
		try {
			try {
				conn = ConnectionManager.getConnection();
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int codebar_item = rs.getInt("codebar_item");
				items.put(codebar_item, (Item) this.get(codebar_item));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro na criacao da tabela 'supermarkets'", e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return items;
	}

	public void delete(Number codebar_item) {
		String sql = "DELETE FROM items WHERE codebar_item = " + codebar_item;
		int qtdRemovidos = 0;

		qtdRemovidos = executeQueryDelete(sql);
		System.out.println("item excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");

	}

	public boolean checksExistence(Number codebar_item) {

		Item itemGet = null;
		itemGet = (Item) this.get(codebar_item);

		if (itemGet == null) {
			return false;
		} else {
			return true;
		}

	}

}
