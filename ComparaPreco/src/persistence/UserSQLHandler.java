package persistence;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.User;

public class UserSQLHandler implements SQLHandler<User> {

	@Override
	public String handle(User user, Boolean exist) {

		StringBuilder sql = new StringBuilder();

		if (!exist) {
			System.out.println("Entrou no if do save");
			sql.append("INSERT INTO users (cpf ,name) ");
			sql.append(" VALUES ('" + user.getCpf() + "',");
			sql.append("'" + user.getName() + "')");

		} else {
			System.out.println("Entrou no else");
			sql.append("UPDATE users SET name = '" + user.getName() + "'");
			sql.append(" WHERE cpf = '" + user.getCpf() + "'");

		}

		return sql.toString();
	}

	@Override
	public RowMapper getRowMapper() {
		RowMapper rm = new UserRowMapper();
		return rm;
	}

	@Override
	public String getSelectSQL() {
		String sql = "SELECT * FROM users WHERE cpf like '";
		return sql;
	}

	@Override
	public String getDeleteSQL() {
		String sql = "DELETE FROM users WHERE cpf like '";
		return sql;
	}

	@Override
	public String getSelectAll() {
		String sql = "SELECT * FROM users";
		return sql;
	}

}
