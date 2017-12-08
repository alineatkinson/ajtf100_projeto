package presentation;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import model.Supermarket;
import model.TakingPrice;
import persistence.DAOFactory;
import persistence.TakingPriceDAOJDBC;

public class TakingPriceControllerConsole {
	private Printer printer = new Printer();
	private Console reader = new Console();
	// ComparePriceDAO takingPriceDao = new TakingPriceDAOCollections();
	private TakingPriceDAOJDBC takingPriceDao = (TakingPriceDAOJDBC) new DAOFactory().getTakingPriceDAO();

	public void createTakingPrice() {

		printer.printMsg("Qual o código do item? \n");
		int codeBarItem = reader.readNumber();
		// codeSupermarket = reader.readNumber();

		printer.printMsg("Qual o código do supermercado? (Código int) \n");
		int codeSupermarket = reader.readNumber();

		printer.printMsg("Qual o preço do item? (Ex.Formato: 5000,23 para R$ 5.000,23) \n");
		double priceItem = reader.readNumberDouble();

		// LocalDateTime timePoint = LocalDateTime.now();
		// System.out.print(timePoint);

		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		// Calendar cal = Calendar.getInstance();
		// System.out.println(dateFormat.format(cal)); //31/01/2017 16 12:08:43

		// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		// LocalDateTime now = LocalDateTime.now();
		// System.out.println(dtf.format(now)); //2016/11/16 12:08:43
		// System.out.println(now);

		java.util.Date now = new Date();
		String dStr = java.text.DateFormat.getDateInstance(DateFormat.LONG).format(now);
		// Convertendo de String para Date
		// Date dataf = parser.parse(data);
		// Convertendo para o tipo sql.Date (formato: yyyy-MM-dd)
		// java.sql.Date dataSql = new java.sql.Date(dataf.getTime());
		System.out.println(dStr);
		this.save(codeBarItem, priceItem, codeSupermarket, now);

	}

	public void save(int codeBarItem, double priceItem, int codeSupermarket, Date now) {
		TakingPrice takingPrice = new TakingPrice(codeBarItem, priceItem, codeSupermarket, now);
		takingPriceDao.save(takingPrice);
	}

	/*
	 * Edit the data of a supermarket
	 */
	public void editTakingPrice() {

		printer.printMsg("Digite o código do item a ser alterado? ");
		int codeBarItem = reader.readNumber();
		TakingPrice tp = null;
		
		printer.printMsg("Digite o código do supermercado a ser alterado? ");
		int codeSmkt = reader.readNumber();
		Supermarket smkt = null;

		// pensando em cadastrar o novo preço sem editar o preço antigo. Senão terei que
		// controlar por muitos atributos.
		if (takingPriceDao.checksExistence(codeBarItem, codeSmkt)) {
			tp = (TakingPrice) takingPriceDao.get(codeBarItem, codeSmkt);
			int codeSupermarket = tp.getCodeSupermarket();
			double priceItem = tp.getPrice();
			Date dateTP = tp.getDate();
			int respEdit = 0;
			
			takingPriceDao.delete(codeBarItem, codeSupermarket);

			do {
				respEdit = this.askWhatEdit(tp);
				if (respEdit == 1) {
					printer.printMsg(" Digite o novo código do item: ");
					int newCodeItem = 0;
					newCodeItem = reader.readNumber();
					this.save(newCodeItem, priceItem, codeSupermarket, dateTP);
				} else if (respEdit == 2) {
					printer.printMsg(" Digite o novo código do supermercado: ");
					int newCodeSupermarket = 0;
					newCodeSupermarket = reader.readNumber();
					this.save(codeBarItem, priceItem, newCodeSupermarket, dateTP);
				} else if (respEdit == 3) {
					printer.printMsg(" Digite o novo preço do item: ");
					double newPriceItem = 0;
					newPriceItem = reader.readNumberDouble();
					this.save(codeBarItem, newPriceItem, codeSupermarket, dateTP);
				} else {
					printer.printMsg("Nenhuma alternativa válida foi digitada. Tente outra vez!");
				}
				
			} while (respEdit != 1 & respEdit != 2 & respEdit != 3);

		} else {
			printer.printMsg("Não existe supermercado com este código cadastrado.");
		}
	}

	public int askWhatEdit(TakingPrice tp) {
		printer.printMsg(" A tomada de preço selecionada contém os seguintes dados: ");
		printer.printMsg(" Código do item: " + tp.codeBarItem);
		printer.printMsg(" Código do supermercado : " + tp.getCodeSupermarket());
		NumberFormat monetaryFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		printer.printMsg("Preço do item :" + monetaryFormatter.format(tp.getPrice()));
		printer.printMsg("Data da tomada de preço :" + tp.getDate());
		printer.printMsg(" Digite para alterar: 1 -> Código do item, 2 -> Código do supermercado, 3 -> Preço do item");
		int respEdit = 0;
		return respEdit = reader.readNumber();
	}

	/*
	 * List all taking prices
	 */
	public List<String> getData() {

		List<String> data = new ArrayList<String>();
		List<TakingPrice> takingPrices = takingPriceDao.getAll();

		for (TakingPrice takingprice : takingPrices) {
			StringBuilder sb = new StringBuilder();
			sb.append("[Código do item] : " + takingprice.getCodeBarItem()+ "\n");
			sb.append("[Código do Supermercado]: " + takingprice.getCodeSupermarket()+ "\n");
			sb.append("Preço: " + takingprice.getPrice()+ "\n");
			sb.append("Data: " + takingprice.getDate() + "\n");
			data.add(sb.toString());
		}
		return data;
	}

	public void listTakingPrice() {

		List<String> data = getData();

		for (String item : data) {
			printer.printMsg(item);
		}
	}

	/*
	 * Delete a supermarket
	 */
	public void deleteTakingPrice() {
		printer.printMsg("Digite o código do item com tomada de preço a ser excluído: ");
		int codeBarItem = 0;
		codeBarItem = reader.readNumber();
		
		printer.printMsg("Digite o código do supermercado com tomada de preço a ser excluído: ");
		int codeSmkt = 0;
		codeSmkt = reader.readNumber();

		if (takingPriceDao.checksExistence(codeBarItem, codeSmkt)) {
			takingPriceDao.delete(codeBarItem, codeSmkt);
		} else {
			printer.printMsg("Não tomada de preço com este código de item.");
		}
	}
}
