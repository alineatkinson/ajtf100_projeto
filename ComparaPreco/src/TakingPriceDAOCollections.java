import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TakingPriceDAOCollections implements ComparePriceDAO {
	ConcurrentHashMap takingPrices = new ConcurrentHashMap();
	Printer printer = new Printer();

	public void save(Object tp) {
		TakingPrice takingPrice = (TakingPrice) tp;
		int keyCodeBarItem = takingPrice.getCodeBarItem(); 
		takingPrices.put(keyCodeBarItem, takingPrice);
		System.out.println("Takin price colecctions salvo");
		}

	public boolean checksExistence(Number keyCodeBarItem) {
		if (takingPrices.containsKey(keyCodeBarItem))
			return true;
		else
			return false;
	}

	public TakingPrice get(Number keyCodeBarItem) {
		return (TakingPrice) takingPrices.get(keyCodeBarItem);
	}

	public void delete(Number keyCodeBarItem) {
		TakingPrice tp = (TakingPrice) takingPrices.remove(keyCodeBarItem);
	}

	
	@Override
	public Map getAll() {
		// TODO Auto-generated method stub
		return takingPrices;
	}

}
