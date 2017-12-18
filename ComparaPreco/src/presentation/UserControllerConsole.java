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

		printer.printMsg("Qual o nome do usuário? \n");
		String nameUser = reader.readText();

		printer.printMsg("Qual o cpf do usuário? \n");
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
			throw new PresentationException("Não foi possível salvar o usuário", e);
			// e.printStackTrace();
		}
	}

	void editUser() throws PresentationException {
		User user = null;
		int respEdit = 0;

		printer.printMsg("Digite o cpf do usuário a ser alterado: ");
		String userKey = reader.readText();

		if (userm.checksExistence(userKey)) {
			user = userm.getUser(userKey);
			String nameUser = user.getName();
			do {
				try {
					respEdit = askWhatEdit(user);
				} catch (NumeroInvalidoException e) {
					// e.printStackTrace();
					throw new PresentationException("A opção digitada não é uma opção válida", e);
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
				printer.printMsg("Nenhuma alternativa válida foi digitada. Tente outra vez!");
			}

		} else {
			printer.printMsg("Não existe usuário com este número de cpf cadastrado.");
		}

	}

	int askWhatEdit(User user) throws NumeroInvalidoException {
		int respEdit = 0;

		printer.printMsg(" O usuário selecionado contém os seguintes dados: ");
		printer.printMsg(" Nome: " + user.getName());
		printer.printMsg(" CPF : " + user.getCpf());
		printer.printMsg(" Digite para alterar: 1 -> Nome, 2 -> CPF");

		respEdit = reader.readNumber();

		if (respEdit != 1 & respEdit != 2) {
			throw new NumeroInvalidoException(respEdit + " é um número inválido, tente novamente!");
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
			throw new PresentationException("Não foi possível executar o sql de seleção de todos usuários", e);
			// e.printStackTrace();
		}

		for (User user : users) {
			StringBuilder sb = new StringBuilder();
			sb.append("[CPF do usuário] : " + user.getCpf());
			sb.append("Nome do usuário: " + user.getName() + "\n");
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
		printer.printMsg("Digite o cpf do usuário a ser excluído: ");
		String userKey = new String();
		userKey = reader.readText();
		if (userm.deleteUser(userKey)) {
			printer.printMsg("Usuário excluído com sucesso");
		} else {
			printer.printMsg("Não há usuário com este cpf");
		}
	}

}
