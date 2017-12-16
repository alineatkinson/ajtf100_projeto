package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.PricesByItem;
import model.TakingPrice;

public class PricesByItemDAO {

	public PricesByItem getPriceByItem(Item item) throws SQLException {

		PricesByItemSQLHandler pbiHandler = new PricesByItemSQLHandler();
		TakingPriceRowMapper tprm = new TakingPriceRowMapper();
		List<TakingPrice> tps = new ArrayList();

		String sql = null;
		//TODO MELHORAR EXCEÇÃO
		try {
			sql = pbiHandler.getSelectSQL(item.getBarCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
