import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
//import org.junit.Assert;

public class Screen {
	public static void main(String[] args) {
		Printer printer = new Printer(); Console reader = new Console(); 
		int resposta2;
		/*
		 * 
		 * int resposta1, resposta2, resposta3, resposta4, respBarCodeItem,
		 * respCodeSupermarket; int codeSupermarket, codeBarItem; String codeUser;
		 * ItemControllerConsole controllerItemConsole = new ItemControllerConsole();
		 * SupermarketControllerConsole controllerSuperarketConsole = new
		 * SupermarketControllerConsole(); UserControllerConsole controllerUserConsole =
		 * new UserControllerConsole(); TakingPriceControllerConsole
		 * controllerTakingPriceConsole = new TakingPriceControllerConsole();
		 */

		
		do {
			Menu menu = new Menu();
			menu.toShow();
			int resp = menu.readAnswer();
			menu.executeOption(resp);
			/*
			 * printer.printMsg("Digite o que deseja fazer: \n" + "1. Cadastrar usuário \n"
			 * + "2. Cadastrar item \n" + "3. Cadastrar supermercado \n" +
			 * "4. Comparar preços \n" + "5. Calcular compra \n" +
			 * "6. Cadastrar tomada de preço \n" + "7. Editar um item. \n" +
			 * "8. Editar um usuário. \n" + "9. Editar um supermercado. \n" +
			 * "10. Editar uma tomada de preço. \n" + "11. Excluir um item. \n" +
			 * "12. Excluir um usuário. \n" + "13. Excluir um supermercado. \n" +
			 * "14. Excluir uma tomada de preço. \n" + "15. Imprime todos os itens. \n" +
			 * "16. Imprime todos os usuários. \n" +
			 * "17. Imprime todos os supermercados. \n" +
			 * "18. Imprime todas as tomadas de preço. "); resposta1 = reader.readNumber();
			 * 
			 * switch (resposta1) { case 1:
			 * System.out.println("Opção 1 selecionada: 1. Cadastrar usuário. ");
			 * controllerUserConsole.createUser(); break; case 2:
			 * System.out.println("Opção 2 selecionada: 2. Cadastrar item. ");
			 * controllerItemConsole.createItem(); break; case 3:
			 * System.out.println("Opção 3 selecionada: 3. Cadastrar supermercado. ");
			 * controllerSuperarketConsole.createSupermarket(); break; case 4:
			 * System.out.println("Opção 4 selecionada: 4. Comparar preços. "); break; case
			 * 5: System.out.println("Opção 5 selecionada: 5. Calcular compra. "); break;
			 * case 6: printer.
			 * printMsg("Opção 6. Você deseja cadastrar tomadas de preços para futuras comparações?"
			 * ); controllerTakingPriceConsole.createTakingPrice(); break; case 7:
			 * printer.printMsg("Opção 7 selecionada: Editar um item. ");
			 * controllerItemConsole.editItem(); break; case 8:
			 * printer.printMsg("Opção 8 selecionada: Editar um usuário. ");
			 * controllerUserConsole.editUser(); break; case 9:
			 * printer.printMsg("Opção 9 selecionada: Editar um supermercado. ");
			 * controllerSuperarketConsole.editSupermarket(); break; case 10:
			 * printer.printMsg("Opção 10 selecionada: Editar uma tomada de preço. ");
			 * controllerTakingPriceConsole.editTakingPrice(); break; case 11:
			 * printer.printMsg("Opção 11 selecionada: Excluir um item. ");
			 * controllerItemConsole.deleteItem(); break; case 12:
			 * printer.printMsg("Opção 12 selecionada: Excluir um usuário. ");
			 * controllerUserConsole.deleteUser(); break; case 13:
			 * printer.printMsg("Opção 13 selecionada: Excluir um supermercado.");
			 * controllerSuperarketConsole.deleteSupermarket(); break; case 14:
			 * printer.printMsg("Opção 14 selecionada: Excluir uma tomada de preço.");
			 * controllerTakingPriceConsole.deleteTakingPrice(); break; case 15:
			 * printer.printMsg("Opção 15 selecionada: Imprime todos os itens");
			 * controllerItemConsole.listItems(); break; case 16:
			 * printer.printMsg("Opção 16 selecionada: Imprime todos os usuários.");
			 * controllerUserConsole.listUsers(); break; case 17:
			 * printer.printMsg("Opção 17 selecionada: Imprime todos os supermercados.");
			 * controllerSuperarketConsole.listSupermarkets(); break; case 18:
			 * printer.printMsg("Opção 18 selecionada: Imprime todas as tomadas de preço.");
			 * controllerTakingPriceConsole.listTakingPrice(); break;
			 * 
			 * default: printer.printMsg("Nenhuma opção válida foi digitada.\n"); }
			 */
			printer.printMsg("Deseja utilizar a aplicação novamente? (1 = SIM | 2 = Não) \n");
			resposta2 = reader.readNumber();
		} while (resposta2 != 2);

	}
}