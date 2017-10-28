import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

public class ItemControllerConsole {
	Printer printer = new Printer();
	Console reader = new Console();
	ItemDAO itemDao = new ItemDAOCollections();
	
	/*
	 * Create the item
	 */
	
	public void createItem() {
		// Ask and assign item's code bar
		String msg = "Qual o c�digo de barras do item? \n";
		printer.printMsg(msg);
		int codeBar = reader.readNumber();
		Item item = new Item(codeBar);
		
		// Ask and assign item's name
		msg = "Qual o nome do item? \n";
		printer.printMsg(msg);
		String name = reader.readText();
		name = reader.readText();
		item.setName(name);
		
		// Save the item
		itemDao.save(item.getBarCode(), item);
		System.out.println("Item inclu�do nome: " + name + " " + item.getBarCode());
		
	}

	/* 
	 * Edit the data of a item
	 */
	public void editItem() {

		printer.printMsg("Digite o c�digo de barras do item a ser alterado: ");
		int itemKey = reader.readNumber();
		Item item = null;

		if (itemDao.checksExistence(itemKey)) {
			item = (Item) itemDao.get(itemKey);
			String nameItem = item.getName();
			printer.printMsg(" O item selecionado cont�m os seguintes dados: ");
			printer.printMsg(" Nome do  item: " + nameItem);
			printer.printMsg(" C�digo de barras : " + itemKey);
			printer.printMsg(" Digite para alterar: 1 -> Nome do item, 2 -> C�digo de barras");
			int respEdit = 0;
			respEdit = reader.readNumber();
			boolean included = false;

			itemDao.delete(itemKey);

			do {
				if (respEdit == 1) {
					printer.printMsg(" Digite o novo nome do item: ");
					String newNome = new String();
					newNome = " ";
					newNome = reader.readText();
					newNome = reader.readText();
					Item newItem = new Item(itemKey);
					newItem.setName(newNome);
					included = itemDao.save(itemKey, newItem);
				} else if (respEdit == 2) {
					printer.printMsg(" Digite o novo c�digo de barras: ");
					int newCodeBar = 0;
					newCodeBar = reader.readNumber();
					Item newItem = new Item(newCodeBar);
					newItem.setName(nameItem);
					included = itemDao.save(newCodeBar, newItem);
				} else {
					printer.printMsg("Nenhuma alternativa v�lida foi digitada. Tente outra vez!");
				}
			} while (respEdit != 1 & respEdit != 2);

			if (included) {
				printer.printMsg("Item modificado com sucesso!\n");
			}
		} else {
			printer.printMsg("N�o existe item com este c�digo de barra cadastrado.");
		}
	}

	
	public void listItems() {

		itemDao.list();

	}

	public void deleteItem() {

		printer.printMsg("Digite o c�digo de barras do item a ser deletado: ");
		int itemKey = 0;
		itemKey = reader.readNumber();
		Item item = null;

		if (itemDao.checksExistence(itemKey)) {
			itemDao.delete(itemKey);
		} else {
			printer.printMsg("N�o existe item com este c�digo de barras.");
		}
	}

}
