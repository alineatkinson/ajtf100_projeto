package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import presentation.Printer;

import model.Item;

public class ItemDAOJDBC extends ComparePriceDAOJDBC implements ComparePriceByNameDAO<Item> {
	private ItemSQLHandler sh = new ItemSQLHandler();
	private Printer printer = new Printer();

	public List<Item> getAll() throws PersistenceException {
		// TODO MELHORAR EXCE��O
		String sql = null;
		try {
			sql = sh.getSelectAll();
		} catch (PersistenceException e1) {
			e1.printStackTrace();
		}
		List<Item> items = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.print(rs.toString());

			while (rs.next()) {
				int codebar_item = rs.getInt("codebar_item");
				System.out.println(codebar_item);
				items.add(this.get(codebar_item));
			}
		} catch (ConnectionException e) {
			throw new PersistenceException("N�o foi obter a conex�o do banco de dados ao recuperar todos itens", e);
			// e.printStackTrace();
		} catch (SQLException e) {
			throw new PersistenceException("N�o foi poss�vel executar o slq de sele��o de todos itens", e);
			// e.printStackTrace();
		} finally {
			ConnectionManager.close(conn, stmt, rs);
		}
		return items;
	}

	public void delete(Number codebar_item) {
		// TODO MELHORAR EXCE��O
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
		System.out.println("item exclu�do do banco com sucesso!" + qtdRemovidos + " linhas excluidas");
	}

	@Override
	// pq n�o consigo colocar o par�metro como item?
	public void save(Object object) throws PersistenceException {
		Item item = (Item) object;
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;

		Connection conn = null;
		try {
			conn = new ConnectionManager("pricecompator;create=true",
					"jdbc:derby://localhost:1527/" + "pricecompator;create=true", "aline", "aline").getConnection();
		} catch (ConnectionException e2) {
			throw new PersistenceException("Erro ao obter a conexao", e2);
			//e2.printStackTrace();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		DatabaseMetaData dbmd = null;

		/*
		 * try { dbmd = conn.getMetaData(); rs = dbmd.getTables(null, "ALINE", "ITEMS",
		 * null); if (!rs.next()) { this.createTable(); } } catch (SQLException e3) { //
		 * TODO Auto-generated catch block e3.printStackTrace(); }
		 */

		int codebar_item = item.getBarCode();
		super.save(item, codebar_item, sh);
	}

	@Override
	public Item get(Number key) {
		// TODO MELHORAR EXCE��O
		String sql = null;
		try {
			sql = sh.getSelectSQL();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return (Item) super.get(key, sh.getRowMapper(), sql);
	}

	public Item get(String name) {
		// TODO MELHORAR EXCE��O
		String sql = null;
		try {
			sql = sh.getSelectNameSQL();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return (Item) super.get(name, sh.getRowMapper(), sql);
	}

	@Override
	public boolean checksExistence(Number key) {
		boolean exist = false;
		try {
			exist = super.checksExistence(key, sh.getRowMapper(), sh.getSelectSQL());
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return exist;
	}

	public boolean checksExistence(String name) {
		boolean exist = false;
		try {
			exist = super.checksExistence(name, sh.getRowMapper(), sh.getSelectNameSQL());
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return exist;
	}

}