package persistence;

import java.io.IOException;
import java.util.List;

import model.Item;
import model.Supermarket;

public class ItemsPricesBySupermarketSQLHandler {

	public String getSelectSQL(List<Item> items, Supermarket supermarket) throws IOException {

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
			String sql;
			ReadFileProperties rfp = new ReadFileProperties();

			sql = rfp.getQuery("selectItemPriceBysupermarket");
			sql = sql.replaceFirst("[?]", codes.toString());
			sql = sql.replaceFirst("[?]", code_supermarket + "");
			return sql;
		} else
			return null;

	}

	public RowMapper getRowMapper() {
		RowMapper rm = new TakingPriceRowMapper();
		return rm;
	}
}
