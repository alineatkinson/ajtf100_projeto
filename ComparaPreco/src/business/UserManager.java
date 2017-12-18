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

	public void saveUser(User user) throws BusinessException {
		try {
			userDAO.save(user);
		} catch (PersistenceException e) {
			throw new BusinessException("Erro ao salvar usuário", e);
			// e.printStackTrace();
		}
	}

	public List<User> listAllUsers() throws BusinessException {
		List<User> users = null;
		try {
			users = userDAO.getAll();
		} catch (PersistenceException e) {
			throw new BusinessException("Erro ao salvar usuário", e);
			// e.printStackTrace();
		}
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
