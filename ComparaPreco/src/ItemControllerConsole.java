import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

public class ItemControllerConsole {
	Printer printer = new Printer();
	Console reader = new Console();
	ItemDAO itemDao = new ItemDAOCollections();
	
	/*
	 * Create the item
	 */
	
	public int createItem() {

		String msg = "Qual o código de barras do item? \n";
		printer.printMsg(msg);
		int codeBar = reader.readNumber();
		Item item = new Item(codeBar);
		
		// Assign item's name
		 
		msg = "Qual o nome do item? \n";
		printer.printMsg(msg);
		String name = reader.readText();
		name = reader.readText();
		item.setNome(name);
		
		itemDao.save(item.getBarCode(), item);
		System.out.println("Item incluído nome: " + name + " " + item.getBarCode());
		return codeBar;
	}

	public void printData() {
		printer.printMsg("\n ======= Impressão do Map ======= \n");
		Set keys = items.keySet();
		Iterator i = keys.iterator();

		while (i.hasNext()) {
			System.out.println("Item código Barra: ");
			Integer key = (Integer) i.next();
			System.out.println("[Chave]: " + key);
			System.out.println(items.get(key) + "\n");
		}
	}

}
