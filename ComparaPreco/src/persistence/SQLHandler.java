package persistence;


public interface SQLHandler<E> {

	public RowMapper getRowMapper();

	public String getSelectSQL();

	public String getDeleteSQL();
	
	public String getSelectAll();

	public String handle(E e, Boolean exist);
	

}
