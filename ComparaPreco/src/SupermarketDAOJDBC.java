import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SupermarketDAOJDBC implements ComparePriceDAO<Supermarket> {

	public void createTable() {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
		} catch (ConnectionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Statement stmt = null;

		String sql = "CREATE TABLE supermarkets";
		sql += " (code_supermarket  int NOT NULL, ";
		sql += " name varchar(100) NOT NULL, ";
		sql += " cep int NOT NULL)";

		System.out.println("Entrou no create table");

		try {
			stmt = conn.createStatement();
			System.out.println("Criou objeto statement");
			stmt.executeUpdate(sql);
			System.out.println("Tabela 'supermarkets' criada com sucesso");
		} catch (SQLException e) {
			throw new RuntimeException("Erro na criacao da tabela 'supermarkets'", e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public void save(Supermarket supermarket) {
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;
		// DatabaseMetaData dbmd; // apagar
		// String schemas; // apagar

		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
		} catch (ConnectionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		DatabaseMetaData dbmd = null;
		try {
			dbmd = conn.getMetaData();
			rs = dbmd.getTables(null, "ALINE", "SUPERMARKETS", null);
			if (!rs.next()) {
				this.createTable();
			}
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		Supermarket supermarketGet = null;
		supermarketGet = (Supermarket) this.get(supermarket.getCode());

		if (supermarketGet == null) {
			System.out.println("Entrou no if do save");
			sql = "INSERT INTO supermarkets (code_supermarket, name, cep) ";
			sql += "  VALUES (" + supermarket.getCode() + ", ";
			sql += "'" + supermarket.getName() + "', ";
			sql += supermarket.getCEP() + " )";

		} else {
			System.out.println("Entrou no else");
			sql = "UPDATE supermarkets SET name = '" + supermarket.getName() + "', ";
			sql += "cep = " + supermarket.getCEP();
			sql += " WHERE code_supermarket = " + supermarket.getCode();
		}

		// Connection conn = ConnectionManager.getConnection();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("SQL = " + sql);
			// dbmd = conn.getMetaData();
			// rs = dbmd.getSchemas();
			// schemas = rs.toString();
			// System.out.println(schemas);
		} catch (SQLException e) {
			throw new RuntimeException("Erro na execucao da query " + sql, e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}
/*
	public Supermarket antigoget(Number code_supermarket) {
		String sql = "SELECT * FROM supermarkets WHERE code_supermarket = " + code_supermarket;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Supermarket supermarket = null;

		try {

			conn = ConnectionManager.getConnection();
			// System.out.println("PEGOU CONEXÃO");
			stmt = conn.createStatement();
			// System.out.println("CRIOU STATEMENT");
			rs = stmt.executeQuery(sql);
			// System.out.println("EXECUTOU STATEMENT");

			if (rs.next()) {
				System.out.println("ENTROU NO IF DO GET SUPERMAKET");
				String name = rs.getString("name");
				int cepSuper = rs.getInt("cep");
				supermarket = new Supermarket(name, cepSuper, (Integer) code_supermarket);
				System.out
						.println("name: " + name + "Cep Super: " + cepSuper + "Code_supermarket :" + code_supermarket);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro na execucao do select: " + sql, e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return supermarket;
	}
*/
	public Supermarket get(Number code_supermarket) {
		String sql = "SELECT * FROM supermarkets WHERE code_supermarket = " + code_supermarket;
		return (Supermarket) executeQueryMap(sql, new SupermarketRowMapper());
	}


/*
	public Map getAllAntigo() {
		String sql = "SELECT * FROM supermarkets";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Map<Number, Supermarket> supermarkets = new ConcurrentHashMap<>();
		try {
			try {
				conn = ConnectionManager.getConnection();
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				int cepSuper = rs.getInt("cep");
				int code_supermarket = rs.getInt("code_supermarket");
				supermarkets.put(code_supermarket, new Supermarket(name, cepSuper, code_supermarket));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro na execucao do select: " + sql, e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return supermarkets;
	}
	*/

	public Map getAll() {
		String sql = "SELECT * FROM supermarkets";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Map<Number, Supermarket> supermarkets = new ConcurrentHashMap<>();
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int code_supermarket = rs.getInt("code_supermarket");
				supermarkets.put(code_supermarket, this.get(code_supermarket));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro na execucao do select: " + sql, e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return supermarkets;
	}
	
	/*
	public void deleteAntigo(Number code_supermarket) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM supermarkets WHERE code_supermarket = " + code_supermarket;
		int qtdRemovidos = 0;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(sql);
			// stmt.setInt(1, code_supermarket);
			qtdRemovidos = stmt.executeUpdate();
			System.out.println("supermercado excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar remover supermercado de code_supermarket " + code_supermarket, e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
		System.out.println(" Total de linhas removidas: " + qtdRemovidos);
	}
	*/

	public void delete(Number code_supermarket) {
		
		String sql = "DELETE FROM supermarkets WHERE code_supermarket = " + code_supermarket;
		int qtdRemovidos = 0;
	
			qtdRemovidos = this.executeQueryDelete(sql);
			System.out.println("supermercado excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");

	}

	public boolean checksExistence(Number code_supermarket) {

		Supermarket supermarketGet = null;
		supermarketGet = (Supermarket) this.get(code_supermarket);

		if (supermarketGet == null) {
			return false;
		} else {
			return true;
		}

	}


}
