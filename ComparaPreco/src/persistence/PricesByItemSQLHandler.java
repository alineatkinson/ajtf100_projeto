package persistence;

import java.io.IOException;

class PricesByItemSQLHandler {
	String getSelectSQL(int codebar_item) throws PersistenceException {
		ReadFileProperties rfp = new ReadFileProperties();
		String sql;
		try {
			sql = rfp.getQuery("selectPryceByItem");
		} catch (IOException e) {
			throw new PersistenceException("Não foi possível obter a propriedade de seleção de preços por item", e);
			// e.printStackTrace();
		}
		sql = sql.replaceFirst("[?]", codebar_item + "");

		return sql;
	}

	RowMapper getRowMapper() {
		RowMapper rm = new TakingPriceRowMapper();
		return rm;
	}
}
