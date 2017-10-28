public class Item {
	String name;
	int barCode;

	/*
	 * Constructor of the item
	 */
	public Item (int barCode, String name) {
		this.setName(name);
		this.barCode = barCode;
	}
	public Item(int barCode) {
		this(barCode, null);
	}

	/*
	 * Assign the variable name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Return the attribute name
	 */
	public String getName() {
		return name;
	}

	/*
	 * Return the attribute barCode
	 */
	public int getBarCode() {
		return barCode;
	}

	/*
	 * Print all items' attribute
	 */
	public void print() {
		System.out.println("Nome do item :" + name);
		System.out.println("Código de barras do item :" + barCode);
	}
	
	// fazer método equals
	// fazer método compareTo

}