package persistence;

import model.ItemsPricesBySupermarket;
import model.PricesByItem;
import model.Supermarket;
import model.TakingPrice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Item;

public class ItemsPricesBySupermarketDAO {

	public ItemsPricesBySupermarket getItemsPricesBySupermarket(List<Item> items, Supermarket supermarket) {

		ItemsPricesBySupermarketSQLHandler ipbs = new ItemsPricesBySupermarketSQLHandler();
		String sql = ipbs.getSelectSQL(items, supermarket);
		System.out.println("execução sql :" + sql);

		SQLHandler tphandler = new TakingPriceSQLHandler();
		TakingPriceRowMapper tprm = new TakingPriceRowMapper();
		List<TakingPrice> tps = new ArrayList();

		ItemsPricesBySupermarket ipbi = new ItemsPricesBySupermarket();
		ipbi.setItems(items);
		ipbi.setSupermarket(supermarket);

		// String sqlTP = tphandler.getSelectSQL();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Object valor = null;

		// for (Item item : items) {
		// sql += item.getBarCode();

		try {
			conn = ConnectionManager.getConnection();
			// System.out.println("PEGOU CONEXÃO");
			stmt = conn.createStatement();
			// System.out.println("CRIOU STATEMENT");
			rs = stmt.executeQuery(sql);
			// System.out.println("EXECUTOU STATEMENT");

			while (rs.next()) {
				valor = tprm.map(rs);
				tps.add((TakingPrice) valor);
			}
			ipbi.setTakingPrices(tps);
			ipbi.sumPrices();

		} catch (SQLException e) {
			throw new RuntimeException("Erro na execucao do select: " + sql, e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		// }
		return ipbi;

	}
}
