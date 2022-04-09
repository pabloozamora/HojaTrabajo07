package tree;

/**
 * Clase BinaryTree. Posee los metodos necesarios para el manejo de un nodo de un arbol binario..
 * @author MAAG
 * @version 08/04/2022
 */
public class BinaryTree<K, V> {

	private V value;
	private K id;
	private BinaryTree<K, V> left;
	private BinaryTree<K, V> right;
	private BinaryTree<K, V> parent;
	
	public BinaryTree(K id, V value) {
		setId(id);
		setValue(value);
		setLeft(null);
		setRight(null);
		setParent(null);
	}
	
	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}
	/**
	 * @return the id
	 */
	public K getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(K id) {
		this.id = id;
	}
	/**
	 * @return the left
	 */
	public BinaryTree<K, V> getLeft() {
		return left;
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(BinaryTree<K, V> left) {
		this.left = left;
	}
	/**
	 * @return the right
	 */
	public BinaryTree<K, V> getRight() {
		return right;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(BinaryTree<K, V> right) {
		this.right = right;
	}
	/**
	 * @return the parent
	 */
	public BinaryTree<K, V> getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(BinaryTree<K, V> parent) {
		this.parent = parent;
	}
	
	
}
