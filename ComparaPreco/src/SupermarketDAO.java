
public interface SupermarketDAO {
	
	public abstract void save(int key, Supermarket supermarket);
	//public abstract void edit(String cpf,User user);
	public abstract void list();
	public abstract boolean checksExistence(int key);
	public abstract Supermarket get(int key);
	public abstract void delete(int key);
}
