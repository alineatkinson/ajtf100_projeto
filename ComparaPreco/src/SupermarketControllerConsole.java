import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Create the supermarket
 */
public class SupermarketControllerConsole {

	// Supermarket[] supermarket = new Supermarket[20];
	// Collection supermercados = new ArrayList();
	ConcurrentHashMap supermarkets = new ConcurrentHashMap();
	Printer printer = new Printer();
	Console reader = new Console();

	public int createSupermarket() {

		Supermarket supermarket;

		// Assign supermarket name attribute to the item
		printer.printMsg("Qual o código do supermercado? (Código int) \n");
		int codeSupermarket = reader.readNumber();

		// Ask the supermarket's name
		printer.printMsg("Qual o nome deste supermercado? \n");
		String nameSuper = reader.readText();
		nameSuper = reader.readText();

		// Ask the supermarket's code adress
		printer.printMsg("Qual o cep deste supermercado? (utilize somente números) \n");
		int cepSuper = reader.readNumber();

		// Create the supermarket with the name, address and code supermarket number
		supermarket = new Supermarket(nameSuper, cepSuper, codeSupermarket);
		//supermarkets.put(supermarket.getCode(), supermarket);
		return codeSupermarket;
	}

	public void printData() {
		System.out.println("\n ======= Impressão do Map ======= \n");
		Set keys = supermarkets.keySet();
		Iterator i = keys.iterator();

		while (i.hasNext()) {
			System.out.println("Item código Barra: ");
			Integer key = (Integer) i.next();
			System.out.println("[Chave]: " + key);
			System.out.println(supermarkets.get(key) + "\n");
		}
	}
}
