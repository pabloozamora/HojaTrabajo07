package tree;

import java.util.Comparator;

/**
 * Clase KeyComparator. Permite la creacion de un comparador de strings.
 * @author Pablo Zamora
 * @version 08/04/2022
 */
public class KeyComparator implements Comparator<String>{

	/**
	 * Metodo compare. Permite comparar dos cadenas distintas.
	 * @param s1 String. Primera cadena a comparar.
	 * @param s2 String. Segunda cadena a comparar.
	 * @return int. Devuelve un valor negativo si la primera cadena es menor que la segunda, positivo si es el caso contrario y 0 si son iguales
	 */
	@Override
	public int compare(String s1, String s2) {
		return s1.compareTo(s2);
	}


}
