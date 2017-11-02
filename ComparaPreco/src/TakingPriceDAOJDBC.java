import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TakingPriceDAOJDBC {

	public void createTable() throws ConnectionException {
		Connection conn = ConnectionManager.getConnection();
		Statement stmt = null;

		String sql = "CREATE TABLE taking_prices";
        sql += " (codebar_item int NOT NULL,";
        sql += " code_supermarket int NOT NULL, ";
		sql += " price DECIMAL(18,2) NOT NULL, ";
		sql += " date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP" ;
        sql += " UNIQUE(codebar_item, date) ";
        sql += " )";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Tabela 'taking_prices' criada com sucesso");
		} catch (SQLException e) {
			throw new ConnectionException("Erro na criacao da tabela 'taking_prices'", e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public void save(TakingPrice taking_price) throws ConnectionException {
		Statement stmt = null;
		String sql = null;
		// Se o id for igual a zero o produto ainda não existe no banco,
		// portanto
		// faremos um INSERT caso contrário faremos um UPDATE
		int id = 2;
		if (taking_price.getCodeBarItem() == 0) {
			sql = "INSERT INTO taking_prices (codebar_item, code_supermarket, price, date) ";
			sql += "  VALUES (" + taking_price.getCodeBarItem() + ", ";
			sql += taking_price.getCodeSupermarket() + ", ";
			sql += taking_price.getPrice() + ", ";
			sql += taking_price.getDate() + "' )";
		} else {
			sql = "UPDATE taking_prices SET code_supermarket = '" + taking_price.getCodeSupermarket() + "', ";
			sql = "price = " + taking_price.getPrice() + ",";
			sql = "date = '" + taking_price.getDate() + "'";
			sql += " WHERE codebar_item = " + taking_price.getCodeBarItem()+ ")";
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

	public TakingPrice getTakingPrice(int codebar_item) throws ConnectionException {
		String sql = "SELECT * FROM items WHERE codebar_item = " + codebar_item;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		TakingPrice taking_price = null;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int code_supermarket = rs.getInt("code_supermarket");
				//Date dateD = rs.getDate("date");
				//Calendar cal;
				Timestamp dateT = rs.getTimestamp("date");
				LocalDateTime date = dateT.toLocalDateTime();
				taking_price = new TakingPrice(codebar_item, price, code_supermarket, date);
			}
		} catch (SQLException e) {
			throw new ConnectionException("Erro na execucao do select: " + sql, e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return taking_price;
	}
	//arrumar para takin price
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
				items.add(new Item(codebar_item, name, description));
			}
		} catch (SQLException e) {
			throw new ConnectionException("Erro na execucao do select: " + sql, e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return items;
	}

	public int delete(int codebar_item) throws ConnectionException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM items WHERE codebar_item = " + codebar_item;
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
