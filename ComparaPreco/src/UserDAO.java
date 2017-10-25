
public interface UserDAO {

	public abstract boolean save(String cpf, User user);
	//public abstract void edit(String cpf,User user);
	public abstract void list();
	public abstract boolean checksExistence(String userKey);
	public abstract User get(String userKey);
	public abstract void delete(String userKey);
	
	
}
