package model;


public class Supermarket {
	private String name;
	private int cepSuper;
	private int code;


	public Supermarket(String name, int cepSuper, int code) {
		this.name = name;
		this.cepSuper = cepSuper;
		this.code = code;
		System.out.println("Supermercado incluído!");
	}
	public Supermarket(String name, int code) {
		this(name, 0, code);
	}
	
	public int getCode() {
		return this.code;
	}
	public int getCEP() {
		return this.cepSuper;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	// fazer método equals
	// fazer método compareTo
	public int hashCode() {
		return code;
	}
	public String getNameClass(){
		return "Supermarket";
	}
}
