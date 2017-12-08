package persistence;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.User;

public class UserSQLHandler implements SQLHandler<User> {
	ReadFileProperties rfp = new ReadFileProperties();

	@Override
	// TODO VER SE PODE MELHORAR EXCEPTION
	public String handle(User user, Boolean exist) throws IOException {

		StringBuilder sql = new StringBuilder();

		if (!exist) {
			// sql = rfp.getQueryUser("insertUser", user.getName(), user.getCpf());

			System.out.println("Entrou no if do save");
			sql.append("INSERT INTO users (cpf ,name) ");
			sql.append(" VALUES ('" + user.getCpf() + "',");
			sql.append("'" + user.getName() + "')");

		} else {
			// sql = rfp.getQueryUser("updateUser", user.getName(), user.getCpf());

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
	public String getSelectSQL() throws IOException {
		String sql = rfp.getQuery("selectUser");
		// String sql = "SELECT * FROM users WHERE cpf like '";
		return sql;
	}

	@Override
	public String getDeleteSQL() throws IOException {
		String sql = rfp.getQuery("deletUser");
		// String sql = "DELETE FROM users WHERE cpf like '";
		return sql;
	}

	@Override
	public String getSelectAll() throws IOException {
		String sql = rfp.getQuery("selectAllUsers");
		// String sql = "SELECT * FROM users";
		return sql;
	}

}
