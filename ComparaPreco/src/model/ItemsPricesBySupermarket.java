package model;
/*
 *  Prices of a group of items by supermarket
 */

import java.util.List;

public class ItemsPricesBySupermarket {
	private List<TakingPrice> takingPrices;
	private List<Item> items;
	private Supermarket supermarket;
	private double sumPrices;
	
	public ItemsPricesBySupermarket(List<TakingPrice> takingPrices, List<Item> items, Supermarket supermarket ){
		this.takingPrices = takingPrices;
		this.items = items;
		this.supermarket = supermarket;
	}
/*
	public void setTakingPrices(List<TakingPrice> tps) {
		this.takingPrices = tps;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setSupermarket(Supermarket supermarket) {
		this.supermarket = supermarket;
	}
*/
	public void sumPrices() {
		System.out.println("tamanho tps: " + takingPrices.size());
		
		for(TakingPrice tp: takingPrices) {
			System.out.println("codigo item: " + tp.getCodeBarItem());
			System.out.println("codigo do super: "+ tp.getCodeSupermarket());
			System.out.println("data : "+ tp.getDate());
			System.out.println("preço : "+ tp.getPrice());
			
			System.out.println("antes "+ sumPrices);
			sumPrices += tp.getPrice();
			System.out.println("depois "+ sumPrices);
		}
	}

	public List<TakingPrice> getTakingPrices() {
		return takingPrices;
	}

	public List<Item> getItems() {
		return items;
	}

	public Supermarket getSupermarket() {
		return supermarket;
	}

	public double getSumPrice() {
		return sumPrices;
	}

}
