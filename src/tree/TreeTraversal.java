package tree;

import java.util.ArrayList;

/**
 * Clase TreeTraversal. Permite la creacion de un traversal que recopila los valores de un arbol binario.
 * @author Pablo Zamora
 * @version 08/04/2022
 * @param <K> Clase a la que pertenecen las llaves del arbol
 * @param <V> Clase a la que pertenecen los valores del arbol
 */
public class TreeTraversal<K, V> implements ITreeTraversal{
	
	private ArrayList<K> keys = new ArrayList<>();
	private ArrayList<V> values = new ArrayList<>();

	/**
	 * Metodo walk. Permite agregar a las listas de llaves y valores la llave y el valor del nodo actual.
	 * @parm key K. Llave del nodo actual.
	 * @param value V. Valor del nodo actual.
	 */
	@Override
	public void Walk(Object key, Object value) {
		keys.add((K) key);
		values.add((V) value);
		
	}

	/**
	 * Metodo getAssociations. Permite obtener una cadena que empareja la llave de cada nodo con su respectivo valor.
	 * @return String. Lista de asociaciones.
	 */
	public String getAssociations() {
		String associations = "";
		for (int i = 0; i < keys.size(); i++) {
			associations += "(";
			associations += keys.get(i);
			associations += ", ";
			associations += values.get(i);
			associations += ") ";
		}
		return associations;
	}

}
