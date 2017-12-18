package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.TakingPrice;
import presentation.Printer;

public class TakingPriceDAOJDBC extends ComparePriceDAOJDBC implements ComparePriceDAO<TakingPrice> {
	private TakingPriceSQLHandler sh = new TakingPriceSQLHandler();
	private Printer printer = new Printer();

	public List<TakingPrice> getAll() throws PersistenceException {

		String sql = null;
		try {
			sql = sh.getSelectAll();
		} catch (PersistenceException e1) {
			e1.printStackTrace();
		}

		List<TakingPrice> takingprices = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int codebar_item = rs.getInt("codebar_item");
				int code_supermarket = rs.getInt("code_supermarket");
				Date date = rs.getTimestamp("date");
				System.out.println("Data do rs: " + date);
				takingprices.add(this.get(codebar_item, code_supermarket, date));
			}
		} catch (SQLException e) {
			throw new PersistenceException("Erro ao executar sql "+ sql, e);
			//e.printStackTrace();
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return takingprices;
	}

	public void delete(Number codebar_item, Number code_supermarket) {
		// TODO melhorar exceção
		String sql = null;
		try {
			sql = sh.getDeleteSQLTP(codebar_item, code_supermarket);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		int qtdRemovidos = 0;

		try {
			qtdRemovidos = executeQuery(sql);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		System.out.println("taking_prices excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	public void save(TakingPrice tp) throws PersistenceException {
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;

		Connection conn = null;
		DatabaseMetaData dbmd = null;

		Boolean exist = this.checksExistence(tp.getCodeBarItem(), tp.getCodeSupermarket(), tp.getDate());
		try {
			sql = sh.handle(tp, exist);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		try {

			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("SQL = " + sql);

		} catch (SQLException ex) {
			throw new PersistenceException("Erro na execucao do select: " + sql, ex);
		} finally {
			ConnectionManager.close(conn, stmt);
		}
	}

	@Override
	public TakingPrice get(Number key) {
		System.out.println("Entrou no get errrado");
		// TODO melhorar exceção
		String sql = null;
		try {
			sql = sh.getSelectSQL();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return (TakingPrice) super.get(key, sh.getRowMapper(), sql);
	}

	public TakingPrice get(Number codebar_item, Number code_supermarket, Date date) {
		// TODO melhorar exceção
		String sql = null;
		try {
			sql = sh.getSelectSQLTP(codebar_item, code_supermarket, date);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		try {
			return (TakingPrice) super.executeQueryMap(sql, sh.getRowMapper());
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checksExistence(Number key) {
		System.out.println("Entrou no check existence errrado");
		// TODO melhorar exceção
		boolean exist = false;
		try {
			exist = super.checksExistence(key, sh.getRowMapper(), sh.getSelectSQL());
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return exist;
	}

	public boolean checksExistence(Number code_item, Number code_supermkt, Date date) {
		Object o = this.get(code_item, code_supermkt, date);

		if (o == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public void delete(Number key) {
		System.out.println("Erro, entrou no delete com chave única do código do item");

	}

}