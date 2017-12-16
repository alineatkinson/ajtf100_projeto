package model;

import java.util.List;

public class PricesByItem {
	private Item item;
	private List<TakingPrice> tps;

	public PricesByItem(Item item, List<TakingPrice> tps) {
		this.item = item;
		this.tps = tps;
	}

	public Item getItem() {
		return this.item;
	}

	public List<TakingPrice> getTakingPrices() {
		return this.tps;
	}

}
