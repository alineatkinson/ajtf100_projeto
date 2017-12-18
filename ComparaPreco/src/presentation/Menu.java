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
class Menu {
	UserControllerConsole controllerUserConsole = new UserControllerConsole();
	ItemControllerConsole controllerItemConsole = new ItemControllerConsole();
	SupermarketControllerConsole controllerSuperarketConsole = new SupermarketControllerConsole();
	TakingPriceControllerConsole controllerTakingPriceConsole = new TakingPriceControllerConsole();
	PricesByItemControllerConsole comparatorPrice = new PricesByItemControllerConsole();
	ItemsPricesBySupermarketControllerConsole sumConsole = new ItemsPricesBySupermarketControllerConsole();

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
	MenuItem menu18 = new MenuItem(18, "Somar compra.", new Item18Handler(sumConsole));

	List<MenuItem> list = new ArrayList<>();

	Map<Integer, MenuItem> map = new HashMap<>();

	Menu() {

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

	void toShow() {
		System.out.println("Digite o que deseja fazer:");
		for (MenuItem item : list) {
			System.out.println(item.toString());
		}

	}

	String readAnswer() throws PresentationException {
		Console readerConsole = new Console();
		// int optionT, optionE = 20;
		// String option = null;

		String option = readerConsole.nextLine();
		System.out.println(option);
		// System.out.println("Parse int: " + Integer.parseInt(option));
		// if (Integer.parseInt(option) > 18 || Integer.parseInt(option) < 1 ||
		// !option.equals("")|| !option.equals(" ")) {

		if (option.equals("")) {
			System.out.println("Entrou no else 11");
			return option;
		} else if (option.equals(" ")) {
			System.out.println("Entrou no else 222");
			return option;
		} else if ((Integer.parseInt(option) <= 18 & Integer.parseInt(option) >= 1)) {
			System.out.println("Entrou no if");
			return option;
		} else {
			throw new PresentationException(option + " não é uma opção válida, tente novamente!", null);
		}

		/*
		 * int option; option = readerConsole.readNumber();
		 * 
		 * if (option > 18 || option < 1) { throw new NumeroInvalidoException(option +
		 * " não é uma opção inválida, tente novamente!"); } return option;
		 */
		// Scanner scanner = new Scanner(System.in);

		/*
		 * atual String option = readerConsole.nextLine(); System.out.println(option);
		 * if (option.equals("")) { System.out.println("Enter Key pressed."); optionT =
		 * 19; } else if (option.equals(" ")) { optionT = 99;
		 * System.out.println("Aplicação finalizada"); } else { optionE =
		 * Integer.parseInt(option); System.out.println("Opção transofrmada :" +
		 * optionE); if (optionE >= 1 & optionE <= 18) {
		 * System.out.println("Opção válida " + optionE); optionT = optionE; } else if
		 * (optionE < 1 || optionE > 18) { optionT = 20;
		 * System.out.println("Opção não válida 2"); } else { optionT = 20; throw new
		 * NumeroInvalidoException(option +
		 * " não é uma opção válida, tente novamente!"); }
		 * 
		 * }
		 */

		// if (readerConsole.hasNextLine())
		// option = readerConsole.nextLine();
		// else
		// option = null;
		// if (Integer.parseInt(option) > 18 || Integer.parseInt(option) < 1 ||
		// !option.equals("")
		// || !option.equals(" ")) {
		// throw new NumeroInvalidoException(option + " não é uma opção válida, tente
		// novamente!");
		// }

		// return optionT;

	}

	void executeOption(int option) {
		MenuItem menu = map.get(option);
		menu.execute();
	}

}
