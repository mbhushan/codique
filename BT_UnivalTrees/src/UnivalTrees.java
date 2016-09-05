
public class UnivalTrees {

	private Node root1;
	private int count = 0;
	
	public static void main(String[] args) {
		UnivalTrees ut = new UnivalTrees();
		
		int [] A = {5, 5, 5, 7, 2};
		ut.buildBST(A);
		
		ut.isUnival();
		
		int [] B = {5, 5, 5, 5};
		ut.buildBST(B);
		ut.isUnival();
		
		int [] C = {5, 5, 2, 5, 5, 5, 7, 1};
		ut.buildBST(C);
		ut.isUnival();
		
		int cnt = ut.countUnivalTrees();
		System.out.println("count: " + cnt);
	}

	
	public int countUnivalTrees() {
		
		countUnivalTrees(root1);
		
		return this.count;
	}
	
	private boolean countUnivalTrees(Node node) {
		if (node == null) {
			return true;
		}
		
		boolean left = countUnivalTrees(node.left);
		boolean right = countUnivalTrees(node.right);
		
		if (left && right) {
			
			//check if leaf
			if (node.left == null && node.right == null) {
				++this.count;
				return true;
			}
			
			//check if left, root & right is valid unival
			else if (node.left != null && node.right != null && node.data == node.left.data && node.data == node.right.data) {
				++this.count;
				return true;
			}
			
			else if (node.left != null && node.data == node.left.data) {
				++this.count;
				return true;
			} 
			
			else if (node.right != null && node.data == node.right.data) {
				++this.count;
				return true;
			}
		}
		return false;
	}
	
	public void isUnival() {
		if (root1 == null) {
			return;
		}
		boolean ans = isUnival(root1, root1.data);
		System.out.println("is unival: " + ans);
	}
	
	private boolean isUnival(Node node, int value) {
		if (node == null) {
			return true;
		}
		
		if (node.data != value) {
			return false;
		}
		
		return (isUnival(node.left, value) && isUnival(node.right, value));
	}
	
	public void buildBST(int [] A) {
		root1 = null;
		if (A == null || A.length < 1) {
			return;
		}
		
		for (int i=0; i<A.length; i++) {
			root1 = insertBST(root1, A[i]);
		}
	}
	
	private Node insertBST(Node node, int value) {
		if (node == null) {
			return new Node(value);
		}
		
		if (value <= node.data) {
			node.left = insertBST(node.left, value);
		} else {
			node.right = insertBST(node.right, value);
		}
		return node;
	}
}

class Node {
	int data;
	Node left;
	Node right;
	
	Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

