package persistence;

import java.io.IOException;

import model.Item;

public class ItemSQLHandler implements SQLHandler<Item> {
	ReadFileProperties rfp = new ReadFileProperties();

	@Override
	public String handle(Item item, Boolean exist) throws IOException {

		// StringBuilder sql = new StringBuilder();
		String sql;

		if (!exist) {
			// System.out.println("Entrou no if do save");
			// sql.append("INSERT INTO items (codebar_item, name, description) ");
			// sql.append(" VALUES (" + item.getBarCode() + ", ");
			// sql.append("'" + item.getName() + "',");
			// sql.append("'" + item.getDescription() + "' )");
			sql = rfp.getQuery("insertItem");
			sql = sql.replaceFirst("[?]", item.getBarCode() + "");
			sql = sql.replaceFirst("[?]", item.getName());
			sql = sql.replaceFirst("[?]", item.getDescription());

		} else {
			// System.out.println("Entrou no else");
			//sql.append("UPDATE items SET name = '" + item.getName() + "', ");
			//sql.append("description = '" + item.getDescription() + "'");
			//sql.append(" WHERE codebar_item = " + item.getBarCode());
			sql = rfp.getQuery("updateItem");
			sql = sql.replaceFirst("[?]", item.getName());
			sql = sql.replaceFirst("[?]", item.getDescription());
			sql = sql.replaceFirst("[?]", item.getBarCode() + "");

		}
		return sql;
	}

	@Override
	public RowMapper getRowMapper() {
		RowMapper rm = new ItemRowMapper();
		return rm;
	}

	@Override
	public String getSelectSQL() throws IOException {
		// String sql = "SELECT * FROM items WHERE codebar_item = ";
		String sql = rfp.getQuery("selectItem");
		return sql;
	}

	public String getSelectNameSQL() throws IOException {
		// String sql = "SELECT * FROM items WHERE name = '";
		String sql = rfp.getQuery("selectItemByName");
		return sql;
	}

	@Override
	public String getDeleteSQL() throws IOException {
		// String sql = "DELETE FROM items WHERE codebar_item = ";
		String sql = rfp.getQuery("deleteItems");
		return sql;
	}

	@Override
	public String getSelectAll() throws IOException {
		// String sql = "SELECT * FROM items";
		String sql = rfp.getQuery("selectAllItems");
		return sql;
	}

}
