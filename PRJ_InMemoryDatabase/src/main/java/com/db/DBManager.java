package main.java.com.db;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import main.java.com.db.cmds.BeginMarker;
import main.java.com.db.cmds.Command;
import main.java.com.db.cmds.GetCommand;
import main.java.com.db.cmds.NumEqualToCommand;
import main.java.com.db.cmds.SetCommand;
import main.java.com.db.cmds.UnsetCommand;

public class DBManager {

	private static final String SET = "SET";
	private static final String GET = "GET";
	private static final String UNSET = "UNSET";
	private static final String NUMEQUALTO = "NUMEQUALTO";
	private static final String BEGIN = "BEGIN";
	private static final String ROLLBACK = "ROLLBACK";
	private static final String COMMIT = "COMMIT";
	
	private Database<String> db;
	
	private static boolean txnInProgress = false;
	
	private Deque<Command<String>> cmdBuffer;
	private Stack<Database<String>> txnStack;
	private Command<String> marker;
	
	public DBManager() {
		this.db = new Database<String>();
		this.txnStack = new Stack<Database<String>>();
		this.cmdBuffer = new LinkedList<Command<String>>();
		this.marker = new BeginMarker<String>();
	}
	
	public void execute(String cmd) {

		String[] tokens = parse(cmd);
		if (tokens.length < 1) {
			return;
		}

		switch (tokens[0].toUpperCase()) {
		case SET:
			setCmd(tokens);
			break;

		case GET:
			getCmd(tokens);
			break;

		case UNSET:
			UnsetCmd(tokens);
			break;

		case NUMEQUALTO:
			numequaltoCmd(tokens);
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
	
	public void setCmd(String [] tokens) {
		if (tokens.length < 3) {
			return;
		}
		String name = tokens[1];
		String value = tokens[2];
		Command<String> command = null;
		if (!txnInProgress) {
			command = new SetCommand<String>(db, name, value);
		} else {
			command = new SetCommand<String>(txnStack.peek(), name, value);
		}
		command.execute();
		cmdBuffer.addFirst(command);
	}
	
	public void UnsetCmd(String [] tokens) {
		if (tokens.length < 2) {
			return;
		}
		String name = tokens[1];
		Command<String> command = null;
		if (!txnInProgress) {
			command = new UnsetCommand<String>(db, name);
		} else {
			if (!txnStack.peek().checkTableMap(name) && this.db.checkTableMap(name)) {
				Command<String> cd = new GetCommand<String>(this.db, name);
				cd.execute();
				String value = ((GetCommand<String>)cd).getResult();
				txnStack.peek().set(name, value);
			}
			command = new UnsetCommand<String>(txnStack.peek(), name);
		}
		command.execute();
		cmdBuffer.addFirst(command);
	}
	
	public void getCmd(String [] tokens) {
		if (tokens.length < 2) {
			return;
		}
		String name = tokens[1];
		Command<String> command = null;
		if (!txnInProgress) {
			command = new GetCommand<String>(db, name);
		} else {
			command = new GetCommand<String>(txnStack.peek(), name);
		}
		command.execute();
		String result = ((GetCommand<String>)command).getResult();
		if (result == null && txnInProgress && !txnStack.peek().checkTableMap(name)) {
			command = new GetCommand<String>(db, name);
			command.execute();
			result = ((GetCommand<String>)command).getResult();
		}
		System.out.println(result);
	}
	
	public void numequaltoCmd(String [] tokens) {
		if (tokens.length < 2) {
			return;
		}
		String value = tokens[1];
		Command<String> command = null;
		if (!txnInProgress) {
			command = new NumEqualToCommand<String>(db, value);
		} else {
			command = new NumEqualToCommand<String>(txnStack.peek(), value);
		}
		command.execute();
		Integer result = ((NumEqualToCommand<String>)command).getResult();
		if (result == 0 && txnInProgress && !txnStack.peek().checkvalueCountMap(value)) {
			//System.out.println("numeq result: " + result +  ", check val:" + txnStack.peek().checkvalueCountMap(value));
			command = new NumEqualToCommand<String>(db, value);
			command.execute();
			result = ((NumEqualToCommand<String>)command).getResult();
		}
		System.out.println(result);
	}
	
	public void begin() {
		Database<String> db = null;
		if(!txnInProgress) {
			txnInProgress = true;
			db = new Database<String>();
		} else {
			db = new Database<String>(txnStack.peek());
		}
		txnStack.push(db);
		
		cmdBuffer.addFirst(marker);
	}
	
	public void rollback() {
		if (!txnInProgress) {
			System.out.println("NO TRANSACTION");
		} else {
			txnStack.pop();
			if (txnStack.isEmpty()) {
				txnInProgress = false;
			}

			//handle queuebuffer as well
			while(cmdBuffer.peek().equals(marker)) {
				cmdBuffer.removeFirst();
			}
			cmdBuffer.removeFirst();
		}
	}
	
	public void commit() {
		if (!txnInProgress) {
			System.out.println("NO TRANSACTION");
			return;
		}
		
		while(!cmdBuffer.isEmpty()) {
			Command<String> cmd = cmdBuffer.removeLast();
			cmd.setDB(this.db);
			cmd.execute();
		}
		//empty txn stack and unset txn progress flag.
		txnStack.clear();
		txnInProgress = false;
	}
	
	private String[] parse(String input) {
		input = input.replaceAll(" +", " ");
		return input.split(" ");
	}
}
