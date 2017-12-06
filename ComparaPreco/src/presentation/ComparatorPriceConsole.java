package presentation;

import business.ComparatorPrice;
import business.SumPrices;
import model.Item;
import model.PricesByItem;
import model.TakingPrice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComparatorPriceConsole {
	Printer printer = new Printer();
	List<String> namesItems = new ArrayList();

	public List<String> askItemsToCompare() {
		Console reader = new Console();
		printer.printMsg("Digite os itens que deseja comparar/somar: ");

		int resp;
		String itemName;
		// comparator = new ComparatorPrice();

		do {
			printer.printMsg("Digite o nome: ");
			itemName = reader.readText();
			// if (this.checkExistence(itemName)) {
			namesItems.add(itemName);
			// } else {
			// printer.printMsg("N�o existe item com este nome.");
			// }
			printer.printMsg("Deseja incluir mais algum item para compara��o? Sim -> 1/ N�o -> 2");
			resp = reader.readNumber();

		} while (resp != 2);

		return namesItems;

	}

	public void createComparation() throws SQLException {
		ComparatorPrice comparator = new ComparatorPrice();
		namesItems = this.askItemsToCompare();
		List<PricesByItem> list = comparator.createComparation(this.namesItems);
		this.showResults(list);
	}

	public void showResults(List<PricesByItem> pricesByItem) {
		List<String> data = this.getData(pricesByItem);

		for (String item : data) {
			printer.printMsg(item);
		}

	}

	public List<String> getData(List<PricesByItem> pricesByItem) {
		List<String> data = new ArrayList<String>();

		for (PricesByItem pbi : pricesByItem) {
			StringBuilder sb = new StringBuilder();
			Item item = pbi.getItem();
			sb.append("[C�digo de barra do item] : " + item.getBarCode() + "\n");
			sb.append("Nome do item: " + item.getName() + "\n");
			sb.append("Descri��o do item: " + item.getDescription() + "\n\n");
			data.add(sb.toString());

			List<TakingPrice> tps = pbi.getTakingPrices();
			for (TakingPrice tp : tps) {
				StringBuilder sbtp = new StringBuilder();
				sbtp.append("[C�digo do Supermercado]: " + tp.getCodeSupermarket() + "\n");
				sbtp.append("Pre�o: " + tp.getPrice() + "\n");
				sbtp.append("Data: " + tp.getDate() + "\n");
				data.add(sbtp.toString());
			}
		}
		return data;
	}

	/*
	 * List all items
	 */
	/*
	 * public void listItems() {
	 * 
	 * List<String> data = getData();
	 * 
	 * for (String item : data) { printer.printMsg(item); } }
	 */

}
