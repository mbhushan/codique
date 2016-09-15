package main.java.com.db.cmds;

import main.java.com.db.Database;

public class GetCommand<T> extends Command<T> {

	private T name;
	private T result;

	public GetCommand(Database<T> db, T name) {
		database = db;
		isWriteCmd = false;
		this.name = name;
		this.result = null;
	}
	
	public T getResult() {
		return this.result;
	}

	@Override
	public void execute() {
		result = database.get(name);
		//System.out.println(result);
	}

}
