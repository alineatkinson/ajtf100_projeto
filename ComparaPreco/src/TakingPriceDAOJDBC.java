import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TakingPriceDAOJDBC implements ComparePriceDAO<TakingPrice> {

	public void createTable() {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
		} catch (ConnectionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Statement stmt = null;

		String sql = "CREATE TABLE taking_prices";
		sql += " (codebar_item int NOT NULL,";
		sql += " code_supermarket int NOT NULL, ";
		sql += " price DECIMAL(18,2) NOT NULL, ";
		sql += " date timestamp )";

		System.out.println("Entrou no create table");

		try {
			stmt = conn.createStatement();
			System.out.println("Criou objeto statement");
			stmt.executeUpdate(sql);
			System.out.println("Tabela 'taking_prices' criada com sucesso");
		} catch (SQLException e) {
			try {
				throw new ConnectionException("Erro na criacao da tabela 'taking_prices'", e);
			} catch (ConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public void save(TakingPrice taking_price) {
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
			rs = dbmd.getTables(null, "ALINE", "TAKING_PRICES", null);
			if (!rs.next()) {
				this.createTable();
			}
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		TakingPrice tpGet = null;
		tpGet = (TakingPrice) this.get(taking_price.getCodeBarItem());

		Date date = taking_price.getDate();
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = parser.format(date);
		if (tpGet == null) {
			System.out.println("Entrou no if do save");
			sql = "INSERT INTO taking_prices (codebar_item, code_supermarket, price, date) ";
			sql += "  VALUES (" + taking_price.getCodeBarItem() + ", ";
			sql += taking_price.getCodeSupermarket() + ", ";
			sql += taking_price.getPrice() + ", '";
			sql += date1 + "' )";

		} else {
			System.out.println("Entrou no else");
			sql = "UPDATE taking_prices SET code_supermarket = " + taking_price.getCodeSupermarket() + ",";
			sql += " price = " + taking_price.getPrice() + ",";
			sql += " date = '" + date1 + "'";
			sql += " WHERE codebar_item = " + taking_price.getCodeBarItem();
		}

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("SQL = " + sql);

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

	public TakingPrice get(Number codebar_item) {
		String sql = "SELECT * FROM taking_prices WHERE codebar_item = " + codebar_item;
		return (TakingPrice) executeQueryMap(sql, new TakingPriceRowMapper());
	}

	public Map getAll() {
		String sql = "SELECT * FROM taking_prices";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Map<Number, TakingPrice> takingprices = new ConcurrentHashMap<>();
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
				takingprices.put(codebar_item, (TakingPrice) this.get(codebar_item));
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
		return takingprices;
	}

	public void delete(Number codebar_item) {
		String sql = "DELETE FROM taking_prices WHERE codebar_item = " + codebar_item;
		int qtdRemovidos = 0;

		qtdRemovidos = executeQueryDelete(sql);
		System.out.println("taking_prices excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");

	}

	public boolean checksExistence(Number codebar_item) {

		TakingPrice takingPriceGet = null;
		takingPriceGet = (TakingPrice) this.get(codebar_item);

		if (takingPriceGet == null) {
			return false;
		} else {
			return true;
		}

	}

}
