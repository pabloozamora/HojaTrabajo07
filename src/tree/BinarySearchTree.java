package tree;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Clase BinarySearchTree. Posee los metodos necesarios para la insercion, eliminacion y busqueda
 * de valores dentro de la estructura de un arbol binario.
 * @author MAAG
 * @version 08/04/2022
 * @param K Clase a la que pertenecen las llaves
 * @param V Clase a la que pertencen los valores
 */
public class BinarySearchTree<K, V> implements IBinarySearchTree<K, V> {
	
	private int count;
	private BinaryTree<K, V> root;
	private Comparator<K> keyComparator;
	
	/**
	 * Metodo constructor.
	 * @param _keyComparator Comparador que se utilizara para ordenar los nodos por llave
	 */
	public BinarySearchTree(Comparator<K> _keyComparator) {
		this.keyComparator = _keyComparator;
		root = null;
		count = 0;
	}

	/**
	 * Metodo insert. Permite insertar un nuevo nodo dentro del arbol
	 * @param id K. Llave del nodo
	 * @param value V. Valor del nodo
	 */
	@Override
	public void insert(K id, V value) {
		
		if (isEmpty()) {
			root = new BinaryTree<K, V>(id, value);
			count++;
		} else {
			internalInsert(root, id, value);
		}
	}

	/**
	 * Metodo delete. Permite eliminar un nodo del arbol y reorganizarlo.
	 * @param id K. Llave del nodo
	 */
	@Override
	public V delete(K id) {
		if (!isEmpty()) {
			int result = keyComparator.compare(root.getId(), id);
			
			if (result > 0) {
				return internalDelete(root.getLeft(), id, true);
			} else if (result < 0) {
				return internalDelete(root.getRight(), id, false);
			} else { //Root is the deleted node
				
				if (count() == 1) {
					
					V temp = root.getValue();
					root = null;
					count--;
					return temp;
					
				} else {
					
					V tempValue = root.getValue();
					
					if (root.getRight() != null) { //Buscar hijo derecho mas izquierdo
							
						BinaryTree<K, V> leftOfTheRights = root.getRight();
						
						while(leftOfTheRights.getLeft() != null) {
							leftOfTheRights = leftOfTheRights.getLeft(); 
						}
						
						//Assigning the left side
						leftOfTheRights.setLeft(root.getLeft());
						if (leftOfTheRights.getLeft() != null)
							leftOfTheRights.getLeft().setParent(leftOfTheRights);
						
						//Assiginig the right side
						if (keyComparator.compare(root.getRight().getId(), leftOfTheRights.getId()) != 0) { //Only if the leftOfTheRights is different than root.right
							leftOfTheRights.getParent().setLeft(null);
							
							BinaryTree<K, V> newRootRight = leftOfTheRights;
							
							while (newRootRight.getRight() != null) {
								newRootRight = newRootRight.getRight();
							}
							
							newRootRight.setRight(root.getRight());
							if (newRootRight.getRight() != null) {
								newRootRight.getRight().setParent(newRootRight);;
							}
							
						}
						
						//Assginig the new parents
						if (leftOfTheRights.getRight() != null)
							leftOfTheRights.getRight().setParent(leftOfTheRights);
						
						leftOfTheRights.setParent(null);
						root = leftOfTheRights;
						
						count--;
						return tempValue;
						
					} else { //Buscar hijo izquierdo mas derecho
						
						BinaryTree<K, V> rightOfTheLefts = root.getLeft();
						
						while(rightOfTheLefts.getRight() != null) {
							rightOfTheLefts = rightOfTheLefts.getRight(); 
						}
						
						//Assigning the right side
						rightOfTheLefts.setRight(root.getRight());
						if (rightOfTheLefts.getRight() != null)
							rightOfTheLefts.getRight().setParent(rightOfTheLefts);
						
						//Assiginig the left side
						if (keyComparator.compare(root.getLeft().getId(), rightOfTheLefts.getId()) != 0) { //Only if the rightOfTheLefts is different than root.left
							rightOfTheLefts.getParent().setRight(null);
							
							BinaryTree<K, V> newRootLeft = rightOfTheLefts;
							
							while (newRootLeft.getLeft() != null) {
								newRootLeft = newRootLeft.getLeft();
							}
							
							newRootLeft.setLeft(root.getLeft());
							if (newRootLeft.getLeft() != null) {
								newRootLeft.getLeft().setParent(newRootLeft);;
							}
							
						}
						
						//Assginig the new parentes
						if (rightOfTheLefts.getLeft() != null)
							rightOfTheLefts.getLeft().setParent(rightOfTheLefts);
						
						rightOfTheLefts.setParent(null);
						root = rightOfTheLefts;
						
						count--;
						return tempValue;
						
						
					}
					
					
				}
				
			}
		}
		
		return null;
	}

	/**
	 * Metodo find. Permite obtener el valor de un nodo.
	 * @param id K. Llave del nodo
	 */
	@Override
	public V find(K id) {
		return internalFind(root, id);
	}

	/**
	 * Metodo count. Permite obtener el conteo de nodos.
	 * @return int. Cantidad de nodos
	 */
	@Override
	public int count() {
		return count;
	}

	/**
	 * Metodo isEmpty. Permite determinar si el arbol esta vacio.
	 * return boolean. True si esta vacio, false si no lo esta
	 */
	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * Metodo getElements. Permite obtener un arreglo con todos los valores del arbol binario.
	 * @return ArrayList. Listado de los valores del arbol.
	 */
	@Override
	public ArrayList<V> getElements() {
		ArrayList<V> list = new ArrayList<V>();
		
		internalGetElements(list, root);
		
		return list;
	}

	/**
	 * Metodo inOrder. Permite recorrer el arbol inOrder.
	 * @param traversal. ITreeTraversal<K,V> traversal que recopila los valores en el orden indicado
	 */
	@Override
	public void inOrder(ITreeTraversal<K, V> traversal) {
		internalInOrder(root, traversal);
	}

	/**
	 * Metodo inOrder. Permite recorrer el arbol preOrder.
	 * @param traversal. ITreeTraversal<K,V> traversal que recopila los valores en el orden indicado
	 */
	@Override
	public void preOrder(ITreeTraversal<K, V> traversal) {
		internalPreOrder(root, traversal);
		
	}

	/**
	 * Metodo inOrder. Permite recorrer el arbol postOrder.
	 * @param traversal. ITreeTraversal<K,V> traversal que recopila los valores en el orden indicado
	 */
	@Override
	public void postOrder(ITreeTraversal<K, V> traversal) {
		internalPostOrder(root, traversal);
	}
	

	/**
	 * metodo internalInsert. Realiza la insercion de un nodo.
	 * @param actual BinaryTree<K, V> Nodo actual
	 * @param id K. Llave del nuevo nodo
	 * @param value V. Valor del nuevo nodo
	 */
	private void internalInsert(BinaryTree<K, V> actual, K id, V value) {
		
		int result = keyComparator.compare(actual.getId(), id);
		
		if (result > 0) { //actual id is greater than new id then search in the left side
			
			if (actual.getLeft() == null) { //Doesn't have left children
				BinaryTree<K, V> newNode = new BinaryTree<K, V>(id, value);
				actual.setLeft(newNode);
				newNode.setParent(actual);
				count++;
			} else {
				internalInsert(actual.getLeft(), id, value);
			}
			
		} else if (result < 0) { //actual id is smaller than the new then search in the right
			if (actual.getRight() == null) { //Doesn't have left children
				BinaryTree<K, V> newNode = new BinaryTree<K, V>(id, value);
				actual.setRight(newNode);
				newNode.setParent(actual);
				count++;
			} else {
				internalInsert(actual.getRight(), id, value);
			}
		}
		
	}
	
	/**
	 * Metodo internalInOrder. Recorre el arbol in-order.
	 * @param actual BinaryTree<K, V> Nodo de donde comienza el recorrido
	 * @param traversal ITreeTraversal<K, V> traversal que recopila los valores del arbol
	 */
	private void internalInOrder(BinaryTree<K, V> actual, ITreeTraversal<K, V> traversal) {
		if (actual != null) {
			internalInOrder(actual.getLeft(), traversal);
			
			traversal.Walk(actual.getId(), actual.getValue());
			
			internalInOrder(actual.getRight(), traversal);
		}
	}
	
	/**
	 * Metodo internalInOrder. Recorre el arbol pre-order.
	 * @param actual BinaryTree<K, V> Nodo de donde comienza el recorrido
	 * @param traversal ITreeTraversal<K, V> traversal que recopila los valores del arbol
	 */
	private void internalPreOrder(BinaryTree<K, V> actual, ITreeTraversal<K, V> traversal) {
		if (actual != null) {
			traversal.Walk(actual.getId(), actual.getValue());
			
			internalPreOrder(actual.getLeft(), traversal);
			
			internalPreOrder(actual.getRight(), traversal);
		}
	}
	
	/**
	 * Metodo internalInOrder. Recorre el arbol post-order.
	 * @param actual BinaryTree<K, V> Nodo de donde comienza el recorrido
	 * @param traversal ITreeTraversal<K, V> traversal que recopila los valores del arbol
	 */
	private void internalPostOrder(BinaryTree<K, V> actual, ITreeTraversal<K, V> traversal) {
		if (actual != null) {
		
			internalPostOrder(actual.getLeft(), traversal);
			
			internalPostOrder(actual.getRight(), traversal);
			
			traversal.Walk(actual.getId(), actual.getValue());
		}
	}
	
	/**
	 * Metodo internalFind. Realiza la busqueda de un nodo,
	 * @param actual BinaryTree<K, V> nodo actual
	 * @param id K. Llave del nodo a encontrar
	 * @return V. Valor del nodo encontrado.
	 */
	private V internalFind(BinaryTree<K, V> actual, K id) {
		if (actual != null) {
			int result = keyComparator.compare(actual.getId(), id);
			
			if (result > 0) {
				return internalFind(actual.getLeft(), id);
			} else if (result < 0) {
				return internalFind(actual.getRight(), id);
			} else {
				return actual.getValue();
			}
			
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo internalGetElements. Recorre el arbol para obtener todos sus valores.
	 * @param list ArrayList<V> Lista de valores
	 * @param actual BinaryTree<K, V> Nodo actual
	 */
	private void internalGetElements(ArrayList<V> list, BinaryTree<K, V> actual) {
		if (actual != null) {
			internalGetElements(list, actual.getLeft());
			
			list.add(actual.getValue());
			
			internalGetElements(list, actual.getRight());
		}
	}
	
	/**
	 * Metodo internalDelete. Realiza la eliminacion de un nodo.
	 * @param actual BinaryTree<K, V> Nodo actual
	 * @param id K. Llave del nodo a eliminar
	 * @param isLeft boolean. Determina si el nodo era hijo izquierdo
	 * @return V. Valor del nodo eliminado
	 */
	private V internalDelete(BinaryTree<K, V> actual, K id, boolean isLeft) {
		if (actual != null) {
			int result = keyComparator.compare(actual.getId(), id);
			
			if (result > 0) { //search in the left side
				return internalDelete(actual.getLeft(), id, true);
			} else if (result < 0) {//search in the right side
				return internalDelete(actual.getRight(), id, false);
			} else { //actual is the node to be deleted
				
				//The actual node is a leaft
				if ( (actual.getLeft() == null) && (actual.getRight() == null) ) { // If is a leaft
					V tempValue = actual.getValue();
					if (isLeft) {
						actual.getParent().setLeft(null);
						actual.setParent(null);
					} else {
						actual.getParent().setRight(null);
						actual.setParent(null);
					}
					count--;
					return tempValue;
				} else { //Is intermediate node
				
					V tempValue = actual.getValue();
					
					if (actual.getRight() != null) { //Buscar hijo derecho mas izquierdo
						
						BinaryTree<K, V> leftOfTheRights = actual.getRight();
						
						while(leftOfTheRights.getLeft() != null) {
							leftOfTheRights = leftOfTheRights.getLeft(); 
						}
						
						//Assigning the left side
						leftOfTheRights.setLeft(actual.getLeft());
						if (leftOfTheRights.getLeft() != null)
							leftOfTheRights.getLeft().setParent(leftOfTheRights);
						
						//Assiginig the right side
						if (keyComparator.compare(actual.getRight().getId(), leftOfTheRights.getId()) != 0) { //Only if the leftOfTheRights is different than root.right
							leftOfTheRights.getParent().setLeft(null);
							
							BinaryTree<K, V> newRootRight = leftOfTheRights;
							
							while (newRootRight.getRight() != null) {
								newRootRight = newRootRight.getRight();
							}
							
							newRootRight.setRight(actual.getRight());
							if (newRootRight.getRight() != null) {
								newRootRight.getRight().setParent(newRootRight);;
							}
							
						}
						
						//Assginig the new parentes
						if (leftOfTheRights.getRight() != null)
							leftOfTheRights.getRight().setParent(leftOfTheRights);
						
						//Assigning new son to the parent
						leftOfTheRights.setParent(actual.getParent());
						if (isLeft) {
							leftOfTheRights.getParent().setLeft(leftOfTheRights);
						} else {
							leftOfTheRights.getParent().setRight(leftOfTheRights);
						}
						
						count--;
						return tempValue;
						
					} else { //Buscar hijo izquierdo mas derecho
						
						BinaryTree<K, V> rightOfTheLefts = actual.getLeft();
						
						while(rightOfTheLefts.getRight() != null) {
							rightOfTheLefts = rightOfTheLefts.getRight(); 
						}
						
						//Assigning the right side
						rightOfTheLefts.setRight(actual.getRight());
						if (rightOfTheLefts.getRight() != null)
							rightOfTheLefts.getRight().setParent(rightOfTheLefts);
						
						//Assiginig the left side
						if (keyComparator.compare(actual.getLeft().getId(), rightOfTheLefts.getId()) != 0) { //Only if the rightOfTheLefts is different than root.left
							rightOfTheLefts.getParent().setRight(null);
							
							BinaryTree<K, V> newRootLeft = rightOfTheLefts;
							
							while (newRootLeft.getLeft() != null) {
								newRootLeft = newRootLeft.getLeft();
							}
							
							newRootLeft.setLeft(actual.getLeft());
							if (newRootLeft.getLeft() != null) {
								newRootLeft.getLeft().setParent(newRootLeft);;
							}
							
						}
						
						//Assginig the new parentes
						if (rightOfTheLefts.getLeft() != null)
							rightOfTheLefts.getLeft().setParent(rightOfTheLefts);
						
						rightOfTheLefts.setParent(actual.getParent());
						if (isLeft) {
							rightOfTheLefts.getParent().setLeft(rightOfTheLefts);
						} else {
							rightOfTheLefts.getParent().setRight(rightOfTheLefts);
						}
						
						count--;
						return tempValue;
						
						
					}
					
					
					
					
					
					
					
					
				}
				
			}
			 
		} else {
			return null;
		}
	}

}
