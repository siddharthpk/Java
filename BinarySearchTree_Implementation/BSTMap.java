import java.util.*;

public class BSTMap<K extends Comparable<K>, V > implements  Map<K, V>  {
	BinarySearchTree<K, V> tree = new BinarySearchTree<K,V>();
	public BSTMap () {
		
	}

	public boolean containsKey(K key) {
		try{
			tree.find(key);
			return true;
		}catch(KeyNotFoundException e){
			return false;
		}
	}

	public V get (K key) throws KeyNotFoundException {
		return tree.find(key);
	}

	public List<Entry<K,V>> entryList() {
		return tree.entryList();
	}

	public void put (K key, V value) {
		tree.insert(key,value);
	}

	public int size() {
		return tree.size();
	}

	public void clear() {
		tree.clear();
	}

	public int getGetLoopCount() {
		return tree.getFindLoopCount();
	}

	public int getPutLoopCount() {
		return tree.getInsertLoopCount();
	}

	public void resetGetLoops() {
		tree.resetFindLoops();
	}
	public void resetPutLoops() {
		tree.resetInsertLoops();
	}
}
