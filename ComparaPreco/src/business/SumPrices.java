package business;

import model.ItemsPricesBySupermarket;
import model.PricesByItem;
import model.Supermarket;
import persistence.ComparePriceByNameDAO;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;
import persistence.ItemDAOJDBC;
import persistence.ItemsPricesBySupermarketDAO;
import persistence.SupermarketDAOJDBC;
import presentation.SumPricesConsole;
import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SumPrices {

	List<ItemsPricesBySupermarket> list = new ArrayList();

	// retornar para console objeto lista
	public List<ItemsPricesBySupermarket> sumPricesBySupermarket(List<Item> items) throws SQLException {
		List<Supermarket> supermarkets = new ArrayList();
		SupermarketDAOJDBC supermarketDAO = new SupermarketDAOJDBC();
		supermarkets = supermarketDAO.getAll();
		for (Item item : items) {
			System.out.println("\n Item : " + item.getName());
		}
		for (Supermarket supermarket : supermarkets) {
			ItemsPricesBySupermarket ipbs = this.getItemsPricesBySupermarket(supermarket, items);
			if (ipbs != null) {
				list.add(ipbs);
			}
		}

		return list;

	}

	public ItemsPricesBySupermarket getItemsPricesBySupermarket(Supermarket supermarket, List<Item> items) throws SQLException {
		ItemsPricesBySupermarket ipbs = null;

		ItemsPricesBySupermarketDAO ipsDAO = new ItemsPricesBySupermarketDAO();

		ipbs = ipsDAO.getItemsPricesBySupermarket(items, supermarket);

		return ipbs;
	}

	public List<Item> getItems(List<String> namesItems) {
		List<Item> items = new ArrayList();

		for (String name : namesItems) {
			Item item = this.getItemByName(name);
			items.add(item);

		}
		return items;
	}
	
	public Item getItemByName(String name) {
		//Não deu certo dessa forma:
		//ComparePriceByNameDAO<Item> itemDao = new DAOFactory().getItemDAO();
		// como deixo compare price pra pegar o get com nome???
		ItemDAOJDBC itemDao = new ItemDAOJDBC();
		Item selectedItem = itemDao.get(name);
		return selectedItem;
	}
}
