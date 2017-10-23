import java.sql.Date;
import java.text.NumberFormat;
import java.util.Locale;

public class TakingPrice {

	int codeBarItem;
	// Date dateTP;
	int price;
	int codeSupermarket;

	public TakingPrice(int codeBarItem, int price, int codeSupermarket) {
		this.codeBarItem = codeBarItem;
		// this.dateTP = dateTP;
		this.price = price;
		this.codeSupermarket = codeSupermarket;
	}

	// public void setSupermercado(int supermercado){
	// this.supermercado = supermercado;
	// }
	/*
	 * Return the attribute
	 */
	// public int getSupermarket() {
	// return supermarket;
	// }
	/*
	 * Assign the variable price as attribute
	 */
	public void setPreco(int price) {
		this.price = price;
	}

	/*
	 * Return the attribute price
	 */
	public double getPrice() {
		return price;
	}

	/*
	 * Print TomadaPreco's attribute
	 */
	public void print() {
		// Object formatter to brazilian monetary formatter
		NumberFormat monetaryFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		System.out.println("------------------------------------");
		System.out.println("Código do Supermercado :" + this.codeSupermarket);
		System.out.println("Preço do item :" + monetaryFormatter.format(this.price));
		System.out.println("Código do item :" + this.codeBarItem);
		System.out.println("------------------------------------");
	}
}
