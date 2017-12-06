package persistence;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.User;
import presentation.Printer;

public class UserDAOJDBC extends DAOJDBC implements UserDAO {

	private SQLHandler<User> sh = new UserSQLHandler();
	Printer printer = new Printer();

	/*
	public void createTable(SQLHandler sh) {
		String sql = sh.getCreateTable();
		int qdtEdited = super.executeQuery(sql);
		printer.printMsg("Tabela Users criada com sucesso");
	}
	*/

	public void delete(String cpf) {

		String sql = sh.getDeleteSQL() + cpf + "'";
		int qtdRemovidos = 0;

		qtdRemovidos = executeQuery(sql);
		System.out.println("user excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	@Override
	public void save(User user) {
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
		/*
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
		*/
		String cpf = user.getCpf();
		super.save(user, cpf, sh);
	}

	@Override
	public User get(String cpf) {
		String sql = sh.getSelectSQL();
		return (User) super.get(cpf, sh.getRowMapper(), sql);
	}

	/*
	public void createTable() {
		String sql = sh.getCreateTable();
		int qdtEdited = super.executeQuery(sql);
		printer.printMsg("Tabela users criada com sucesso");
	}
	*/

	public List<User> getAll() {
		String sql = sh.getSelectAll();
		List<User> users = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Entrou no while");
				String cpf = rs.getString("cpf");
				users.add(this.get(cpf));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return users;
	}

	@Override
	public boolean checksExistence(String key) {
		boolean exist = super.checksExistence(key, sh.getRowMapper(), sh.getSelectSQL());
		return exist;
	}

}