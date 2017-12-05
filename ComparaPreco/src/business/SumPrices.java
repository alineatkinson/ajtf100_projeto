package business;

import model.ItemsPricesBySupermarket;
import model.Supermarket;
import persistence.ItemsPricesBySupermarketDAO;
import persistence.SupermarketDAOJDBC;
import presentation.SumPricesConsole;
import model.Item;

import java.util.ArrayList;
import java.util.List;

public class SumPrices {

	SumPricesConsole console = new SumPricesConsole();
	List<ItemsPricesBySupermarket> lista = new ArrayList();

	// retornar para console objeto lista
	public void sumPricesBySupermarket(List<Item> items) {
		List<Supermarket> supermarkets = new ArrayList();
		SupermarketDAOJDBC supermarketDAO = new SupermarketDAOJDBC();
		supermarkets = supermarketDAO.getAll();
		for(Item item: items) {
			System.out.println("\n Item : "+ item.getName());
		}
		for (Supermarket supermarket : supermarkets) {
			ItemsPricesBySupermarket ipbs = this.getItemsPricesBySupermarket(supermarket, items);
			if (ipbs != null) {
				lista.add(ipbs);
			}
		}
		console.showResults(lista);
	}

	public ItemsPricesBySupermarket getItemsPricesBySupermarket(Supermarket supermarket, List<Item> items) {
		ItemsPricesBySupermarket ipbs = null;

		ItemsPricesBySupermarketDAO ipsDAO = new ItemsPricesBySupermarketDAO();

		ipbs = ipsDAO.getItemsPricesBySupermarket(items, supermarket);

		return ipbs;
	}

}
