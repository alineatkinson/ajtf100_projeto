import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

public class ItemController {
	ConcurrentHashMap items = new ConcurrentHashMap();

	/*
	 * Create the item
	 */
	public int createItem() {

		Printer printer = new Printer();
		Console reader = new Console();

		// @Test
		String msg = "Qual o código de barras do item? \n";
		printer.printMsg(msg);
		int codeBar = reader.readNumber();
		Item item = new Item(codeBar);
		Assert.assertEquals(codeBar, item.getBarCode());

		// Assign item's name
		// @Test
		msg = "Qual o nome do item? \n";
		printer.printMsg(msg);
		String name = reader.readText();
		name = reader.readText();
		item.setNome(name);
		items.put(item.getBarCode(), item);
		System.out.println("Item incluído nome: " + name + " " + item.getBarCode());
		Assert.assertEquals(name, item.getName());
		return codeBar;
	}

	public void imprimeDados() {
		System.out.println("\n ======= Impressão do Map ======= \n");
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
