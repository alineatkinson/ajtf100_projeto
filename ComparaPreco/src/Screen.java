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
			 * printer.printMsg("Digite o que deseja fazer: \n" + "1. Cadastrar usu�rio \n"
			 * + "2. Cadastrar item \n" + "3. Cadastrar supermercado \n" +
			 * "4. Comparar pre�os \n" + "5. Calcular compra \n" +
			 * "6. Cadastrar tomada de pre�o \n" + "7. Editar um item. \n" +
			 * "8. Editar um usu�rio. \n" + "9. Editar um supermercado. \n" +
			 * "10. Editar uma tomada de pre�o. \n" + "11. Excluir um item. \n" +
			 * "12. Excluir um usu�rio. \n" + "13. Excluir um supermercado. \n" +
			 * "14. Excluir uma tomada de pre�o. \n" + "15. Imprime todos os itens. \n" +
			 * "16. Imprime todos os usu�rios. \n" +
			 * "17. Imprime todos os supermercados. \n" +
			 * "18. Imprime todas as tomadas de pre�o. "); resposta1 = reader.readNumber();
			 * 
			 * switch (resposta1) { case 1:
			 * System.out.println("Op��o 1 selecionada: 1. Cadastrar usu�rio. ");
			 * controllerUserConsole.createUser(); break; case 2:
			 * System.out.println("Op��o 2 selecionada: 2. Cadastrar item. ");
			 * controllerItemConsole.createItem(); break; case 3:
			 * System.out.println("Op��o 3 selecionada: 3. Cadastrar supermercado. ");
			 * controllerSuperarketConsole.createSupermarket(); break; case 4:
			 * System.out.println("Op��o 4 selecionada: 4. Comparar pre�os. "); break; case
			 * 5: System.out.println("Op��o 5 selecionada: 5. Calcular compra. "); break;
			 * case 6: printer.
			 * printMsg("Op��o 6. Voc� deseja cadastrar tomadas de pre�os para futuras compara��es?"
			 * ); controllerTakingPriceConsole.createTakingPrice(); break; case 7:
			 * printer.printMsg("Op��o 7 selecionada: Editar um item. ");
			 * controllerItemConsole.editItem(); break; case 8:
			 * printer.printMsg("Op��o 8 selecionada: Editar um usu�rio. ");
			 * controllerUserConsole.editUser(); break; case 9:
			 * printer.printMsg("Op��o 9 selecionada: Editar um supermercado. ");
			 * controllerSuperarketConsole.editSupermarket(); break; case 10:
			 * printer.printMsg("Op��o 10 selecionada: Editar uma tomada de pre�o. ");
			 * controllerTakingPriceConsole.editTakingPrice(); break; case 11:
			 * printer.printMsg("Op��o 11 selecionada: Excluir um item. ");
			 * controllerItemConsole.deleteItem(); break; case 12:
			 * printer.printMsg("Op��o 12 selecionada: Excluir um usu�rio. ");
			 * controllerUserConsole.deleteUser(); break; case 13:
			 * printer.printMsg("Op��o 13 selecionada: Excluir um supermercado.");
			 * controllerSuperarketConsole.deleteSupermarket(); break; case 14:
			 * printer.printMsg("Op��o 14 selecionada: Excluir uma tomada de pre�o.");
			 * controllerTakingPriceConsole.deleteTakingPrice(); break; case 15:
			 * printer.printMsg("Op��o 15 selecionada: Imprime todos os itens");
			 * controllerItemConsole.listItems(); break; case 16:
			 * printer.printMsg("Op��o 16 selecionada: Imprime todos os usu�rios.");
			 * controllerUserConsole.listUsers(); break; case 17:
			 * printer.printMsg("Op��o 17 selecionada: Imprime todos os supermercados.");
			 * controllerSuperarketConsole.listSupermarkets(); break; case 18:
			 * printer.printMsg("Op��o 18 selecionada: Imprime todas as tomadas de pre�o.");
			 * controllerTakingPriceConsole.listTakingPrice(); break;
			 * 
			 * default: printer.printMsg("Nenhuma op��o v�lida foi digitada.\n"); }
			 */
			printer.printMsg("Deseja utilizar a aplica��o novamente? (1 = SIM | 2 = N�o) \n");
			resposta2 = reader.readNumber();
		} while (resposta2 != 2);

	}
}