import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TakingPriceDAOJDBC extends ComparePriceDAOJDBC implements ComparePriceDAO<TakingPrice> {
	private SQLHandler<TakingPrice> sh = new TakingPriceSQLHandler();
	Printer printer = new Printer();

	public void createTable() {
		String sql = sh.getCreateTable();
		int qdtEdited = super.executeQuery(sql);
		printer.printMsg("Tabela TakingPrices criada com sucesso");
	}

	public List<TakingPrice> getAll() {
		String sql = sh.getSelectAll();
		List<TakingPrice> takingprices = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int codebar_item = rs.getInt("codebar_item");
				takingprices.add(this.get(codebar_item));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return takingprices;
	}

	public void delete(Number codebar_item) {

		String sql = sh.getDeleteSQL() + codebar_item;
		int qtdRemovidos = 0;

		qtdRemovidos = executeQuery(sql);
		System.out.println("taking_prices excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	@Override
	public void save(TakingPrice object) {
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
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		int codebar_item = object.getCodeBarItem();
		super.save(object, codebar_item, sh);
	}

	@Override
	public TakingPrice get(Number key) {
		String sql = sh.getSelectSQL() + key;
		return (TakingPrice) super.get(key, sh.getRowMapper(), sql);
	}

	@Override
	public boolean checksExistence(Number key) {
		boolean exist = super.checksExistence(key, sh.getRowMapper(), sh.getSelectSQL());
		return exist;
	}

}