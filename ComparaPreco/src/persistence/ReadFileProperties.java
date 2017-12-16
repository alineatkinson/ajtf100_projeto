package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

class ReadFileProperties {

	String getQuery(String nameProperties) throws IOException {
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
}
