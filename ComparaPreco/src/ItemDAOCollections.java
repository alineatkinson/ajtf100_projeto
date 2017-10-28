import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ItemDAOCollections implements ItemDAO{
	ConcurrentHashMap items = new ConcurrentHashMap();
	Printer printer = new Printer();
	
	public boolean save(int chave, Item item) {
		items.put(chave , item);
		System.out.println("Item incluído");
		return true;
	}
	
	public void list() {
		Set keys = items.keySet();
		Iterator i = keys.iterator();
		Item itemPrint = null;
		
		while (i.hasNext()) {
		int key = (int) i.next();
		printer.printMsg("[Código de Barras] : " + key);
		itemPrint = (Item)items.get(key);
		printer.printMsg("Nome do item: "+ itemPrint.getName()+"\n"); 
					
		}	
	}
	public boolean checksExistence(int key) {
		if (items.containsKey(key))
			return true;
		else
			return false;
	}
	public Item get(int key) {
		return (Item) items.get(key);
	}
	public void delete(int key) {
		Item item = (Item) items.remove(key);
	}
}
