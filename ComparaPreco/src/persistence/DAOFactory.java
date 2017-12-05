package persistence;

import model.Item;
import model.Supermarket;
import model.TakingPrice;


public class DAOFactory {
	public ComparePriceDAO<TakingPrice> getTakingPriceDAO() {
		return new TakingPriceDAOJDBC();
		//return new ComparePriceDAOCollections<TakingPrice>();
	}

	public ComparePriceDAO<Supermarket> getSupermarketDAO() {
		return new SupermarketDAOJDBC();
		//return new ComparePriceDAOCollections<Supermarket>();
	}

	public ComparePriceDAO<Item> getItemDAO() {
		return new ItemDAOJDBC();
		//return new ComparePriceDAOCollections<Item>();
	}

	public UserDAO getUserDAO() {
		return new UserDAOJDBC();
		//return new UserDAOCollections();
	}

}
