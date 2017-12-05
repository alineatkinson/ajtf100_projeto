package persistence;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComparePriceDAOJDBC<E, K> {

	public void save(E e, K k, SQLHandler ir) {

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
		Boolean exist = this.checksExistence(k, ir.getRowMapper(), ir.getSelectSQL());
		sql = ir.handle(e, exist);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("SQL = " + sql);

		} catch (SQLException ex) {
			throw new RuntimeException("Erro na execucao do select: " + sql, ex);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	public E get(K k, RowMapper rm, String sql) {
		sql += "" + k;
		return (E) executeQueryMap(sql, rm);
	}

	public E get(String name, RowMapper rm, String sql) { //Precisa?
		sql += "" + name + "'";
		return (E) executeQueryMap(sql, rm);
	}

	public Object executeQueryMap(String sql, RowMapper m) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Object valor = null;

		try {

			conn = ConnectionManager.getConnection();
			// System.out.println("PEGOU CONEXÃO");
			stmt = conn.createStatement();
			// System.out.println("CRIOU STATEMENT");
			rs = stmt.executeQuery(sql);
			// System.out.println("EXECUTOU STATEMENT");

			if (rs.next()) {
				valor = m.map(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro na execucao do select: " + sql, e);
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

	public boolean checksExistence(String name, RowMapper rm, String sql) {
		Object o = this.get(name, rm, sql);
		if (o == null) {
			return false;
		} else {
			return true;
		}
	}

	public int executeQuery(String sql) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int qtdRemovidos = 0;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.prepareStatement(sql);
			// stmt.setInt(1, code_supermarket);
			qtdRemovidos = stmt.executeUpdate();
			System.out.println("objeto excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar remover supermercado de code_supermarket ", e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
		System.out.println(" Total de linhas removidas: " + qtdRemovidos);

		return qtdRemovidos;
	}

}
