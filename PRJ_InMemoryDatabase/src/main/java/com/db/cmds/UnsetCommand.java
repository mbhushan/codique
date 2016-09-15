package main.java.com.db.cmds;

import main.java.com.db.Database;

public class UnsetCommand<T> extends Command<T> {

	private T name;

	public UnsetCommand(Database<T> db, T name) {
		database = db;
		this.name = name;
		isWriteCmd = true;
	}

	@Override
	public void execute() {
		database.unset(name);
	}
}
