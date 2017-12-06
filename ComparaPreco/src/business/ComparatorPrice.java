package business;

import model.Item;
import model.PricesByItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.ComparePriceByNameDAO;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;
import persistence.ItemDAOJDBC;
import persistence.PriceByItemDAO;
import presentation.ComparatorPriceConsole;

public class ComparatorPrice {

	ComparatorPriceConsole console = new ComparatorPriceConsole();
	PriceByItemDAO priceByItemDAO = new PriceByItemDAO();

	// retornar obj priceByItem
	public List<PricesByItem> createComparation(List<String> namesItems) throws SQLException {
		List<PricesByItem> pricesByItem = new ArrayList();
		List<Item> items = new ArrayList();

		for (String name : namesItems) {
			Item item = this.getItemByName(name);
			items.add(item);
			PricesByItem pbi = this.getPriceByItem(item);
			pricesByItem.add(pbi);
		}
		return pricesByItem;
	}

	public Item getItemByName(String name) {
		//ComparePriceByNameDAO<Item> itemDao = new DAOFactory().getItemDAO();
		// como deixo compare price pra pegar o get com nome???
		ItemDAOJDBC itemDao = new ItemDAOJDBC();
		Item selectedItem = itemDao.get(name);
		return selectedItem;
	}

	public PricesByItem getPriceByItem(Item item) throws SQLException {
		PricesByItem pbi;
		pbi = priceByItemDAO.getPriceByItem(item);
		return pbi;
	}
}
