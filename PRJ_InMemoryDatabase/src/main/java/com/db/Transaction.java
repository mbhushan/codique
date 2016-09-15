package main.java.com.db;

import java.util.HashMap;
import java.util.Map;


public class Transaction<T> {
	
	private Map<T, T> tableMap;
	private Map<T, Integer> valueCountMap;
	
	public Transaction() {
		this.tableMap = new HashMap<T, T>();
		this.valueCountMap = new HashMap<T, Integer>();
	}

	public Transaction(Transaction<T> txn) {
		this.tableMap = new HashMap<T, T>(txn.tableMap);
		this.valueCountMap = new HashMap<T, Integer>(txn.valueCountMap);
	}

}
