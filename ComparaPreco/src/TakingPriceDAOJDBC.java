import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TakingPriceDAOJDBC implements ComparePriceDAO {

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
		sql += " date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)";

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

	public void save(Object objTP) {
		TakingPrice taking_price = (TakingPrice) objTP;
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
			rs = dbmd.getTables(null, "ALINE", "taking_prices", null);
			if (!rs.next()) {
				this.createTable();
			}
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		TakingPrice tpGet = null;
		tpGet = (TakingPrice) this.get(taking_price.getCodeBarItem());

		if (tpGet == null) {
			System.out.println("Entrou no if do save");
			sql = "INSERT INTO taking_prices (codebar_item, code_supermarket, price, date) ";
			sql += "  VALUES (" + taking_price.getCodeBarItem() + ", ";
			sql += taking_price.getCodeSupermarket() + ", ";
			sql += taking_price.getPrice() + ", '";
			sql += taking_price.getDate() + "' )";

		} else {
			System.out.println("Entrou no else");
			sql = "UPDATE taking_prices SET code_supermarket = " + taking_price.getCodeSupermarket() + ",";
			sql += " price = '" + taking_price.getPrice() + "',";
			sql += " date = '" + taking_price.getDate() + "'";
			sql += " WHERE codebar_item = " + taking_price.getCodeBarItem();
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
		String sql = "SELECT * FROM taking_prices WHERE codebar_item = " + codebar_item;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		TakingPrice taking_price = null;

		try {

			conn = ConnectionManager.getConnection();
			// System.out.println("PEGOU CONEXÃO");
			stmt = conn.createStatement();
			// System.out.println("CRIOU STATEMENT");
			rs = stmt.executeQuery(sql);
			// System.out.println("EXECUTOU STATEMENT");

			if (rs.next()) {
				System.out.println("ENTROU NO IF DO GET TakingPrice");
				double price = rs.getDouble("price");
				int code_supermarket = rs.getInt("code_supermarket");
				Timestamp date = rs.getTimestamp("date");
				taking_price = new TakingPrice((Integer)codebar_item, price, code_supermarket, null);
				System.out
						.println("cod_item: " + codebar_item + "code_supermarket: " + code_supermarket + "price :" + price + "date :" + date );
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
		return taking_price;
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
				double price = rs.getDouble("price");
				int code_supermarket = rs.getInt("code_supermarket");
				Timestamp date = rs.getTimestamp("date");
				takingprices.put(codebar_item, new TakingPrice(codebar_item, price, code_supermarket, null));
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
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM taking_prices WHERE codebar_item = " + codebar_item;
		int qtdRemovidos = 0;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(sql);
			// stmt.setInt(1, code_supermarket);
			qtdRemovidos = stmt.executeUpdate();
			System.out.println("taking_price excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
		} catch (ConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			String errorMsg = "Erro ao tentar remover taking_price de codebar_item " + codebar_item;
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