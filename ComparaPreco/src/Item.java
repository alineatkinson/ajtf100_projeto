public class Item {
	String name;
	int barCode;
	String description;
	/*
	 * Constructor of the item
	 */
	public Item (int barCode, String name, String description) {
		this.setName(name);
		this.barCode = barCode;
		this.setDescription(description);
	}
	public Item (int barCode, String name) {
		this(barCode, name, null);
	}
	public Item(int barCode) {
		this(barCode, null, null);
	}

	/*
	 * Assign the variable name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Assign the variable description
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * Return the attribute barCode
	 */
	public String getDescription() {
		return description;
	}
	/*
	 * Print all items' attribute
	 */
	public void print() {
		System.out.println("Nome do item :" + name);
		System.out.println("C�digo de barras do item :" + barCode);
	}
	
	// fazer m�todo equals
	// fazer m�todo compareTo

}