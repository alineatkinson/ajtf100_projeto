import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ComparePriceDAOCollectionsTest {

	@Test
	public void saveCPCollections() {
		Supermarket supermarket = new Supermarket("Angeloni", 88117310, 123);
		ComparePriceDAOCollections<Supermarket> cpCollections= new ComparePriceDAOCollections<>();
		cpCollections.save(supermarket);
		Supermarket superMk = (Supermarket) cpCollections.get(123);
		Assert.assertEquals(supermarket.getCode(),superMk.getCode());
		Assert.assertEquals(supermarket.getName(),superMk.getName());
		Assert.assertEquals(supermarket.getCEP(),superMk.getCEP());
	}
	
	@Test
	public void checkExistenceCPCollections() {
		Supermarket supermarket = new Supermarket("Angeloni", 88117310, 123);
		ComparePriceDAOCollections<Supermarket> cpCollections= new ComparePriceDAOCollections<>();
		cpCollections.save(supermarket);
		Assert.assertEquals(true, cpCollections.checksExistence(123));
	}
	
	@Test
	public void deleteCPCollections() {
		Supermarket supermarket = new Supermarket("Angeloni", 88117310, 123);
		ComparePriceDAOCollections<Supermarket> cpCollections= new ComparePriceDAOCollections<>();
		cpCollections.save(supermarket);
		Assert.assertEquals(true, cpCollections.checksExistence(123));
		cpCollections.delete(123);
		Assert.assertEquals(false, cpCollections.checksExistence(123));
	}



}
