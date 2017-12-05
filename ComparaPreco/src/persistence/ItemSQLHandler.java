package persistence;

import model.Item;

public class ItemSQLHandler implements SQLHandler<Item> {

	@Override
	public String handle(Item item, Boolean exist) {

		StringBuilder sql = new StringBuilder();

		if (!exist) {
			System.out.println("Entrou no if do save");
			sql.append("INSERT INTO items (codebar_item, name, description) ");
			sql.append("  VALUES (" + item.getBarCode() + ", ");
			sql.append("'" + item.getName() + "',");
			sql.append("'" + item.getDescription() + "' )");

		} else {
			System.out.println("Entrou no else");
			sql.append("UPDATE items SET name = '" + item.getName() + "', ");
			sql.append("description = '" + item.getDescription() + "'");
			sql.append(" WHERE codebar_item = " + item.getBarCode());
		}
		return sql.toString();
	}

	@Override
	public RowMapper getRowMapper() {
		RowMapper rm = new ItemRowMapper();
		return rm;
	}

	@Override
	public String getSelectSQL() {
		String sql = "SELECT * FROM items WHERE codebar_item = ";
		return sql;
	}
	
	public String getSelectNameSQL() {
		String sql = "SELECT * FROM items WHERE name = '";
		return sql;
	}

	@Override
	public String getDeleteSQL() {
		String sql = "DELETE FROM items WHERE codebar_item = ";
		return sql;
	}

	@Override
	public String getCreateTable() {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE items");
		sql.append(" (codebar_item int NOT NULL,");
		sql.append(" name varchar(100) NOT NULL, ");
		sql.append(" description varchar(500))");
		return sql.toString();
	}

	@Override
	public String getSelectAll() {
		String sql = "SELECT * FROM items";
		return sql;
	}

}
