package persistence;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.TakingPrice;

public class TakingPriceSQLHandler implements SQLHandler<TakingPrice> {
	ReadFileProperties rfp = new ReadFileProperties();

	@Override
	public String handle(TakingPrice e, Boolean exist) {

		StringBuilder sql = new StringBuilder();
		Date date = e.getDate();
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = parser.format(date);

		if (!exist) {
			System.out.println("Entrou no if do save");
			sql.append("INSERT INTO taking_prices (codebar_item, code_supermarket, price, date) ");
			sql.append("  VALUES (" + e.getCodeBarItem() + ", ");
			sql.append(e.getCodeSupermarket() + ", ");
			sql.append(e.getPrice() + ", '");
			sql.append(date1 + "' )");

		} else {
			System.out.println("Entrou no else");
			sql.append("UPDATE taking_prices SET code_supermarket = " + e.getCodeSupermarket() + ",");
			sql.append(" price = " + e.getPrice() + ",");
			sql.append(" date = '" + date1 + "'");
			sql.append(" WHERE codebar_item = " + e.getCodeBarItem());
			sql.append(" and code_supermarket = " + e.getCodeSupermarket());
		}
		System.out.println(sql.toString());
		return sql.toString();
	}

	@Override
	public RowMapper getRowMapper() {
		RowMapper rm = new TakingPriceRowMapper();
		return rm;
	}

	@Override
	public String getSelectSQL() throws IOException {
		// String sql = "SELECT * FROM taking_prices WHERE codebar_item = ";
		String sql = rfp.getQuery("selectTP");
		return sql;
	}

	@Override
	public String getDeleteSQL() throws IOException {
		// String sql = "DELETE FROM taking_prices WHERE codebar_item = ";
		String sql = rfp.getQuery("deleteTP");
		return sql;
	}

	public String getDeleteSQLTP(Number codebar_item, Number code_supermarket) throws IOException {
		String sql = "DELETE FROM taking_prices WHERE codebar_item = "+codebar_item+" and code_supermarket = "+code_supermarket;
		//String sql = rfp.getQueryTP("deleteSQLTP", codebar_item, code_supermarket);
		return sql;
	}

	@Override
	public String getSelectAll() throws IOException {
		//String sql = "SELECT * FROM taking_prices";
		String sql = rfp.getQuery("selectAllTP");
		System.out.println(sql);
		return sql;
	}

	public String getSelectSQLTP(Number codebar_item, Number code_supermarket) throws IOException {
		// String sql = "SELECT * FROM taking_prices WHERE codebar_item = " +
		// codebar_item + " and code_supermarket = " + code_supermarket;
		String sql = rfp.getQueryTP("selectSQLTP", codebar_item, code_supermarket);
		return sql;
	}
}
