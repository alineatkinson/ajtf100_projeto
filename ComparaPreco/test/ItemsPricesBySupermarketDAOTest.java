import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import model.Item;
import model.ItemsPricesBySupermarket;
import model.Supermarket;
import model.TakingPrice;
import persistence.ItemsPricesBySupermarketDAO;

public class ItemsPricesBySupermarketDAOTest {

	@Test
	public void test() {
		Item item1 = new Item(900, "itemTeste1", "Item teste descrição 1");
		Item item2 = new Item(990, "itemTeste2", "Item teste descrição 2");
		List<Item> items = new ArrayList();

		items.add(item1);
		items.add(item2);
		ItemsPricesBySupermarket ipbs1 = new ItemsPricesBySupermarket();
		ipbs1.setItems(items);

		double price1 = 99.99;
		double price2 = 99.99;
		@SuppressWarnings("deprecation")
		Date date = new Date(2017, 11, 18);
		TakingPrice tp1 = new TakingPrice(999, price1, 999, date);
		TakingPrice tp2 = new TakingPrice(999, price2, 999, date);
		List<TakingPrice> tps = new ArrayList();
		tps.add(tp1);
		tps.add(tp2);

		ipbs1.setTakingPrices(tps);

		Supermarket supermarket = new Supermarket("Nome", 88117, 999);

		ipbs1.setSupermarket(supermarket);
		ItemsPricesBySupermarketDAO ipbsDAO = new ItemsPricesBySupermarketDAO();
		ItemsPricesBySupermarket ipbs2 = ipbsDAO.getItemsPricesBySupermarket(ipbs1.getItems(), ipbs1.getSupermarket());
		Assert.assertEquals(ipbs1.getItems(), ipbs2.getItems());
		Assert.assertEquals(ipbs1.getTakingPrices(), ipbs2.getTakingPrices());
		Assert.assertEquals(ipbs1.getSupermarket(), ipbs2.getSupermarket());
	}

}
