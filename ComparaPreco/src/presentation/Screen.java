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

	public static void main(String[] args) {
		Printer printer = new Printer();
		Console reader = new Console();
		int resposta2;
		// int resp = 0;
		String resp = null;
		menu = new Menu();

		do {

			// do {
			menu.toShow();
			// try {
			
				try {
					resp = menu.readAnswer();
				} catch (PresentationException e) {
					e.printStackTrace();
				}
				// } catch (NumeroInvalidoException e) {
				// printer.printMsg("Tente novamente");


			// }

			// } while (Integer.parseInt(resp) > 18 || Integer.parseInt(resp) < 1 ||
			// !resp.equals(" ")
			// || !resp.equals(""));

			if (Integer.parseInt(resp) >= 1 & Integer.parseInt(resp) <= 18) {
				// if (resp >= 1 & resp <= 18) {
				// menu.executeOption(resp);
				menu.executeOption(Integer.parseInt(resp));
			}

		} while (!resp.equals(""));
		// } while (resp != 99);
	}
}