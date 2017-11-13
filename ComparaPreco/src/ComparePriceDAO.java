import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public interface ComparePriceDAO<E> {

	public abstract void save(E object);

	public abstract E get(Number key);

	public abstract boolean checksExistence(Number key);

	public abstract void delete(Number key);

	public abstract Map getAll();

	public default Object executeQueryMap(String sql, RowMapper m) {

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

	public default int executeQueryDelete(String sql) {
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
	/*
	 * public default void executeUpdate(String sql) { Connection conn = null;
	 * PreparedStatement stmt = null;
	 * 
	 * try { conn = ConnectionManager.getConnection(); stmt =
	 * conn.prepareStatement(sql); // stmt.setInt(1, code_supermarket);
	 * stmt.executeUpdate(sql);
	 * 
	 * } catch (SQLException e) { throw new
	 * RuntimeException("Erro ao tentar remover supermercado de code_supermarket ",
	 * e); } finally { ConnectionManager.close(conn, stmt); } }
	 * 
	 * public default Object executeQuery(String sql) {
	 * 
	 * Connection conn = null; Statement stmt = null; ResultSet rs = null; Object
	 * valor = null;
	 * 
	 * try {
	 * 
	 * conn = ConnectionManager.getConnection(); //
	 * System.out.println("PEGOU CONEXÃO"); stmt = conn.createStatement(); //
	 * System.out.println("CRIOU STATEMENT"); rs = stmt.executeQuery(sql); //
	 * System.out.println("EXECUTOU STATEMENT");
	 * 
	 * } catch (SQLException e) { throw new
	 * RuntimeException("Erro na execucao do select: " + sql, e); } finally {
	 * ConnectionManager.close(conn, stmt, rs); } return valor;
	 * 
	 * }
	 * 
	 * 
	 */

}
