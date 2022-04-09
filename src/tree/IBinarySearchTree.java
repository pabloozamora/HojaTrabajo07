package tree;

import java.util.ArrayList;

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
