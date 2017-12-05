import org.junit.Assert;
import org.junit.Test;

import model.Supermarket;
import persistence.SupermarketDAOJDBC;

public class SupermarketJDBCTest {
	@Test
	public void saveCPCollections() {
		Supermarket supermarket = new Supermarket("Angeloni", 88117310, 123);
		SupermarketDAOJDBC cpJDBC= new SupermarketDAOJDBC();
		cpJDBC.save(supermarket);
		Supermarket superMk = (Supermarket) cpJDBC.get(123);
		Assert.assertEquals(supermarket.getCode(),superMk.getCode());
		Assert.assertEquals(supermarket.getName(),superMk.getName());
		Assert.assertEquals(supermarket.getCEP(),superMk.getCEP());
	}
	
	@Test
	public void checkExistenceCPCollections() {
		Supermarket supermarket = new Supermarket("Angeloni", 88117310, 123);
		SupermarketDAOJDBC cpJDBC= new SupermarketDAOJDBC();
		cpJDBC.save(supermarket);
		Assert.assertEquals(true, cpJDBC.checksExistence(123));
	}
	
	@Test
	public void deleteCPCollections() {
		Supermarket supermarket = new Supermarket("Angeloni", 88117310, 123);
		SupermarketDAOJDBC cpJDBC= new SupermarketDAOJDBC();
		cpJDBC.save(supermarket);
		Assert.assertEquals(true, cpJDBC.checksExistence(123));
		cpJDBC.delete(123);
		Assert.assertEquals(false, cpJDBC.checksExistence(123));
	}
}
