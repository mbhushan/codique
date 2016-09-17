import java.util.ArrayList;


/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, 
called the "root." Besides the root, each house has one and only one parent house. After a tour, the 
smart thief realized that "all houses in this place forms a binary tree". It will automatically contact 
the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.
*/

public class HouseRobber3 {

	private TreeNode root;
	
	public static void main(String[] args) {
		HouseRobber3 hr = new HouseRobber3();
		
		//int [] A = {10, 5, 15, 2, 7, 13, 20, 6, 9, 18};
		//int [] A = {10, 5, 15, 4, 9};
		int [] A = {4, 2, 3};
		
		//hr.buildBST(A);
		hr.buildTree();
		int ans = hr.maxRobValue();
		System.out.println("ans: " + ans);
		
	}
	
	
	public int maxRobValue() {
		ArrayList<Integer> X = new ArrayList<Integer>();
		ArrayList<Integer> Y = new ArrayList<Integer>();
		int max =  Math.max(maxRobValue(root, false, X), maxRobValue(root, true, Y));
		System.out.println(X.toString());
		System.out.println(Y.toString());
		return max;
	}
	
	private int maxRobValue(TreeNode node, boolean flag, ArrayList<Integer> L) {
		if (node == null) {
			return 0;
		}
		int v1 = 0, v2 = 0;
		if (flag) {
			L.add(node.data);
			v1 = maxRobValue(node.left, false, L) + maxRobValue(node.right, false, L) + node.data;
		} else {
			v2 = Math.max(maxRobValue(node.left, true, L) + maxRobValue(node.right, true, L),
					maxRobValue(node.left, false, L) + maxRobValue(node.right, false, L));
		}
		
		return Math.max(v1, v2);
	}
	
	public void buildTree() {
//		this.root = new TreeNode(4);
//		this.root.left = new TreeNode(1);
//		this.root.left.left = new TreeNode(2);
//		this.root.left.left.left = new TreeNode(3);
		this.root = new TreeNode(2);
		this.root.left = new TreeNode(1);
		this.root.right = new TreeNode(3);
		this.root.left.right = new TreeNode(4);
	}
	
	public void buildBST(int [] A) {
		for (int i=0; i<A.length; i++) {
			this.root = insertBST(this.root, A[i]); 
		}
	}
	
	private TreeNode insertBST(TreeNode node, int value) {
		if (node == null) {
			return new TreeNode(value);
		}
		if (value <= node.data) {
			node.left = insertBST(node.left, value);
		} else {
			node.right = insertBST(node.right, value);
		}
		return node;
	}
}

class TreeNode {
	int data;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}
