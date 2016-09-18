package main.java.com.db.cmd;

import main.java.com.db.Database;

public abstract class Command<T> {

    protected Database<T> database;

    public void execute(){}

    public void setDB(Database<T> db) {
        this.database = db;
    }
}
