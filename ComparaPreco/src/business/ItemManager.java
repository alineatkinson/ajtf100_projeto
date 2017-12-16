package business;

import java.util.List;

import model.Item;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;
import persistence.PersistenceException;

public class ItemManager extends ComparePriceManager {

	private ComparePriceDAO itemtDao = new DAOFactory().getItemDAO();

	public void save(Item item) {
		super.save(item, itemtDao);
	}

	public List<Item> listAll() {
		return super.listAll(itemtDao);
	}

	public boolean delete(int objectKey) {
		return super.delete(objectKey, itemtDao);
	}

	public Item get(int objectKey) {
		return (Item) super.get(objectKey, itemtDao);
	}

	public boolean checksExistence(int objectKey) {
		return super.checksExistence(objectKey, itemtDao);
	}

}
