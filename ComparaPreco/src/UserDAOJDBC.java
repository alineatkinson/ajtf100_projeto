import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJDBC {
	public void createTable() throws ConnectionException {
		Connection conn = ConnectionManager.getConnection();
		Statement stmt = null;

		String sql = "CREATE TABLE users";
        sql += " (cpf varchar(12) NOT NULL,";
        sql += " name varchar(300) NOT NULL, ";
        sql += " UNIQUE(cpf) ";
        sql += " )";
        
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Tabela 'users' criada com sucesso");
		} catch (SQLException e) {
			throw new ConnectionException("Erro na criacao da tabela 'users'",
					e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public void save(User user) throws ConnectionException {
		Statement stmt = null;
		String sql = null;
		// Se o id for igual a zero o produto ainda não existe no banco,
		// portanto
		// faremos um INSERT caso contrário faremos um UPDATE
		int id = 2;
		if (user.getCpf().equals(0)) {
			sql = "INSERT INTO users (cpf ,name) ";
			sql += "  VALUES ('" + user.getCpf() + "', ";
			sql += "'" + user.getName() + "' )";
		} else {
			sql = "UPDATE users SET nome = '" + user.getName() + "', ";
			sql += " WHERE cpf = '" + user.getCpf() + "', ";
		}
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

	public User getUser(int id) throws ConnectionException {
		String sql = "SELECT * FROM users WHERE id = " + id;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("name");
				String cpf = rs.getString("cpf");
				user = new User(name, cpf);
			}
		} catch (SQLException e) {
			throw new ConnectionException("Erro na execucao do select: " + sql,
					e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return user;
	}

	public List getAllUsers() throws ConnectionException {
		String sql = "SELECT * FROM users";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List users = new ArrayList();
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				String cpf = rs.getString("cpf");
				users.add(new User(name, cpf));
			}
		} catch (SQLException e) {
			throw new ConnectionException("Erro na execucao do select: " + sql,
					e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return users;
	}
	public int delete(int id) throws ConnectionException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM produtos WHERE cpf = '"+ id + "'";
		int qtdRemovidos = 0;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			qtdRemovidos = stmt.executeUpdate();
		} catch (SQLException e) {
			String errorMsg = "Erro ao tentar remover usu�rio de cpf " + id;
			throw new ConnectionException(errorMsg, e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
		return qtdRemovidos;
	}
}
