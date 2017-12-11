package persistence;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.User;

public class UserSQLHandler implements SQLHandler<User> {
	ReadFileProperties rfp = new ReadFileProperties();

	@Override
	public String handle(User user, Boolean exist) throws IOException {

		String sql;

		if (!exist) {
			sql = rfp.getQuery("insertUser");
			sql = sql.replaceFirst("[?]", user.getName());
			sql = sql.replaceFirst("[?]", user.getCpf());

		} else {
			sql = rfp.getQuery("updateUser");
			sql = sql.replaceFirst("[?]", user.getName());
			sql = sql.replaceFirst("[?]", user.getCpf());
		}

		return sql;
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
