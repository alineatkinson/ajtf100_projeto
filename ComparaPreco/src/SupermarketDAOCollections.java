import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SupermarketDAOCollections implements SupermarketDAO {
	
	ConcurrentHashMap supermarkets = new ConcurrentHashMap();
	Printer printer = new Printer();
	
	public void save(int chave, Supermarket supermarket) {
		supermarkets.put(chave , supermarket);
		System.out.println("Supermercado incluído no dao collections");
	}
	public void list() {
		Set keys = supermarkets.keySet();
		Iterator i = keys.iterator();
		Supermarket supermarketPrint = null;
		
		while (i.hasNext()) {
		int key = (int) i.next();
		printer.printMsg("[Código do Supermercado] : " + key);
		supermarketPrint = (Supermarket)supermarkets.get(key);
		printer.printMsg("Nome do supermercado: "+ supermarketPrint.getName()); 
		printer.printMsg("CEP do supermercado: "+ supermarketPrint.getCEP()+ "\n");			
		}	
		
		SupermarketDAOJDBC supermarketJDBC = new SupermarketDAOJDBC();
		try {
			List listaSuper = supermarketJDBC.getAllSupermarkets();
			String lista = listaSuper.toString();
			printer.printMsg(lista);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public boolean checksExistence(int key) {
		if (supermarkets.containsKey(key))
			return true;
		else
			return false;
	}
	public Supermarket get(int key) {
		return (Supermarket) supermarkets.get(key);
	}
	public void delete(int key) {
		Supermarket supermarket = (Supermarket) supermarkets.remove(key);
	}

}
