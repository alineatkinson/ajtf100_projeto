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
import persistence.PersistenceException;

public class ComparatorItemsControllerConsole {
	private Printer printer = new Printer();
	private Console reader = new Console();
	private ComparePriceDAO supermarketDao = new DAOFactory().getSupermarketDAO();
	private ComparePriceDAO itemDao = new DAOFactory().getItemDAO();
	private ComparePriceDAO takingPriceDao = new DAOFactory().getTakingPriceDAO();

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
		try {
			list = itemDao.getAll();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		try {
			list = supermarketDao.getAll();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List selectTakingPrices(Item item, List<Supermarket> supermarkets) {
		List<TakingPrice> list = new ArrayList();

		List<TakingPrice> selectedTP = new ArrayList();
		try {
			list = takingPriceDao.getAll();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				if (item.getBarCode() == tp.getCodeBarItem() & supermarket.getCode() == tp.getCodeSupermarket()) {
					StringBuilder sbTP = new StringBuilder();
					sbTP.append("Nomea do supermercado: " + supermarket.getName() + "\n");
					sbTP.append("Preço do item :" + tp.getPrice() + "\n");
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
