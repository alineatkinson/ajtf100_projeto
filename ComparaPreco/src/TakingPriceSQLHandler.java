import java.text.SimpleDateFormat;
import java.util.Date;

public class TakingPriceSQLHandler implements SQLHandler<TakingPrice> {

	@Override
	public String handle(TakingPrice e, Boolean exist) {

		StringBuilder sql = new StringBuilder();
		Date date = e.getDate();
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = parser.format(date);

		if (exist) {
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
		}
		return sql.toString();
	}

	@Override
	public RowMapper getRowMapper() {
		RowMapper rm = new TakingPriceRowMapper();
		return rm;
	}

	@Override
	public String getSelectSQL() {
		String sql = "SELECT * FROM taking_prices WHERE codebar_item = ";
		return sql;
	}

	@Override
	public String getDeleteSQL() {
		String sql = "DELETE FROM taking_prices WHERE codebar_item = ";
		return sql;
	}

	@Override
	public String getCreateTable() {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE taking_prices");
		sql.append(" (codebar_item int NOT NULL,");
		sql.append(" code_supermarket int NOT NULL, ");
		sql.append(" price DECIMAL(18,2) NOT NULL, ");
		sql.append(" date timestamp )");
		return sql.toString();
	}

	@Override
	public String getSelectAll() {
		StringBuilder sql = null;
		sql.append("SELECT * FROM taking_prices");
		return sql.toString();
	}

}
