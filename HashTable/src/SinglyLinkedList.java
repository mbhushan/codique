import java.util.LinkedList;
import java.util.Queue;


public class SinglyLinkedList<K, V> {
	
	private int N = 0; //number of K, V pairs
	private Node first = null; //linked list of key, value pairs.
	
	public SinglyLinkedList() {}
	
	public int size() {
		return this.N;
	}
	
	public boolean isEmpty() {
		return this.N == 0;
	}
	
	public boolean containsKey(K key) {
		if (key == null) {
			throw new NullPointerException("arguments to containsKey method is null");
		}
		
		return get(key) != null;
	}
	
	public V get(K key) {
		if (key == null) {
			throw new NullPointerException("arguments to get method is null");
		}
		
		for (Node x = first; x != null; x=x.next) {
			if (key.equals(x.key)) {
				return (V) x.value;
			}
		}
		return null;
	}
	
	public void put(K key, V value) {
		if (key == null) {
			throw new NullPointerException("argument to put is null");
		}
		if (value == null) {
			delete(key);
			return;
		}
		
		for (Node x=first; x != null; x=x.next) {
			if (key.equals(x.key)) {
				x.value = value;
			}
		}
		first = new Node(key, value, first);
		++N;
	}
	
	public void delete(K key) {
		if (key == null) {
			throw new NullPointerException(" argument to delete method is null");
		}
		first = delete(first, key);
	}
	
	private Node delete(Node node, K key) {
		if (node == null) {
			return node;
		}
		if (key.equals(node.key)) {
			--N;
			return node.next;
		}
		node.next = delete(node.next, key);
		return node;
	}
	
	public Iterable<K> keys() {
		Queue<K> Q = new LinkedList<K>();	
		for (Node<K, V> x=first; x!=null; x=x.next) {
			Q.add(x.key);
		}
		return Q;
	}
	
}

class Node<K, V> {
	K key;
	V value;
	
	Node<K, V> next;
	
	Node(K key, V value, Node<K, V> node) {
		this.key = key;
		this.value = value;
		this.next = node;
	}
	
	public String toString() {
		return "[" + this.key + ", " + this.value + "]";
	}
}
