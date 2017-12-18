package presentation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import business.BusinessException;
import business.UserManager;
import model.User;
import persistence.PersistenceException;

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

		try {
			this.save(nameUser, cpfUser);
		} catch (PresentationException e) {
			e.printStackTrace();
		}

	}

	void save(String nameUser, String cpfUser) throws PresentationException {
		User user = new User(nameUser, cpfUser);
		try {
			userm.saveUser(user);
		} catch (BusinessException e) {
			throw new PresentationException("N�o foi poss�vel salvar o usu�rio", e);
			// e.printStackTrace();
		}
	}

	void editUser() throws PresentationException {
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
					throw new PresentationException("A op��o digitada n�o � uma op��o v�lida", e);
				}
			} while (respEdit != 1 & respEdit != 2);
			userm.deleteUser(userKey);

			if (respEdit == 1) {
				printer.printMsg(" Digite o novo nome: ");
				String newNome = new String();
				newNome = reader.readText();
				// newNome = reader.readText();
				try {
					this.save(newNome, userKey);
				} catch (PresentationException e) {
					e.printStackTrace();
				}
			} else if (respEdit == 2) {
				printer.printMsg(" Digite o novo CPF: ");
				String newCPF = new String();
				newCPF = " ";
				newCPF = reader.readText();
				// newCPF = reader.readText();
				try {
					this.save(nameUser, newCPF);
				} catch (PresentationException e) {
					e.printStackTrace();
				}
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
	List<String> getData() throws PresentationException {

		List<String> data = new ArrayList<String>();
		List<User> users = null;
		try {
			users = userm.listAllUsers();
		} catch (BusinessException e) {
			throw new PresentationException("N�o foi poss�vel executar o sql de sele��o de todos usu�rios", e);
			// e.printStackTrace();
		}

		for (User user : users) {
			StringBuilder sb = new StringBuilder();
			sb.append("[CPF do usu�rio] : " + user.getCpf());
			sb.append("Nome do usu�rio: " + user.getName() + "\n");
			data.add(sb.toString());
		}
		return data;
	}

	void listUsers() {

		List<String> data = null;
		try {
			data = getData();
		} catch (PresentationException e) {
			e.printStackTrace();
		}

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
