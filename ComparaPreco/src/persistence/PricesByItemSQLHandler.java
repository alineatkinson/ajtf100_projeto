package persistence;

import java.io.IOException;

class PricesByItemSQLHandler {
	String getSelectSQL(int codebar_item) throws IOException {
		ReadFileProperties rfp = new ReadFileProperties();
		String sql;
		sql = rfp.getQuery("selectPryceByItem");
		sql = sql.replaceFirst("[?]", codebar_item + "");

		return sql;
	}

	RowMapper getRowMapper() {
		RowMapper rm = new TakingPriceRowMapper();
		return rm;
	}
}
