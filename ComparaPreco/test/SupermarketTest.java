import static org.junit.Assert.*;
import org.junit.Test;

public class SupermarketTest {

	@Test
	public void testCreationName() {
		Supermarket supermarket = new Supermarket("Nome", 88117, 123);
		org.junit.Assert.assertEquals("Nome", supermarket.getName());
	}
	
	@Test
	public void testCreationCEP() {
		Supermarket supermarket = new Supermarket("Nome", 88117, 123);
		org.junit.Assert.assertEquals(88117, supermarket.getCEP());
	}
	
	@Test
	public void testCreationCode() {
		Supermarket supermarket = new Supermarket("Nome", 88117, 123);
		org.junit.Assert.assertEquals(123, supermarket.getCode());
	}
	
	@Test
	public void testCreationHashCode() {
		Supermarket supermarket = new Supermarket("Nome", 88117, 123);
		org.junit.Assert.assertEquals(123, supermarket.hashCode());
	}

}
