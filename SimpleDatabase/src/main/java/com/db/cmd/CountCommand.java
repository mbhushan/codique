package main.java.com.db.cmd;

import main.java.com.db.Database;

public class CountCommand<T> extends Command<T> {

	private T value;
	private Integer result;

	public CountCommand(Database<T> db, T value) {
		database = db;
		this.value = value;
		this.result = null;
	}

	public Integer getResult() {
		return this.result;
	}

	@Override
	public void execute() {
		this.result = database.count(value);
	}
}
