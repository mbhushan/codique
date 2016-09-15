package main.java.com.db.cmds;

import main.java.com.db.Database;

public abstract class Command<T> {

	protected boolean isWriteCmd;
	protected Database<T> database;
	
	public void execute(){}
	
	public void setDB(Database<T> db) {
		this.database = db;
	}
	
//	public T getResult(){
//		return null;
//	}

}
