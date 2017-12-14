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
import persistence.PersistenceException;
import persistence.TakingPriceDAOJDBC;

public class TakingPriceControllerConsole {
	private Printer printer = new Printer();
	private Console reader = new Console();
	// ComparePriceDAO takingPriceDao = new TakingPriceDAOCollections();
	private TakingPriceDAOJDBC takingPriceDao = (TakingPriceDAOJDBC) new DAOFactory().getTakingPriceDAO();

	public void createTakingPrice() {

		printer.printMsg("Qual o c�digo do item? \n");
		int codeBarItem = reader.readNumber();
		// codeSupermarket = reader.readNumber();

		printer.printMsg("Qual o c�digo do supermercado? (C�digo int) \n");
		int codeSupermarket = reader.readNumber();

		printer.printMsg("Qual o pre�o do item? (Ex.Formato: 5000,23 para R$ 5.000,23) \n");
		double priceItem = reader.readNumberDouble();

		java.util.Date now = new Date();
		String dStr = java.text.DateFormat.getDateInstance(DateFormat.LONG).format(now);

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
		TakingPrice tp = null;
		int codeBarItem;
		int codeSmkt;
		int newCodeItem;

		printer.printMsg("Digite o c�digo do item a ser alterado? ");
		codeBarItem = reader.readNumber();

		printer.printMsg("Digite o c�digo do supermercado a ser alterado? ");
		codeSmkt = reader.readNumber();

		Supermarket smkt = null;
		// pensando em cadastrar o novo pre�o sem editar o pre�o antigo. Sen�o terei que
		// controlar por muitos atributos.

		if (takingPriceDao.checksExistence(codeBarItem, codeSmkt)) {
			tp = (TakingPrice) takingPriceDao.get(codeBarItem, codeSmkt);
			int codeSupermarket = tp.getCodeSupermarket();
			double priceItem = tp.getPrice();
			Date dateTP = tp.getDate();
			int respEdit = 0;

			takingPriceDao.delete(codeBarItem, codeSupermarket);

			do {
				try {
					respEdit = this.askWhatEdit(tp);

				} catch (NumeroInvalidoException e) {

				}
			} while (respEdit != 1 & respEdit != 2 & respEdit != 3);

			if (respEdit == 1) {
				printer.printMsg(" Digite o novo c�digo do item: ");
				newCodeItem = reader.readNumber();
				this.save(newCodeItem, priceItem, codeSupermarket, dateTP);
			} else if (respEdit == 2) {
				printer.printMsg(" Digite o novo c�digo do supermercado: ");
				int newCodeSupermarket = 0;
				newCodeSupermarket = reader.readNumber();
				this.save(codeBarItem, priceItem, newCodeSupermarket, dateTP);
			} else if (respEdit == 3) {
				printer.printMsg(" Digite o novo pre�o do item: ");
				double newPriceItem = 0;
				newPriceItem = reader.readNumberDouble();
				this.save(codeBarItem, newPriceItem, codeSupermarket, dateTP);
			}

		} else {
			printer.printMsg("N�o existe tomada de pre�o com estes c�digos cadastrados.");
		}
	}

	public int askWhatEdit(TakingPrice tp) throws NumeroInvalidoException {
		int respEdit = 0;

		printer.printMsg(" A tomada de pre�o selecionada cont�m os seguintes dados: ");
		printer.printMsg(" C�digo do item: " + tp.codeBarItem);
		printer.printMsg(" C�digo do supermercado : " + tp.getCodeSupermarket());
		NumberFormat monetaryFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		printer.printMsg("Pre�o do item :" + monetaryFormatter.format(tp.getPrice()));
		printer.printMsg("Data da tomada de pre�o :" + tp.getDate());
		printer.printMsg(" Digite para alterar: 1 -> C�digo do item, 2 -> C�digo do supermercado, 3 -> Pre�o do item");
		respEdit = reader.readNumber();

		if (respEdit != 1 & respEdit != 2 & respEdit != 3) {
			throw new NumeroInvalidoException(respEdit + " � um n�mero inv�lido, tente novamente!");
		}

		return respEdit;
	}

	/*
	 * List all taking prices
	 */
	public List<String> getData() {

		List<String> data = new ArrayList<String>();
		List<TakingPrice> takingPrices;
		try {
			takingPrices = takingPriceDao.getAll();
			for (TakingPrice takingprice : takingPrices) {
				StringBuilder sb = new StringBuilder();
				sb.append("[C�digo do item] : " + takingprice.getCodeBarItem() + "\n");
				sb.append("[C�digo do Supermercado]: " + takingprice.getCodeSupermarket() + "\n");
				sb.append("Pre�o: " + takingprice.getPrice() + "\n");
				sb.append("Data: " + takingprice.getDate() + "\n");
				data.add(sb.toString());
			}	} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		int codeBarItem = 0;
		int codeSmkt = 0;

		printer.printMsg("Digite o c�digo do item com tomada de pre�o a ser exclu�do: ");
		codeBarItem = 0;
		codeBarItem = reader.readNumber();

		printer.printMsg("Digite o c�digo do supermercado com tomada de pre�o a ser exclu�do: ");
		codeSmkt = 0;
		codeSmkt = reader.readNumber();

		if (takingPriceDao.checksExistence(codeBarItem, codeSmkt)) {
			takingPriceDao.delete(codeBarItem, codeSmkt);
		} else {
			printer.printMsg("N�o existe tomada de pre�o com estes c�digos.");
		}
	}
}
