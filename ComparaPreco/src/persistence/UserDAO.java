package persistence;

import java.util.List;
import java.util.Map;

import model.User;

public interface UserDAO {

	abstract void save(User object) throws PersistenceException;

	abstract User get(String key);

	abstract boolean checksExistence(String key);

	abstract void delete(String key);

	abstract List getAll() throws PersistenceException;
}
