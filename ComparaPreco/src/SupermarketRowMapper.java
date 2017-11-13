import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class SupermarketRowMapper implements RowMapper {

	public Object map(ResultSet rs) throws SQLException {

		String name = rs.getString("name");
		int cepSuper = rs.getInt("cep");
		int code_supermarket = rs.getInt("code_supermarket");
		Supermarket valor = new Supermarket(name, cepSuper, code_supermarket);
		System.out.println("name: " + name + "Cep Super: " + cepSuper + "Code_supermarket :" + code_supermarket);
		return valor;
	}

	/*
	 * public void execute (String sql) { Statement stmt = null; ResultSet rs =
	 * null; Connection conn = null;
	 * 
	 * try { stmt = conn.createStatement(); stmt.executeUpdate(sql);
	 * System.out.println("SQL = " + sql); // dbmd = conn.getMetaData(); // rs =
	 * dbmd.getSchemas(); // schemas = rs.toString(); //
	 * System.out.println(schemas); } catch (SQLException e) { throw new
	 * RuntimeException("Erro na execucao da query " + sql, e); } finally {
	 * ConnectionManager.close(conn, stmt); }
	 * 
	 * }
	 * 
	 */

}
