import java.util.concurrent.ConcurrentHashMap;

public class UserController {
	ConcurrentHashMap users = new ConcurrentHashMap();

	public String createUser() {
		Printer printer = new Printer();
		Console reader = new Console();
		int rg = 0;
		User user;

		// Assign supermarket name attribute to the item
		printer.printMsg("Qual o nome do usuário? \n");
		String nameUser = reader.readText();

		// Ask the supermarket's name
		printer.printMsg("Qual o cpf do usuário? \n");
		String cpfUser = reader.readText();
		
		user = new User(nameUser, cpfUser);
		users.put(cpfUser, user);
		return cpfUser;
	}
}
