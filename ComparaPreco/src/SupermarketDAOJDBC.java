import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupermarketDAOJDBC {

	public void createTable() throws ConnectionException {
		Connection conn = ConnectionManager.getConnection();
		Statement stmt = null;

		String sql = "CREATE TABLE supermarkets";
        sql += " (code_supermarket  int NOT NULL, ";
        sql += " name varchar(100) NOT NULL, ";
		sql += " cep int NOT NULL)";
		//sql += " UNIQUE(code_supermarket) ";
		//sql += " )";
	System.out.println("Entrou no create table");
		//String sql = "CREATE TABLE supermarkets (code_supermarket int, 9cep int)";
	/*
String sql = "CREATE TABLE produtoss";
sql += " (id int NOT NULL,";
sql += " nome varchar(20) NOT NULL, ";
sql += " preco int NOT NULL, ";
sql += " UNIQUE(id) ";
sql += " )";
*/

		try {
			stmt = conn.createStatement();
			System.out.println("Criou objeto statement");
			stmt.executeUpdate(sql);
			System.out.println("Tabela 'supermarkets' criada com sucesso");
		} catch (SQLException e) {
			throw new ConnectionException("Erro na criacao da tabela 'supermarkets'",
					e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public void save(Supermarket supermarket) throws ConnectionException {
		Statement stmt = null;
		String sql = null;
		// Se o id for igual a zero o produto ainda não existe no banco,
		// portanto
		// faremos um INSERT caso contrário faremos um UPDATE
		/*
		int id = 2;
		if (supermarket.getCode() == 0) {
			sql = "INSERT INTO supermarkets (code_supermarket, name, cep) ";
			sql += "  VALUES (" + supermarket.getCode() + ", ";
			sql += "'" + supermarket.getName() + "', ";
			sql += supermarket.getCEP() + " )";
		} else {
			sql = "UPDATE supermarkets SET name = '" + supermarket.getName() + "', ";
			sql = "cep = " + supermarket.getCEP();
			sql += " WHERE code_supermarket = " + supermarket.getCode() + ")";
		}
		*/
		sql = "INSERT INTO supermarkets (code_supermarket, name, cep) VALUES (123,'Angeloni',88117)";
		Connection conn = ConnectionManager.getConnection();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("SQL = " + sql);
		} catch (SQLException e) {
			throw new ConnectionException("Erro na execucao da query " + sql, e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public Supermarket getSupermarket(int code_supermarket) throws ConnectionException {
		String sql = "SELECT * FROM supermarkets WHERE code_supermarket = " + code_supermarket;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Supermarket supermarket = null;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("name");
				int cepSuper = rs.getInt("cep");
				supermarket = new Supermarket (name, cepSuper, code_supermarket);
			}
		} catch (SQLException e) {
			throw new ConnectionException("Erro na execucao do select: " + sql,
					e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return supermarket;
	}

	public List getAllSupermarkets() throws ConnectionException {
		String sql = "SELECT * FROM supermarkets";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List supermarkets = new ArrayList();
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				int cepSuper = rs.getInt("cep");
				int code_supermarket = rs.getInt("code_supermarket");
				supermarkets.add( new Supermarket(name, cepSuper, code_supermarket));
			}
		} catch (SQLException e) {
			throw new ConnectionException("Erro na execucao do select: " + sql,
					e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return supermarkets;
	}
	public int delete(int code_supermarket) throws ConnectionException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM supermarkets WHERE code_supermarket = "+ code_supermarket;
		int qtdRemovidos = 0;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, code_supermarket);
			qtdRemovidos = stmt.executeUpdate();
		} catch (SQLException e) {
			String errorMsg = "Erro ao tentar remover supermercado de code_supermarket " + code_supermarket;
			throw new ConnectionException(errorMsg, e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
		return qtdRemovidos;
	}
}
