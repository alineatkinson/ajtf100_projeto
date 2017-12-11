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
import presentation.SumPricesConsole;

public class ItemsPricesBySupermarketTest {
	List<TakingPrice> takingPrices;
	List<Item> items;
	Supermarket supermarket;
	double sumPrices;

	@Test
	public void getItemsTest() {
		Item item1 = new Item(900, "itemTeste1", "Item teste descrição 1");
		Item item2 = new Item(990, "itemTeste2", "Item teste descrição 2");
		List<Item> items = new ArrayList();

		items.add(item1);
		items.add(item2);

		double price1 = 99.99;
		double price2 = 99.99;
		@SuppressWarnings("deprecation")
		Date date = new Date(2017, 11, 18);
		TakingPrice tp1 = new TakingPrice(999, price1, 999, date);
		TakingPrice tp2 = new TakingPrice(999, price2, 999, date);
		List<TakingPrice> tps = new ArrayList();
		tps.add(tp1);
		tps.add(tp2);

		Supermarket supermarket = new Supermarket("Nome", 88117, 999);

		ItemsPricesBySupermarket ipbs = new ItemsPricesBySupermarket(tps, items, supermarket);
		// ipbs.setItems(items);

		Assert.assertEquals(items, ipbs.getItems());

	}

	@Test
	public void getTakingPricesTest() {
		Item item1 = new Item(900, "itemTeste1", "Item teste descrição 1");
		Item item2 = new Item(990, "itemTeste2", "Item teste descrição 2");
		List<Item> items = new ArrayList();

		items.add(item1);
		items.add(item2);

		double price1 = 99.99;
		double price2 = 99.99;
		@SuppressWarnings("deprecation")
		Date date = new Date(2017, 11, 18);
		TakingPrice tp1 = new TakingPrice(999, price1, 999, date);
		TakingPrice tp2 = new TakingPrice(999, price2, 999, date);
		List<TakingPrice> tps = new ArrayList();
		tps.add(tp1);
		tps.add(tp2);

		Supermarket supermarket = new Supermarket("Nome", 88117, 999);

		ItemsPricesBySupermarket ipbs = new ItemsPricesBySupermarket(tps, items, supermarket);
		org.junit.Assert.assertEquals(tps, ipbs.getTakingPrices());
	}

	@Test
	public void getSupermarket() {
		Item item1 = new Item(900, "itemTeste1", "Item teste descrição 1");
		Item item2 = new Item(990, "itemTeste2", "Item teste descrição 2");
		List<Item> items = new ArrayList();

		items.add(item1);
		items.add(item2);

		double price1 = 99.99;
		double price2 = 99.99;
		@SuppressWarnings("deprecation")
		Date date = new Date(2017, 11, 18);
		TakingPrice tp1 = new TakingPrice(999, price1, 999, date);
		TakingPrice tp2 = new TakingPrice(999, price2, 999, date);
		List<TakingPrice> tps = new ArrayList();
		tps.add(tp1);
		tps.add(tp2);

		Supermarket supermarket = new Supermarket("Nome", 88117, 999);

		ItemsPricesBySupermarket ipbs = new ItemsPricesBySupermarket(tps, items, supermarket);
		org.junit.Assert.assertEquals(supermarket, ipbs.getSupermarket());
	}

	@Test
	public void getSumPrices() {
		Item item1 = new Item(900, "itemTeste1", "Item teste descrição 1");
		Item item2 = new Item(990, "itemTeste2", "Item teste descrição 2");
		List<Item> items = new ArrayList();

		items.add(item1);
		items.add(item2);

		double price1 = 99.99;
		double price2 = 99.99;
		@SuppressWarnings("deprecation")
		Date date = new Date(2017, 11, 18);
		TakingPrice tp1 = new TakingPrice(999, price1, 999, date);
		TakingPrice tp2 = new TakingPrice(999, price2, 999, date);
		List<TakingPrice> tps = new ArrayList();
		tps.add(tp1);
		tps.add(tp2);

		Supermarket supermarket = new Supermarket("Nome", 88117, 999);

		ItemsPricesBySupermarket ipbs = new ItemsPricesBySupermarket(tps, items, supermarket);
		org.junit.Assert.assertEquals(199.98, ipbs.getSumPrice(), 0);

	}
}
