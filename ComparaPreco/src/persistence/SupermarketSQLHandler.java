package persistence;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Supermarket;

class SupermarketSQLHandler implements SQLHandler<Supermarket> {
	private ReadFileProperties rfp = new ReadFileProperties();

	@Override
	public String handle(Supermarket supermarket, Boolean exist) throws PersistenceException {
		String sql;

		if (!exist) {
			try {
				sql = rfp.getQuery("insertSupermarket");
			} catch (IOException e) {
				throw new PersistenceException("N�o foi poss�vel obter a propriedade de inclus�o de supermercado", e);
				// e.printStackTrace();
			}
			sql = sql.replaceFirst("[?]", supermarket.getCode() + "");
			sql = sql.replaceFirst("[?]", supermarket.getName() + "");
			sql = sql.replaceFirst("[?]", supermarket.getCEP() + "");

		} else {
			try {
				sql = rfp.getQuery("updateSupermarket");
			} catch (IOException e) {
				throw new PersistenceException("N�o foi poss�vel obter a propriedade de atualiza��o de supermercado",
						e);
				// e.printStackTrace();
			}
			sql = sql.replaceFirst("[?]", supermarket.getName() + "");
			sql = sql.replaceFirst("[?]", supermarket.getCEP() + "");
			sql = sql.replaceFirst("[?]", supermarket.getCode() + "");

		}
		return sql.toString();
	}

	@Override
	public RowMapper getRowMapper() {
		RowMapper rm = new SupermarketRowMapper();
		return rm;
	}

	@Override
	public String getSelectSQL() throws PersistenceException {
		String sql = null;
		try {
			sql = rfp.getQuery("selectSupermarket");
		} catch (IOException e) {
			throw new PersistenceException("N�o foi poss�vel obter a propriedade de sele��o de supermercado", e);
			// e.printStackTrace();
		}
		return sql;
	}

	@Override
	public String getDeleteSQL() throws PersistenceException {
		String sql = null;
		try {
			sql = rfp.getQuery("deleteSupermarket");
		} catch (IOException e) {
			throw new PersistenceException("N�o foi poss�vel obter a propriedade de dele��o de supermercado", e);
			// e.printStackTrace();
		}
		return sql;
	}

	@Override
	public String getSelectAll() throws PersistenceException {
		String sql = null;
		try {
			sql = rfp.getQuery("selectAllSupermarkets");
		} catch (IOException e) {
			throw new PersistenceException("N�o foi poss�vel obter a propriedade de sele��o de todos supermercado", e);
			// e.printStackTrace();
		}
		return sql;
	}

}
