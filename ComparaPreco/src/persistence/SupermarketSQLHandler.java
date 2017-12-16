package persistence;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Supermarket;

class SupermarketSQLHandler implements SQLHandler<Supermarket> {
	private ReadFileProperties rfp = new ReadFileProperties();

	@Override
	public String handle(Supermarket supermarket, Boolean exist) throws IOException {
		String sql;

		if (!exist) {
			sql = rfp.getQuery("insertSupermarket");
			sql = sql.replaceFirst("[?]", supermarket.getCode() + "");
			sql = sql.replaceFirst("[?]", supermarket.getName() + "");
			sql = sql.replaceFirst("[?]", supermarket.getCEP() + "");

		} else {
			sql = rfp.getQuery("updateSupermarket");
			sql = sql.replaceFirst("[?]", supermarket.getName() + "");
			sql = sql.replaceFirst("[?]", supermarket.getCEP() + "");
			sql = sql.replaceFirst("[?]", supermarket.getCode() + "");

		}
		return sql.toString();
	}

	@Override
	public RowMapper getRowMapper() {
		RowMapper rm = new SupermarketRowMapper();
		return rm;
	}

	@Override
	public String getSelectSQL() throws IOException {
		String sql = rfp.getQuery("selectSupermarket");
		return sql;
	}

	@Override
	public String getDeleteSQL() throws IOException {
		String sql = rfp.getQuery("deleteSupermarket");
		return sql;
	}

	@Override
	public String getSelectAll() throws IOException {
		String sql = rfp.getQuery("selectAllSupermarkets");
		return sql;
	}

}
