import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
public class ReadFileProperties {
	
	public void createTable(String nameProperties) throws IOException {
		try (FileInputStream leitor = new FileInputStream("arquivo_sql.properties")) {
			Properties properties = new Properties();
			properties.load(leitor);
			for (Object key : properties.keySet()) {
				System.out.println(key + ":\n\t " + properties.get(key));
				System.out.println();
			}
			
			System.out.println("=============");
			System.out.println("Lendo propriedade: "+ nameProperties);
			//String sql = properties.getProperty("createTableSupermarket");
			String sql = nameProperties;
			System.out.println(sql);
			this.execute(sql);
			//PrapareStatement pstm = connection.prepareStatement(sql);
			//pstm.setXXX(1, "Fulano");
			//...
		}
	}
			
	
		public String getQuery (String nameProperties) throws IOException {
		String sql;
		
		try (FileInputStream leitor = new FileInputStream("arquivo_sql.properties")) {
			Properties properties = new Properties();
			properties.load(leitor);
			for (Object key : properties.keySet()) {
				System.out.println(key + ":\n\t " + properties.get(key));
				System.out.println();
			}
			
			System.out.println("=============");
			System.out.println("Lendo propriedade: "+ nameProperties);
			//String sql = properties.getProperty("createTableSupermarket");
			sql = nameProperties;
			System.out.println(sql);
			//PrapareStatement pstm = connection.prepareStatement(sql);
			//pstm.setXXX(1, "Fulano");
			//...
		}
		return sql;
	}
}
*/