package main.java.com.db;

import java.util.HashMap;
import java.util.Map;

public class Transaction<T> {

	private Map<T, T> txnMap;
	private Map<T, Integer> txnCountMap;
	
	public Transaction() {
		this.txnMap =  new HashMap<T, T>();
		this.txnCountMap = new HashMap<T, Integer>();
	}
	
	public Transaction(Transaction<T> txn) {
		this.txnMap = new HashMap<T, T>(txn.txnMap);
        this.txnCountMap = new HashMap<T, Integer>(txn.txnCountMap);
	}
}
