import java.util.Map;

public interface ComparePriceDAO {
	
	public abstract void save(Object object);
	public abstract Object get(Number key);
	public abstract boolean checksExistence(Number key);
	public abstract void delete(Number key);
	public abstract Map getAll();
}
