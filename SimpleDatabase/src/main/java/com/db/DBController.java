package main.java.com.db;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import main.java.com.db.cmd.BeginMarker;
import main.java.com.db.cmd.Command;
import main.java.com.db.cmd.CountCommand;
import main.java.com.db.cmd.DeleteCommand;
import main.java.com.db.cmd.GetCommand;
import main.java.com.db.cmd.SetCommand;

public class DBController {

    private static final String SET = "SET";
    private static final String GET = "GET";
    private static final String DELETE = "DELETE";
    private static final String COUNT = "COUNT";
    private static final String BEGIN = "BEGIN";
    private static final String ROLLBACK = "ROLLBACK";
    private static final String COMMIT = "COMMIT";

    private Database<String> db;

    private static boolean txnInProgress = false;

    private Deque<Command<String>> cmdBuffer;
    private Stack<Database<String>> txnStack;
    private Command<String> marker;

    //TODO: should be singleton.
    public DBController() {
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

        case DELETE:
            deleteCmd(tokens);
            break;

        case COUNT:
            countCmd(tokens);
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

    
    public void deleteCmd(String [] tokens) {
        if (tokens.length < 2) {
            return;
        }
        String name = tokens[1];
        Command<String> command = null;
        if (!txnInProgress) {
            command = new DeleteCommand<String>(db, name);
        } else {
        	//TODO: restructure this block
            if (!txnStack.peek().checkStoreMap(name) && this.db.checkStoreMap(name)) {
            //	System.out.println("txn stack: " + txnStack.toString());
                Command<String> cd = new GetCommand<String>(this.db, name);
                cd.execute();
                String value = ((GetCommand<String>)cd).getResult();
                txnStack.peek().set(name, value);
            }
            command = new DeleteCommand<String>(txnStack.peek(), name);
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
        if (result == null && txnInProgress && !txnStack.peek().checkStoreMap(name)) {
        	//System.out.println("txn stack top: " + txnStack.peek().toString());
            command = new GetCommand<String>(db, name);
            command.execute();
            result = ((GetCommand<String>)command).getResult();
        }
        System.out.println(result);
    }

    
    public void countCmd(String [] tokens) {
        if (tokens.length < 2) {
            return;
        }
        String value = tokens[1];
        Command<String> command = null;
        if (!txnInProgress) {
            command = new CountCommand<String>(db, value);
        } else {
            command = new CountCommand<String>(txnStack.peek(), value);
        }
        command.execute();
        Integer result = ((CountCommand<String>)command).getResult();
        if (result == 0 && txnInProgress && !txnStack.peek().checkCountMap(value)) {
        	//System.out.println("txn stack top: " + txnStack.peek().toString());
            command = new CountCommand<String>(db, value);
            command.execute();
            result = ((CountCommand<String>)command).getResult();
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
    	//replace multiple spaces with single space.
        input = input.replaceAll(" +", " ");
        return input.split(" ");
    }
}
