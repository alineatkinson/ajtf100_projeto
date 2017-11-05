import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ItemDAOJDBC implements ComparePriceDAO {
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
			try {
				throw new ConnectionException("Erro na criacao da tabela 'items'", e);
			} catch (ConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public void save(Object objItem) {
		Item item = (Item) objItem;
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
			rs = dbmd.getTables(null, "ALINE", "ITEMS", null);
			if (!rs.next()) {
				this.createTable();
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
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

		// Connection conn = ConnectionManager.getConnection();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("SQL = " + sql);
			// dbmd = conn.getMetaData();
			// rs = dbmd.getSchemas();
			// schemas = rs.toString();
			// System.out.println(schemas);
		} catch (SQLException e) {
			try {
				throw new ConnectionException("Erro na execucao da query " + sql, e);
			} catch (ConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public Object get(Number codebar_item) {
		String sql = "SELECT * FROM items WHERE codebar_item = " + codebar_item;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Item item = null;

		try {

			conn = ConnectionManager.getConnection();
			// System.out.println("PEGOU CONEXÃO");
			stmt = conn.createStatement();
			// System.out.println("CRIOU STATEMENT");
			rs = stmt.executeQuery(sql);
			// System.out.println("EXECUTOU STATEMENT");

			if (rs.next()) {
				System.out.println("ENTROU NO IF DO GET item");
				String name = rs.getString("name");
				String description = rs.getString("description");
				item = new Item((Integer)codebar_item, name, description);
				System.out
						.println("name: " + name + "código de barras: " + codebar_item + "Descrição :" + description);
			}
		} catch (SQLException e) {
			try {
				throw new ConnectionException("Erro na execucao do select: " + sql, e);
			} catch (ConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ConnectionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return item;
	}

	public Map getAll() {
		String sql = "SELECT * FROM items";
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
				String name = rs.getString("name");
				int codebar_item = rs.getInt("codebar_item");
				String description = rs.getString("description");
				items.put(codebar_item, new Item(codebar_item, name, description));
			}
		} catch (SQLException e) {
			try {
				throw new ConnectionException("Erro na execucao do select: " + sql, e);
			} catch (ConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return items;
	}

	public void delete(Number codebar_item) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM items WHERE codebar_item = " + codebar_item;
		int qtdRemovidos = 0;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(sql);
			// stmt.setInt(1, code_supermarket);
			qtdRemovidos = stmt.executeUpdate();
			System.out.println("item excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
		} catch (ConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			String errorMsg = "Erro ao tentar remover item de code_supermarket " + codebar_item;
			try {
				throw new ConnectionException(errorMsg, e);
			} catch (ConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			ConnectionManager.close(conn, stmt);
		}
		System.out.println(" Total de linhas removidas: " + qtdRemovidos);
	}

	public boolean checksExistence(Number code_item) {
		Item itemGet = null;
		itemGet = (Item) this.get(code_item);

		if (itemGet == null) {
			return false;
		} else {
			return true;
		}

	}

}
