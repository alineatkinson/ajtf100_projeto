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
		// TODO melhorar exceção
		String sql = null;
		try {
			sql = sh.getDeleteSQL() + cpf + "'";
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		int qtdRemovidos = 0;

		try {
			qtdRemovidos = executeQuery(sql);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		System.out.println("user excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	@Override
	public void save(User user) throws PersistenceException {
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;

		Connection conn = null;
		try {
			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
		} catch (ConnectionException e2) {
			throw new PersistenceException("Não foi possível conectar ao banco de dados ao salvar o usuário", e2);
			// e2.printStackTrace();
		}
		DatabaseMetaData dbmd = null;

		String cpf = user.getCpf();
		super.save(user, cpf, sh);
	}

	@Override
	public User get(String cpf) {
		// TODO melhorar exceção
		String sql = null;
		try {
			sql = sh.getSelectSQL();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return (User) super.get(cpf, sh.getRowMapper(), sql);
	}

	public List<User> getAll() throws PersistenceException {
		// TODO melhorar exceção
		String sql = null;
		try {
			sql = sh.getSelectAll();
		} catch (PersistenceException e1) {
			e1.printStackTrace();
		}
		List<User> users = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Entrou no while");
				String cpf = rs.getString("cpf");
				users.add(this.get(cpf));
			}
		} catch (SQLException e) {
			throw new PersistenceException("Não foi possível executar o sql de seleção de todos usuários", e);
			//e.printStackTrace();
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return users;
	}

	@Override
	public boolean checksExistence(String key) {
		// TODO melhorar exceção
		boolean exist = false;
		try {
			exist = super.checksExistence(key, sh.getRowMapper(), sh.getSelectSQL());
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return exist;
	}

}