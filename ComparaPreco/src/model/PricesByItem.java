package model;

import java.util.List;

public class PricesByItem {
	Item item;
	List<TakingPrice> tps;
	
	public Item getItem() {
		return this.item;
	}
	public List<TakingPrice> getTakingPrices(){
		return this.tps;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public void setTakingPrices(List<TakingPrice> tps) {
		this.tps = tps;
	}
 
}
