package persistence;

import java.io.IOException;

import model.Item;

class ItemSQLHandler implements SQLHandler<Item> {
	ReadFileProperties rfp = new ReadFileProperties();

	@Override
	public String handle(Item item, Boolean exist) throws IOException {

		String sql;

		if (!exist) {
			sql = rfp.getQuery("insertItem");
			sql = sql.replaceFirst("[?]", item.getBarCode() + "");
			sql = sql.replaceFirst("[?]", item.getName());
			sql = sql.replaceFirst("[?]", item.getDescription());

		} else {
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
		String sql = rfp.getQuery("selectItem");
		return sql;
	}

	public String getSelectNameSQL() throws IOException {
		String sql = rfp.getQuery("selectItemByName");
		return sql;
	}

	@Override
	public String getDeleteSQL() throws IOException {
		String sql = rfp.getQuery("deleteItems");
		return sql;
	}

	@Override
	public String getSelectAll() throws IOException {
		String sql = rfp.getQuery("selectAllItems");
		return sql;
	}

}
