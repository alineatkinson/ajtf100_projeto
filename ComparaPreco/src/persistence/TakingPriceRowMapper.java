package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.TakingPrice;

public class TakingPriceRowMapper implements RowMapper {

	public Object map(ResultSet rs) throws SQLException {

		double price = rs.getDouble("price");
		int code_supermarket = rs.getInt("code_supermarket");
		int codebar_item = rs.getInt("codebar_item");
		Date date = rs.getTimestamp("date");
		TakingPrice valor = new TakingPrice(codebar_item, price, code_supermarket, date);
		System.out.println("cod_item: " + codebar_item + "code_supermarket: " + code_supermarket + "price :" + price
				+ "date :" + date);

		return valor;
	}
}