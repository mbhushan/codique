import java.util.Stack;


public class ValidBST {

	private Node root1;
	private Node root2;
	
	public static void main(String[] args) {
		ValidBST bst = new ValidBST();
		
		int [] A = {9, 5, 15, 2, 7, 10, 12};
		
		bst.buildBST(A);
		bst.inorder();
		
		bst.validBST();
		
	}
	
	
	public void validBST() {
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		
		boolean ans = validBST(root1, min, max);
		System.out.println("valid bst: " + ans);
	}
	
	private boolean validBST(Node node, int min, int max) {
		if (node == null) {
			return true;
		}
		
		if ((min <= node.data && node.data < max) 
				&& validBST(node.left, min, node.data)
				&& validBST(node.right, node.data, max)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void buildBST(int [] A) {
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
	
	public void inorder() {
		Node node = this.root1;
		
		Stack<Node> stack = new Stack<Node>();
		
		System.out.println("inorder: ");
		while (!stack.isEmpty() || node != null) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				System.out.print(node.data + " ");
				node = node.right;
			}
		}
		System.out.println();
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
