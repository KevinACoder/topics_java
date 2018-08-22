package Linked;
import java.util.Random;
import java.util.Arrays;
import java.util.Vector;

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


	}
}
