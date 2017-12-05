import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import model.Item;
import model.Supermarket;
import persistence.ComparePriceDAO;
import persistence.DAOFactory;
import presentation.ComparatorItemsControllerConsole;

public class ComparatorItemsControllerConsoleTest {

	@Test
	public void getSelectItemTest(){
		ComparePriceDAO itemDao = new DAOFactory().getItemDAO();
		Item item = new Item(987654,"itemTeste","descrição item teste");
		itemDao.save(item);
		ComparatorItemsControllerConsole comparator = new ComparatorItemsControllerConsole();
		Item itemmComparator = comparator.getSelectItem("itemTeste");
		Assert.assertEquals(item.getBarCode(), itemmComparator.getBarCode());
		//itemDao.delete(987654);
	}
	
	@Test
	public void selectSupermarketsTest(){
		Supermarket super1 = new Supermarket("TesteSuper1", 987);
		Supermarket super2 = new Supermarket("TesteSuper1", 876);
		
		ComparePriceDAO superDao = new DAOFactory().getSupermarketDAO();
		
		superDao.save(super1);
		superDao.save(super2);
		ComparatorItemsControllerConsole comparator = new ComparatorItemsControllerConsole();
		List<Supermarket> supermarketList = comparator.selectSupermarkets();
		
		//?? como testar??
		
	}
	
	@Test
	public void selectTakingPricesTest() {
		ComparatorItemsControllerConsole comparator = new ComparatorItemsControllerConsole();
		Item item = new Item(987654,"itemTeste","descrição item teste");
		comparator.selectTakingPrices(item, supermarkets)
		
		ComparePriceDAO superDao = new DAOFactory().getSupermarketDAO();
		superDao.save(super1);
		superDao.save(super2);
		ComparatorItemsControllerConsole comparator = new ComparatorItemsControllerConsole();
		List<Supermarket> supermarketList = comparator.selectSupermarkets();
		
		
		
	}
	
	@Test
	public void comparePriceItemsTest() {
		
	}
	
	
	TakingPriceControllerConsole tp = new TakingPriceControllerConsole();
	List<String> data = tp.getData();
	for(String item: data) {
		//Assert.assertTrue(item.contains("[Código do item]"));
	}
}





List selectTakingPrices(Item item, List<Supermarket> supermarkets)

List<String> comparePriceItems(Item item, List<TakingPrice> selectedSortedTakingPrices,
		List<Supermarket> selectedSupermarkets)

