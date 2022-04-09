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
	
	private int detectLanguage(String text) {
		String[] words = text.split(" ", 0);
		for (String word : words){
			if (engDictionary.find(word) != null) {
				return 1;
			}
		}
		for (String word : words){
			if (frDictionary.find(word) != null) {
				return 2;
			}
		}
		return 0;
	}
	
	public String translate(String word) {
		if (engDictionary.find(word) != null) {
			return engDictionary.find(word);
		}
		if (frDictionary.find(word) != null) {
			return frDictionary.find(word);
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
	
	/*public String deleteWord(String word) {
		
	}*/
}
