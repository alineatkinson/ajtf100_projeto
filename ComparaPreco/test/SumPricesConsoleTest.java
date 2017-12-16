
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
import presentation.ItemsPricesBySupermarketControllerConsole;

public class SumPricesConsoleTest {

	@Test
	public void listItemsPricesBySupermarket() {
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
		// ipbs.setItems(items);
		// ipbs.setTakingPrices(tps);
		ItemsPricesBySupermarket ipbs = new ItemsPricesBySupermarket(tps, items, supermarket);

		ItemsPricesBySupermarketControllerConsole console = new ItemsPricesBySupermarketControllerConsole();

		List<ItemsPricesBySupermarket> list = new ArrayList();

		List<String> data = console.getData(list);
		for (String print : data) {
			Assert.assertTrue(print.contains("Total da soma para o supermercado "));
		}
	}

}
