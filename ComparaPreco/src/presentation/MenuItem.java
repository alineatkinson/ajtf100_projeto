package presentation;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItem {
	public int option;
	private String description;
	private Handler handler;

	public MenuItem(int option, String description, Handler handler) {
		this.option = option;
		this.description = description;
		this.handler = handler;
	}

	// tá correto isso?
	public void execute() {
		try {
			handler.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toString() {
		return String.format("%s . %s ", option, description);
	}
}

interface Handler {
	void execute() throws SQLException;
}

class Item1Handler implements Handler {
	UserControllerConsole controllerUserConsole;

	public Item1Handler(UserControllerConsole controllerUserConsole) {
		super();
		this.controllerUserConsole = controllerUserConsole;
	}

	public void execute() {
		System.out.println(this.controllerUserConsole.hashCode());
		this.controllerUserConsole.createUser();

	}
}

class Item2Handler implements Handler {
	UserControllerConsole controllerUserConsole;

	public Item2Handler(UserControllerConsole controllerUserConsole) {
		super();
		this.controllerUserConsole = controllerUserConsole;
	}

	public void execute() {
		System.out.println(this.controllerUserConsole.hashCode());
		this.controllerUserConsole.editUser();
	}
}

class Item3Handler implements Handler {
	UserControllerConsole controllerUserConsole;

	public Item3Handler(UserControllerConsole controllerUserConsole) {
		super();
		this.controllerUserConsole = controllerUserConsole;
	}

	public void execute() {
		System.out.println(this.controllerUserConsole.hashCode());
		this.controllerUserConsole.deleteUser();
	}
}

class Item4Handler implements Handler {
	UserControllerConsole controllerUserConsole;

	public Item4Handler(UserControllerConsole controllerUserConsole) {
		super();
		this.controllerUserConsole = controllerUserConsole;
	}

	public void execute() {
		System.out.println(this.controllerUserConsole.hashCode());
		this.controllerUserConsole.listUsers();
	}
}

class Item5Handler implements Handler {
	ItemControllerConsole controllerItemConsole;

	public Item5Handler(ItemControllerConsole controllerItemConsole) {
		super();
		this.controllerItemConsole = controllerItemConsole;
	}

	public void execute() {
		controllerItemConsole.createItem();
	}
}

class Item6Handler implements Handler {
	ItemControllerConsole controllerItemConsole;

	public Item6Handler(ItemControllerConsole controllerItemConsole) {
		super();
		this.controllerItemConsole = controllerItemConsole;
	}

	public void execute() {
		controllerItemConsole.editItem();
	}
}

class Item7Handler implements Handler {
	ItemControllerConsole controllerItemConsole;

	public Item7Handler(ItemControllerConsole controllerItemConsole) {
		super();
		this.controllerItemConsole = controllerItemConsole;
	}

	public void execute() {
		controllerItemConsole.deleteItem();
	}
}

class Item8Handler implements Handler {
	ItemControllerConsole controllerItemConsole;

	public Item8Handler(ItemControllerConsole controllerItemConsole) {
		super();
		this.controllerItemConsole = controllerItemConsole;
	}

	public void execute() {
		controllerItemConsole.listItems();
	}
}

class Item9Handler implements Handler {
	SupermarketControllerConsole controllerSuperarketConsole;

	public Item9Handler(SupermarketControllerConsole controllerSuperarketConsole) {
		super();
		this.controllerSuperarketConsole = controllerSuperarketConsole;
	}

	public void execute() {
		controllerSuperarketConsole.createSupermarket();
	}
}

class Item10Handler implements Handler {
	SupermarketControllerConsole controllerSuperarketConsole;

	public Item10Handler(SupermarketControllerConsole controllerSuperarketConsole) {
		super();
		this.controllerSuperarketConsole = controllerSuperarketConsole;
	}

	public void execute() {
		controllerSuperarketConsole.editSupermarket();
	}
}

class Item11Handler implements Handler {
	SupermarketControllerConsole controllerSuperarketConsole;

	public Item11Handler(SupermarketControllerConsole controllerSuperarketConsole) {
		super();
		this.controllerSuperarketConsole = controllerSuperarketConsole;
	}

	public void execute() {
		controllerSuperarketConsole.deleteSupermarket();
	}
}

class Item12Handler implements Handler {
	SupermarketControllerConsole controllerSuperarketConsole;

	public Item12Handler(SupermarketControllerConsole controllerSuperarketConsole) {
		super();
		this.controllerSuperarketConsole = controllerSuperarketConsole;
	}

	public void execute() {
		controllerSuperarketConsole.listSupermarkets();
	}
}

class Item13Handler implements Handler {
	TakingPriceControllerConsole controllerTakingPriceConsole;

	public Item13Handler(TakingPriceControllerConsole controllerTakingPriceConsole) {
		super();
		this.controllerTakingPriceConsole = controllerTakingPriceConsole;
	}

	public void execute() {
		controllerTakingPriceConsole.createTakingPrice();
	}
}

class Item14Handler implements Handler {
	TakingPriceControllerConsole controllerTakingPriceConsole;

	public Item14Handler(TakingPriceControllerConsole controllerTakingPriceConsole) {
		super();
		this.controllerTakingPriceConsole = controllerTakingPriceConsole;
	}

	public void execute() {
		controllerTakingPriceConsole.editTakingPrice();
	}
}

class Item15Handler implements Handler {
	TakingPriceControllerConsole controllerTakingPriceConsole;

	public Item15Handler(TakingPriceControllerConsole controllerTakingPriceConsole) {
		super();
		this.controllerTakingPriceConsole = controllerTakingPriceConsole;
	}

	public void execute() {
		controllerTakingPriceConsole.deleteTakingPrice();
	}
}

class Item16Handler implements Handler {
	TakingPriceControllerConsole controllerTakingPriceConsole;

	public Item16Handler(TakingPriceControllerConsole controllerTakingPriceConsole) {
		super();
		this.controllerTakingPriceConsole = controllerTakingPriceConsole;
	}

	public void execute() {
		controllerTakingPriceConsole.listTakingPrice();
	}
}

class Item17Handler implements Handler {
	PricesByItemControllerConsole comparator;

	public Item17Handler(PricesByItemControllerConsole comparator) { // ComparatorItemsControllerConsole comparator) {
		super();
		this.comparator = comparator;
	}

	public void execute() throws SQLException {
		comparator.createComparation();
		// comparator.createComparation();
	}
}

class Item18Handler implements Handler {
	ItemsPricesBySupermarketControllerConsole comparator;

	public Item18Handler(ItemsPricesBySupermarketControllerConsole comparator) { // ComparatorItemsControllerConsole comparator) {
		super();
		this.comparator = comparator;
	}

	public void execute() throws SQLException {
		comparator.createSumPrices();
	}
}
