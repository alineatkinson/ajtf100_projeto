package persistence;

import model.ItemsPricesBySupermarket;
import model.PricesByItem;
import model.Supermarket;
import model.TakingPrice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Item;

public class ItemsPricesBySupermarketDAO {

	public ItemsPricesBySupermarket getItemsPricesBySupermarket(List<Item> items, Supermarket supermarket)
			throws SQLException {

		ItemsPricesBySupermarketSQLHandler ipbs = new ItemsPricesBySupermarketSQLHandler();
		String sql = null;
		// TODO MELHORAR EXCEÇÃO
		try {
			sql = ipbs.getSelectSQL(items, supermarket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("execução sql :" + sql);

		SQLHandler tphandler = new TakingPriceSQLHandler();
		TakingPriceRowMapper tprm = new TakingPriceRowMapper();
		List<TakingPrice> tps = new ArrayList();

		ComparePriceDAOJDBC cp = new ComparePriceDAOJDBC();
		ResultSet rs = cp.executeSql(sql);
		Object valor = null;

		while (rs.next()) {
			valor = tprm.map(rs);
			tps.add((TakingPrice) valor);
		}

		ItemsPricesBySupermarket ipbi = new ItemsPricesBySupermarket(tps, items, supermarket);
		ipbi.sumPrices();
		return ipbi;

	}
}
