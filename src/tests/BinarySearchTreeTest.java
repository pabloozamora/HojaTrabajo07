package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tree.BinarySearchTree;
import tree.KeyComparator;

class BinarySearchTreeTest {

	@Test
	void testInsert() {
		BinarySearchTree tree = new BinarySearchTree(new KeyComparator());
		tree.insert("hola", "hi");
		assertEquals(tree.getElements().contains("hi"), true);
	}

	@Test
	void testFind() {
		BinarySearchTree tree = new BinarySearchTree(new KeyComparator());
		tree.insert("hola", "hi");
		assertEquals(tree.find("hola"), "hi");
	}

}
