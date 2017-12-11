package presentation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import model.User;
import persistence.DAOFactory;
import persistence.UserDAO;

public class UserControllerConsole {
	private Printer printer = new Printer();
	private Console reader = new Console();
	// UserDAO userDao = new UserDAOCollections();= new UserDAOJDBC();
	private UserDAO userDao = new DAOFactory().getUserDAO();

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
		save(nameUser, cpfUser);

	}

	public void save(String nameUser, String cpfUser) {
		// Create the supermarket with the name, address and code supermarket number
		User user = new User(nameUser, cpfUser);
		userDao.save(user);
	}

	public void editUser() {
		User user = null;
		int respEdit = 0;

		printer.printMsg("Digite o cpf do usu�rio a ser alterado: ");
		String userKey = reader.readText();

		if (userDao.checksExistence(userKey)) {
			user = (User) userDao.get(userKey);
			String nameUser = user.getName();
			do {
				try {
					respEdit = askWhatEdit(user);
				} catch (NumeroInvalidoException e) {
					// e.printStackTrace();
				}
			} while (respEdit != 1 & respEdit != 2);
			userDao.delete(userKey);

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

	public int askWhatEdit(User user) throws NumeroInvalidoException {
		int respEdit = 0;

		printer.printMsg(" O usu�rio selecionado cont�m os seguintes dados: ");
		printer.printMsg(" Nome: " + user.getName());
		printer.printMsg(" CPF : " + user.getCpf());
		printer.printMsg(" Digite para alterar: 1 -> Nome, 2 -> CPF");

		respEdit = reader.readNumber();
		// String numero1 = JOptionPane.showInputDialog("Entre com um n�mero");
		// a=Integer.parseInt(respEdit);
		// break;

		if (respEdit != 1 & respEdit != 2) {
			throw new NumeroInvalidoException(respEdit + " � um n�mero inv�lido, tente novamente!");
		}

		// } while (respEdit != 1 || respEdit != 2);
		return respEdit;

	}

	/*
	 * List all taking prices
	 */
	public List<String> getData() {

		List<String> data = new ArrayList<String>();
		List<User> users = userDao.getAll();

		for (User user : users) {
			StringBuilder sb = new StringBuilder();
			sb.append("[CPF do usu�rio] : " + user.getCpf());
			sb.append("Nome do usu�rio: " + user.getName() + "\n");
			data.add(sb.toString());
		}
		return data;
	}

	public void listUsers() {

		List<String> data = getData();

		for (String user : data) {
			printer.printMsg(user);
		}
	}

	public void deleteUser() {
		printer.printMsg("Digite o cpf do usu�rio a ser exclu�do: ");
		String userKey = new String();
		userKey = reader.readText();
		if (userDao.checksExistence(userKey)) {
			userDao.delete(userKey);
		} else {
			printer.printMsg("N�o h� usu�rio com este cpf");
		}
	}

}
