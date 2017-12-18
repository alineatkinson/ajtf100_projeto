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

	public void save(TakingPrice tp) throws BusinessException {
		try {
			tpDao.save(tp);
		} catch (PersistenceException e) {
			throw new BusinessException("Erro ao salvar tomada de preço", e);
			//e.printStackTrace();
		}
	}

	public List<TakingPrice> listAllTakingPrices() throws BusinessException {
		List<TakingPrice> tps = null; 
		try {
			tps = tpDao.getAll();
		} catch (PersistenceException e) { 
			throw new BusinessException("Erro ao listar todas tomadas de preços", e);
		}
		return tps;
	}

	public boolean deleteTakingPrice(int codebar_item, int code_supermarket, Date date) {
		if (tpDao.checksExistence(codebar_item, code_supermarket, date)) {
			tpDao.delete(codebar_item, code_supermarket);
			return true;
		} else {
			return false;
		}
	}

	public TakingPrice getTakingPrice(int codebar_item, int code_supermarket, Date date) {
		TakingPrice tp = (TakingPrice) tpDao.get(codebar_item, code_supermarket, date);
		return tp;
	}

	public boolean checksExistence(int codebar_item, int code_supermarket, Date date) {
		if (tpDao.checksExistence(codebar_item, code_supermarket, date)) {
			return true;
		} else {
			return false;
		}
	}

}
