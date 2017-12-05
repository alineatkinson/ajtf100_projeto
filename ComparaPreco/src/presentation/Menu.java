package presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 1. Mostrar os itens
 * 2. Selecionar um item
 * 3. Executar o item selecionado
 */
public class Menu {
	UserControllerConsole controllerUserConsole = new UserControllerConsole();
	ItemControllerConsole controllerItemConsole = new ItemControllerConsole();
	SupermarketControllerConsole controllerSuperarketConsole = new SupermarketControllerConsole();
	TakingPriceControllerConsole controllerTakingPriceConsole = new TakingPriceControllerConsole();
	ComparatorPriceConsole comparatorPrice = new ComparatorPriceConsole();
	
	MenuItem menu1 = new MenuItem(1, " Cadastrar usuário.", new Item1Handler(controllerUserConsole));
	MenuItem menu2 = new MenuItem(2, " Editar usuário.", new Item2Handler(controllerUserConsole));
	MenuItem menu3 = new MenuItem(3, " Excluir usuário.", new Item3Handler(controllerUserConsole));
	MenuItem menu4 = new MenuItem(4, " Imprimir usuário.", new Item4Handler(controllerUserConsole));

	MenuItem menu5 = new MenuItem(5, " Cadastrar item.", new Item5Handler(controllerItemConsole));
	MenuItem menu6 = new MenuItem(6, " Editar item.", new Item6Handler(controllerItemConsole));
	MenuItem menu7 = new MenuItem(7, " Excluir item.", new Item7Handler(controllerItemConsole));
	MenuItem menu8 = new MenuItem(8, " Imprimir item.", new Item8Handler(controllerItemConsole));

	MenuItem menu9 = new MenuItem(9, " Cadastrar supermercado.", new Item9Handler(controllerSuperarketConsole));
	MenuItem menu10 = new MenuItem(10, "Editar supermercado.", new Item10Handler(controllerSuperarketConsole));
	MenuItem menu11 = new MenuItem(11, "Excluir supermercado.", new Item11Handler(controllerSuperarketConsole));
	MenuItem menu12 = new MenuItem(12, "Imprimir supermercado.", new Item12Handler(controllerSuperarketConsole));

	MenuItem menu13 = new MenuItem(13, "Cadastrar tomada de preço.", new Item13Handler(controllerTakingPriceConsole));
	MenuItem menu14 = new MenuItem(14, "Editar tomada de preço.", new Item14Handler(controllerTakingPriceConsole));
	MenuItem menu15 = new MenuItem(15, "Excluir tomada de preço.", new Item15Handler(controllerTakingPriceConsole));
	MenuItem menu16 = new MenuItem(16, "Imprimir tomada de preço.", new Item16Handler(controllerTakingPriceConsole));

	MenuItem menu17 = new MenuItem(17, "Comparar preço de item.", new Item17Handler(comparatorPrice)); //
	MenuItem menu18 = new MenuItem(18, "Somar compra.", new Item18Handler());

	List<MenuItem> list = new ArrayList<>();

	Map<Integer, MenuItem> map = new HashMap<>();

	public Menu() {

		list.add(menu1);
		list.add(menu2);
		list.add(menu3);
		list.add(menu4);
		list.add(menu5);
		list.add(menu6);
		list.add(menu7);
		list.add(menu8);
		list.add(menu9);
		list.add(menu10);
		list.add(menu11);
		list.add(menu12);
		list.add(menu13);
		list.add(menu14);
		list.add(menu15);
		list.add(menu16);
		list.add(menu17);
		list.add(menu18);

		for (MenuItem menu : list) {
			map.put(menu.option, menu);
		}
	}

	public void toShow() {
		System.out.println("Digite o que deseja fazer:");
		for (MenuItem item : list) {
			System.out.println(item.toString());
		}

	}

	public int readAnswer() {
		Console readerConsole = new Console();
		return readerConsole.readNumber();
	}

	public void executeOption(int option) {
		MenuItem menu = map.get(option);
		menu.execute();
	}

}
