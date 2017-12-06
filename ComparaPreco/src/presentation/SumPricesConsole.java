package presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.SumPrices;
import model.Item;
import model.PricesByItem;
import model.Supermarket;
import model.TakingPrice;
import model.ItemsPricesBySupermarket;

public class SumPricesConsole {
	Printer printer = new Printer();

	public void createSumPrices() throws SQLException {
		ComparatorPriceConsole consoleComparator = new ComparatorPriceConsole();
		SumPrices sumPrices = new SumPrices();
		List<String> namesItems = consoleComparator.askItemsToCompare();
		List<Item> items = sumPrices.getItems(namesItems);
		List<ItemsPricesBySupermarket> list = sumPrices.sumPricesBySupermarket(items);
		this.showResults(list);
	}
	
	public void showResults(List<ItemsPricesBySupermarket> itemsPricesBySupermarket) {
		List<String> data = this.getData(itemsPricesBySupermarket);

		for (String itemBySuper : data) {
			printer.printMsg(itemBySuper);
		}
	}

	public List<String> getData(List<ItemsPricesBySupermarket> itemsPricesBySupermarket) {
		List<String> data = new ArrayList<String>();
		printer.printMsg("-------------------------------------------------------\n");
		printer.printMsg("TOTALIZAÇÃO POR SUPERMERCADO: ");

		for (ItemsPricesBySupermarket ipbs : itemsPricesBySupermarket) {
			StringBuilder sbtp = new StringBuilder();
			sbtp.append("-------------------------------------------------------\n");
			Supermarket supermarket = ipbs.getSupermarket();
			// StringBuilder sbSuper = new StringBuilder();

			// data.add(sbSuper.toString());
			List<Item> items = ipbs.getItems();
			for (Item item : items) {
				StringBuilder sbItem = new StringBuilder();
				// sbItem.append("[Código de barra do item] : " + item.getBarCode() + "\n");
				sbItem.append("Nome do item: " + item.getName() + "\n");
				// sbItem.append("Descrição do item: " + item.getDescription() + "\n\n");
				data.add(sbItem.toString());
			}

			List<TakingPrice> tps = ipbs.getTakingPrices();
			for (TakingPrice tp : tps) {
				StringBuilder tpBuilder = new StringBuilder();
				tpBuilder.append("[Código do item]: " + tp.getCodeBarItem() + "\n");
				tpBuilder.append("[Código do Supermercado]: " + tp.getCodeSupermarket() + "\n");
				tpBuilder.append("Preço: " + tp.getPrice() + "\n");
				tpBuilder.append("Data: " + tp.getDate() + "\n");
				data.add(tpBuilder.toString());
			}

			double sumPrices = ipbs.getSumPrice();
			sbtp.append("Total da soma para o supermercado " + supermarket.getName() + " : " + sumPrices + "\n");
			sbtp.append("------------------------------------------------------\n");
			data.add(sbtp.toString());
		}

		return data;
	}

}
