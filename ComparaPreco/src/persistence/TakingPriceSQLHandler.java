package persistence;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.TakingPrice;

class TakingPriceSQLHandler implements SQLHandler<TakingPrice> {
	private ReadFileProperties rfp = new ReadFileProperties();

	@Override
	public String handle(TakingPrice e, Boolean exist) throws IOException {

		Date date = e.getDate();
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = parser.format(date);
		String sql;

		if (!exist) {
			System.out.println("Entrou no if do save");
			sql = rfp.getQuery("insertTP");
			sql = sql.replaceFirst("[?]", e.getCodeBarItem() + "");
			sql = sql.replaceFirst("[?]", e.getCodeSupermarket() + "");
			sql = sql.replaceFirst("[?]", e.getPrice() + "");
			sql = sql.replaceFirst("[?]", date1);

		} else {
			sql = rfp.getQuery("updateTP");
			sql = sql.replaceFirst("[?]", e.getCodeSupermarket() + "");
			sql = sql.replaceFirst("[?]", e.getPrice() + "");
			sql = sql.replaceFirst("[?]", date1);
			sql = sql.replaceFirst("[?]", e.getCodeBarItem() + "");
			sql = sql.replaceFirst("[?]", e.getCodeSupermarket() + "");

		}
		return sql;
	}

	@Override
	public RowMapper getRowMapper() {
		RowMapper rm = new TakingPriceRowMapper();
		return rm;
	}

	@Override
	public String getSelectSQL() throws IOException {
		String sql = rfp.getQuery("selectTP");
		return sql;
	}

	@Override
	public String getDeleteSQL() throws IOException {
		String sql = rfp.getQuery("deleteTP");
		return sql;
	}

	public String getDeleteSQLTP(Number codebar_item, Number code_supermarket) throws IOException {
		String sql = rfp.getQuery("deleteSQLTP");
		sql = sql.replaceFirst("[?]", codebar_item.toString());
		sql = sql.replaceFirst("[?]", code_supermarket.toString());
		return sql;
	}

	@Override
	public String getSelectAll() throws IOException {
		String sql = rfp.getQuery("selectAllTP");
		return sql;
	}

	public String getSelectSQLTP(Number codebar_item, Number code_supermarket, Date date) throws IOException {
		String sql = rfp.getQuery("selectSQLTP");
		sql = sql.replaceFirst("[?]", codebar_item.toString());
		sql = sql.replaceFirst("[?]", code_supermarket.toString());

		String pattern = "yyyy-mm-dd hh:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		System.out.println("" + simpleDateFormat.format(date));

		sql = sql.replaceFirst("[?]", simpleDateFormat.format(date));
		return sql;
	}
}
