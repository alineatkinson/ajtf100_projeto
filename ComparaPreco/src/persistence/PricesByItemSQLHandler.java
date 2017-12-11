package persistence;

import java.io.IOException;

public class PricesByItemSQLHandler {
	public String getSelectSQL(int codebar_item) throws IOException {
		ReadFileProperties rfp = new ReadFileProperties();
		// String sql = "select tp.codebar_item, tp.code_supermarket, tp.price, tp.date,
		// it.name, it.description from taking_prices as tp";
		// sql += " inner join items as it on it.codebar_item = tp.codebar_item";
		// sql += " where it.codebar_item = " + codebar_item;
		// sql += " order by tp.price ASC";
		String sql;
		sql = rfp.getQuery("selectPryceByItem");
		sql = sql.replaceFirst("[?]", codebar_item + "");

		return sql;
	}

	public RowMapper getRowMapper() {
		RowMapper rm = new TakingPriceRowMapper();
		return rm;
	}
}
