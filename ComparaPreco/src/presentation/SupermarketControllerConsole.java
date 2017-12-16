package presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import business.SupermarketManager;
import model.Supermarket;
import model.User;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;
import persistence.PersistenceException;

/*
 * Create the supermarket
 */
class SupermarketControllerConsole {
	private Printer printer = new Printer();
	private Console reader = new Console();
	private SupermarketManager sm = new SupermarketManager();

	void createSupermarket() {

		// Assign supermarket name attribute to the item
		printer.printMsg("Qual o código do supermercado? (Código int) \n");
		int codeSupermarket = reader.readNumber();

		// Ask the supermarket's name
		printer.printMsg("Qual o nome deste supermercado? \n");
		String nameSuper = reader.readText();
		// nameSuper = reader.readText();

		// Ask the supermarket's code adress
		printer.printMsg("Qual o cep deste supermercado? (utilize somente números) \n");
		int cepSuper = reader.readNumber();
		this.saveSupermarket(nameSuper, cepSuper, codeSupermarket);
	}

	void saveSupermarket(String nameSuper, int cepSuper, int codeSupermarket) {
		Supermarket supermarket = new Supermarket(nameSuper, cepSuper, codeSupermarket);
		sm.save(supermarket);
	}

	/*
	 * Edit the data of a supermarket
	 */
	void editSupermarket() {

		printer.printMsg("Digite o código do supermercado a ser alterado: ");
		int supermarketKey = reader.readNumber();
		Supermarket supermarket = null;

		if (sm.checksExistence(supermarketKey)) {
			supermarket = (Supermarket) sm.get(supermarketKey);

			int respEdit = 0;
			sm.delete(supermarketKey);

			do {
				try {
					respEdit = this.askWhatEdit(supermarket);
				} catch (NumeroInvalidoException e) {
					// printer.printMsg("Opção inválida, tente novamente! \n");
					// e.printStackTrace();
				}
				if (respEdit == 1) {
					printer.printMsg(" Digite o novo nome do supermercado: ");
					String newNome = new String();
					newNome = " ";
					newNome = reader.readText();
					// newNome = reader.readText();
					this.saveSupermarket(newNome, supermarket.getCEP(), supermarket.getCode());

				} else if (respEdit == 2) {
					printer.printMsg(" Digite o novo código do supermercado: ");
					int newCodeSupermarket = 0;
					newCodeSupermarket = reader.readNumber();
					this.saveSupermarket(supermarket.getName(), supermarket.getCEP(), newCodeSupermarket);

				} else if (respEdit == 3) {
					printer.printMsg(" Digite o novo CEP do supermercado: ");
					int newCEPSupermarket = 0;
					newCEPSupermarket = reader.readNumber();
					this.saveSupermarket(supermarket.getName(), newCEPSupermarket, supermarket.getCode());
				} else {
					printer.printMsg("Nenhuma alternativa válida foi digitada. Tente outra vez!");
				}
			} while (respEdit != 1 & respEdit != 2 & respEdit != 3);

		} else {
			printer.printMsg("Não existe supermercado com este código cadastrado.");
		}
	}

	int askWhatEdit(Supermarket sm) throws NumeroInvalidoException {
		printer.printMsg(" O supermercado selecionado contém os seguintes dados: ");
		printer.printMsg(" Nome : " + sm.getName());
		printer.printMsg(" Código : " + sm.getCode());
		printer.printMsg(" CEP : " + sm.getCEP());
		printer.printMsg(" Digite para alterar: 1 -> Nome do supermercado, 2 -> Código do supermercado, 3 -> CEP");
		int respEdit = 0;
		respEdit = reader.readNumber();
		if (respEdit != 1 & respEdit != 2 & respEdit != 3) {
			throw new NumeroInvalidoException(respEdit + " é um número inválido, tente novamente!");
		}
		return respEdit;
	}

	List<String> getData() {
		List<String> data = new ArrayList<String>();
		List<Supermarket> supermarkets;
		supermarkets = sm.listAll();

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

	void listSupermarkets() {

		List<String> data = getData();

		for (String supermarket : data) {
			printer.printMsg(supermarket);
		}
	}

	/*
	 * Delete a supermarket
	 */
	void deleteSupermarket() {
		printer.printMsg("Digite o código do supermercado a ser deletado: ");
		int supermarketKey = 0;
		supermarketKey = reader.readNumber();
		sm.delete(supermarketKey);

		if (sm.delete(supermarketKey)) {
			printer.printMsg("Supermercado excluído com sucesso!");
		} else {
			printer.printMsg("Não há supermercado com este código.");
		}

	}

}
