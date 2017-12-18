package presentation;

import business.PricesByItemManager;
import business.ItemsPricesBySupermarketManager;
import model.Item;
import model.PricesByItem;
import model.TakingPrice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class PricesByItemControllerConsole {
	private Printer printer = new Printer();
	private List<String> namesItems = new ArrayList();

	List<String> askItemsToCompare() {
		Console reader = new Console();
		printer.printMsg("Digite os itens que deseja comparar/somar: ");

		int resp;
		String itemName;
		// comparator = new ComparatorPrice();
		namesItems.clear();
		
		do {
			printer.printMsg("Digite o nome: ");
			itemName = reader.readText();
			// if (this.checkExistence(itemName)) {
			namesItems.add(itemName);
			// } else {
			// printer.printMsg("Não existe item com este nome.");
			// }
			printer.printMsg("Deseja incluir mais algum item para comparação? Sim -> 1/ Não -> 2");
			resp = reader.readNumber();

		} while (resp != 2);

		return namesItems;

	}

	void createComparation() throws SQLException {
		PricesByItemManager comparator = new PricesByItemManager();
		namesItems = this.askItemsToCompare();
		List<PricesByItem> list = null;
		list = comparator.createComparation(this.namesItems);
		this.showResults(list);
	}

	void showResults(List<PricesByItem> pricesByItem) {
		List<String> data = this.getData(pricesByItem);

		for (String item : data) {
			printer.printMsg(item);
		}

	}

	List<String> getData(List<PricesByItem> pricesByItem) {
		List<String> data = new ArrayList<String>();

		for (PricesByItem pbi : pricesByItem) {
			StringBuilder sb = new StringBuilder();
			Item item = pbi.getItem();
			sb.append("[Código de barra do item] : " + item.getBarCode() + "\n");
			sb.append("Nome do item: " + item.getName() + "\n");
			sb.append("Descrição do item: " + item.getDescription() + "\n\n");
			data.add(sb.toString());

			List<TakingPrice> tps = pbi.getTakingPrices();
			for (TakingPrice tp : tps) {
				StringBuilder sbtp = new StringBuilder();
				sbtp.append("[Código do Supermercado]: " + tp.getCodeSupermarket() + "\n");
				sbtp.append("Preço: " + tp.getPrice() + "\n");
				sbtp.append("Data: " + tp.getDate() + "\n");
				data.add(sbtp.toString());
			}
		}
		return data;
	}

}
