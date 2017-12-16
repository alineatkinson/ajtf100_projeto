package presentation;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import model.Item;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;
import persistence.PersistenceException;

class ItemControllerConsole {
	private Printer printer = new Printer();
	private Console reader = new Console();
	private ComparePriceDAO itemDao = new DAOFactory().getItemDAO();

	/*
	 * Create the item
	 */

	void createItem() {
		// Ask and assign item's code bar
		String msg = "Qual o c�digo de barras do item? \n";
		printer.printMsg(msg);
		int codeBar = reader.readNumber();

		// Ask and assign item's name
		msg = "Qual o nome do item? \n";
		printer.printMsg(msg);
		String name = reader.readText();
		// name = reader.readText();

		// Ask and assign item's description
		msg = "Qual a descri��o do item? \n";
		printer.printMsg(msg);
		String description = reader.readText();

		// Save the item
		this.save(codeBar, name, description);

	}

	void save(int codeBar, String name, String description) {
		Item item = new Item(codeBar, name, description);
		itemDao.save(item);
	}

	/*
	 * Edit the data of a item
	 */
	void editItem() {

		printer.printMsg("Digite o c�digo de barras do item a ser alterado: ");
		int itemKey = reader.readNumber();
		Item item = null;

		if (itemDao.checksExistence(itemKey)) {
			item = (Item) itemDao.get(itemKey);
			int respEdit = 0;
			itemDao.delete(itemKey);

			do {
				try {
					respEdit = this.askWhatEdit(item);
				} catch (NumeroInvalidoException e) {
					// printer.printMsg("Escolha a op��o novamente: ");
					// e.printStackTrace();
				}
				if (respEdit == 1) {
					printer.printMsg(" Digite o novo c�digo de barras: ");
					int newCodeBar = 0;
					newCodeBar = reader.readNumber();
					this.save(newCodeBar, item.getName(), item.getDescription());

				} else if (respEdit == 2) {
					printer.printMsg(" Digite o novo nome do item: ");
					String newName = new String();
					newName = " ";
					newName = reader.readText();
					// newName = reader.readText();
					this.save(item.getBarCode(), newName, item.getDescription());

				} else if (respEdit == 3) {
					printer.printMsg(" Digite a nova descri��o do item: ");
					String newDescription = new String();
					newDescription = " ";
					newDescription = reader.readText();
					// newDescription = reader.readText();
					this.save(item.getBarCode(), item.getName(), newDescription);

				} else {
					printer.printMsg("Nenhuma alternativa v�lida foi digitada. Tente outra vez!");
				}
			} while (respEdit != 1 & respEdit != 2 & respEdit != 3);

		} else {
			printer.printMsg("N�o existe item com este c�digo de barra cadastrado.");
		}
	}

	int askWhatEdit(Item item) throws NumeroInvalidoException {
		printer.printMsg(" O item selecionado cont�m os seguintes dados: ");
		printer.printMsg(" C�digo de barras : " + item.getBarCode());
		printer.printMsg(" Nome do  item: " + item.getName());
		printer.printMsg(" Descri��o do  item: " + item.getDescription());
		printer.printMsg(" Digite para alterar: 1 -> C�digo de barras , 2 -> Nome do item,  3 -> Descri��o");
		int respEdit = reader.readNumber();
		if (respEdit != 1 & respEdit != 2 & respEdit != 3) {
			throw new NumeroInvalidoException(respEdit + " � um n�mero inv�lido, tente novamente! \n");
		}
		return respEdit;
	}

	List<String> getData() {

		List<String> data = new ArrayList<String>();
		List<Item> items;
		try {
			items = itemDao.getAll();

			for (Item item : items) {
				StringBuilder sb = new StringBuilder();
				sb.append("[C�digo de barra do item] : " + item.getBarCode() + "\n");
				sb.append("Nome do item: " + item.getName() + "\n");
				sb.append("Descri��o do item: " + item.getDescription() + "\n");
				data.add(sb.toString());
			}
		} catch (PersistenceException e) {
			printer.printMsg(e.getMessage());
		}

		return data;
	}

	/*
	 * List all items
	 */
	void listItems() {

		List<String> data = getData();

		for (String item : data) {
			printer.printMsg(item);
		}
	}

	/*
	 * Delete a item
	 */
	void deleteItem() {

		printer.printMsg("Digite o c�digo de barras do item a ser deletado: ");
		int itemKey = 0;
		itemKey = reader.readNumber();

		if (itemDao.checksExistence(itemKey)) {
			itemDao.delete(itemKey);
		} else {
			printer.printMsg("N�o h� item com este c�digo.");
		}

	}

}
