import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.sql.ResultSet;

public class TestaJDBC {

	public static void main(String[] args) throws ConnectionException, SQLException {
		// TODO Auto-generated method stub
		Printer printer = new Printer();
		Console reader = new Console();
		SupermarketDAOJDBC supermarketJDBC = new SupermarketDAOJDBC();

		Supermarket supermarket;
		// Connection dbConnection =
		// DriverManager.getConnection(“jdbc:derby:MyDb;create=true”);
		Statement stmt = null;

		try (Connection conn = ConnectionManager.getConnection()) {
			System.out.println("Conectado 1");
			System.out.println(conn.toString());
			System.out.printf("%s %s %n", conn.getMetaData().getDatabaseProductName(),
					conn.getMetaData().getDatabaseProductVersion());

		} catch (SQLException e) {
			throw new ConnectionException("Erro ao obter a conexao", e);
		}

		Connection conn = null;

		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			System.out.println("Criou objeto statement");
			stmt.executeUpdate("DELETE FROM SUPERMARKETS");
		} catch (SQLException e) {
			throw new ConnectionException("Erro NO DROP", e);
		} finally {
			ConnectionManager.close(conn, stmt);
		}

		conn = ConnectionManager.getConnection();
		DatabaseMetaData dbmd = conn.getMetaData();
		ResultSet rs = dbmd.getTables(null, "ALINE", "SUPERMARKETS", null);

		if (!rs.next()) {
			try {
				supermarketJDBC.createTable();
			} catch (ConnectionException e) {
				System.out.println("Falha ao criar tabelas");
				e.printStackTrace();
			}
		}

		// Assign supermarket name attribute to the item
		printer.printMsg("Qual o código do supermercado? (Código int) \n");
		int codeSupermarket = reader.readNumber();

		// Ask the supermarket's name
		printer.printMsg("Qual o nome deste supermercado? \n");
		String nameSuper = reader.readText();
		nameSuper = reader.readText();

		// Ask the supermarket's code adress
		printer.printMsg("Qual o cep deste supermercado? (utilize somente números) \n");
		int cepSuper = reader.readNumber();

		// Create the supermarket with the name, address and code supermarket number
		supermarket = new Supermarket(nameSuper, cepSuper, codeSupermarket);

		try {
			supermarketJDBC.save(supermarket);
		} catch (ConnectionException e) {
			System.out.println("erro ao salvar o supermercado no banco");
			e.printStackTrace();
		}

		// List supermarkets = new ArrayList();
		// supermarkets = supermarketJDBC.getAllSupermarkets();
		try {
			List listaSuper = supermarketJDBC.getAllSupermarkets();
			String lista = listaSuper.toString();
			System.out.println("\n Imprimindo lista de supermercados");
			printer.printMsg(lista);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\n Imprimindo supermercado");
		Supermarket supermarketGet = supermarketJDBC.getSupermarket(codeSupermarket);
		System.out.println(supermarketGet.getName() + " " + supermarketGet.getCode() + " " + supermarketGet.getCEP());
		/*
		 * System.out.println("Excluindo supermercado code: "+ codeSupermarket);
		 * supermarketJDBC.delete(codeSupermarket); try { List listaSuper =
		 * supermarketJDBC.getAllSupermarkets(); String lista = listaSuper.toString();
		 * System.out.println("Imprimindo lista de supermercados");
		 * printer.printMsg(lista); } catch (ConnectionException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		System.out.println("\n Salvando supermarket com mesmo id para ver se entra no else do save");
		supermarket = new Supermarket(nameSuper, cepSuper, codeSupermarket);
		try {
			supermarketJDBC.save(supermarket);
		} catch (ConnectionException e) {
			System.out.println("erro ao salvar o supermercado no banco");
			e.printStackTrace();
		}

		try {
			List listaSuper = supermarketJDBC.getAllSupermarkets();
			// String lista = listaSuper.toString();
			System.out.println("\n Imprimindo lista de supermercados");
			// printer.printMsg(lista);

			Supermarket supermarketPrint = null;

			for (int i = 0; i < listaSuper.size(); i++) {
				supermarketPrint = (Supermarket) listaSuper.get(i);
				printer.printMsg("[Código do Supermercado] : " + supermarketPrint.getCode());
				printer.printMsg("Nome do supermercado: " + supermarketPrint.getName());
				printer.printMsg("CEP do supermercado: " + supermarketPrint.getCEP() + "\n");

			}

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
