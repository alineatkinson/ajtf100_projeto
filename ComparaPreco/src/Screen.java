import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
//import org.junit.Assert;

public class Screen {
	static Menu menu;
		
	public static void main(String[] args) throws ConnectionException {
		Printer printer = new Printer();
		Console reader = new Console();
		int resposta2;
		int resp;
		menu = new Menu();
		
		try (Connection conn = ConnectionManager.getConnection()) {
			System.out.println("Conectado 1");
			System.out.println(conn.toString());
			System.out.printf("%s %s %n", conn.getMetaData().getDatabaseProductName(),
					conn.getMetaData().getDatabaseProductVersion());
		} catch (SQLException e) {
			throw new ConnectionException("Erro ao obter a conexao", e);
		}
		
		

		do {
			menu.toShow();
			resp = menu.readAnswer();
			menu.executeOption(resp);

			printer.printMsg("Deseja utilizar a aplicação novamente? (1 = SIM | 2 = Não) \n");
			resposta2 = reader.readNumber();
		} while (resposta2 != 2);

	}
}