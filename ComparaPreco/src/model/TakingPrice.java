package model;

import java.util.Date;

public class TakingPrice {// implements Comparable<TakingPrice> {

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

}
