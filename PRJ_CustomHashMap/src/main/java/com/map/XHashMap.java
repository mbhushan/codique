package main.java.com.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class XHashMap<K, V> {

	private Map<K, Value<V>> hmap;
	private int index;
	private Map<Integer, K> keyMap;

	public XHashMap() {
		this.hmap = new HashMap<K, Value<V>>();
		this.keyMap = new HashMap<Integer, K>();
		this.index = 0;
	}

	public void insert(K key, V value) {
		Value<V> val = new Value<V>(value, index);
		this.hmap.put(key, val);
		this.keyMap.put(index, key);
		++index;
	}

	public V get(K key) {
		V value = null;
		if (this.hmap.containsKey(key)) {
			value = this.hmap.get(key).data;
		}
		return value;
	}

	public K getRandomKey() {
		if (this.index == 0) {
			return null;
		}
		Random rand = new Random();
		int pos = rand.nextInt(this.index);
		return this.keyMap.get(pos);
	}

	public void delete(K key) {
		if (!this.hmap.containsKey(key)) {
			return;
		}
		Value<V> value = this.hmap.get(key);
		// get the index;
		int i = value.index;

		// give the index i to last key,value pair and update keyMap and hmap.
		K lastKey = this.keyMap.get(this.index - 1);
		Value<V> lastValue = this.hmap.get(lastKey);
		lastValue.index = i;
		this.hmap.put(lastKey, lastValue);
		this.keyMap.put(i, lastKey);
		this.keyMap.remove(this.index - 1);

		--this.index;
		this.hmap.remove(key);

	}

	public String toString() {
		return this.hmap.toString();
	}

	private class Value<V> {
		V data;
		int index;

		public Value(V data, int index) {
			this.data = data;
			this.index = index;
		}

		public String toString() {
			return "[" + data + ", " + index + "]";
		}
	}
}
