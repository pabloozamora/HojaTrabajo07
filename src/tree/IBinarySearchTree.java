package tree;

import java.util.ArrayList;

/**
 * Interfaz IBinarySearchTree. Posee todos los metodos que debe implementar un arbol binario.
 * @author MAAG
 * @version 08/04/2022
 */
public interface IBinarySearchTree<K, V> {

	void insert(K id, V value);
	
	V delete(K id);
	
	V find(K id);
	
	int count();
	
	boolean isEmpty();
	
	ArrayList<V> getElements();
	
	void inOrder(ITreeTraversal<K, V> traversal);
	
	void preOrder(ITreeTraversal<K, V> traversal);
	
	void postOrder(ITreeTraversal<K, V> traversal);
	
}
