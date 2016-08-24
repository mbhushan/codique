package tree_UnionFind;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet<T> {
	
	Map<T, Node<T>> hmap = new HashMap<T, Node<T>>();
	
	public void makeSet(T data) {
		Node<T> node = new Node<T>(data);
		hmap.put(data, node);
	}
	
	public boolean union(T a, T b) {
		
		Node<T> first = findSet(a);
		Node<T> second = findSet(b);
		
		if (first == null || second == null) {
			return false;
		}
		
		if (first.value.equals(second.value)) {
			return  false;
		}
		
		if (first.height >= second.height) {
			first.height = (first.height == second.height) ? first.height+1: first.height;
			second.parent = first;
		} else {
			first.parent = second;
		}
		
		return true;
	}
	
	public Node<T> findSet(T data) {
		if (!hmap.containsKey(data)) {
			return null;
		}
		Node<T> node = hmap.get(data);
		return findSet(node);
		
	}
	
	private Node<T> findSet(Node<T> node) {
		if (node.equals(node.parent)) {
			return node.parent;
		}
		return findSet(node.parent);
	}
}

class Node<T> {
	T value;
	Node<T> parent;
	int height;
	
	public Node(T value) {
		this.value = value;
		this.parent = this;
		this.height = 0;
	}
	
	public String toString() {
		return "[value: " + this.value + "; height: " + this.height + "]"; 
	}
}