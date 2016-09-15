package main.com.db.impl;

import java.util.Stack;

public class Command {

	private static final String SET = "SET";
	private static final String GET = "GET";
	private static final String UNSET = "UNSET";
	private static final String NUMEQUALTO = "NUMEQUALTO";
	private static final String BEGIN = "BEGIN";
	private static final String ROLLBACK = "ROLLBACK";
	private static final String COMMIT = "COMMIT";

	private Database<String> db;
	private Stack<Database<String>> stack;

	public Command() {
		this.db = new Database<String>();
		this.stack = new Stack<Database<String>> ();
		this.stack.push(this.db);
	}

	public void execute(String cmd) {

		String[] tokens = parse(cmd);
		if (tokens.length < 1) {
			return;
		}

		String name = "";
		String value = "";

		switch (tokens[0].toUpperCase()) {
		case SET:
			name = tokens[1].trim();
			value = tokens[2].trim();
			stack.peek().set(name, value);
			break;

		case GET:
			name = tokens[1];
			value = stack.peek().get(name);
			System.out.println(value);
			break;

		case UNSET:
			name = tokens[1];
			stack.peek().unset(name);
			break;

		case NUMEQUALTO:
			value = tokens[1];
			int count = stack.peek().numequalto(value);
			System.out.println(count);
			break;
			
		//transaction commands
		case BEGIN:
			begin();
			break;
			
		case ROLLBACK:
			rollback();
			break;
			
		case COMMIT:
			commit();
			break;
			
		default:
				break;
		}
	}

	private String[] parse(String input) {
		input = input.replaceAll(" +", " ");
		return input.split(" ");
	}
	
	public void begin() {
		Database<String> db = new Database<String>(this.stack.peek());
		this.stack.push(db);
	}
	
	public void rollback() {
		if (this.stack.size() == 1) {
			System.out.println("NO TRANSACTION");
		} else {
			this.stack.pop();
		}
	}
	
	public void commit() {
		if (this.stack.size() == 1) {
			System.out.println("NO TRANSACTION");
		} else {
			this.db = new Database<String>(this.stack.peek());
			this.stack = new Stack<Database<String>>();
			this.stack.push(this.db);
		}
	}

}
