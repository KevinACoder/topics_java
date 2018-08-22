package Linked;
import java.util.Random;
import java.util.Arrays;
import java.util.Vector;
import java.util.Stack;

public class BinarySearchTree {
	class BST_Node{
		public int		 val;
		public BST_Node left;
		public BST_Node right;
		
		public BST_Node(int val){
			this.val = val;
			left = null;
			right = null;
		}
	}

	private BST_Node insert(BST_Node root, int val){
		if(root == null){
			return new BST_Node(val);
		}

		if(root.val > val){
			root.left = insert(root.left, val);
		}
		else if(root.val < val){
			root.right = insert(root.right, val);
		}
		else{
		}

		return root;
	}

	private void inorderVisit(BST_Node root, Vector<Integer> list){
		if(root == null){
			return;
		}

		inorderVisit(root.left, list);
		list.addElement(new Integer(root.val));
		inorderVisit(root.right, list);
	}
	
	private boolean isBST(BST_Node root) {
		return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean isBSTUtil(BST_Node root, int min, int max) {
		if(root == null) {
			return true;
		}
		
		if(root.val <= min || root.val >= max) {
			return false;
		}
		
		return isBSTUtil(root.left, min, root.val) && 
				isBSTUtil(root.right, root.val, max);
	}
	
	/*iter inorder traversal*/
	private int findKthSmallestElement(BST_Node root, int k){
		if(root == null){
			return Integer.MIN_VALUE;
		}

		Stack<BST_Node> s = new Stack<BST_Node>();
		BST_Node curr = root;
		int count = 0;

		while(curr != null || s.size() > 0){
			while(curr != null){
				s.push(curr);
				curr = curr.left;
			}

			curr = s.pop();
			++count;
			if(count == k)
				return curr.val;

			curr = curr.right;
		}
		
		return Integer.MAX_VALUE;
	}
	
	private int findFloorElement(BST_Node root, final int key) {
		if(root == null) {
			return Integer.MAX_VALUE; //max value
		}
		
		if(root.val == key) {
			return root.val;
		}
		
		if(root.val > key) {
			return findFloorElement(root.left, key);
		}
		
		int rightFloor = findFloorElement(root.right, key);//try to find floor in right subtree 
		if(rightFloor <= key) {//if valid
			return rightFloor;
		}
		else {
			return root.val;
		}
	
	}
	
	BST_Node deleteNode(BST_Node root, final int key){
		if(root == null){
			return root;
		}

		if(root.val > key){
			root.left = deleteNode(root.left, key);
		}
		else if(root.val < key){
			root.right = deleteNode(root.right, key);
		}
		else{
			if(root.left == null){
				return root.right;
			}
			else if(root.right == null){
				return root.left;
			}

			root.val = findMiniVal(root);//raplace value of target node 
			root.right = deleteNode(root.right, key);//reduce problem
		}

		return root;
	}

	int findMiniVal(BST_Node root){
		while(root.left != null){
			root = root.left;
		}
		return root.val;
	}
	
	public void demo() {
		System.out.println("binary search tree");
		final int len = 15;
		Random rand = new Random();
		int[] items =  new int[len];
		BST_Node root = null;
		
		for (int j = 0; j < items.length; j++) {
			items[j] = rand.nextInt(100);
			root = insert(root, items[j]);
		}
		System.out.println("add order: \n" + Arrays.toString(items) + "\n");
		
		Vector<Integer> v = new Vector<Integer>(3, 2); //ini size is 3, increment is 2
		inorderVisit(root, v);
		
		for(int j = 0; j < v.size(); ++j) {
			System.out.print(v.elementAt(j) + " ");
		}
		System.out.println("");
		System.out.println("is bst :" + isBST(root));
		System.out.println("the 3rd node: " + findKthSmallestElement(root, 3));
		System.out.println("floor element of 40: " + findFloorElement(root, 40));
		
		deleteNode(root, items[len/2]);
		System.out.println("after deletion");
		v.clear();
		inorderVisit(root, v);
		
		for(int j = 0; j < v.size(); ++j) {
			System.out.print(v.elementAt(j) + " ");
		}
	}
}
