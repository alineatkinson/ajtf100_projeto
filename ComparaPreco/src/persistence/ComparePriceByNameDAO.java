package persistence;

public interface ComparePriceByNameDAO<E> extends ComparePriceDAO {
	
	public abstract E get(String name);

}
