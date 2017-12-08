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
	private Printer printer = new Printer();
	private List<String> namesItems = new ArrayList();

	protected List<String> askItemsToCompare() {
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
			// printer.printMsg("Não existe item com este nome.");
			// }
			printer.printMsg("Deseja incluir mais algum item para comparação? Sim -> 1/ Não -> 2");
			resp = reader.readNumber();

		} while (resp != 2);

		return namesItems;

	}

	protected void createComparation() throws SQLException {
		ComparatorPrice comparator = new ComparatorPrice();
		namesItems = this.askItemsToCompare();
		List<PricesByItem> list = comparator.createComparation(this.namesItems);
		this.showResults(list);
	}

	private void showResults(List<PricesByItem> pricesByItem) {
		List<String> data = this.getData(pricesByItem);

		for (String item : data) {
			printer.printMsg(item);
		}

	}

	private List<String> getData(List<PricesByItem> pricesByItem) {
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
