package persistence;

import java.io.IOException;
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

	public void delete(String cpf) {
		//TODO melhorar exceção
		String sql = null;
		try {
			sql = sh.getDeleteSQL() + cpf + "'";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int qtdRemovidos = 0;

		qtdRemovidos = executeQuery(sql);
		System.out.println("user excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	@Override
	public void save(User user) {
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;

		Connection conn = null;
		try {
			conn = ConnectionManager.getConnection();
		} catch (ConnectionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		DatabaseMetaData dbmd = null;

		String cpf = user.getCpf();
		super.save(user, cpf, sh);
	}

	@Override
	public User get(String cpf) {
		//TODO melhorar exceção
		String sql = null;
		try {
			sql = sh.getSelectSQL();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (User) super.get(cpf, sh.getRowMapper(), sql);
	}

	public List<User> getAll() {
		//TODO melhorar exceção
		String sql = null;
		try {
			sql = sh.getSelectAll();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		//TODO melhorar exceção
		boolean exist = false;
		try {
			exist = super.checksExistence(key, sh.getRowMapper(), sh.getSelectSQL());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exist;
	}

}