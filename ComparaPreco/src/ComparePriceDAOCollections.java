import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ComparePriceDAOCollections<E>  implements ComparePriceDAO {
	ConcurrentHashMap objects = new ConcurrentHashMap();
	Printer printer = new Printer();

	public void save(Object objSuper) {
		E object = (E)objSuper;
		int key = object.hashCode(); //implementar em cada classe o hashCode
		objects.put(key, object);
		System.out.println("objeto incluído no dao collections");
	}

	public Map getAll() {
		return (Map) objects;
	}

	public boolean checksExistence(Number key) {
		if (objects.containsKey(key))
			return true;
		else
			return false;
	}

	public Object get(Number key) {
		return (E) objects.get(key);
	}

	public void delete(Number key) {
		E object = (E) objects.remove(key);
	}

}

