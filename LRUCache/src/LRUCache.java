import java.util.HashMap;
import java.util.Map;

/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
it should invalidate the least recently used item before inserting a new item.
APPROACH:
The LRU cache is a hash table of keys and double linked nodes. 
The hash table makes the time of get() to be O(1). The list of double linked 
nodes make the nodes adding/removal operations O(1).
*/

public class LRUCache<K, V> {
	
	private Map<K, Node<K, V>> cache ;
	private Node<K, V> head;
	private Node<K, V> tail;
	private int capacity;
	
	public LRUCache(int capacity) {
		cache = new HashMap<K, Node<K, V>>();
		this.capacity = capacity;
		this.head = null;
		this.tail = null;
	}

	public void set(K key, V value) {
		if (cache.containsKey(key)) {
			cache.get(key).setValue(value);
			moveNode(cache.get(key));
		} else {
			if (cache.size() >= this.capacity) {
				cache.remove(this.head.key);
				removeLRUNode(); //ie head
			}
			
			Node<K, V> node = new Node<K, V>(key, value);
			cache.put(key, node);
			insert(node);
			
		}
	}
	
	public V get(K key) {
		if (!cache.containsKey(key)) {
			return null;
		}
		Node<K, V> node = cache.get(key);
		moveNode(node); //move to tail
		return node.value;
	}
	
	private void insert(Node<K, V> node) {
		if (this.head == null) {
			this.head = node;
			this.tail = node;
		} else {
			this.tail.next = node;
			node.prev = this.tail;
			this.tail = this.tail.next;
		}
	}
	
	//remove the head of the list (LRU)
	private void removeLRUNode() {
		if (this.head.equals(this.tail)) {
			this.head = null;
			this.tail = null;
		} else {
			Node<K, V> node = this.head;
			this.head = this.head.next;
			if (this.head != null) {
				this.head.prev = null;
			}
			node.next = null;
		}
	}
	
	//move the node to the tail of the list (most recently used)
	private void moveNode(Node<K, V> node) {
		if (node.equals(this.tail)) {
			return;
		} else if (node.equals(this.head)) {
			this.head = this.head.next;
			this.head.prev = null;
			
			node.next = this.tail.next;
			this.tail.next = node;
			node.prev = this.tail;
			this.tail = this.tail.next;
		} else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			
			this.tail.next = node;
			node.prev = this.tail;
			this.tail = this.tail.next;
			this.tail.next = null;
		}
	}
	
	public int size() {
		if (this.cache == null) {
			return 0;
		}
		return this.cache.size();
	}
}

class Node<K, V> {
	K key;
	V value;
	Node<K, V> prev;
	Node<K, V> next;

	public Node(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public String toString() {
		return "[" + this.key + ", " + this.value + "]";
	}
}
