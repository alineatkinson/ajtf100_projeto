import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserDAOCollections implements UserDAO {
	ConcurrentHashMap users = new ConcurrentHashMap();
	Printer printer = new Printer();

	public void save(User user) {
		users.put(user.getCpf(), user);
		System.out.println("Usuário adicionado");
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

	@Override
	public List getAll() {
		List<User> list = new ArrayList(users.values());
		return null;
	}

}
