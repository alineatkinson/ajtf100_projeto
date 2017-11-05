import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SupermarketDAOCollections implements ComparePriceDAO {

	ConcurrentHashMap supermarkets = new ConcurrentHashMap();
	Printer printer = new Printer();

	public void save(Object objSuper) {
		Supermarket supermarket = (Supermarket) objSuper;
		int chave = supermarket.getCode();
		supermarkets.put(chave, supermarket);
		System.out.println("Supermercado incluído no dao collections");
	}

	public Map getAll() {
		return (Map) supermarkets;
	}

	public boolean checksExistence(Number key) {
		if (supermarkets.containsKey(key))
			return true;
		else
			return false;
	}

	public Object get(Number key) {
		return (Supermarket) supermarkets.get(key);
	}

	public void delete(Number key) {
		Supermarket supermarket = (Supermarket) supermarkets.remove(key);
	}

}
