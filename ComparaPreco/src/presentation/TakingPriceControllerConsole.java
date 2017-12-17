package presentation;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import business.TakingPriceManager;
import model.TakingPrice;

class TakingPriceControllerConsole {
	private Printer printer = new Printer();
	private Console reader = new Console();
	private TakingPriceManager tpm = new TakingPriceManager();

	void createTakingPrice() {

		printer.printMsg("Qual o código do item? \n");
		int codeBarItem = reader.readNumber();
		// codeSupermarket = reader.readNumber();

		printer.printMsg("Qual o código do supermercado? (Código int) \n");
		int codeSupermarket = reader.readNumber();

		printer.printMsg("Qual o preço do item? (Ex.Formato: 5000,23 para R$ 5.000,23) \n");
		double priceItem = reader.readNumberDouble();

		printer.printMsg(
				"Qual a data da tomada de preço deste item? (Ex.Formato: '2017-12-16 10:55:53' para 16 de dezembro de 2017, 10 horas 55 min e 53 seg) \n");
		// java.util.Date now = new Date();
		String dateTP = reader.readText();
		dateTP = reader.readText();

		// String dStr =
		// java.text.DateFormat.getDateInstance(DateFormat.LONG).format(now);

		System.out.println(dateTP);

		// String date="02/12/2004";
		// String dt = "2017-05-08 10:05:55";
		String pattern = "yyyy-mm-dd hh:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateTP);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Date:" + date);

		this.saveTakingPrice(codeBarItem, priceItem, codeSupermarket, date);

	}

	public void saveTakingPrice(int codeBarItem, double priceItem, int codeSupermarket, Date date) { // Create the
		TakingPrice takingPrice = new TakingPrice(codeBarItem, priceItem, codeSupermarket, date);
		tpm.save(takingPrice);
	}

	/*
	 * Edit the data of a supermarket
	 */
	void editTakingPrice() {
		TakingPrice tp = null;
		int codebar_item;
		int code_supermarket;
		int newCodeItem;

		printer.printMsg("Digite o código do item a ser alterado? ");
		codebar_item = reader.readNumber();

		printer.printMsg("Digite o código do supermercado a ser alterado? ");
		code_supermarket = reader.readNumber();

		printer.printMsg(
				" Digite a data da tomada de preço do item: (Ex.Formato: '2017-12-16 10:55:53' para 16 de dezembro de 2017, 10 horas 55 min e 53 seg)");
		String dateTP = reader.readText();
		dateTP = reader.readText();

		String pattern = "yyyy-mm-dd hh:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date dateTPF = null;
		try {
			dateTPF = simpleDateFormat.parse(dateTP);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// pensando em cadastrar o novo preço sem editar o preço antigo. Senão terei que
		// controlar por muitos atributos.

		if (tpm.checksExistence(codebar_item, code_supermarket, dateTPF)) {
			tp = tpm.getTakingPrice(codebar_item, code_supermarket, dateTPF);
			int codeSupermarket = tp.getCodeSupermarket();
			double priceItem = tp.getPrice();
			Date dateTPE = tp.getDate();
			int respEdit = 0;

			tpm.deleteTakingPrice(codebar_item, code_supermarket, dateTPE);

			do {
				try {
					respEdit = this.askWhatEdit(tp);

				} catch (NumeroInvalidoException e) {

				}

				if (respEdit == 1) {
					printer.printMsg(" Digite o novo código do item: ");
					newCodeItem = reader.readNumber();
					this.saveTakingPrice(newCodeItem, priceItem, codeSupermarket, dateTPE);
				} else if (respEdit == 2) {
					printer.printMsg(" Digite o novo código do supermercado: ");
					int newCodeSupermarket = 0;
					newCodeSupermarket = reader.readNumber();
					this.saveTakingPrice(codebar_item, priceItem, newCodeSupermarket, dateTPE);
				} else if (respEdit == 3) {
					printer.printMsg(" Digite o novo preço do item: ");
					double newPriceItem = 0;
					newPriceItem = reader.readNumberDouble();
					this.saveTakingPrice(codebar_item, newPriceItem, codeSupermarket, dateTPE);
				} else if (respEdit == 4) {
					printer.printMsg(
							" Digite a nova data da tomada de preço do item: (Ex.Formato: '2017-12-16 10:55:53' para 16 de dezembro de 2017, 10 horas 55 min e 53 seg)");
					String date = reader.readText();
					date = reader.readText();

					// String pattern = "yyyy-mm-dd hh:mm:ss";
					// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					Date newDateTP = null;
					try {
						newDateTP = simpleDateFormat.parse(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					System.out.println("Date:" + date);

					this.saveTakingPrice(codebar_item, priceItem, codeSupermarket, newDateTP);

				}
			} while (respEdit != 1 & respEdit != 2 & respEdit != 3 & respEdit != 4);

		} else

		{
			printer.printMsg("Não existe tomada de preço com estes códigos cadastrados.");
		}

	}

	int askWhatEdit(TakingPrice tp) throws NumeroInvalidoException {
		int respEdit = 0;

		printer.printMsg(" A tomada de preço selecionada contém os seguintes dados: ");
		printer.printMsg(" Código do item: " + tp.codeBarItem);
		printer.printMsg(" Código do supermercado : " + tp.getCodeSupermarket());
		NumberFormat monetaryFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		printer.printMsg("Preço do item :" + monetaryFormatter.format(tp.getPrice()));
		printer.printMsg("Data da tomada de preço :" + tp.getDate());
		printer.printMsg(
				" Digite para alterar: 1 -> Código do item, 2 -> Código do supermercado, 3 -> Preço do item, 4 -> Data da tomada de preço");
		respEdit = reader.readNumber();

		if (respEdit != 1 & respEdit != 2 & respEdit != 3 & respEdit != 4) {
			throw new NumeroInvalidoException(respEdit + " é um número inválido, tente novamente!");
		}

		return respEdit;
	}

	/*
	 * List all taking prices
	 */
	List<String> getData() {

		List<String> data = new ArrayList<String>();
		List<TakingPrice> takingPrices = null;
		takingPrices = tpm.listAllTakingPrices();

		for (TakingPrice takingprice : takingPrices) {
			StringBuilder sb = new StringBuilder();
			sb.append("[Código do item] : " + takingprice.getCodeBarItem() + "\n");
			sb.append("[Código do Supermercado]: " + takingprice.getCodeSupermarket() + "\n");
			sb.append("Preço: " + takingprice.getPrice() + "\n");
			sb.append("Data: " + takingprice.getDate() + "\n");
			data.add(sb.toString());
		}
		return data;
	}

	void listTakingPrice() {

		List<String> data = getData();

		for (String item : data) {
			printer.printMsg(item);
		}
	}

	/*
	 * Delete a supermarket
	 */
	void deleteTakingPrice() {
		int codebar_item = 0;
		int codeSmkt = 0;

		printer.printMsg("Digite o código do item com tomada de preço a ser excluído: ");
		codebar_item = 0;
		codebar_item = reader.readNumber();

		printer.printMsg("Digite o código do supermercado com tomada de preço a ser excluído: ");
		codeSmkt = 0;
		codeSmkt = reader.readNumber();

		printer.printMsg(
				" Digite a data da tomada de preço do item: (Ex.Formato: '2017-12-16 10:55:53' para 16 de dezembro de 2017, 10 horas 55 min e 53 seg)");
		String dateTP = reader.readText();
		dateTP = reader.readText();

		String pattern = "yyyy-mm-dd hh:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date dateTPF = null;
		try {
			dateTPF = simpleDateFormat.parse(dateTP);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (tpm.deleteTakingPrice(codebar_item, codeSmkt, dateTPF)) {
			printer.printMsg("Tomada de Preço excluído com sucesso!");
		} else {
			printer.printMsg("Não existe tomada de preço com estes códigos.");
		}
	}

}
