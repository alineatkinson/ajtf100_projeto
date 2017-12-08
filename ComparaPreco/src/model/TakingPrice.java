package model;

import java.util.Date;

public class TakingPrice{// implements Comparable<TakingPrice> {

	public int codeBarItem;
	// Date dateTP;
	private double price;
	private int codeSupermarket;
	private Date date;

	public TakingPrice(int codeBarItem, double price, int codeSupermarket, Date date) {
		this.codeBarItem = codeBarItem;
		// this.dateTP = dateTP;
		this.price = price;
		this.codeSupermarket = codeSupermarket;
		this.date = date;
	}

	/*
	 * Assign the variable price as attribute
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/*
	 * Return the attribute price
	 */
	public int getCodeSupermarket() {
		return codeSupermarket;
	}

	/*
	 * Return the attribute price
	 */
	public int getCodeBarItem() {
		return codeBarItem;
	}

	/*
	 * Return the attribute price
	 */
	public double getPrice() {
		return price;
	}

	/*
	 * Return the attribute date
	 */
	public Date getDate() {
		return date;
	}

	public int hashCode() {
		return codeBarItem;
	}
	/*
	 * Print TomadaPreco's attribute
	 */
	/*
	 * public void print() { // Object formatter to brazilian monetary formatter
	 * NumberFormat monetaryFormatter = NumberFormat.getCurrencyInstance(new
	 * Locale("pt", "BR"));
	 * System.out.println("------------------------------------");
	 * System.out.println("Código do Supermercado :" + this.codeSupermarket);
	 * System.out.println("Preço do item :" + monetaryFormatter.format(this.price));
	 * System.out.println("Código do item :" + this.codeBarItem);
	 * System.out.println("------------------------------------"); }
	 */
/*
	@Override
	public int compareTo(TakingPrice tp) {

		if (this.getPrice() < this.getPrice()) {
			return -1;
		}

		if (this.getPrice() > this.getPrice()) {
			return 1;
		}

		return 0;
	}
	*/
}
