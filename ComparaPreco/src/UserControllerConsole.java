import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserControllerConsole {
	Printer printer = new Printer();
	Console reader = new Console();
	//UserDAO userDao = new UserDAOCollections();= new UserDAOJDBC();
	UserDAO userDao = new DAOFactory().getUserDAO();

	public void createUser() {

		int rg = 0;
		User newUser;

		// Assign supermarket name attribute to the item
		printer.printMsg("Qual o nome do usuário? \n");
		String nameUser = reader.readText();

		// Ask the supermarket's name
		printer.printMsg("Qual o cpf do usuário? \n");
		String cpfUser = reader.readText();

		// save the user
		userDao.save(new User(nameUser, cpfUser));

	}

	public void editUser() {

		printer.printMsg("Digite o cpf do usuário a ser alterado: ");
		String userKey = reader.readText();
		User user = null;

		if (userDao.checksExistence(userKey)) {
			user = (User) userDao.get(userKey);
			String nameUser = user.getName();
			printer.printMsg(" O usuário selecionado contém os seguintes dados: ");
			printer.printMsg(" Nome: " + nameUser);
			printer.printMsg(" CPF : " + userKey);
			printer.printMsg(" Digite para alterar: 1 -> Nome, 2 -> CPF");
			int respEdit = 0;
			respEdit = reader.readNumber();

			userDao.delete(userKey);

			do {
				if (respEdit == 1) {
					printer.printMsg(" Digite o novo nome: ");
					String newNome = new String();
					newNome = reader.readText();
					newNome = reader.readText();
					userDao.save(new User(newNome, userKey));
				} else if (respEdit == 2) {
					printer.printMsg(" Digite o novo CPF: ");
					String newCPF = new String();
					newCPF = " ";
					newCPF = reader.readText();
					newCPF = reader.readText();
					userDao.save(new User(nameUser, newCPF));
				} else {
					printer.printMsg("Nenhuma alternativa válida foi digitada. Tente outra vez!");
				}
			} while (respEdit != 1 & respEdit != 2);

		} else {
			printer.printMsg("Não existe usuário com este número de cpf cadastrado.");
		}
	}

	// public void deleteUser();
	public void listUsers() {

		Map users = userDao.getAll();
		Set keys = users.keySet();
		Iterator i = keys.iterator();
		User userPrint = null;

		while (i.hasNext()) {
			String key = (String) i.next();
			userPrint = (User) users.get(key);
			printer.printMsg("[CPF do usuário] : " + userPrint.getCpf());
			printer.printMsg("Nome do usuário: " + userPrint.getName() + "\n");
		}
	}

	public void deleteUser() {
		printer.printMsg("Digite o cpf do usuário a ser excluído: ");
		String userKey = new String();
		userKey = reader.readText();

		userDao.delete(userKey);
	}
}
