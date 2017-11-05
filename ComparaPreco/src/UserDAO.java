import java.util.Map;

public interface UserDAO {

	public abstract void save(Object object);

	public abstract Object get(String key);

	public abstract boolean checksExistence(String key);

	public abstract void delete(String key);

	public abstract Map getAll();
}
