package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.PricesByItem;
import model.TakingPrice;

public class PriceByItemDAO {

	/*
	 * public PricesByItem getPriceByItem(Item item) { PricesByItem pbi = new
	 * PricesByItem(); PricesByItemSQLHandler pbiHandler = new
	 * PricesByItemSQLHandler(); TakingPriceRowMapper tprm = new
	 * TakingPriceRowMapper(); List<TakingPrice> tps = new ArrayList();
	 * 
	 * String sql = pbiHandler.getSelectSQL(item.getBarCode());
	 * 
	 * Connection conn = null; Statement stmt = null; ResultSet rs = null; Object
	 * valor = null;
	 * 
	 * try {
	 * 
	 * conn = ConnectionManager.getConnection(); //
	 * System.out.println("PEGOU CONEXÃO"); stmt = conn.createStatement(); //
	 * System.out.println("CRIOU STATEMENT"); rs = stmt.executeQuery(sql); //
	 * System.out.println("EXECUTOU STATEMENT");
	 * 
	 * pbi.setItem(item); while (rs.next()) { valor = tprm.map(rs);
	 * tps.add((TakingPrice) valor); } pbi.setTakingPrices(tps);
	 * 
	 * } catch (SQLException e) { throw new
	 * RuntimeException("Erro na execucao do select: " + sql, e); } finally {
	 * ConnectionManager.close(conn, stmt, rs); }
	 * 
	 * return pbi; }
	 */

	public PricesByItem getPriceByItem(Item item) throws SQLException {

		PricesByItemSQLHandler pbiHandler = new PricesByItemSQLHandler();
		TakingPriceRowMapper tprm = new TakingPriceRowMapper();
		List<TakingPrice> tps = new ArrayList();

		String sql = pbiHandler.getSelectSQL(item.getBarCode());

		ComparePriceDAOJDBC cp = new ComparePriceDAOJDBC();
		ResultSet rs = cp.executeSql(sql);
		Object valor = null;

		while (rs.next()) {
			valor = tprm.map(rs);
			tps.add((TakingPrice) valor);
		}

		PricesByItem pbi = new PricesByItem(item, tps);
		return pbi;
	}

}
