package persistence;

import java.io.IOException;

import model.Item;

class ItemSQLHandler implements SQLHandler<Item> {
	ReadFileProperties rfp = new ReadFileProperties();

	@Override
	public String handle(Item item, Boolean exist) throws PersistenceException {

		String sql;

		if (!exist) {

			try {
				sql = rfp.getQuery("insertItem");
			} catch (IOException e) {
				throw new PersistenceException("N�o foi poss�vel obter a propriedade de inclus�o de um item", e);
				// e.printStackTrace();
			}

			sql = sql.replaceFirst("[?]", item.getBarCode() + "");
			sql = sql.replaceFirst("[?]", item.getName());
			sql = sql.replaceFirst("[?]", item.getDescription());

		} else {
			try {
				sql = rfp.getQuery("updateItem");
			} catch (IOException e) {
				throw new PersistenceException("N�o foi poss�vel obter a propriedade de atualiza��o de um item", e);
				// e.printStackTrace();
			}
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
	public String getSelectSQL() throws PersistenceException {
		String sql = null;
		try {
			sql = rfp.getQuery("selectItem");
		} catch (IOException e) {
			throw new PersistenceException("N�o foi poss�vel obter a propriedade de sele��o de um item", e);
			// e.printStackTrace();
		}
		return sql;
	}

	public String getSelectNameSQL() throws PersistenceException {
		String sql;
		try {
			sql = rfp.getQuery("selectItemByName");
		} catch (IOException e) {
			throw new PersistenceException("N�o foi poss�vel a propriedade de um item pelo nome", e);
			// e.printStackTrace();
		}
		return sql;
	}

	@Override
	public String getDeleteSQL() throws PersistenceException {
		String sql;
		try {
			sql = rfp.getQuery("deleteItems");
		} catch (IOException e) {
			throw new PersistenceException("N�o foi poss�vel a propriedade de dele��o de todos itens", e);
			// e.printStackTrace();
		}
		return sql;
	}

	@Override
	public String getSelectAll() throws PersistenceException {
		String sql;
		try {
			sql = rfp.getQuery("selectAllItems");
		} catch (IOException e) {
			throw new PersistenceException("N�o foi poss�vel a propriedade de sele��o de todos itens", e);
			// e.printStackTrace();
		}
		return sql;
	}

}
