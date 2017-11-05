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

public class UserDAOJDBC implements UserDAO {
	public void createTable() {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
		} catch (ConnectionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Statement stmt = null;

		String sql = "CREATE TABLE users";
        sql += " (cpf varchar(12) NOT NULL,";
        sql += " name varchar(300) NOT NULL)";
        
		System.out.println("Entrou no create table");

		try {
			stmt = conn.createStatement();
			System.out.println("Criou objeto statement");
			stmt.executeUpdate(sql);
			System.out.println("Tabela 'users' criada com sucesso");
		} catch (SQLException e) {
			try {
				throw new ConnectionException("Erro na criacao da tabela 'users'", e);
			} catch (ConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public void save(Object objUser) {
		User user = (User) objUser;
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
			rs = dbmd.getTables(null, "ALINE", "USERS", null);
			if (!rs.next()) {
				this.createTable();
			}
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		User userGet = null;
		userGet = (User) get(user.getCpf());

		if (userGet == null) {
			System.out.println("Entrou no if do save");
			sql = "INSERT INTO users (cpf ,name) ";
			sql += "  VALUES ('" + user.getCpf() + "', ";
			sql += "'" + user.getName() + "' )";
		} else {
			System.out.println("Entrou no else");
			sql = "UPDATE users SET name = '" + user.getName() + "', ";
			sql += " WHERE cpf = '" + user.getCpf() + "'";
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
			try {
				throw new ConnectionException("Erro na execucao da query " + sql, e);
			} catch (ConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public Object get(String cpfUser) {
		String sql = "SELECT * FROM users WHERE cpf like '" + cpfUser + "'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		User user = null;

		try {

			conn = ConnectionManager.getConnection();
			// System.out.println("PEGOU CONEXÃO");
			stmt = conn.createStatement();
			// System.out.println("CRIOU STATEMENT");
			rs = stmt.executeQuery(sql);
			// System.out.println("EXECUTOU STATEMENT");

			if (rs.next()) {
				System.out.println("ENTROU NO IF DO GET user");
				String name = rs.getString("name");
				user = new User(name, cpfUser);
				System.out.println("name: " + name + "cpf user: " + cpfUser);
			}
		} catch (SQLException e) {
			try {
				throw new ConnectionException("Erro na execucao do select: " + sql, e);
			} catch (ConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ConnectionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return user;
	}

	public Map getAll() {
		String sql = "SELECT * FROM users";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Map<String, User> users = new ConcurrentHashMap<>();
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
				String cpf = rs.getString("cpf");
				users.put(cpf, new User(name, cpf));
			}
		} catch (SQLException e) {
			try {
				throw new ConnectionException("Erro na execucao do select: " + sql, e);
			} catch (ConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return users;
	}

	public void delete(String cpfUser) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM users WHERE cpf like '" + cpfUser + "'";
		int qtdRemovidos = 0;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(sql);
			// stmt.setInt(1, code_supermarket);
			qtdRemovidos = stmt.executeUpdate();
			System.out.println("user excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
		} catch (ConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			String errorMsg = "Erro ao tentar remover user de cpf " + cpfUser;
			try {
				throw new ConnectionException(errorMsg, e);
			} catch (ConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			ConnectionManager.close(conn, stmt);
		}
		System.out.println(" Total de linhas removidas: " + qtdRemovidos);
	}

	public boolean checksExistence(String cpfUser) {

		User userGet = null;
		userGet = (User) this.get(cpfUser);

		if (userGet == null) {
			return false;
		} else {
			return true;
		}

	}

}
