package presentation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
//import org.junit.Assert;

import persistence.ConnectionException;

public class Screen {
	static Menu menu;

	public static void main(String[] args) throws ConnectionException, NumeroInvalidoException {
		Printer printer = new Printer();
		Console reader = new Console();
		int resposta2;
		int resp = 0;
		menu = new Menu();

		do {
			do {
				menu.toShow();
				try {
					resp = menu.readAnswer();
				} catch (NumeroInvalidoException e) {
					printer.printMsg("Tente novamente");
				}
			} while (resp > 18 || resp < 1);

			menu.executeOption(resp);

			printer.printMsg("Deseja utilizar a aplica��o novamente? (1 = SIM | 2 = N�o) \n");
			resposta2 = reader.readNumber();
		} while (resposta2 != 2);

	}
}