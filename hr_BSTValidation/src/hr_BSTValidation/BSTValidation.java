package hr_BSTValidation;

import java.util.Stack;

public class BSTValidation {
	
	private Node root = null;
	public static void main(String[] args) {
		BSTValidation bst = new BSTValidation();
		
		int [] A = {5, 3, 7, 1, 4, 6, 9};
		
		bst.buildBST(A);
		System.out.println(bst.checkBST());
	}
	
	public void buildBST(int [] A) {
		for (int i=0; i<A.length; i++) {
			root = insertBST(root, A[i]);
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
	
	public boolean isBST() {
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		return isBST(root, min, max);
	}
	
	private boolean isBST(Node node, int min, int max) {
		if (node == null) {
			return true;
		}
		
		if (node.data < min || node.data > max) {
			return false;
		}
		
		return (isBST(node.left, min, node.data-1) && isBST(node.right, node.data+1, max));
	}

	public boolean checkBST() {
		return checkBST(root);
	}
	
	private boolean checkBST(Node node) {
	    if (node == null) {
	    	return true;
	    }
	    boolean isBST = true;
	    
	    Stack<Node> stack = new Stack<Node>();
	    stack.push(node);
	    int prev = node.data;
	    
	    while (stack.isEmpty() || node != null) {
	    	if (node != null) {
	    		stack.push(node);
	    		node = node.left;
	    	} else {
	    		node = stack.pop();
	    		if (node.data < prev) {
	    			isBST = false;
	    			break;
	    		}
	    		prev = node.data;
	    		node = node.right;
	    	}
	    }
	    
	    return isBST;
	}
}

class Node {
    int data;
    Node left;
    Node right;
    
    Node (int data) {
    	this.data = data;
    	this.left = null;
    	this.right = null;
    }
 }

