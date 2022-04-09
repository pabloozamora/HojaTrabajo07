package dictionary;

import tree.*;

public class Dictionary {

	BinarySearchTree<String, String> engDictionary;
	BinarySearchTree<String, String> frDictionary;
	
	public Dictionary(){
		engDictionary = new BinarySearchTree(new KeyComparator());
		frDictionary = new BinarySearchTree(new KeyComparator());
	}
	
	public void addWord(String spanish, String english, String french) {
		engDictionary.insert(english.toLowerCase(), spanish.toLowerCase());
		frDictionary.insert(french.toLowerCase(), spanish.toLowerCase());
	}
	
	public String translate(String word) {
		word.toLowerCase();
		if (engDictionary.find(word) != null) {
			return "*" + engDictionary.find(word) + "*";
		}
		if (frDictionary.find(word) != null) {
			return "*" + frDictionary.find(word) + "*";
		}
		return word;
	}
	
	public String getEntries(int dictionary) {
		String result = "";
		if (dictionary == 1) {
			TreeTraversal traversal = new TreeTraversal();
			engDictionary.inOrder(traversal);
			result = traversal.getAssociations();
		}
		else {
			TreeTraversal traversal = new TreeTraversal();
			frDictionary.inOrder(traversal);
			result = traversal.getAssociations();
		}
		return result;
	}
	
	public String deleteWord(String english, String french) {
		String result = null;
		if (engDictionary.find(english.toLowerCase())!=null && frDictionary.find(french.toLowerCase())!=null) {
			result = engDictionary.delete(english);
			frDictionary.delete(french);
		}
		return result;
	}
}
