package persistence;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.User;

class UserSQLHandler implements SQLHandler<User> {
	private ReadFileProperties rfp = new ReadFileProperties();

	@Override
	public String handle(User user, Boolean exist) throws PersistenceException {

		String sql;

		if (!exist) {
			try {
				sql = rfp.getQuery("insertUser");
			} catch (IOException e) {
				throw new PersistenceException("N�o foi poss�vel obter a propriedade de inclus�o de usu�rio", e);
				// e.printStackTrace();
			}
			sql = sql.replaceFirst("[?]", user.getName());
			sql = sql.replaceFirst("[?]", user.getCpf());

		} else {
			try {
				sql = rfp.getQuery("updateUser");
			} catch (IOException e) {
				throw new PersistenceException("N�o foi poss�vel obter a propriedade de atualiza��o de usu�rio", e);
				// e.printStackTrace();
			}
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
	public String getSelectSQL() throws PersistenceException {
		String sql;
		try {
			sql = rfp.getQuery("selectUser");
		} catch (IOException e) {
			throw new PersistenceException("N�o foi poss�vel obter a propriedade de sele��o de usu�rio", e);
			// e.printStackTrace();
		}
		return sql;
	}

	@Override
	public String getDeleteSQL() throws PersistenceException {
		String sql;
		try {
			sql = rfp.getQuery("deletUser");
		} catch (IOException e) {
			throw new PersistenceException("N�o foi poss�vel obter a propriedade de dele��o de usu�rio", e);
			// e.printStackTrace();
		}
		return sql;
	}

	@Override
	public String getSelectAll() throws PersistenceException {
		String sql;
		try {
			sql = rfp.getQuery("selectAllUsers");
		} catch (IOException e) {
			throw new PersistenceException("N�o foi poss�vel obter a propriedade de dele��o de usu�rio", e);
			// e.printStackTrace();
		}
		return sql;
	}

}
