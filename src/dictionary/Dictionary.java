package dictionary;

import tree.*;

/**
 * Clase Dictionary. Clase controladora que posee todos los metodos necesarios para agregar, buscar, editar y modificar
 * palabras de ambos diccionarios.
 * @author Pablo Zamora
 * @version 08/04/2022
 */
public class Dictionary {

	BinarySearchTree<String, String> engDictionary; //Diccionario ingles-espanol
	BinarySearchTree<String, String> frDictionary; //Diccionario frances-espanol
	
	/**
	 * Metodo constructor.
	 */
	public Dictionary(){
		engDictionary = new BinarySearchTree(new KeyComparator());
		frDictionary = new BinarySearchTree(new KeyComparator());
	}
	
	/**
	 * Metodo addWord. Permite agregar una nueva palabra a ambos diccionarios.
	 * @param spanish String. Traduccion en espanol
	 * @param english String. Palabra en ingles
	 * @param french String. Palabra en frances
	 */
	public void addWord(String spanish, String english, String french) {
		engDictionary.insert(english.toLowerCase(), spanish.toLowerCase());
		frDictionary.insert(french.toLowerCase(), spanish.toLowerCase());
	}
	
	/**
	 * Metodo translate. Busca una palabra en ambos diccionarios y devuelve su traduccion al espanol.
	 * @param word String. Palabra (ya sea en ingles o en frances)
	 * @return String. Traduccion en espanol o la palabra original, en caso de que aun no se encuentre en el diccionario
	 */
	public String translate(String word) {
		word.toLowerCase(); //Se transforman todos los caracteres a minusculas
		if (engDictionary.find(word) != null) { //Si la palabra esta en el diccionario ingles-espanol
			return "*" + engDictionary.find(word) + "*"; //Se devuelve su traduccion
		}
		if (frDictionary.find(word) != null) { //Si la palabra esta en el diccionario frances-espanol
			return "*" + frDictionary.find(word) + "*"; //Se devuelve su traduccion
		}
		return word; //Si no se encuentra en ninguno de los diccionarios, se devuelve la palabra original
	}
	
	/**
	 * Metodo getEntries. Permite obtener todas las entradas de uno de los diccionarios hasta el momento (in-order).
	 * @param dictionary int. Determina el diccionario a desplegar
	 * @return Listado de palabras y su respectiva traduccion al espanol
	 */
	public String getEntries(int dictionary) {
		String result = "";
		if (dictionary == 1) { //Diccionario ingles-espanol
			TreeTraversal traversal = new TreeTraversal(); //Se crea un traversal
			engDictionary.inOrder(traversal); //Se recorre el arbol In-Order
			result = traversal.getAssociations(); //Se obtiene el listado de asociaciones
		}
		else { //Diccionario frances-espanol
			TreeTraversal traversal = new TreeTraversal(); //Se crea un traversal
			frDictionary.inOrder(traversal); //Se recorre el arbol In-Order
			result = traversal.getAssociations(); //Se obtiene el listado de asociaciones
		}
		return result;
	}
	
	/**
	 * Metodo deleteWord. Permite eliminar una palabra de ambos diccionarios.
	 * @param english String. Palabra en ingles
	 * @param french String. Palabra en frances
	 * @return String. Traduccion a espanol de la palabra eliminada
	 */
	public String deleteWord(String english, String french) {
		String result = null;
		if (engDictionary.find(english.toLowerCase())!=null && frDictionary.find(french.toLowerCase())!=null) { //Se determina si la palabra existe en ambos diccionarios
			result = engDictionary.delete(english); //Se elimina del diccionario ingles-espanol
			frDictionary.delete(french); //Se elimina del diccionario frances-espanol
		}
		return result;
	}
}
