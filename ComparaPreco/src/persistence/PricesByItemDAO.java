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

	public PricesByItem getPriceByItem(Item item) throws PersistenceException {

		PricesByItemSQLHandler pbiHandler = new PricesByItemSQLHandler();
		TakingPriceRowMapper tprm = new TakingPriceRowMapper();
		List<TakingPrice> tps = new ArrayList();

		String sql = null;

		try {
			sql = pbiHandler.getSelectSQL(item.getBarCode());
		} catch (PersistenceException e1) {
			e1.printStackTrace();
		}

		ComparePriceDAOJDBC cp = new ComparePriceDAOJDBC();
		ResultSet rs = cp.executeSql(sql);
		Object valor = null;

		try {
			while (rs.next()) {
				valor = tprm.map(rs);
				tps.add((TakingPrice) valor);
			}
		} catch (SQLException e) {
			throw new PersistenceException("Não foi possível mapear o objeto 'price by item' resultante da consulta",
					e);
			// e.printStackTrace();
		}

		PricesByItem pbi = new PricesByItem(item, tps);
		return pbi;
	}

}
