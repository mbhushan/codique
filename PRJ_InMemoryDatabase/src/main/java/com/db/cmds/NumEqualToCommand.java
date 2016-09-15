package main.java.com.db.cmds;

import main.java.com.db.Database;

public class NumEqualToCommand<T> extends Command<T> {

	private T value;
	private Integer result;

	public NumEqualToCommand(Database<T> db, T value) {
		database = db;
		isWriteCmd = false;
		this.value = value;
		this.result = null;
	}

	public Integer getResult() {
		return this.result;
	}
	
	@Override
	public void execute() {
		this.result = database.numequalto(value);
		//System.out.println(result);
		
	}
}
