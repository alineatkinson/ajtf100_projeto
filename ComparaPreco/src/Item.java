public class Item {
	String name;
	int barCode;

	/*
	 * Constructor of the item
	 */
	public Item(int barCode) {
		this.barCode = barCode;
	}

	/*
	 * Assign the variable name
	 */
	public void setNome(String name) {
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