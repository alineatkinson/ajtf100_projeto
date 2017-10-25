import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TakingPriceControllerConsole {
	//ConcurrentHashMap tomandaDePrecos = new ConcurrentHashMap();
	Printer printer = new Printer();
	Console reader = new Console();

	public void createTomadaDePreco(int barCodeItem, int codeSupermarket) {

		
		// Assign item's price
		printer.printMsg("Qual o pre�o do item? \n");
		int price = reader.readNumber();
		// Date dateTP = new Date();
		
		TakingPrice tomadaPreco = new TakingPrice(barCodeItem, price, codeSupermarket);
		//tomandaDePrecos.put(barCodeItem, tomadaPreco);
		System.out.println("Tomada Pre�o Criada");
	}
	
	public void printData() {
		System.out.println("\n ======= Impress�o do Map ======= \n");
		Set keys = tomandaDePrecos.keySet();
		Iterator i = keys.iterator();

		while (i.hasNext()) {
			System.out.println("Item c�digo Barra: ");
			Integer key = (Integer) i.next();
			System.out.println("[Chave]: " + key);
			System.out.println(tomandaDePrecos.get(key) + "\n");
		}
	}
}
