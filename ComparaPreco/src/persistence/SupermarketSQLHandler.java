package persistence;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Supermarket;

public class SupermarketSQLHandler implements SQLHandler<Supermarket> {

	@Override
	public String handle(Supermarket supermarket, Boolean exist) {
		StringBuilder sql = new StringBuilder();

		if (!exist) {
			System.out.println("Entrou no if do save");
			sql.append("INSERT INTO supermarkets (code_supermarket, name, cep) ");
			sql.append("  VALUES (" + supermarket.getCode() + ", ");
			sql.append("'" + supermarket.getName() + "', ");
			sql.append(supermarket.getCEP() + " )");

		} else {
			System.out.println("Entrou no else");
			sql.append("UPDATE supermarkets SET name = '" + supermarket.getName() + "', ");
			sql.append("cep = " + supermarket.getCEP());
			sql.append(" WHERE code_supermarket = " + supermarket.getCode());
		}
		return sql.toString();
	}

	@Override
	public RowMapper getRowMapper() {
		RowMapper rm = new SupermarketRowMapper();
		return rm;
	}

	@Override
	public String getSelectSQL() {
		String sql = "SELECT * FROM supermarkets WHERE code_supermarket = ";
		return sql;
	}

	@Override
	public String getDeleteSQL() {
		String sql = "DELETE FROM supermarkets WHERE code_supermarket = ";
		return sql;
	}

	@Override
	public String getCreateTable() {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE supermarkets");
		sql.append(" (code_supermarket  int NOT NULL, ");
		sql.append(" name varchar(100) NOT NULL, ");
		sql.append(" cep int NOT NULL)");
		return sql.toString();
	}

	@Override
	public String getSelectAll() {
		String sql = "SELECT * FROM supermarkets";
		return sql;
	}

}
