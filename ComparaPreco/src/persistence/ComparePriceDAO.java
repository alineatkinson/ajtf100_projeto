package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public interface ComparePriceDAO<E> {

	public abstract void save(E object);

	public abstract E get(Number key);

	public abstract boolean checksExistence(Number key);

	public abstract void delete(Number key);

	public abstract List<E> getAll() throws PersistenceException;

}
