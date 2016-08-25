package unionfind_NumberOfIslands;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {

	private Map<Integer, Node> hmap = new HashMap<Integer, Node>();
	
	public void makeSet(int key) {
		Node node = new Node(key);
		hmap.put(key, node);
	}
	
	public boolean union(int a, int b) {
		if (!hmap.containsKey(a) || !hmap.containsKey(b)) {
			return false;
		}
		
		Node aParent = findSet(a);
		Node bParent = findSet(b);
		
		if (aParent.key == bParent.key) {
			return false;
		}
		
		if (aParent.rank >= bParent.rank) {
			//increase rank only if both rank are same.
			aParent.rank = (aParent.rank == bParent.rank) ? aParent.rank + 1: bParent.rank;
			bParent = aParent;
		} else {
			aParent = bParent;
		}
		
		return true;
	}
	
	public Node findSet(int key) {
		if (!hmap.containsKey(key)) {
			return null;
		}
		return findSet(hmap.get(key));
	}
	
	private Node findSet(Node node) {
		if (node.equals(node.parent)) {
			return node;
		}
		return findSet(node.parent);
	}
	
}

class Node {
	int key;
	Node parent;
	int rank;
	
	Node(int key) {
		this.key = key;
		this.parent = this;
		rank = 0;
	}
	
	public String toString() {
		return "[key " + this.key + ", parent: " + this.parent.key + ", rank: " + this.rank + "]";
	}
}
