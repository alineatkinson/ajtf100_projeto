package persistence;

import java.util.List;

import model.Item;
import model.Supermarket;

public class ItemsPricesBySupermarketSQLHandler {

	public String getSelectSQL(List<Item> items, Supermarket supermarket) {

		System.out.println(" tamanho de items: " + items.size());
		if (items != null & supermarket != null) {
			String codes = "";
			int size = items.size();

			while (size >= 1) {
				for (Item item : items) {
					System.out.println("\n nome item:  " + item.getName());

					if (size == 1) {
						codes += item.getBarCode() + "";
						System.out.println("codes 1: " + codes);
						size -= 1;
					}
					if (size > 1) {
						codes += item.getBarCode() + ", ";
						System.out.println("codes 2: " + codes);
						size -= 1;
					}

				}
			}

			int code_supermarket = supermarket.getCode();

			String sql = "SELECT distinct tp.codebar_item, tp.code_supermarket, tp.price, tp.date, it.name, it.description";
			sql += " FROM taking_prices AS tp";
			sql += " INNER JOIN items AS it ON it.codebar_item = tp.codebar_item";
			sql += " inner join supermarkets as sp on sp.code_supermarket = tp.code_supermarket";
			sql += " where it.codebar_item in ( " + codes + ") and tp.code_supermarket = " + code_supermarket;
			// sql += " GROUP BY tp.code_supermarket";
			return sql;
		} else
			return null;

	}

	public RowMapper getRowMapper() {
		RowMapper rm = new TakingPriceRowMapper();
		return rm;
	}
}
