package persistence;


public interface GenericDAO<T> {
	
	public boolean save(Object key, T user);
	public void list();
	public boolean checksExistence(Object key);
	public Object get(Object key);
	public abstract void delete(Object key);

}
