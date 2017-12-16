package business;

import java.util.Date;
import java.util.List;

import model.Supermarket;
import model.TakingPrice;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;
import persistence.PersistenceException;
import persistence.TakingPriceDAOJDBC;
import persistence.UserDAO;

public class TakingPriceManager {
	private TakingPriceDAOJDBC tpDao = new TakingPriceDAOJDBC();

	public void save(TakingPrice tp) {
		tpDao.save(tp);
	}

	public List<TakingPrice> listAllTakingPrices() {
		List<TakingPrice> tps = null; // TODO MELHORAR EXCEÇÕES
		try {
			tps = tpDao.getAll();
		} catch (PersistenceException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tps;
	}

	public boolean deleteTakingPrice(int codebar_item, int code_supermarket) {
		if (tpDao.checksExistence(codebar_item, code_supermarket)) {
			tpDao.delete(codebar_item, code_supermarket);
			return true;
		} else {
			return false;
		}
	}

	public TakingPrice getTakingPrice(int codebar_item, int code_supermarket) {
		TakingPrice tp = (TakingPrice) tpDao.get(codebar_item, code_supermarket);
		return tp;
	}

	public boolean checksExistence(int codebar_item, int code_supermarket) {
		if (tpDao.checksExistence(codebar_item, code_supermarket)) {
			return true;
		} else {
			return false;
		}
	}

}
