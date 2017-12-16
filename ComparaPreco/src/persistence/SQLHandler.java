package persistence;

import java.io.IOException;

interface SQLHandler<E> {

	RowMapper getRowMapper();

	String getSelectSQL() throws IOException;

	String getDeleteSQL() throws IOException;

	String getSelectAll() throws IOException;

	String handle(E e, Boolean exist) throws IOException;

}
