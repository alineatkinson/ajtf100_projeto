import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TakingPriceDAOCollections implements TakingPriceDAO {
	ConcurrentHashMap takingPrices = new ConcurrentHashMap();
	Printer printer = new Printer();

	public boolean save(int keyCodeBarItem, TakingPrice tp) {
		takingPrices.put(keyCodeBarItem, tp);
		System.out.println("Teste save dao colecctions");
		return true;
	}

	public boolean checksExistence(int keyCodeBarItem) {
		if (takingPrices.containsKey(keyCodeBarItem))
			return true;
		else
			return false;
	}

	public TakingPrice get(int keyCodeBarItem) {
		return (TakingPrice) takingPrices.get(keyCodeBarItem);
	}

	public void delete(int keyCodeBarItem) {
		TakingPrice tp = (TakingPrice) takingPrices.remove(keyCodeBarItem);
	}

	public void list() {
		Set chaves = takingPrices.keySet();
		Iterator i = chaves.iterator();
		TakingPrice tpPrint = null;
		NumberFormat monetaryFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		System.out.println("Teste list dao colecctions");
		while (i.hasNext()) {
			int chave = (int) i.next();
			tpPrint = (TakingPrice) takingPrices.get(chave);
			printer.printMsg("------------------------------------");
			printer.printMsg("[Código do item:] :" + chave);
			printer.printMsg("Código do Supermercado :" + tpPrint.getCodeSupermarket() + "\n");
			printer.printMsg("Preço do item :" + monetaryFormatter.format(tpPrint.getPrice()));
			printer.printMsg("------------------------------------");
		}
	}

}
