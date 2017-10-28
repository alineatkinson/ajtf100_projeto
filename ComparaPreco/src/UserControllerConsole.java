import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserControllerConsole {
	Printer printer = new Printer();
	Console reader = new Console();
	UserDAO userDao = new UserDAOCollections();

	public void createUser() {

		int rg = 0;
		User newUser;

		// Assign supermarket name attribute to the item
		printer.printMsg("Qual o nome do usu�rio? \n");
		String nameUser = reader.readText();

		// Ask the supermarket's name
		printer.printMsg("Qual o cpf do usu�rio? \n");
		String cpfUser = reader.readText();

		// save the user
		newUser = new User(nameUser, cpfUser);
		boolean adicionado = userDao.save(cpfUser, newUser);
		if (adicionado) {
			printer.printMsg("Usu�rio adicionado!\n");
		}
	}

	public void editUser() {

		printer.printMsg("Digite o cpf do usu�rio a ser alterado: ");
		String userKey = reader.readText();
		User user = null;

		if (userDao.checksExistence(userKey)) {
			user = (User) userDao.get(userKey);
			String nameUser = user.getName();
			printer.printMsg(" O usu�rio selecionado cont�m os seguintes dados: ");
			printer.printMsg(" Nome: " + nameUser);
			printer.printMsg(" CPF : " + userKey);
			printer.printMsg(" Digite para alterar: 1 -> Nome, 2 -> CPF");
			int respEdit = 0;
			respEdit = reader.readNumber();
			boolean included = false;

			userDao.delete(userKey);

			do {
				if (respEdit == 1) {
					printer.printMsg(" Digite o novo nome: ");
					String newNome = new String();
					newNome = reader.readText();
					User newUser = new User(newNome, userKey);
					included = userDao.save(userKey, newUser);
				} else if (respEdit == 2) {
					printer.printMsg(" Digite o novo CPF: ");
					String newCPF = new String();
					newCPF = reader.readText();
					User newUser = new User(nameUser, newCPF);
					included = userDao.save(newCPF, newUser);
				} else {
					printer.printMsg("Nenhuma alternativa v�lida foi digitada. Tente outra vez!");
				}
			} while (respEdit != 1 & respEdit != 2);

			if (included) {
				printer.printMsg("Usu�rio modificado com sucesso!\n");
			}
		} else {
			printer.printMsg("N�o existe usu�rio com este n�mero de cpf cadastrado.");
		}
	}

	// public void deleteUser();
	public void listUsers() {

		userDao.list();

	}

	public void deleteUser() {

		printer.printMsg("Digite o cpf do usu�rio a ser deletado: ");
		String userKey = new String();
		userKey = reader.readText();
		User user = null;

		if (userDao.checksExistence(userKey)) {
			userDao.delete(userKey);
		} else {
			printer.printMsg("N�o existe usu�rio com este n�mero de cpf cadastrado.");
		}
	}
}
