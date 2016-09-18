package main.java.com.db;

import java.util.HashMap;
import java.util.Map;

public class Database<T> {

	private Map<T, T> storeMap;
	private Map<T, Integer> countMap;
	
	public Database() {
        this.storeMap = new HashMap<T, T>();
        this.countMap = new HashMap<T, Integer>();
    }

    public Database(Database<T> db) {
        this.storeMap = new HashMap<T, T>(db.storeMap);
        this.countMap = new HashMap<T, Integer>(db.countMap);
    }
    
    public boolean checkStoreMap(T name) {
        return this.storeMap.containsKey(name);
    }

    public boolean checkCountMap(T value) {
        return this.countMap.containsKey(value);
    }
    
    public void set(T name, T value) {
        T oldValue = null;
        if (this.storeMap.containsKey(name)) {
            oldValue = this.storeMap.get(name);
        }

        if ((oldValue != null) && oldValue.equals(value)) {
            return; // no update required, same key-value pair.
        }

        this.storeMap.put(name, value);

        int count = 0;
        if (this.countMap.containsKey(value)) {
            count = this.countMap.get(value);
        }
        ++count;
        this.countMap.put(value, count);

        if ((oldValue != null) && (!oldValue.equals(value))) {
            decrementValueCount(oldValue);
        }
    }
    
    public T get(T name) {
        T value = null;
        if (this.storeMap.containsKey(name)) {
            value = this.storeMap.get(name);
        }
        return value;
    }
    
    
    public void delete(T name) {
        if (this.storeMap.containsKey(name)) {
            T value = this.storeMap.get(name);
            this.storeMap.put(name, null);

            // decrement value count
            if (value != null) {
                decrementValueCount(value);
            }
        }
    }
    
    private void decrementValueCount(T value) {
        int count = 0;
        if (this.countMap.containsKey(value)) {
            count = this.countMap.get(value);
        }
        if (count > 0) {
            --count;
        }
        this.countMap.put(value, count);
    }
    
    public int count(T value) {
        int count = 0;
        if (this.countMap.containsKey(value)) {
            count = this.countMap.get(value);
        }
        return count;
    }

}
