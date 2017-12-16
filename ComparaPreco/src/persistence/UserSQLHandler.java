package persistence;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.User;

class UserSQLHandler implements SQLHandler<User> {
	private ReadFileProperties rfp = new ReadFileProperties();

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
		return sql;
	}

	@Override
	public String getDeleteSQL() throws IOException {
		String sql = rfp.getQuery("deletUser");
		return sql;
	}

	@Override
	public String getSelectAll() throws IOException {
		String sql = rfp.getQuery("selectAllUsers");
		return sql;
	}

}
