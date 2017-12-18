package business;

import java.util.List;

import model.Supermarket;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;
import persistence.PersistenceException;

class ComparePriceManager<E> {

	//private ComparePriceDAO objectDao;

	public void save(E object, ComparePriceDAO objectDao) {
		try {
			objectDao.save(object);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}

	public List<E> listAll(ComparePriceDAO objectDao) {
		List<E> objects = null;
		try {
			objects = objectDao.getAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return objects;
	}

	public boolean delete(int objectKey, ComparePriceDAO objectDao) {
		if (objectDao.checksExistence(objectKey)) {
			objectDao.delete(objectKey);
			return true;
		} else {
			return false;
		}
	}

	public E get(int objectKey, ComparePriceDAO objectDao) {
		E object = (E) objectDao.get(objectKey);
		return object;
	}

	public boolean checksExistence(int objectKey, ComparePriceDAO objectDao) {
		if (objectDao.checksExistence(objectKey)) {
			return true;
		} else {
			return false;
		}
	}

}