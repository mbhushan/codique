import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;


public class ConsistentHash<T> {

	private HashFunction hashFunction;
	private int numReplicas;
	private SortedMap<Integer, T> circle ;
	
	public ConsistentHash(HashFunction hashFunction, int numReplicas, Collection<T> nodes) {
		this.hashFunction = hashFunction;
		this.numReplicas = numReplicas;
		circle = new TreeMap<Integer, T>();
		
		for (T node: nodes) {
			add(node);
		}
	}
	
	public void add(T node) {
		for (int i=0; i<this.numReplicas; i++) {
			circle.put(hashFunction.hash(node.toString() + i), node);
		}
	}
	
	public void remove(T node) {
		for (int i=0; i<this.numReplicas; i++) {
			circle.remove(hashFunction.hash(node.toString() + 1));
		}
	}
	
	public T get(Object key) {
		
		if (circle.isEmpty()) {
			return null;
		}
		
		int hash = hashFunction.hash(key.toString());
		if (!circle.containsKey(hash)) {
			SortedMap<Integer, T> tailMap = circle.tailMap(hash);
			
			hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
		}
		
		return circle.get(hash);
	}
}
