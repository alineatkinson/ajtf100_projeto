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
	SupermarketDAO supermarketDao = new SupermarketDAOCollections();

	public void createSupermarket() {

		Supermarket supermarket;

		// Assign supermarket name attribute to the item
		printer.printMsg("Qual o c�digo do supermercado? (C�digo int) \n");
		int codeSupermarket = reader.readNumber();

		// Ask the supermarket's name
		printer.printMsg("Qual o nome deste supermercado? \n");
		String nameSuper = reader.readText();
		nameSuper = reader.readText();

		// Ask the supermarket's code adress
		printer.printMsg("Qual o cep deste supermercado? (utilize somente n�meros) \n");
		int cepSuper = reader.readNumber();

		// Create the supermarket with the name, address and code supermarket number
		supermarket = new Supermarket(nameSuper, cepSuper, codeSupermarket);
		supermarketDao.save(codeSupermarket, supermarket);
	}

	/*
	 * Edit the data of a supermarket
	 */
	public void editSupermarket() {

		printer.printMsg("Digite o c�digo do supermercado a ser alterado: ");
		int supermarketKey = reader.readNumber();
		Supermarket supermarket = null;

		if (supermarketDao.checksExistence(supermarketKey)) {
			supermarket = (Supermarket) supermarketDao.get(supermarketKey);
			String nameSupermarket = supermarket.getName();
			int cepSupermarket = supermarket.getCEP();
			printer.printMsg(" O supermercado selecionado cont�m os seguintes dados: ");
			printer.printMsg(" Nome : " + nameSupermarket);
			printer.printMsg(" C�digo : " + supermarketKey);
			printer.printMsg(" CEP : " + cepSupermarket);
			printer.printMsg(" Digite para alterar: 1 -> Nome do item, 2 -> C�digo do supermercado, 3 -> CEP");
			int respEdit = 0;
			respEdit = reader.readNumber();

			supermarketDao.delete(supermarketKey);

			do {
				if (respEdit == 1) {
					printer.printMsg(" Digite o novo nome do supermercado: ");
					String newNome = new String();
					newNome = " ";
					newNome = reader.readText();
					newNome = reader.readText();
					Supermarket newSupermarket = new Supermarket(newNome, cepSupermarket, supermarketKey);
					supermarketDao.save(supermarketKey, newSupermarket);
				} else if (respEdit == 2) {
					printer.printMsg(" Digite o novo c�digo do supermercado: ");
					int newCodeSupermarket = 0;
					newCodeSupermarket = reader.readNumber();
					Supermarket newSupermarket = new Supermarket(nameSupermarket, cepSupermarket, newCodeSupermarket);
					supermarketDao.save(newCodeSupermarket, newSupermarket);
				} else if (respEdit == 3) {
					printer.printMsg(" Digite o novo CEP do supermercado: ");
					int newCEPSupermarket = 0;
					newCEPSupermarket = reader.readNumber();
					Supermarket newSupermarket = new Supermarket(nameSupermarket, newCEPSupermarket, supermarketKey);
					supermarketDao.save(supermarketKey, newSupermarket);
				} else {
					printer.printMsg("Nenhuma alternativa v�lida foi digitada. Tente outra vez!");
				}
			} while (respEdit != 1 & respEdit != 2 & respEdit != 3);

		} else {
			printer.printMsg("N�o existe supermercado com este c�digo cadastrado.");
		}
	}

	/*
	 * List all supermarkets
	 */
	public void listSupermarkets() {
		supermarketDao.list();
	}

	/*
	 * Delete a supermarket
	 */
	public void deleteSupermarket() {
		printer.printMsg("Digite o c�digo do supermercado a ser deletado: ");
		int supermarketKey = 0;
		supermarketKey = reader.readNumber();

		if (supermarketDao.checksExistence(supermarketKey)) {
			supermarketDao.delete(supermarketKey);
		} else {
			printer.printMsg("N�o existe supermercado com este c�digo.");
		}
	}

}
