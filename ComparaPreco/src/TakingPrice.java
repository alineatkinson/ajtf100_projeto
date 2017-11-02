import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class TakingPrice {

	int codeBarItem;
	// Date dateTP;
	double price;
	int codeSupermarket;
	LocalDateTime date;

	public TakingPrice(int codeBarItem, double price, int codeSupermarket, LocalDateTime date) {
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
	public LocalDateTime getDate() {
		return date;
	}
	/*
	 * Print TomadaPreco's attribute
	 */
	/*
	public void print() {
		// Object formatter to brazilian monetary formatter
		NumberFormat monetaryFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		System.out.println("------------------------------------");
		System.out.println("Código do Supermercado :" + this.codeSupermarket);
		System.out.println("Preço do item :" + monetaryFormatter.format(this.price));
		System.out.println("Código do item :" + this.codeBarItem);
		System.out.println("------------------------------------");
	}
	*/
}
