package presentation;

import java.util.Scanner;

/**
 * @author Aline Atkinson da Cunha
 */

class Console implements Reader {

	private Scanner keyboard = new Scanner(System.in);

	/**
	 * O teclado lê o caracter digitado pelo usuário
	 *
	 */
	public char readCharacter() {
		// keyboard.next().charAt(0);

		String input = keyboard.next();
		// char charactere = keyboard.next().charAt(0);
		if (input.length() > 1) {
			throw new IllegalArgumentException("Por favor, insira apenas um caractere.");
		}
		char choice = input.charAt(0);
		return choice;
	}

	/**
	 * The keyboard read the text wirtten into console
	 *
	 */
	public String readText() {
		return keyboard.nextLine();
	}

	/**
	 * O teclado lê o número digitado pelo usuário
	 *
	 */
	public double readNumberDouble() {
		Double number = 0.0;
		try {
			// double c = Double.parseDouble(getValorSolic());
			number = keyboard.nextDouble();
		} catch (NumberFormatException e) {
			System.out.println("Valor solicitado � inv�lido!");
		}

		return number;
	}

	/**
	 * O teclado lê o número digitado pelo usuário
	 *
	 */
	public int readNumber() {
		int number = 0;

		while (true) {
			try {
				String text = readText();
				number = Integer.parseInt(text);
				break;
			} catch (NumberFormatException ex) {
				System.out.println("ERRO! Digite um n�mero!");
				continue;
			}
		}
		return number;
	}

}
