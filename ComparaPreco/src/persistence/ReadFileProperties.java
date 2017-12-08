package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ReadFileProperties {

	public String getQuery(String nameProperties) throws IOException {
		String sql = null;
		/*
		 * try (FileInputStream leitor = new FileInputStream("arquivo_sql.properties"))
		 * { Properties properties = new Properties(); properties.load(leitor); for
		 * (Object key : properties.keySet()) { System.out.println(key + ":\n\t " +
		 * properties.get(key)); System.out.println(); }
		 * 
		 * System.out.println("============="); System.out.println("Lendo propriedade: "
		 * + nameProperties); // String sql =
		 * properties.getProperty("createTableSupermarket"); sql = nameProperties;
		 * System.out.println(sql); // PrapareStatement pstm =
		 * connection.prepareStatement(sql); // pstm.setXXX(1, "Fulano"); // ... }
		 * return sql;
		 */

		try (FileInputStream leitor = new FileInputStream("arquivo_sql.properties")) {
			Properties properties = new Properties();
			properties.load(leitor);

			System.out.println("Lendo propriedade: " + nameProperties);
			sql = properties.getProperty(nameProperties);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}

	public String getQueryTP(String nameProperties, Number codebar_item, Number code_supermarket) {
		String sql = null;
		PreparedStatement preparedStatement = null;

		// dbConnection = getDBConnection();

		try (FileInputStream leitor = new FileInputStream("arquivo_sql.properties")) {
			Properties properties = new Properties();
			properties.load(leitor);

			System.out.println("Lendo propriedade: " + nameProperties);
			sql = properties.getProperty(nameProperties);
			Connection conn = ConnectionManager.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, (int) codebar_item);
			preparedStatement.setInt(2, (int) code_supermarket);
			sql = preparedStatement.toString();
			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}

	/*
	 * public String getQueryUser(String nameProperties, String nameUser, String
	 * cpfUser) throws IOException { String sql = null; PreparedStatement
	 * preparedStatement = null;
	 * 
	 * // dbConnection = getDBConnection();
	 * 
	 * try (FileInputStream leitor = new FileInputStream("arquivo_sql.properties"))
	 * { Properties properties = new Properties(); properties.load(leitor);
	 * 
	 * System.out.println("Lendo propriedade: " + nameProperties); sql =
	 * properties.getProperty(nameProperties); Connection conn =
	 * ConnectionManager.getConnection(); preparedStatement =
	 * conn.prepareStatement(sql); preparedStatement.setString(1, nameUser);
	 * preparedStatement.setString(2, cpfUser); sql = preparedStatement.toString();
	 * System.out.println(sql); } catch (Exception e) { e.printStackTrace(); }
	 * return sql; }
	 */
}
