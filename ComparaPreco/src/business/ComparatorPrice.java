package business;

import model.Item;
import model.PricesByItem;
import java.util.ArrayList;
import java.util.List;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;
import persistence.ItemDAOJDBC;
import persistence.PriceByItemDAO;
import presentation.ComparatorPriceConsole;

public class ComparatorPrice {

	ComparatorPriceConsole console = new ComparatorPriceConsole();
	PriceByItemDAO priceByItemDAO = new PriceByItemDAO();

	// retornar obj priceByItem
	public void createComparation(List<String> namesItems) {
		List<PricesByItem> pricesByItem = new ArrayList();
		List<Item> items = new ArrayList();

		for (String name : namesItems) {
			Item item = this.getItemByName(name);
			items.add(item);
			PricesByItem pbi = this.getPriceByItem(item);
			pricesByItem.add(pbi);
		}

		console.showResults(pricesByItem);
		
		// colocar pra console chamar caso de uso
		SumPrices sumPrices = new SumPrices();
		sumPrices.sumPricesBySupermarket(items);

	}

	public Item getItemByName(String name) {
		ComparePriceDAO<Item> itemDao = new DAOFactory().getItemDAO();
		// como deixo compare price pra pegar o get com nome???
		Item selectedItem = itemDao.get(name);
		return selectedItem;
	}

	public PricesByItem getPriceByItem(Item item) {
		PricesByItem pbi;
		pbi = priceByItemDAO.getPriceByItem(item);
		return pbi;
	}
}
