package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Supermarket;
import presentation.Printer;

public class SupermarketDAOJDBC extends ComparePriceDAOJDBC implements ComparePriceDAO<Supermarket> {

	private SQLHandler<model.Supermarket> sh = new SupermarketSQLHandler();

	public List<Supermarket> getAll() throws PersistenceException {
		String sql = null;

		// TODO MELHORAR EXCEÇÃO

		try {
			sql = sh.getSelectAll();
		} catch (PersistenceException e1) {
			e1.printStackTrace();
		}

		List<Supermarket> supermarkets = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int code_supermarket = rs.getInt("code_supermarket");
				supermarkets.add(this.get(code_supermarket));
			}
		} catch (SQLException e) {
			throw new PersistenceException("Não foi possível executar a consulta de seleção de todos supermercados", e);
			// e.printStackTrace();
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return supermarkets;
	}

	public void delete(Number codebar_item) {
		// TODO MELHORAR EXCEÇÃO
		String sql = null;

		try {
			sql = sh.getDeleteSQL() + codebar_item;
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		int qtdRemovidos = 0;

		try {
			qtdRemovidos = executeQuery(sql);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		System.out.println("supermercado excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	@Override
	public void save(Supermarket object) throws PersistenceException {
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;

		Connection conn = null;
		try {
			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
			;
		} catch (ConnectionException e2) {
			throw new PersistenceException("Não foi possível conectar no banco de dados ao salvar o supermercado", e2);
			// e2.printStackTrace();
		}
		DatabaseMetaData dbmd = null;

		int code_supermarket = object.getCode();
		super.save(object, code_supermarket, sh);
	}

	@Override
	public Supermarket get(Number key) {
		// TODO MELHORAR EXCEÇÃO
		String sql = null;

		try {
			sql = sh.getSelectSQL();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		return (Supermarket) super.get(key, sh.getRowMapper(), sql);
	}

	@Override
	public boolean checksExistence(Number key) {
		boolean exist = false;

		try {
			exist = super.checksExistence(key, sh.getRowMapper(), sh.getSelectSQL());
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exist;
	}

}
