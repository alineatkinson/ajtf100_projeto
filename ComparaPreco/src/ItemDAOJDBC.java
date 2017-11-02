import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOJDBC {
	public void createTable() throws ConnectionException {
		Connection conn = ConnectionManager.getConnection();
		Statement stmt = null;

		String sql = "CREATE TABLE items";
        sql += " (codebar_item int NOT NULL,";
        sql += " name varchar(300) NOT NULL, ";
        sql += " description varchar(500)NULL, ";
        sql += " UNIQUE(codebar_item) ";
        sql += " )";
        
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Tabela 'items' criada com sucesso");
		} catch (SQLException e) {
			throw new ConnectionException("Erro na criacao da tabela 'items'",
					e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public void save(Item item) throws ConnectionException {
		Statement stmt = null;
		String sql = null;
		// Se o id for igual a zero o produto ainda não existe no banco,
		// portanto
		// faremos um INSERT caso contrário faremos um UPDATE
		int id = 2;
		if (item.getBarCode() == 0) {
			sql = "INSERT INTO items (codebar_item, name, description) ";
			sql += "  VALUES (" + item.getBarCode() + ", ";
			sql += "'" + item.getName() + "',";
			sql += "'" + item.getDescription() + "' )";
		} else {
			sql = "UPDATE items SET name = '" + item.getName() + "', ";
			sql = "description = '" + item.getDescription()+ "'";
			sql += " WHERE codebar_item = " + item.getBarCode() + ")";
		}
		Connection conn = ConnectionManager.getConnection();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("SQL = " + sql);
		} catch (SQLException e) {
			throw new ConnectionException("Erro na execucao da query " + sql, e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public Item getItem(int codebar_item) throws ConnectionException {
		String sql = "SELECT * FROM items WHERE codebar_item = " + codebar_item;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Item item = null;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				item = new Item(codebar_item, name, description);
			}
		} catch (SQLException e) {
			throw new ConnectionException("Erro na execucao do select: " + sql,
					e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return item;
	}

	public List getAllItems() throws ConnectionException {
		String sql = "SELECT * FROM items";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List items = new ArrayList();
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				int codebar_item = rs.getInt("codebar_item");
				items.add( new Item(codebar_item, name, description));
			}
		} catch (SQLException e) {
			throw new ConnectionException("Erro na execucao do select: " + sql,
					e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return items;
	}
	public int delete(int codebar_item) throws ConnectionException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM items WHERE codebar_item = "+ codebar_item;
		int qtdRemovidos = 0;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, codebar_item);
			qtdRemovidos = stmt.executeUpdate();
		} catch (SQLException e) {
			String errorMsg = "Erro ao tentar remover item de codebar_item " + codebar_item;
			throw new ConnectionException(errorMsg, e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
		return qtdRemovidos;
	}
}
