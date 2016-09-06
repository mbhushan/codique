import java.util.LinkedList;
import java.util.Queue;


public class HashTable<K, V>{

	private static final int INIT_CAPACITY = 4;
	private int N = 0; //no. of key-value pairs in hashtable.
	private int M = 0; //size of hashtable.
	private SinglyLinkedList<K, V> [] table;
	private static final int LIST_SIZE = 10;
	
	HashTable() {
		this(INIT_CAPACITY);
	}
	
	HashTable(int m) {
		this.M = m;
		table = new SinglyLinkedList[this.M];
		for (int i=0; i<this.M; i++) {
			table[i] = new SinglyLinkedList<K, V>();
		}
	}
	
	private void resize(int chains) {
		HashTable<K, V> temp = new HashTable<K, V>(chains);
		for (int i=0; i<M; i++) {
			for (K key: table[i].keys()) {
				temp.put(key, table[i].get(key));
			}
		}
		this.M = temp.M;
		this.N = temp.N;
		this.table = temp.table;
	}
	
	public boolean containsKey(K key) {
		if (key == null) {
			throw new NullPointerException("argument to containsKey is NULL");
		}
		return get(key) != null;
	}
	
	public V get(K key) {
		if (key == null) {
			throw new NullPointerException("argument to key is NULL");
		}
		int h = hash(key);
		return table[h].get(key);
	}
	
	public void put(K key, V value) {
		if (key == null) {
			throw new NullPointerException("argument to put is NULL");
		}
		
		if (value == null) {
			delete(key);
			return;
		}
		
		// double table size if average length of list >= 10
		if (N >= (M*LIST_SIZE)) {
			resize(2*M);
		}
		
		int hkey = hash(key);
		if (!table[hkey].containsKey(key)) {
			++N;
		}
		
		table[hkey].put(key, value);
	}
	
	public void delete(K key) {
		if (key == null) {
			throw new NullPointerException("arguments to delete method is null");
		}
		
		int hkey = hash(key);
		if (table[hkey].containsKey(key)) {
			--N;
		}
		
		table[hkey].delete(key);
		
		if (M > INIT_CAPACITY && (N <= 2*M)) {
			resize(M/2);
		}
	}
	
	public Iterable<K> keys() {
		Queue<K> queue = new LinkedList<K>();
		for (int i=0; i<this.M; i++) {
			for (K key: table[i].keys()) {
				queue.add(key);
			}
		}
		
		return queue;
	}
	
	private int hash(K key) {
		int hash = (key.hashCode() & 0x7fffffff) % M;
		return hash;
	}
	
	public int size() {
		return this.N;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
}
