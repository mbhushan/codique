package main.com.db.impl;

import java.util.Stack;

public class Transaction {

	private Stack<Database<String>> stack;
	private static boolean inProgress = false;
	private Database<String> db;

	
	public Transaction(Database<String> db) {
		this.stack = new Stack<Database<String>>();
		this.db = new Database<String>(db);
		
	}
	
	public void begin() {
		if (stack.isEmpty()) {
			this.stack.push(this.db);
		} else {
			Database<String> db = new Database<String>(this.stack.peek());
			this.stack.push(db);
		}
	}
	
	public void rollback() {
		if (this.stack.isEmpty()) {
			System.out.println("NO TRANSACTION");
		} else {
			this.stack.pop();
		}
	}
	
	public void commit() {
		if (this.stack.isEmpty()) {
			System.out.println("NO TRANSACTION");
		} else {
			
		}
	}
}
