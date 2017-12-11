package persistence;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Supermarket;

public class SupermarketSQLHandler implements SQLHandler<Supermarket> {
	ReadFileProperties rfp = new ReadFileProperties();

	@Override
	public String handle(Supermarket supermarket, Boolean exist) throws IOException {
		// StringBuilder sql = new StringBuilder();
		String sql;

		if (!exist) {
			System.out.println("Entrou no if do save");
			// sql.append("INSERT INTO supermarkets (code_supermarket, name, cep) ");
			// sql.append(" VALUES (" + supermarket.getCode() + ", ");
			// sql.append("'" + supermarket.getName() + "', ");
			// sql.append(supermarket.getCEP() + " )");
			sql = rfp.getQuery("insertSupermarket");
			sql = sql.replaceFirst("[?]", supermarket.getCode() + "");
			sql = sql.replaceFirst("[?]", supermarket.getName() + "");
			sql = sql.replaceFirst("[?]", supermarket.getCEP() + "");

		} else {
			System.out.println("Entrou no else");
			// sql.append("UPDATE supermarkets SET name = '" + supermarket.getName() + "',
			// ");
			// sql.append("cep = " + supermarket.getCEP());
			// sql.append(" WHERE code_supermarket = " + supermarket.getCode());
			sql = rfp.getQuery("updateSupermarket");
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
	public String getSelectSQL() throws IOException {
		// String sql = "SELECT * FROM supermarkets WHERE code_supermarket = ";
		String sql = rfp.getQuery("selectSupermarket");
		return sql;
	}

	@Override
	public String getDeleteSQL() throws IOException {
		// String sql = "DELETE FROM supermarkets WHERE code_supermarket = ";
		String sql = rfp.getQuery("deleteSupermarket");
		return sql;
	}

	@Override
	public String getSelectAll() throws IOException {
		// String sql = "SELECT * FROM supermarkets";
		String sql = rfp.getQuery("selectAllSupermarkets");
		return sql;
	}

}
