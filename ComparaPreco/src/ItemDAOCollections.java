import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ItemDAOCollections implements ComparePriceDAO {
	ConcurrentHashMap items = new ConcurrentHashMap();
	Printer printer = new Printer();

	public void save(Object objItem) {
		Item item = (Item) objItem;
		int key = item.getBarCode();
		items.put(key, item);
		System.out.println("Item incluído no collections");
	}

	public Map getAll() {
		return items;
	}

	public boolean checksExistence(Number key) {
		if (items.containsKey(key))
			return true;
		else
			return false;
	}

	public Object get(Number key) {
		return (Item) items.get(key);
	}

	public void delete(Number key) {
		Item item = (Item) items.remove(key);
	}

}
