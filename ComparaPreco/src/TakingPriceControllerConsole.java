import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TakingPriceControllerConsole {
	Printer printer = new Printer();
	Console reader = new Console();
	TakingPriceDAO takingPriceDao = new TakingPriceDAOCollections();

	public void createTakingPrice() {

		TakingPrice takingPrice;

		printer.printMsg("Qual o c�digo do item? \n");
		int codeBarItem = reader.readNumber();
		//codeSupermarket = reader.readNumber();
	
		
		printer.printMsg("Qual o c�digo do supermercado? (C�digo int) \n");
		int codeSupermarket = reader.readNumber();

	
		printer.printMsg("Qual o pre�o do item? (Ex.Formato: 5000,23 para R$ 5.000,23) \n");
		double priceItem = reader.readNumberDouble();
		
		//LocalDateTime timePoint = LocalDateTime.now(); 
		//System.out.print(timePoint);
		
		//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//Calendar cal = Calendar.getInstance();
		//System.out.println(dateFormat.format(cal)); //31/01/2017 16 12:08:43
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now)); //2016/11/16 12:08:43
		
		takingPrice = new TakingPrice(codeBarItem, priceItem, codeSupermarket); 
		takingPriceDao.save(codeBarItem, takingPrice);
	}

	/*
	 * Edit the data of a supermarket
	 */
	public void editTakingPrice() {

		printer.printMsg("Digite o c�digo do item a ser alterado? ");
		int codeBarItem = reader.readNumber();
		TakingPrice tp = null;

		// pensando em cadastrar o novo pre�o sem editar o pre�o antigo. Sen�o terei que controlar por muitos atributos.
		if (takingPriceDao.checksExistence(codeBarItem)) {
			tp = (TakingPrice) takingPriceDao.get(codeBarItem);
			int codeSupermarket = tp.getCodeSupermarket();
			double priceItem = tp.getPrice();
			printer.printMsg(" A tomada de pre�o selecionada cont�m os seguintes dados: ");
			printer.printMsg(" C�digo do item: " + codeBarItem);
			printer.printMsg(" C�digo do supermercado : " + codeSupermarket);
			NumberFormat monetaryFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
			printer.printMsg("Pre�o do item :" + monetaryFormatter.format(priceItem));
			printer.printMsg(" Digite para alterar: 1 -> C�digo do item, 2 -> C�digo do supermercado, 3 -> Pre�o do item");
			int respEdit = 0;
			respEdit = reader.readNumber();

			takingPriceDao.delete(codeBarItem);

			do {
				if (respEdit == 1) {
					printer.printMsg(" Digite o novo c�digo do item: ");
					int newCodeItem = 0;
					newCodeItem = reader.readNumber();
					TakingPrice newTP = new TakingPrice(newCodeItem, priceItem, codeSupermarket);
					takingPriceDao.save(newCodeItem, newTP);
				} else if (respEdit == 2) {
					printer.printMsg(" Digite o novo c�digo do supermercado: ");
					int newCodeSupermarket = 0;
					newCodeSupermarket = reader.readNumber();
					TakingPrice newTP = new TakingPrice(codeBarItem, priceItem, newCodeSupermarket);
					takingPriceDao.save(codeBarItem, newTP);
				} else if (respEdit == 3) {
					printer.printMsg(" Digite o novo pre�o do item: ");
					double newPriceItem = 0;
					newPriceItem = reader.readNumberDouble();
					TakingPrice newTP = new TakingPrice(codeBarItem, newPriceItem, codeSupermarket);
					takingPriceDao.save(codeBarItem, newTP);
				} else {
					printer.printMsg("Nenhuma alternativa v�lida foi digitada. Tente outra vez!");
				}
			} while (respEdit != 1 & respEdit != 2 & respEdit != 3);

		} else {
			printer.printMsg("N�o existe supermercado com este c�digo cadastrado.");
		}
	}

	/*
	 * List all supermarkets
	 */
	public void listTakingPrice() {
		takingPriceDao.list();
	}

	/*
	 * Delete a supermarket
	 */
	public void deleteTakingPrice() {
		printer.printMsg("Digite o c�digo do item com tomada de pre�o a ser exclu�do: ");
		int codeBarItemKey = 0;
		codeBarItemKey = reader.readNumber();

		if (takingPriceDao.checksExistence(codeBarItemKey)) {
			takingPriceDao.delete(codeBarItemKey);
		} else {
			printer.printMsg("N�o tomada de pre�o com este c�digo de item.");
		}
	}
}
