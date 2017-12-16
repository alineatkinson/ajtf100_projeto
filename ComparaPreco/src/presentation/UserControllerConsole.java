package presentation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import business.UserManager;
import model.User;

class UserControllerConsole {
	private Printer printer = new Printer();
	private Console reader = new Console();
	private UserManager userm = new UserManager();

	void createUser() {

		int rg = 0;
		User newUser;

		printer.printMsg("Qual o nome do usu�rio? \n");
		String nameUser = reader.readText();

		printer.printMsg("Qual o cpf do usu�rio? \n");
		String cpfUser = reader.readText();

		this.save(nameUser, cpfUser);

	}

	void save(String nameUser, String cpfUser) {
		User user = new User(nameUser, cpfUser);
		userm.saveUser(user);
	}

	void editUser() {
		User user = null;
		int respEdit = 0;

		printer.printMsg("Digite o cpf do usu�rio a ser alterado: ");
		String userKey = reader.readText();

		if (userm.checksExistence(userKey)) {
			user = userm.getUser(userKey);
			String nameUser = user.getName();
			do {
				try {
					respEdit = askWhatEdit(user);
				} catch (NumeroInvalidoException e) {
					// e.printStackTrace();
				}
			} while (respEdit != 1 & respEdit != 2);
			userm.deleteUser(userKey);

			if (respEdit == 1) {
				printer.printMsg(" Digite o novo nome: ");
				String newNome = new String();
				newNome = reader.readText();
				// newNome = reader.readText();
				this.save(newNome, userKey);
			} else if (respEdit == 2) {
				printer.printMsg(" Digite o novo CPF: ");
				String newCPF = new String();
				newCPF = " ";
				newCPF = reader.readText();
				// newCPF = reader.readText();
				this.save(nameUser, newCPF);
			} else {
				printer.printMsg("Nenhuma alternativa v�lida foi digitada. Tente outra vez!");
			}

		} else {
			printer.printMsg("N�o existe usu�rio com este n�mero de cpf cadastrado.");
		}

	}

	int askWhatEdit(User user) throws NumeroInvalidoException {
		int respEdit = 0;

		printer.printMsg(" O usu�rio selecionado cont�m os seguintes dados: ");
		printer.printMsg(" Nome: " + user.getName());
		printer.printMsg(" CPF : " + user.getCpf());
		printer.printMsg(" Digite para alterar: 1 -> Nome, 2 -> CPF");

		respEdit = reader.readNumber();

		if (respEdit != 1 & respEdit != 2) {
			throw new NumeroInvalidoException(respEdit + " � um n�mero inv�lido, tente novamente!");
		}
		return respEdit;

	}

	/*
	 * List all taking prices
	 */
	List<String> getData() {

		List<String> data = new ArrayList<String>();
		List<User> users = userm.listAllUsers();

		for (User user : users) {
			StringBuilder sb = new StringBuilder();
			sb.append("[CPF do usu�rio] : " + user.getCpf());
			sb.append("Nome do usu�rio: " + user.getName() + "\n");
			data.add(sb.toString());
		}
		return data;
	}

	void listUsers() {

		List<String> data = getData();

		for (String user : data) {
			printer.printMsg(user);
		}
	}

	void deleteUser() {
		printer.printMsg("Digite o cpf do usu�rio a ser exclu�do: ");
		String userKey = new String();
		userKey = reader.readText();
		if (userm.deleteUser(userKey)) {
			printer.printMsg("Usu�rio exclu�do com sucesso");
		} else {
			printer.printMsg("N�o h� usu�rio com este cpf");
		}
	}

}
