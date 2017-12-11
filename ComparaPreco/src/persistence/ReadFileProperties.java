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
	/*
	 * public String getQueryTPUpdate(String nameProperties, Number codebar_item,
	 * Number code_supermarket, double price, String date) { String sql = null; //
	 * PreparedStatement preparedStatement = null; // ResultSet rs = null;
	 * 
	 * // dbConnection = getDBConnection();
	 * 
	 * try (FileInputStream leitor = new FileInputStream("arquivo_sql.properties"))
	 * { Properties properties = new Properties(); properties.load(leitor);
	 * 
	 * System.out.println("Lendo propriedade: " + nameProperties); sql =
	 * properties.getProperty(nameProperties); // Connection conn =
	 * ConnectionManager.getConnection(); sql = sql.replaceFirst("[?]",
	 * codebar_item.toString()); sql = sql.replaceFirst("[?]",
	 * code_supermarket.toString()); sql = sql.replaceFirst("[?]", price + ""); sql
	 * = sql.replaceFirst("[?]", date);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return sql; }
	 */

	/*
	 * public String getQueryTP(String nameProperties, Number codebar_item, Number
	 * code_supermarket) { String sql = null; // PreparedStatement preparedStatement
	 * = null; // ResultSet rs = null;
	 * 
	 * // dbConnection = getDBConnection();
	 * 
	 * try (FileInputStream leitor = new FileInputStream("arquivo_sql.properties"))
	 * { Properties properties = new Properties(); properties.load(leitor);
	 * 
	 * System.out.println("Lendo propriedade: " + nameProperties); sql =
	 * properties.getProperty(nameProperties); Connection conn =
	 * ConnectionManager.getConnection(); sql = sql.replaceFirst("[?]",
	 * codebar_item.toString()); sql = sql.replaceFirst("[?]",
	 * code_supermarket.toString());
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return sql; }
	 */

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
	 * properties.getProperty(nameProperties); // Connection conn =
	 * ConnectionManager.getConnection();
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return sql; }
	 */

}
