import java.util.Map;

public interface UserDAO {

	public abstract void save(User object);

	public abstract User get(String key);

	public abstract boolean checksExistence(String key);

	public abstract void delete(String key);

	public abstract Map getAll();
}
