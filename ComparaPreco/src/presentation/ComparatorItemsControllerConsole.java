package presentation;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import model.ComparatorTakingPrice;
import model.Item;
import model.Supermarket;
import model.TakingPrice;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;

public class ComparatorItemsControllerConsole {
	Printer printer = new Printer();
	Console reader = new Console();
	ComparePriceDAO supermarketDao = new DAOFactory().getSupermarketDAO();
	ComparePriceDAO itemDao = new DAOFactory().getItemDAO();
	ComparePriceDAO takingPriceDao = new DAOFactory().getTakingPriceDAO();

	public void createComparation() {
		Item selectedItem;
		List<Supermarket> supermarkets;
		List<TakingPrice> selectedSortedTakingPrices;
		List resultsComparations;

		List<String> nameItems = askItemsToCompare();
		int productsQtt = nameItems.size();
		// int qdtItens = nameItems.size();
		System.out.println("quantidade de nomes: " + productsQtt);

		if (nameItems != null) {
			for (int i = 0; i < productsQtt; i++) {
				System.out.println("\n LOOP: " + i);
				selectedItem = getSelectItem(nameItems.get(i));
				System.out.println(" código do item selecionado pelo nome :" + selectedItem.getBarCode());
				supermarkets = selectSupermarkets();
				selectedSortedTakingPrices = selectTakingPrices(selectedItem, supermarkets);
				resultsComparations = comparePriceItems(selectedItem, selectedSortedTakingPrices, supermarkets);
				showResults(resultsComparations);
			}
		} else {
			printer.printMsg("\n Não foi encontrado item com este nome na base de dados.");
		}
	}

	public List<String> askItemsToCompare() {

		printer.printMsg("Digite os itens que deseja comparar: ");
		List<String> list = new ArrayList();
		int resp;
		String itemName;

		do {
			printer.printMsg("Digite o nome: ");
			itemName = reader.readText();
			// if (this.checkExistence(itemName)) {
			list.add(itemName);
			// } else {
			// printer.printMsg("Não existe item com este nome.");
			// }

			printer.printMsg("Deseja incluir mais algum item para comparação? Sim -> 1/ Não -> 2");
			resp = reader.readNumber();

		} while (resp != 2);

		return list;
	}

	public Item getSelectItem(String nameItem) {
		List<Item> list = new ArrayList();
		list = itemDao.getAll();
		Item item = null;

		for (Item oItem : list) {
			if (oItem.getName().equals(nameItem)) {
				item = oItem;
			}
		}
		return item;
	}

	public List selectSupermarkets() {
		List<Supermarket> list = new ArrayList();

		// List<Supermarket> selectedSupermarkets = new ArrayList();

		list = supermarketDao.getAll();
		/*
		 * list = supermarketDao.getAll(); for (TakingPrice tp : takingPrices) { for
		 * (Supermarket smk : list) { if (tp.getCodeSupermarket() == smk.getCode()) {
		 * selectedSupermarkets.add(smk); } } }
		 */
		System.out.println("SAiu do select supermarkets com lista = ");
		return list;
	}

	public List selectTakingPrices(Item item, List<Supermarket> supermarkets) {
		List<TakingPrice> list = new ArrayList();

		List<TakingPrice> selectedTP = new ArrayList();

		list = takingPriceDao.getAll();
		if (supermarkets != null) {
			System.out.println("supermarkets = " + supermarkets.toString());
		}
		if (list != null) {
			System.out.println("lista tomada de preço = " + list.toString());
		}
		System.out.println("Item nome :" + item.getName());
		System.out.println("Item código :" + item.getBarCode());

		for (Supermarket supermkt : supermarkets) {
			System.out.println("Código super:" + supermkt.getCode());

			for (TakingPrice tp : list) {

				if (item.getBarCode() == tp.getCodeBarItem() & supermkt.getCode() == tp.getCodeSupermarket()) {
					selectedTP.add(tp);
				}
			}
		}
		Collections.sort(selectedTP, new ComparatorTakingPrice());
		return selectedTP;
	}

	/*
	 * public boolean checkExistence(String nameItems) {
	 * 
	 * ComparePriceDAO itemDao = new DAOFactory().getItemDAO();
	 * 
	 * if (itemDao.checksExistence(nameItems)) { return true; } else { return false;
	 * }
	 * 
	 * }
	 */

	public List<String> comparePriceItems(Item item, List<TakingPrice> selectedSortedTakingPrices,
			List<Supermarket> selectedSupermarkets) {

		List<String> resultsComparation = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();
		sb.append("------ Comparação para item: ---------\n");
		sb.append("[Código de barra do item] : " + item.getBarCode() + "\n");
		sb.append(" Nome do item: " + item.getName() + "\n");
		sb.append(" Descrição do item: " + item.getDescription() + "\n");
		sb.append("------------------------------------\n");
		resultsComparation.add(sb.toString());

		Collections.sort(selectedSortedTakingPrices, new ComparatorTakingPrice());

		for (TakingPrice tp : selectedSortedTakingPrices) {
			for (Supermarket supermarket : selectedSupermarkets) {
				// System.out.println("Código do item : " + item.getBarCode() + "\n");
				// System.out.println("Código do item na tp : " + tp.getCodeBarItem() + "\n");
				// System.out.println("Código do supermercado : " + supermarket.getCode() +
				// "\n");
				// System.out.println("Código do supermercado na tp : " +
				// tp.getCodeSupermarket() + "\n");
				if (item.getBarCode() == tp.getCodeBarItem() & supermarket.getCode() == tp.getCodeSupermarket()) {
					// System.out.print("\nEntrou no if do compare");
					// System.out.println("Nomea do supermercado: " + supermarket.getName());
					StringBuilder sbTP = new StringBuilder();
					sbTP.append("Nomea do supermercado: " + supermarket.getName() + "\n");
					// NumberFormat monetaryFormatter = NumberFormat.getCurrencyInstance(new
					// Locale("pt", "BR"));
					// sb.append("Preço do item :" + monetaryFormatter.format(tp.getPrice()) +
					// "\n");
					sbTP.append("Preço do item :" + tp.getPrice() + "\n");
					// System.out.println("Preço do item :" + tp.getPrice() + "\n");
					// Collections.sort(resultsComparation);
					resultsComparation.add(sbTP.toString());

				}
			}
		}
		return resultsComparation;
	}

	public void showResults(List<String> resultsComparations) {

		for (String resultComparation : resultsComparations) {
			printer.printMsg(resultComparation);
		}
	}

}
