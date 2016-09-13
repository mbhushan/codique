package main.com.db.impl;

import java.util.HashMap;
import java.util.Map;

public class Database<T> {

	private Map<T, T> tableMap;
	private Map<T, Integer> valueCountMap;
	
	public Database() {
		this.tableMap = new HashMap<T, T>();
		this.valueCountMap = new HashMap<T, Integer>();
	}
	
	
	public void set(T name, T value) {
		T oldValue = null;
		if (this.tableMap.containsKey(name)) {
			oldValue = this.tableMap.get(name);
		}
		
		if ((oldValue != null) && oldValue.equals(value)) {
			return; //no update required, same key-value pair.
		}
		
		this.tableMap.put(name, value);
		
		int count = 0;
		if (this.valueCountMap.containsKey(value)) {
			count = this.valueCountMap.get(value);
		}
		++count;
		this.valueCountMap.put(value, count);
		
		if ((oldValue != null) && (!oldValue.equals(value))) {
			decrementValueCount(oldValue);
		}
	}
	
	public T get(T name) {
		T value = null;
		if (this.tableMap.containsKey(name)) {
			value = this.tableMap.get(name);
		}
		return value;
	}
	
	public void unset(T name) {
		if (this.tableMap.containsKey(name)) {
			T value = this.tableMap.get(name);
			this.tableMap.put(name, null);
			
			//decrement value count
			if (value != null) {
				decrementValueCount(value);
			}
		}
	}
	
	private void decrementValueCount(T value) {
		int count = 0;
		if (this.valueCountMap.containsKey(value)) {
			count = this.valueCountMap.get(value);
		}
		--count;
		if (count == 0) {
			this.valueCountMap.remove(value);
		} else {
			this.valueCountMap.put(value, count);
		}
	}
	
	public int numequalto(T value) {
		int count = 0;
		if (this.valueCountMap.containsKey(value)) {
			count = this.valueCountMap.get(value);
		}
		return count;
	}
}
