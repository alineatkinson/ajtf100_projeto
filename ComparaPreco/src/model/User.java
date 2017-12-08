package model;


public class User {
	private String name;
	private String cpf;
	
	public User(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}
	/**
	 * @return CPF's User
	 */
	public String getCpf() {
		return cpf;
	}
	/**
	 * @return Name's User
	 */
	public String getName() {
		return name;
	}
}
