package business;

import java.util.Date;
import java.util.List;

import model.User;
import persistence.UserDAO;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;
import persistence.PersistenceException;

public class UserManager extends ComparePriceManager {
	private UserDAO userDAO = new DAOFactory().getUserDAO();

	public void saveUser(User user) {
		userDAO.save(user);
	}
	public List<User> listAllUsers() {
		List<User> users = null;
		users = userDAO.getAll();
		return users;
	}

	public boolean deleteUser(String cpfUser) {
		if (userDAO.checksExistence(cpfUser)) {
			userDAO.delete(cpfUser);
			return true;
		} else {
			return false;
		}
	}

	public User getUser(String cpfUser) {
		User user = userDAO.get(cpfUser);
		return user;
	}

	public boolean checksExistence(String cpfUser) {
		if (userDAO.checksExistence(cpfUser)) {
			return true;
		} else {
			return false;
		}
	}

}
