package persistence;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.TakingPrice;

class TakingPriceSQLHandler implements SQLHandler<TakingPrice> {
	private ReadFileProperties rfp = new ReadFileProperties();

	@Override
	public String handle(TakingPrice e, Boolean exist) throws PersistenceException {

		Date date = e.getDate();
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = parser.format(date);
		String sql = null;

		if (!exist) {
			System.out.println("Entrou no if do save");
			try {
				sql = rfp.getQuery("insertTP");
			} catch (IOException e1) {
				throw new PersistenceException("Não foi possível obter a propriedade de inclusão de tomada de preço", e1);
				// e1.printStackTrace();
			}
			sql = sql.replaceFirst("[?]", e.getCodeBarItem() + "");
			sql = sql.replaceFirst("[?]", e.getCodeSupermarket() + "");
			sql = sql.replaceFirst("[?]", e.getPrice() + "");
			sql = sql.replaceFirst("[?]", date1);

		} else {
			try {
				sql = rfp.getQuery("updateTP");
			} catch (IOException e1) {
				throw new PersistenceException("Não foi possível obter a propriedade de atualizaçaõ de tomada de preço", e1);
				// e1.printStackTrace();
			}
			sql = sql.replaceFirst("[?]", e.getCodeSupermarket() + "");
			sql = sql.replaceFirst("[?]", e.getPrice() + "");
			sql = sql.replaceFirst("[?]", date1);
			sql = sql.replaceFirst("[?]", e.getCodeBarItem() + "");
			sql = sql.replaceFirst("[?]", e.getCodeSupermarket() + "");

		}
		return sql;
	}

	@Override
	public RowMapper getRowMapper() {
		RowMapper rm = new TakingPriceRowMapper();
		return rm;
	}

	@Override
	public String getSelectSQL() throws PersistenceException {
		String sql;
		try {
			sql = rfp.getQuery("selectTP");
		} catch (IOException e) {
			throw new PersistenceException("Não foi possível obter a propriedade de seleção de tomada de preço(1)", e);
			// e.printStackTrace();
		}
		return sql;
	}

	@Override
	public String getDeleteSQL() throws PersistenceException {
		String sql;
		try {
			sql = rfp.getQuery("deleteTP");
		} catch (IOException e) {
			throw new PersistenceException("Não foi possível obter a propriedade de seleção de tomada de preço(1)", e);
			// e.printStackTrace();
		}
		return sql;
	}

	public String getDeleteSQLTP(Number codebar_item, Number code_supermarket) throws PersistenceException {
		String sql;
		try {
			sql = rfp.getQuery("deleteSQLTP");
		} catch (IOException e) {
			throw new PersistenceException("Não foi possível obter a propriedade de deleção de tomada de preço(2)", e);
			// e.printStackTrace();
		}
		sql = sql.replaceFirst("[?]", codebar_item.toString());
		sql = sql.replaceFirst("[?]", code_supermarket.toString());
		return sql;
	}

	@Override
	public String getSelectAll() throws PersistenceException {
		String sql;
		try {
			sql = rfp.getQuery("selectAllTP");
		} catch (IOException e) {
			throw new PersistenceException(
					"Não foi possível obter a propriedade de seleção de todas as tomadas de preço", e);
			// e.printStackTrace();
		}
		return sql;
	}

	public String getSelectSQLTP(Number codebar_item, Number code_supermarket, Date date) throws PersistenceException {
		String sql;
		try {
			sql = rfp.getQuery("selectSQLTP");
		} catch (IOException e) {
			throw new PersistenceException("Não foi possível obter a propriedade de seleção de tomada de preço", e);
			// e.printStackTrace();
		}
		sql = sql.replaceFirst("[?]", codebar_item.toString());
		sql = sql.replaceFirst("[?]", code_supermarket.toString());

		String pattern = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		System.out.println("" + simpleDateFormat.format(date));

		sql = sql.replaceFirst("[?]", simpleDateFormat.format(date));
		return sql;
	}
}
