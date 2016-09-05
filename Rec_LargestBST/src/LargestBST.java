

public class LargestBST {

	private Node root1;
	
	public static void main(String[] args) {
		LargestBST bst = new LargestBST();
		
		int [] A = {9, 5, 15, 2, 7, 10, 12};
		
		bst.buildBST(A);
		
		bst.maxBST();
	}
	
	public void maxBST() {
		MinMax ans = maxBST(root1);
		System.out.println(ans);
	}
	
	private MinMax maxBST(Node node) {
		if (node == null) {
			return new MinMax();
		}
		
		MinMax left = maxBST(node.left);
		MinMax right = maxBST(node.right);
		
		MinMax curr = new MinMax();
		
		if (!left.isBST || !right.isBST || left.max > node.data || right.min <= node.data) {
			curr.isBST = false;
			curr.size = Math.max(left.size, right.size);
			return curr;
		}
		
		curr.isBST = true;
		curr.size = 1 + left.size + right.size;
		
		curr.min = node.left != null ? left.min: node.data;
		curr.max = node.right != null ? right.max : node.data;
		
		return curr;
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

class MinMax {
	int min;
	int max;
	int size;
	boolean isBST;
	
	MinMax() {
		this.min = Integer.MAX_VALUE;
		this.max = Integer.MIN_VALUE;
		this.size = 0;
		isBST = true;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[min: " + this.min + ", ");
		sb.append("max: " + this.max + ", ");
		sb.append("size: " + this.size + ", ");
		sb.append("isBST: " + this.isBST + "]");
		
		return sb.toString();
	}
}
