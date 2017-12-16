package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import model.Supermarket;

class SupermarketRowMapper implements RowMapper {

	public Supermarket map(ResultSet rs) throws SQLException {

		String name = rs.getString("name");
		int cepSuper = rs.getInt("cep");
		int code_supermarket = rs.getInt("code_supermarket");
		Supermarket valor = new Supermarket(name, cepSuper, code_supermarket);
		System.out.println("name: " + name + "Cep Super: " + cepSuper + "Code_supermarket :" + code_supermarket);
		return valor;
	}

}
