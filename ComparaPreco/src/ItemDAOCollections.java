import java.util.concurrent.ConcurrentHashMap;

public class ItemDAOCollections implements ItemDAO{
	ConcurrentHashMap items = new ConcurrentHashMap();
	
	public void save(int chave, Item item) {
		items.put(chave , item);
		
	}
}
