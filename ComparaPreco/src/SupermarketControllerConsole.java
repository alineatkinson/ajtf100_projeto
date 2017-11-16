import java.sql.SQLException;
import java.util.ArrayList;
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

	Printer printer = new Printer();
	Console reader = new Console();
	// ComparePriceDAO supermarketDao = new SupermarketDAOCollections(); new
	// SupermarketDAOJDBC();
	ComparePriceDAO supermarketDao = new DAOFactory().getSupermarketDAO();

	public void createSupermarket() {

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
		this.save(nameSuper, cepSuper, codeSupermarket);
	}

	public void save(String nameSuper, int cepSuper, int codeSupermarket) {
		// Create the supermarket with the name, address and code supermarket number
		Supermarket supermarket = new Supermarket(nameSuper, cepSuper, codeSupermarket);
		supermarketDao.save(supermarket);
	}

	/*
	 * Edit the data of a supermarket
	 */
	public void editSupermarket() {

		printer.printMsg("Digite o código do supermercado a ser alterado: ");
		int supermarketKey = reader.readNumber();
		Supermarket supermarket = null;

		if (supermarketDao.checksExistence(supermarketKey)) {
			supermarket = (Supermarket) supermarketDao.get(supermarketKey);

			int respEdit = 0;
			supermarketDao.delete(supermarketKey);

			do {
				respEdit = this.askWhatEdit(supermarket);
				if (respEdit == 1) {
					printer.printMsg(" Digite o novo nome do supermercado: ");
					String newNome = new String();
					newNome = " ";
					newNome = reader.readText();
					newNome = reader.readText();
					this.save(newNome, supermarket.getCEP(), supermarket.getCode());

				} else if (respEdit == 2) {
					printer.printMsg(" Digite o novo código do supermercado: ");
					int newCodeSupermarket = 0;
					newCodeSupermarket = reader.readNumber();
					this.save(supermarket.getName(), supermarket.getCEP(), newCodeSupermarket);

				} else if (respEdit == 3) {
					printer.printMsg(" Digite o novo CEP do supermercado: ");
					int newCEPSupermarket = 0;
					newCEPSupermarket = reader.readNumber();
					this.save(supermarket.getName(), newCEPSupermarket, supermarket.getCode());
				} else {
					printer.printMsg("Nenhuma alternativa válida foi digitada. Tente outra vez!");
				}
			} while (respEdit != 1 & respEdit != 2 & respEdit != 3);

		} else {
			printer.printMsg("Não existe supermercado com este código cadastrado.");
		}
	}

	public int askWhatEdit(Supermarket sm) {
		printer.printMsg(" O supermercado selecionado contém os seguintes dados: ");
		printer.printMsg(" Nome : " + sm.getName());
		printer.printMsg(" Código : " + sm.getCode());
		printer.printMsg(" CEP : " + sm.getCEP());
		printer.printMsg(" Digite para alterar: 1 -> Nome do supermercado, 2 -> Código do supermercado, 3 -> CEP");
		int respEdit = 0;
		return respEdit = reader.readNumber();
	}

	public List<String> getData() {

		List<String> data = new ArrayList<String>();
		List<Supermarket> supermarkets = supermarketDao.getAll();

		for (Supermarket supermarket : supermarkets) {
			StringBuilder sb = new StringBuilder();
			String codigoSuper = "[Código do Supermercado] : " + supermarket.getCode() + " \n";
			sb.append(codigoSuper);
			String nomeSuper = "Nome do supermercado: " + supermarket.getName() + " \n";
			sb.append(nomeSuper);
			String cepSuper = "CEP do supermercado: " + supermarket.getCEP() + "\n";
			sb.append(cepSuper);
			data.add(sb.toString());
		}
		return data;
	}

	public void listSupermarkets() {

		List<String> data = getData();

		for (String supermarket : data) {
			printer.printMsg(supermarket);
		}
	}

	/*
	 * List all supermarkets
	 */
	/*
	 * public void listSupermarkets() { List<Supermarket> supermarkets =
	 * supermarketDao.getAll();
	 * 
	 * Iterator<Supermarket> i = supermarkets.iterator(); Supermarket
	 * supermarketPrint = null;
	 * 
	 * while (i.hasNext()) { supermarketPrint = i.next();
	 * printer.printMsg("[Código do Supermercado] : " + supermarketPrint.getCode());
	 * printer.printMsg("Nome do supermercado: " + supermarketPrint.getName());
	 * printer.printMsg("CEP do supermercado: " + supermarketPrint.getCEP() + "\n");
	 * } }
	 */

	/*
	 * Delete a supermarket
	 */
	public void deleteSupermarket() {
		printer.printMsg("Digite o código do supermercado a ser deletado: ");
		int supermarketKey = 0;
		supermarketKey = reader.readNumber();

		if (supermarketDao.checksExistence(supermarketKey)) {
			supermarketDao.delete(supermarketKey);
		} else {
			printer.printMsg("Não há supermercado com este código.");
		}
	}

}
