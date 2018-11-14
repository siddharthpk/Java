/*
*	Siddharth Pathak 
*	V00876495
*	CSC225 Assignment_3
*/

public class AVL_BST{
	public static boolean checkAVL(BST b){
		return (findMaxDepth(b.getRoot()) - findMinDepth(b.getRoot())) <= 1;
	}
 
    public static int findMaxDepth(Node root){
        if (root == null){
            return 0;
        }
        return 1 + Math.max(findMaxDepth(root.leftNode), findMaxDepth(root.rightNode));
    }
 
    public static int findMinDepth(Node root){
        if (root == null){
            return 0;
        }
        return 1 + Math.min(findMinDepth(root.leftNode), findMinDepth(root.rightNode));
    }
	
	public static BST createBST(int[] a){
		BST test = new BST();
		for (int i=0; i<a.length; ++i)
			test.insert(a[i]);
		return test;
	}
	public static void main(String[] args){
		int[] A = {5,2,8,6,1,9,52,3};
		int[] A2 = {82, 85, 153, 195, 124, 66, 200, 193, 185, 243, 73, 153, 76};
		int[] A3 = {5, 3, 7, 1};
		int[] A4 = {5, 1, 98, 100, -3, -5, 55, 3, 56, 50};
		int[] A5 = {297, 619, 279, 458, 324, 122, 505, 549, 83, 186, 131, 71};
		int[] A6 = {78};
		BST b = createBST(A5);
		System.out.println(checkAVL(b));
	}
}

class Node{
	int key;
    public Node leftNode = null;
    public Node rightNode = null;
    
	public Node(int item){
        key = item;
	}
}

class BST{
	Node root;
	public BST(){
		root = null;
	}
	
	Node getRoot(){
		return root;
	}
	
	void insert(int key){
       root = insertRec(root, key);
    }
	
    Node insertRec(Node root, int key){
        if (root == null){
            root = new Node(key);
            return root;
        }
        if (key < root.key)
            root.leftNode = insertRec(root.leftNode, key);
        else if (key > root.key)
            root.rightNode = insertRec(root.rightNode, key);
        return root;
    }
}