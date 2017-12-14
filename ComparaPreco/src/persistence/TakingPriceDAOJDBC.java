package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.TakingPrice;
import presentation.Printer;

public class TakingPriceDAOJDBC extends ComparePriceDAOJDBC implements ComparePriceDAO<TakingPrice> {
	private TakingPriceSQLHandler sh = new TakingPriceSQLHandler();
	Printer printer = new Printer();

	/*
	 * public void createTable() { String sql = sh.getCreateTable(); int qdtEdited =
	 * super.executeQuery(sql);
	 * printer.printMsg("Tabela TakingPrices criada com sucesso"); }
	 */

	public List<TakingPrice> getAll() throws PersistenceException {
		// TODO melhorar exceção
		String sql = null;
		try {
			sql = sh.getSelectAll();
		} catch (IOException e1) {
			throw new PersistenceException("Não foi possível obter todas tomadas de preço", e1);
		}
		List<TakingPrice> takingprices = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int codebar_item = rs.getInt("codebar_item");
				int code_supermarket = rs.getInt("code_supermarket");
				takingprices.add(this.get(codebar_item, code_supermarket));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int qtdRemovidos = 0;

		qtdRemovidos = executeQuery(sql);
		System.out.println("taking_prices excluído do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	public void save(TakingPrice tp) {
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;

		Connection conn = null;
		DatabaseMetaData dbmd = null;

		Boolean exist = this.checksExistence(tp.getCodeBarItem(), tp.getCodeSupermarket());
		//TODO MELHORAR EXCEÇÃO
		try {
			sql = sh.handle(tp, exist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("SQL = " + sql);

		} catch (SQLException ex) {
			throw new RuntimeException("Erro na execucao do select: " + sql, ex);
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (TakingPrice) super.get(key, sh.getRowMapper(), sql);
	}

	public TakingPrice get(Number codebar_item, Number code_supermarket) {
		// TODO melhorar exceção
		String sql = null;
		try {
			sql = sh.getSelectSQLTP(codebar_item, code_supermarket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (TakingPrice) super.executeQueryMap(sql, sh.getRowMapper());
	}

	@Override
	public boolean checksExistence(Number key) {
		System.out.println("Entrou no check existence errrado");
		// TODO melhorar exceção
		boolean exist = false;
		try {
			exist = super.checksExistence(key, sh.getRowMapper(), sh.getSelectSQL());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exist;
	}

	public boolean checksExistence(Number code_item, Number code_supermkt) {

		Object o = this.get(code_item, code_supermkt);

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