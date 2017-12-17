//package test;

//import static test.DatabaseExecutor.executeQuery;
import static test.DatabaseExecutor.executeUpdate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseExecutorTest {

	@Test
	public void executeQuery_happy_1found() throws SQLException {
		executeUpdate("DELETE FROM taking_prices");
		executeUpdate(
				"INSERT INTO taking_prices (codebar_item,code_supermarket,price,date) VALUES (1, 10, 1.5, '2017-12-15 17:30:00')");
		executeUpdate(
				"INSERT INTO taking_prices (codebar_item,code_supermarket,price,date) VALUES (1, 20, 3.0, '2017-12-15 17:30:00')");
		executeUpdate(
				"INSERT INTO taking_prices (codebar_item,code_supermarket,price,date) VALUES (2, 10, 1.7, '2017-12-15 17:35:00')");
		executeUpdate(
				"INSERT INTO taking_prices (codebar_item,code_supermarket,price,date) VALUES (2, 20, 3.5, '2017-12-16 17:40:00')");
		executeUpdate(
				"INSERT INTO taking_prices (codebar_item,code_supermarket,price,date) VALUES (1, 10, 1.5, '2017-12-16 17:40:00')");
		int actual2 = executeQuery(
				"SELECT * FROM taking_prices WHERE codebar_item = 1 and code_supermarket = 20 and date = '2017-12-15 17:30:00'");
		assertSize(1, actual2);
		int actual3 = executeQuery(
				"SELECT * FROM taking_prices WHERE date BETWEEN '2017-12-15 00:00:00' AND '2017-12-18 00:00:00'");
		assertSize(5, actual3);
		executeUpdate("DELETE FROM taking_prices");
		int actual4 = executeQuery("SELECT * FROM taking_prices");
		assertSize(0, actual4);
	}


	static void createTable() throws SQLException{
		 executeUpdate("CREATE TABLE taking_prices (codebar_item int NOT NULL, code_supermarket int NOT NULL, price DECIMAL(18,2) NOT NULL, date timestamp)");
		 int actual1 = executeQuery("SELECT * FROM taking_prices WHERE codebar_item = 1 and code_supermarket = 20 and date = '2017-12-15 17:30'");
		 assertSize(0,actual1);
	}

	static void assertSize(int expectedSize, int actualSize) throws SQLException {
		Assert.assertEquals(expectedSize, actualSize);
	}
}

class DatabaseExecutor {

	private DatabaseExecutor() {
	}

	static int executeUpdate(String sql) throws SQLException {
		int result = 0;
		try (Connection connection = ConnectionProvider.provide();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			result = ps.executeUpdate();
		}
		return result;
	}

	static int executeQuery(String sql) throws SQLException {
		int result = 0;

		try (Connection connection = ConnectionProvider.provide();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				result++;
			}
		}
		return result;
	}

}

class ConnectionProvider {

	private ConnectionProvider() {
	}

	static Connection provide() throws SQLException {
		return DriverManager.getConnection("jdbc:derby:alinedb;create=true");
	}

}
