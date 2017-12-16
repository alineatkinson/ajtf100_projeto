package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

class UserRowMapper  implements RowMapper {
	public Object map(ResultSet rs) throws SQLException {
		String name = rs.getString("name");
		String cpf = rs.getString("cpf");
		User user = new User(name, cpf);
		System.out.println("name: " + name + "cpf user: " + cpf);
		return user;
	}
}
