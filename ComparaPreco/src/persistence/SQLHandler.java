package persistence;

import java.io.IOException;

interface SQLHandler<E> {

	RowMapper getRowMapper();

	String getSelectSQL() throws PersistenceException;

	String getDeleteSQL() throws PersistenceException;

	String getSelectAll() throws PersistenceException;

	String handle(E e, Boolean exist) throws PersistenceException;

}
