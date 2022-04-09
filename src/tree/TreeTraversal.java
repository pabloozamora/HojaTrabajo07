package tree;

import java.util.ArrayList;

public class TreeTraversal<K, V> implements ITreeTraversal{
	
	private ArrayList<K> keys = new ArrayList<>();
	private ArrayList<V> values = new ArrayList<>();

	@Override
	public void Walk(Object key, Object value) {
		keys.add((K) key);
		values.add((V) value);
		
	}

	public String getAssociations() {
		String associations = "";
		for (int i = 0; i < keys.size(); i++) {
			associations += keys.get(i);
			associations += ", ";
			associations += values.get(i);
			associations += " ";
		}
		return associations;
	}

}
