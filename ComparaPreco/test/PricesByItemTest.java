import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import model.Item;
import model.PricesByItem;
import model.Supermarket;
import model.TakingPrice;

public class PricesByItemTest {
	// Lista de TakingPrices
	// Um item
	PricesByItem ipbi = new PricesByItem();

	@Test
	public void setItemtest() {
		Item item = new Item(999, "teste item", "teste item descrição");
		ipbi.setItem(item);
		org.junit.Assert.assertEquals(item, ipbi.getItem());
	}

	@Test
	public void setTakingPriceTest() {
		double price1 = 99.99;
		double price2 = 99.99;
		@SuppressWarnings("deprecation")
		Date date = new Date(2017, 11, 18);
		TakingPrice tp1 = new TakingPrice(999, price1, 999, date);
		TakingPrice tp2 = new TakingPrice(999, price2, 999, date);
		List<TakingPrice> tps = new ArrayList();
		tps.add(tp1);
		tps.add(tp2);
		ipbi.setTakingPrices(tps);
		org.junit.Assert.assertEquals(tps, ipbi.getTakingPrices());
	}
}
