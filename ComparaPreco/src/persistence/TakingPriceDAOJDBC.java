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
		// TODO melhorar exce��o
		String sql = null;
		try {
			sql = sh.getSelectAll();
		} catch (IOException e1) {
			throw new PersistenceException("N�o foi poss�vel obter todas tomadas de pre�o", e1);
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
				takingprices.add(this.get(codebar_item, code_supermarket, date));
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
		// TODO melhorar exce��o
		String sql = null;
		try {
			sql = sh.getDeleteSQLTP(codebar_item, code_supermarket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int qtdRemovidos = 0;

		qtdRemovidos = executeQuery(sql);
		System.out.println("taking_prices exclu�do do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	public void save(TakingPrice tp) {
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;

		Connection conn = null;
		DatabaseMetaData dbmd = null;

		Boolean exist = this.checksExistence(tp.getCodeBarItem(), tp.getCodeSupermarket(), tp.getDate());
		// TODO MELHORAR EXCE��O
		try {
			sql = sh.handle(tp, exist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
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
		// TODO melhorar exce��o
		String sql = null;
		try {
			sql = sh.getSelectSQL();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (TakingPrice) super.get(key, sh.getRowMapper(), sql);
	}

	public TakingPrice get(Number codebar_item, Number code_supermarket, Date date) {
		// TODO melhorar exce��o
		String sql = null;
		try {
			sql = sh.getSelectSQLTP(codebar_item, code_supermarket, date);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (TakingPrice) super.executeQueryMap(sql, sh.getRowMapper());
	}

	@Override
	public boolean checksExistence(Number key) {
		System.out.println("Entrou no check existence errrado");
		// TODO melhorar exce��o
		boolean exist = false;
		try {
			exist = super.checksExistence(key, sh.getRowMapper(), sh.getSelectSQL());
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		System.out.println("Erro, entrou no delete com chave �nica do c�digo do item");

	}

}