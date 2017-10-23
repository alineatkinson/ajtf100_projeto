import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
//import org.junit.Assert;

public class Screen {
	public static void main(String[] args) {

		Printer printer = new Printer();
		Console reader = new Console();
		int resposta1, resposta2, resposta3, respBarCodeItem, respCodeSupermarket;
		int codeSupermarket, codeBarItem;
		String codeUser;
		ItemController controllerItem = new ItemController();
		SupermarketController controllerSuperarket = new SupermarketController();
		UserController controllerUser = new UserController();

		do {

			printer.printMsg("Digite o que deseja fazer: \n" + "1. Cadastrar usuário \n" + "2. Cadastrar item \n"
					+ "3. Cadastrar supermercado \n" + "4. Comparar preços \n" + "5. Calcular compra \n" + "6. Cadastrar tomada de preço \n");
			resposta1 = reader.readNumber();

			switch (resposta1) {
			case 1:
				System.out.println("Opção 1 selecionada: 1. Cadastrar usuário");
				codeUser = controllerUser.createUser();
				break;

			case 2:
				System.out.println("Opção 2 selecionada: 2. Cadastrar item");
				codeBarItem = controllerItem.createItem();
				break;

			case 3:
				System.out.println("Opção 3 selecionada: 3. Cadastrar supermercado");
				codeSupermarket = controllerSuperarket.createSupermarket();
				break;

			case 4:
				System.out.println("Opção 4 selecionada: 4. Comparar preços");
				break;

			case 5:
				System.out.println("Opção 5 selecionada: 5. Calcular compra");
				break;

			case 6:
				printer.printMsg(
						"Opção 6. Você deseja cadastrar o preço do item para futuras comparações? (1 = SIM | 2 = Não)");
				resposta3 = reader.readNumber();
				if (resposta3 == 1) {
					TakingPriceController controladorTomadaPreco = new TakingPriceController();
					controllerSuperarket.imprimeDados();
					printer.printMsg("Selecione o código do supermercado do item: ");
					respBarCodeItem = reader.readNumber();

					controllerItem.imprimeDados();
					printer.printMsg("Selecione o código do item: ");
					respCodeSupermarket = reader.readNumber();
					controladorTomadaPreco.createTomadaDePreco(respBarCodeItem, respCodeSupermarket);
					controladorTomadaPreco.imprimeDados();
				}
			default:
				printer.printMsg("Nenhuma opção válida foi digitada.");
			}

			printer.printMsg("Deseja rever o menu? (1 = SIM | 2 = Não)");
			resposta2 = reader.readNumber();
		} while (resposta2 != 2);

		printer.printMsg("Deseja utilizar a aplicação novamente? (1 = SIM | 2 = Não)");

	}
}