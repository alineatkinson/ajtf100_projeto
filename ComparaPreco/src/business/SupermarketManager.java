package business;

import java.util.List;

import model.Item;
import model.Supermarket;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;
import persistence.PersistenceException;

public class SupermarketManager extends ComparePriceManager {
	private ComparePriceDAO supermarketDao = new DAOFactory().getSupermarketDAO();

	/*
	 * public void saveSupermarket(String nameSuper, int cepSuper, int
	 * codeSupermarket) { // Create the supermarket with the name, address and code
	 * supermarket number Supermarket supermarket = new Supermarket(nameSuper,
	 * cepSuper, codeSupermarket); supermarketDao.save(supermarket); }
	 */
	/*
	 * public boolean deleteSupermarket(int supermarketKey) { if
	 * (this.checksExistence(supermarketKey)) {
	 * supermarketDao.delete(supermarketKey); return true; } else { return false; }
	 * }
	 */

	/*
	 * public List<Supermarket> listAllSupermarkets() { List<Supermarket>
	 * supermarkets = null; // TODO MELHORAR EXCEÇÕES try { supermarkets =
	 * supermarketDao.getAll(); } catch (PersistenceException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } return supermarkets; }
	 */
	/*
	 * public Supermarket getSupermarket(int supermarketKey) { Supermarket
	 * supermarket = (Supermarket) supermarketDao.get(supermarketKey); return
	 * supermarket; }
	 * 
	 * public boolean checksExistence(int supermarketKey) { if
	 * (supermarketDao.checksExistence(supermarketKey)) { return true; } else {
	 * return false; } }
	 */

	public void save(Supermarket supermarket) {
		super.save(supermarket, supermarketDao);
	}

	public List<Supermarket> listAll() {
		return super.listAll(supermarketDao);
	}

	public boolean delete(int objectKey) {
		return super.delete(objectKey, supermarketDao);
	}

	public Supermarket get(int objectKey) {
		return (Supermarket) super.get(objectKey, supermarketDao);
	}

	public boolean checksExistence(int objectKey) {
		return super.checksExistence(objectKey, supermarketDao);
	}
}
