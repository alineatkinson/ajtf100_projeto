package business;

import model.Item;
import model.PricesByItem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.ComparePriceByNameDAO;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;
import persistence.ItemDAOJDBC;
import persistence.PersistenceException;
import persistence.PricesByItemDAO;

public class PricesByItemManager {

	// private PricesByItemControllerConsole console = new
	// PricesByItemControllerConsole();
	private PricesByItemDAO priceByItemDAO = new PricesByItemDAO();

	// retornar obj priceByItem
	public List<PricesByItem> createComparation(List<String> namesItems)  {
		List<PricesByItem> pricesByItem = new ArrayList();
		List<Item> items = new ArrayList();

		for (String name : namesItems) {
			Item item = this.getItemByName(name);
			items.add(item);
			PricesByItem pbi = null;
			try {
				pbi = this.getPriceByItem(item);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			pricesByItem.add(pbi);
		}
		return pricesByItem;
	}

	public Item getItemByName(String name) {
		// ComparePriceByNameDAO<Item> itemDao = new DAOFactory().getItemDAO();
		// como deixo compare price pra pegar o get com nome???
		ItemDAOJDBC itemDao = new ItemDAOJDBC();
		Item selectedItem = itemDao.get(name);
		return selectedItem;
	}

	public PricesByItem getPriceByItem(Item item) throws BusinessException {
		PricesByItem pbi;
		try {
			pbi = priceByItemDAO.getPriceByItem(item);
		} catch (PersistenceException e) {
			throw new BusinessException("Erro ao recuperar um preço por item", e);
			// e.printStackTrace();
		}
		return pbi;
	}
}
