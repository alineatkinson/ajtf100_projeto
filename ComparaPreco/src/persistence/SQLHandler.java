package persistence;

import java.io.IOException;

interface SQLHandler<E> {

	public RowMapper getRowMapper();

	public String getSelectSQL() throws IOException;

	public String getDeleteSQL() throws IOException;
	
	public String getSelectAll() throws IOException;

	public String handle(E e, Boolean exist) throws IOException;
	

}
