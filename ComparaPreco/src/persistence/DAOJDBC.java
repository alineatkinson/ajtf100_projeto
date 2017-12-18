package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOJDBC<E, K> {

	public void save(E e, K k, SQLHandler ir) throws PersistenceException {

		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;

		Connection conn = null;
		try {
			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
		} catch (ConnectionException e2) {
			throw new PersistenceException("Não foi obter a conexão do banco de dados ao salvar um objeto genérico",
					e2);
			// e2.printStackTrace();
		}
		DatabaseMetaData dbmd = null;
		Boolean exist = null;
		try {
			exist = this.checksExistence(k, ir.getRowMapper(), ir.getSelectSQL());
			sql = ir.handle(e, exist);
		} catch (PersistenceException e1) {
			e1.printStackTrace();
		}

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("SQL = " + sql);

		} catch (SQLException ex) {
			throw new PersistenceException("Erro na execucao do select: " + sql, ex);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public E get(K k, RowMapper rm, String sql) {
		sql += "" + k + "'";
		try {
			return (E) executeQueryMap(sql, rm);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object executeQueryMap(String sql, RowMapper m) throws PersistenceException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Object valor = null;

		try {

			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
			// System.out.println("PEGOU CONEXÃO");
			stmt = conn.createStatement();
			// System.out.println("CRIOU STATEMENT");
			rs = stmt.executeQuery(sql);
			// System.out.println("EXECUTOU STATEMENT");

			if (rs.next()) {
				valor = m.map(rs);
			}
		} catch (SQLException e) {
			throw new PersistenceException("Erro na execucao do select: " + sql, e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return valor;

	}

	public boolean checksExistence(K k, RowMapper rm, String sql) {

		Object o = this.get(k, rm, sql);

		if (o == null) {
			return false;
		} else {
			return true;
		}

	}

	public int executeQuery(String sql) throws PersistenceException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int qtdRemovidos = 0;

		try {
			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
			stmt = conn.prepareStatement(sql);
			// stmt.setInt(1, code_supermarket);
			qtdRemovidos = stmt.executeUpdate();
			System.out.println("objeto excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");

		} catch (SQLException e) {
			throw new PersistenceException("Erro na execucao do select: " + sql, e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
		System.out.println(" Total de linhas removidas: " + qtdRemovidos);

		return qtdRemovidos;
	}

	public ResultSet executeSql(String sql) throws PersistenceException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
			// System.out.println("PEGOU CONEXÃO");
			stmt = conn.createStatement();
			// System.out.println("CRIOU STATEMENT");
			rs = stmt.executeQuery(sql);
			// System.out.println("EXECUTOU STATEMENT");

		} catch (SQLException e) {
			throw new PersistenceException("Erro na execucao do select: " + sql, e);
			// throw new RuntimeException("Erro na execucao do select: " + sql, e);
		}
		return rs;

	}

}