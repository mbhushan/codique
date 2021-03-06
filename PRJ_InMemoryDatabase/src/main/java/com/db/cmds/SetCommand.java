package main.java.com.db.cmds;

import main.java.com.db.Database;

public class SetCommand<T> extends Command<T> {

	private T name;
	private T value;

	public SetCommand(Database<T> db, T name, T value) {
		this.name = name;
		this.value = value;
		database = db;
		isWriteCmd = true;
	}

	@Override
	public void execute() {
		database.set(name, value);
	}

}
