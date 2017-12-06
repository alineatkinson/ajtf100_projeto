package persistence;

import java.util.List;
import java.util.Map;

import model.User;

public interface UserDAO {

	public abstract void save(User object);

	public abstract User get(String key);

	public abstract boolean checksExistence(String key);

	public abstract void delete(String key);

	public abstract List getAll();
}