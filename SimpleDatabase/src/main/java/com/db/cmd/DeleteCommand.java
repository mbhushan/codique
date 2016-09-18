package main.java.com.db.cmd;

import main.java.com.db.Database;

public class DeleteCommand<T> extends Command<T> {

	private T name;

	public DeleteCommand(Database<T> db, T name) {
		database = db;
		this.name = name;
	}

	@Override
	public void execute() {
		database.delete(name);
	}
}