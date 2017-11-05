import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

public class ItemControllerConsole {
	Printer printer = new Printer();
	Console reader = new Console();
	//ComparePriceDAO itemDao = new ItemDAOCollections();
	ComparePriceDAO itemDao = new ItemDAOJDBC();

	/*
	 * Create the item
	 */

	public void createItem() {
		// Ask and assign item's code bar
		String msg = "Qual o código de barras do item? \n";
		printer.printMsg(msg);
		int codeBar = reader.readNumber();
		
		// Ask and assign item's name
		msg = "Qual o nome do item? \n";
		printer.printMsg(msg);
		String name = reader.readText();
		name = reader.readText();
		
		// Ask and assign item's description
		msg = "Qual a descrição do item? \n";
		printer.printMsg(msg);
		String description = reader.readText();
		
		
		// Save the item
		itemDao.save(new Item(codeBar, name, description));
		System.out.println("Item incluído nome: " + name + " Código de barras " + codeBar);

	}

	/*
	 * Edit the data of a item
	 */
	public void editItem() {

		printer.printMsg("Digite o código de barras do item a ser alterado: ");
		int itemKey = reader.readNumber();
		Item item = null;

		if (itemDao.checksExistence(itemKey)) {
			item = (Item) itemDao.get(itemKey);
			String nameItem = item.getName();
			String description = item.getDescription();
			int respEdit = 0;

			do {
				printer.printMsg(" O item selecionado contém os seguintes dados: ");
				printer.printMsg(" Código de barras : " + itemKey);
				printer.printMsg(" Nome do  item: " + nameItem);
				printer.printMsg(" Descrição do  item: " + description);
				printer.printMsg(" Digite para alterar: 1 -> Código de barras , 2 -> Nome do item,  3 -> Descrição");

				respEdit = reader.readNumber();
				boolean included = false;

				itemDao.delete(itemKey);

				if (respEdit == 1) {
					printer.printMsg(" Digite o novo código de barras: ");
					int newCodeBar = 0;
					newCodeBar = reader.readNumber();
					Item newItem = new Item(newCodeBar, nameItem, description);
					itemDao.save(newItem);
				} else if (respEdit == 2) {
					printer.printMsg(" Digite o novo nome do item: ");
					String newName = new String();
					newName = " ";
					newName = reader.readText();
					newName = reader.readText();
					Item newItem = new Item(itemKey, newName, description);
					itemDao.save(newItem);
				} else if (respEdit == 3) {
					printer.printMsg(" Digite a nova descrição do item: ");
					String newDescription = new String();
					newDescription = " ";
					newDescription = reader.readText();
					newDescription = reader.readText();
					Item newItem = new Item(itemKey, nameItem, newDescription);
					itemDao.save(newItem);
				} else {
					printer.printMsg("Nenhuma alternativa válida foi digitada. Tente outra vez!");
				}
			} while (respEdit != 1 & respEdit != 2 & respEdit != 3);

		} else {
			printer.printMsg("Não existe item com este código de barra cadastrado.");
		}
	}

	/*
	 * List all items
	 */
	public void listItems() {

		Map items = itemDao.getAll();
		Set keys = items.keySet();
		Iterator i = keys.iterator();
		Item itemPrint = null;

		while (i.hasNext()) {
			int key = (Integer) i.next();
			itemPrint = (Item) items.get(key);
			printer.printMsg("[Código de barra do item] : " + itemPrint.getBarCode());
			printer.printMsg("Nome do item: " + itemPrint.getName());
			printer.printMsg("Descrição do item: " + itemPrint.getDescription() + "\n");

		}

	}

	/*
	 * Delete a item
	 */
	public void deleteItem() {

		printer.printMsg("Digite o código de barras do item a ser deletado: ");
		int itemKey = 0;
		itemKey = reader.readNumber();
		itemDao.delete(itemKey);
	}

}
