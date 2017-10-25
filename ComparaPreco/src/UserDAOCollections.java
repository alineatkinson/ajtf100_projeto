import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserDAOCollections implements UserDAO {
	ConcurrentHashMap users = new ConcurrentHashMap();
	Printer printer = new Printer();

	public boolean save(String cpfUser, User user) {
		users.put(cpfUser, user);
		return true;
	}

	public boolean checksExistence(String cpfUser) {
		if (users.containsKey(cpfUser))
			return true;
		else
			return false;
	}

	public User get(String cpfUser) {
		return (User) users.get((Object) cpfUser);
	}

	public void delete(String cpfUser) {
		User user = (User) users.remove(cpfUser);
	}

	public void list() {
		Set chaves = users.keySet();
		Iterator i = chaves.iterator();
		User userPrint = null;
		
		while (i.hasNext()) {
		String chave = (String) i.next();
		printer.printMsg("[CPF] : " + chave);
		userPrint = (User)users.get(chave);
		printer.printMsg("Nome: "+ userPrint.getName()+"\n"); 
					
		}	
	}

}
