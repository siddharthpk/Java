 import java.util.*;

//
// An implementation of a binary search tree.
//
// This tree stores both keys and values associated with those keys.
//
// More information about binary search trees can be found here:
//
// http://en.wikipedia.org/wiki/Binary_search_tree
//
// Note: Wikipedia is using a different definition of
//       depth and height than we are using.  Be sure
//       to read the comments in this file for the
//	 	 height function.
//
class BinarySearchTree <K extends Comparable<K>, V>  {

	public static final int BST_PREORDER  = 1;
	public static final int BST_POSTORDER = 2;
	public static final int BST_INORDER   = 3;

	// These are package friendly for the TreeView class
	BSTNode<K,V>	root;
	int		count;

	int		findLoops;
	int		insertLoops;

	public BinarySearchTree () {
		root = null;
		count = 0;
		resetFindLoops();
		resetInsertLoops();
	}

	public int getFindLoopCount() {
		return findLoops;
	}

	public int getInsertLoopCount() {
		return insertLoops;
	}

	public void resetFindLoops() {
		findLoops = 0;
	}
	public void resetInsertLoops() {
		insertLoops = 0;
	}

	//
	// Purpose:
	//
	// Insert a new Key:Value Entry into the tree.  If the Key
	// already exists in the tree, update the value stored at
	// that n with the new value.
	//
	// Pre-Conditions:
	// 	the tree is a valid binary search tree
	//
	
	public void insert (K k, V v) {
		BSTNode<K,V> New = new BSTNode<K,V>(k,v);
		BSTNode<K,V> tmp_root = null;
		count++;
        if (root == null) {
            root = New;
			
            return;
        }
		BSTNode<K,V> tmp = root;
        while (tmp != null) {
			insertLoops++;
            tmp_root = tmp;
            if      (k.compareTo(tmp.key) < 0)
				tmp = tmp.left;
            else if (k.compareTo(tmp.key) > 0)
				tmp = tmp.right;
            else {
                tmp.value = v;
				count--;
                return;
            }
        }
        if (k.compareTo(tmp_root.key) < 0) 
			tmp_root.left = New;
        else
			tmp_root.right = New;
	}

	//
	// Purpose:
	//
	// Return the value stored at key.  Throw a KeyNotFoundException
	// if the key isn't in the tree.
	//
	// Pre-conditions:
	//	the tree is a valid binary search tree
	//
	// Returns:
	//	the value stored at key
	//
	// Throws:
	//	KeyNotFoundException if key isn't in the tree
	//
	public V find (K key) throws KeyNotFoundException {
		BSTNode<K,V> n = root;
		while(!n.key.equals(key)){
			findLoops++;
			if(n != null) {
			//go to left tree
				if(n.key.compareTo(key) > 0){
					n = n.left;
				}else {                
					n = n.right;
				}
			//not found
			if(n == null){
					throw new KeyNotFoundException();
				}
			}			
		}
		return n.value;
	}
	
	//
	// Purpose:
	//
	// Return the number of nodes in the tree.
	//
	// Returns:
	//	the number of nodes in the tree.
	public int size() {
		return count;
	}
	
	//
	// Purpose:
	//	Remove all nodes from the tree.
	//
	public void clear() {
		root = null;
		count = 0;
	    resetFindLoops();
		resetInsertLoops();
	}

	//
	// Purpose:
	//
	// Return the height of the tree.  We define height
	// as being the number of nodes on the path from the root
	// to the deepest n.
	//
	// This means that a tree with one n has height 1.
	//
	// Examples:
	//	See the assignment PDF and the test program for
	//	examples of height.
	//
	public int height() {
		return recursiveHeight(root);
	}
	//helper height function
	private int recursiveHeight(BSTNode<K,V> n){
		if(n == null)
			return 0;
		return Math.max(recursiveHeight(n.left), recursiveHeight(n.right))+1;
	}

	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a level-order
	// traversal of the tree.
	//
	// Level order is most commonly implemented using a queue of nodes.
	//
	//  From wikipedia (they call it breadth-first), the algorithm for level order is:
	//
	//	levelorder()
	//		q = empty queue
	//		q.enqueue(root)
	//		while not q.empty do
	//			n := q.dequeue()
	//			visit(n)
	//			if n.left != null then
	//			      q.enqueue(n.left)
	//			if n.right != null then
	//			      q.enqueue(n.right)
	//
	// Note that we will use the Java LinkedList as a Queue by using
	// only the removeFirst() and addLast() methods.
	//
	public List<Entry<K,V>> entryList() {
		List<Entry<K, V>> ls = new LinkedList<Entry<K,V> >();
		LinkedList<BSTNode<K,V>> node = new LinkedList<BSTNode<K,V> >();		
		node.addLast(root);
        while (!node.isEmpty()){
			BSTNode<K,V> tempNode = node.removeFirst();
			ls.add(new Entry<K,V>(tempNode.key, tempNode.value));
            /*Enqueue left child */
            if (tempNode.left != null) {
                node.addLast(tempNode.left);
            }
            /*Enqueue right child */
            if (tempNode.right != null) {
				node.addLast(tempNode.right);
            }
        }
		return ls;
	}
	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a traversal 
	// specified by the parameter which.
	//
	// If which is:
	//	BST_PREORDER	perform a pre-order traversal
	//	BST_POSTORDER	perform a post-order traversal
	//	BST_INORDER	perform an in-order traversal
	//
	public List<Entry<K,V> > entryList (int which) {
		List<Entry<K,V> > ls = new LinkedList<Entry<K,V> >();
		if(which == BST_PREORDER)
			preOrder(root,ls);
		if(which == BST_POSTORDER)
			postOrder(root,ls);
		if(which==BST_INORDER)
			inOrder(root,ls);
		return ls;
	}
	private void inOrder (BSTNode<K,V> n, List <Entry<K,V> > ls){
		if (n == null)
            return;
		inOrder(n.left,ls);
		ls.add(new Entry<K,V>(n.key, n.value));
		inOrder(n.right,ls);
	}
	private void preOrder (BSTNode<K,V> n, List <Entry<K,V> > ls){
		if (n == null)
            return;
		
		ls.add(new Entry<K,V>(n.key, n.value));
		preOrder(n.left,ls);
		preOrder(n.right,ls);
	}
	private void postOrder (BSTNode<K,V> n, List <Entry<K,V> > ls){
		if (n == null)
            return;
		postOrder(n.left,ls);
		postOrder(n.right,ls);
		ls.add(new Entry<K,V>(n.key, n.value));
		
	}
	// Your instructor had the following private methods in his solution:
	// private void doInOrder (BSTNode<K,V> n, List <Entry<K,V> > ls);
	// private void doPreOrder (BSTNode<K,V> n, List <Entry<K,V> > ls);
	// private void doPostOrder (BSTNode<K,V> n, List <Entry<K,V> > ls);
	// private int doHeight (BSTNode<K,V> t)
}
