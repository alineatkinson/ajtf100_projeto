package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

interface RowMapper {
	
	Object map (ResultSet rs) throws SQLException;
	
	// TODO PRECISA?
	default Object executeQueryMap(String sql, RowMapper m) throws PersistenceException {

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
			//throw new RuntimeException("Erro na execucao do select: " + sql, e);
			throw new PersistenceException("Não foi possível obter a propriedade de seleção de preços por item", e);
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return valor;

	}
	
	
}
