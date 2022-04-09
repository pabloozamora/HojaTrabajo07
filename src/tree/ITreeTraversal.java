package tree;

/**
 * Interfaz ITreeTraversal. Posee todos los metodos que debe implementar un traversal.
 * @author MAAG
 * @version 08/04/2022
 */
public interface ITreeTraversal<K, V> {

	void Walk(K key, V value);
	
}
