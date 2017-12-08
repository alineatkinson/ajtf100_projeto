package presentation;

import java.util.Scanner;

/**
 * @author Aline Atkinson da Cunha
 */

interface Reader {

	/**
	 * Read the character written into console
	 *
	 */
	abstract char readCharacter();

	// return teclado.next().charAt(0);

	/**
	 * Read the text written into console
	 *
	 */
	abstract String readText();

	// return teclado.nextLine();

	/**
	 * Read the int number written into console
	 *
	 */
	// public abstract int readNumber();

	/**
	 * Read the double number written into console
	 *
	 */
	int readNumber();

}